package com.example.user.contactlist.data;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import com.example.user.contactlist.data.local.AppDatabase;
import com.example.user.contactlist.data.local.ContactEntity;
import com.example.user.contactlist.data.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactRepository {

    private Context context;
    private List<Contact> contacts;
    private AppDatabase database;

    public ContactRepository(Context context) {
        this.context = context;
        contacts = new ArrayList<>();
        database = AppDatabase.getAppDatabase(context);
    }

    public List<Contact> fetchContacts() {

        Cursor cursor = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null, null, null,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");

        if ((cursor != null ? cursor.getCount() : 0) > 0) {
            while (cursor.moveToNext()) {

                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                String phoneNo = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                String photoUri = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI));

                Contact contact = new Contact();
                contact.setName(name);
                contact.setPhoneNumber(phoneNo);
                contact.setPhotoUri(photoUri);

                contacts.add(contact);
                ContactEntity contactEntity = new ContactEntity();
                contactEntity.setName(contact.getName());
                contactEntity.setPhoneNo(contact.getPhoneNumber());
                contactEntity.setPhotoUri(contact.getPhotoUri());
                database.contactDao().insert(contactEntity);
            }
        }
        if (cursor != null) {
            cursor.close();
        }
        return contacts;
    }
}
