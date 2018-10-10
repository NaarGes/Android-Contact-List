package com.example.user.contactlist.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.util.Log;

import com.example.user.contactlist.model.Contact;
import com.example.user.contactlist.model.ContactRepository;

import java.util.List;

public class ContactViewModel extends BaseObservable {

    private ObservableArrayList<Contact> contacts;
    private ContactRepository repository;

    public ContactViewModel(Context context) {
        contacts = new ObservableArrayList<>();
        repository = new ContactRepository(context);
    }

    public List<Contact> getContacts() {
        contacts.addAll(repository.fetchContacts());
        return contacts;
    }
}
