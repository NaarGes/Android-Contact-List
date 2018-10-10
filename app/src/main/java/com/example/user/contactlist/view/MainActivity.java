package com.example.user.contactlist.view;

import android.Manifest;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.user.contactlist.R;
import com.example.user.contactlist.data.model.Contact;
import com.example.user.contactlist.viewmodel.ContactViewModel;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private ContactViewModel contactViewModel;
    public final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!hasPhoneContactsPermission(Manifest.permission.READ_CONTACTS))
                requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_CODE);
            else
                init();
            } else {
            init();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    init();
                else
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
        }
    }

    private boolean hasPhoneContactsPermission(String permission) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int hasPermission = ContextCompat.checkSelfPermission(getApplicationContext(), permission);
            return hasPermission == PackageManager.PERMISSION_GRANTED;
        } else
            return true;
    }

    private void init() {

        // fixme doesn't load contacts when orientation changes or get permission
        final RecyclerView recyclerView = findViewById(R.id.contact_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        final ContactAdapter adapter = new ContactAdapter(this);

        contactViewModel = ViewModelProviders.of(this).get(ContactViewModel.class);
        contactViewModel.setup(this);
        contactViewModel.SetLiveDataString("this is live data");

        contactViewModel.getContacts().observe(MainActivity.this, new Observer<List<Contact>>() {
            @Override
            public void onChanged(@Nullable List<Contact> contacts) {
                recyclerView.setAdapter(adapter);
                adapter.setContacts(contacts);
            }
        });

        contactViewModel.getLiveDataString().observe(MainActivity.this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
