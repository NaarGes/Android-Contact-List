package com.example.user.contactlist.data;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import com.example.user.contactlist.data.local.AppDatabase;
import com.example.user.contactlist.data.model.Contact;

import java.util.ArrayList;
import java.util.List;


public class ContactRepository {

    private Context context;
    private AppDatabase database;
    private List<Contact> contacts;

    public ContactRepository(Context context, AppDatabase database) {
        this.context = context;
        this.database = database;
        contacts = new ArrayList<>();
    }

    public void saveContactsInDataBase() {

        contacts = database.contactDao().getContacts();
        if (contacts.size() == 0) {
            Cursor cursor = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null, null, null,
                    ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");

            if ((cursor != null ? cursor.getCount() : 0) > 0) {
                while (cursor.moveToNext()) {

                    String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    String phoneNo = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    String photoUri = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI));

                    Contact contact = new Contact(name, phoneNo, photoUri);
                    database.contactDao().insert(contact);
                }
            }
            if (cursor != null) {
                cursor.close();
            }
        }
    }
}
