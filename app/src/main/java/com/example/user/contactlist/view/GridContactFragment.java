package com.example.user.contactlist.view;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.contactlist.R;
import com.example.user.contactlist.data.model.Contact;
import com.example.user.contactlist.viewmodel.ContactViewModel;

import java.util.List;


public class GridContactFragment extends Fragment {

    public GridContactFragment() {
    }

    public static GridContactFragment newInstance() {
        GridContactFragment fragment = new GridContactFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_grid_contact, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    private void init(View view) {

        final RecyclerView recyclerView = view.findViewById(R.id.grid_contact_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), 2));
        final ContactAdapter adapter = new ContactAdapter();

        ContactViewModel contactViewModel = ViewModelProviders.of(this).get(ContactViewModel.class);
        contactViewModel.setup(getContext());

        contactViewModel.getContacts().observe(GridContactFragment.this, new Observer<List<Contact>>() {
            @Override
            public void onChanged(@Nullable List<Contact> contacts) {
                recyclerView.setAdapter(adapter);
                adapter.setContacts(contacts);
            }
        });
    }
}
