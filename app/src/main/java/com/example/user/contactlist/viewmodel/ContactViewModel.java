package com.example.user.contactlist.viewmodel;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.example.user.contactlist.data.local.AppDatabase;
import com.example.user.contactlist.data.model.Contact;
import com.example.user.contactlist.data.ContactRepository;

import java.util.List;

public class ContactViewModel extends AndroidViewModel {

    @SuppressLint("StaticFieldLeak")
    private Context context;
    private AppDatabase database;

    public ContactViewModel(Application application) {
        super(application);
    }

    public void setup(Context context) {
        this.context = context;
        database = AppDatabase.getAppDatabase(context);
        ContactRepository repository = new ContactRepository(context, database);
        repository.saveContactsInDataBase();
    }

    public LiveData<List<Contact>> getContacts() {

        return database.contactDao().getAllContacts();
    }
}
