package com.example.user.contactlist.viewmodel;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.user.contactlist.data.local.AppDatabase;
import com.example.user.contactlist.data.model.Contact;
import com.example.user.contactlist.data.ContactRepository;
import com.example.user.contactlist.data.model.SingleEventLiveData;

import java.util.List;

public class ContactViewModel extends AndroidViewModel {

    @SuppressLint("StaticFieldLeak")
    private AppDatabase database;
    private SingleEventLiveData<String> liveDataString;
    private LiveData<List<Contact>> contacts;
    private ContactRepository repository;

    public ContactViewModel(Application application) {
        super(application);
        database = AppDatabase.getAppDatabase(application);
        repository = new ContactRepository(application, database);
        liveDataString = new SingleEventLiveData<>();
        repository.saveContactsInDataBase();
    }

    public void setup() {

        contacts = repository.getContacts();
    }

    public LiveData<List<Contact>> getContacts() {

        return contacts;
    }

    public void SetLiveDataString(String newString) {

        this.liveDataString.setValue(newString);
    }

    public LiveData<String> getLiveDataString() {
        return liveDataString;
    }
}
