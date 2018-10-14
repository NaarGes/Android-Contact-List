package com.example.user.contactlist.view;


import android.Manifest;
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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.user.contactlist.R;
import com.example.user.contactlist.viewmodel.ContactViewModel;


public class LinearContactFragment extends Fragment {

    private static TransferInterface listener;
    public final int REQUEST_CODE = 1;
    private View view;

    public LinearContactFragment() {

    }

    public static LinearContactFragment newInstance(TransferInterface transferInterface) {

        Bundle args = new Bundle();
        listener = transferInterface;
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
        grid.setOnClickListener(v -> listener.goToGrid());
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
                    toast(getString(R.string.permission_denied));
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

        final ContactViewModel contactViewModel = ViewModelProviders.of(getActivity()).get(ContactViewModel.class);
        contactViewModel.setup();
        contactViewModel.SetLiveDataString(getString(R.string.live_data_message));

        contactViewModel.getContacts().observe(this, contacts -> {
            recyclerView.setAdapter(adapter);
            adapter.setContacts(contacts);
        });

        contactViewModel.getLiveDataString().observe(this, this::toast);
    }

    private void toast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
