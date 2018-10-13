package com.example.user.contactlist.view;


import android.Manifest;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.user.contactlist.R;
import com.example.user.contactlist.data.model.Contact;
import com.example.user.contactlist.viewmodel.ContactViewModel;

import java.util.List;

public class LinearContactFragment extends Fragment {

    public final int REQUEST_CODE = 1;
    private View view;
    private TransferInterface listener;

    public LinearContactFragment() {

    }

    public void setListener(TransferInterface listener) {
        this.listener = listener;
    }

    public static LinearContactFragment newInstance() {

        Bundle args = new Bundle();

        LinearContactFragment fragment = new LinearContactFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_linear_contact, container, false);
        return result;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!hasPhoneContactsPermission(Manifest.permission.READ_CONTACTS))
                requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_CODE);
            else
                init(view);
        } else {
            init(view);
        }

        Button grid = view.findViewById(R.id.grid_btn);
        grid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.goToGrid();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    init(view);
                else
                    Toast.makeText(getContext(), "permission denied", Toast.LENGTH_LONG).show();
        }
    }

    private boolean hasPhoneContactsPermission(String permission) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int hasPermission = ContextCompat.checkSelfPermission(getContext(), permission);
            return hasPermission == PackageManager.PERMISSION_GRANTED;
        } else
            return true;
    }

    private void init(View view) {

        final RecyclerView recyclerView = view.findViewById(R.id.contact_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        final ContactAdapter adapter = new ContactAdapter();

        ContactViewModel contactViewModel = ViewModelProviders.of(getActivity()).get(ContactViewModel.class);
        contactViewModel.setup();
        contactViewModel.SetLiveDataString("this is live data");

        contactViewModel.getContacts().observe(getActivity(), new Observer<List<Contact>>() {
            @Override
            public void onChanged(@Nullable List<Contact> contacts) {
                recyclerView.setAdapter(adapter);
                adapter.setContacts(contacts);
            }
        });

        // todo toast increase
        /*contactViewModel.getLiveDataString().observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
            }
        });*/
    }
}
