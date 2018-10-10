package com.example.user.contactlist.view;

import android.Manifest;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.user.contactlist.R;
import com.example.user.contactlist.data.local.AppDatabase;
import com.example.user.contactlist.databinding.ActivityMainBinding;
import com.example.user.contactlist.viewmodel.ContactViewModel;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ContactViewModel contactViewModel;
    public final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        contactViewModel = new ContactViewModel(getApplicationContext());
        binding.setViewModel(contactViewModel);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!hasPhoneContactsPermission(Manifest.permission.READ_CONTACTS))
                requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_CODE);
            else
                initRecyclerView();
            } else {
            initRecyclerView();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    initRecyclerView();
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

    // todo use binding
    private void initRecyclerView() {
        ContactAdapter adapter = new ContactAdapter(this);
        adapter.setContacts(contactViewModel.getContacts());
        binding.contactRecyclerView.setAdapter(adapter);


        AppDatabase database = AppDatabase.getAppDatabase(this);
        Log.e("contacts in database", "onCreate: "+database.contactDao().getContact() );
    }
}
