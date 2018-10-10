package com.example.user.contactlist.model;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ContactRepository {

    private Context context;
    private List<Contact> contacts;

    public ContactRepository(Context context) {
        this.context = context;
        contacts = new ArrayList<>();
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

            }
        }
        if (cursor != null) {
            cursor.close();
        }
        return contacts;
    }
}
