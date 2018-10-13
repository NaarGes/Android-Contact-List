package com.example.user.contactlist.data;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import com.example.user.contactlist.data.local.AppDatabase;
import com.example.user.contactlist.data.model.Contact;

import java.util.List;


public class ContactRepository {

    private Context context;
    private AppDatabase database;

    public ContactRepository(Context context, AppDatabase database) {
        this.context = context;
        this.database = database;
    }

    public void saveContactsInDataBase() {

        Cursor cursor = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null, null, null,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");

        if ((cursor != null ? cursor.getCount() : 0) > 0) {
            while (cursor.moveToNext()) {

                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                String phoneNo = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                String photoUri = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI));

                Contact contact = new Contact(name, phoneNo, photoUri);
                if (database.contactDao().contains(phoneNo) == null)
                    database.contactDao().insert(contact);
            }
        }
        if (cursor != null) {
            cursor.close();
        }
    }

    public LiveData<List<Contact>> getContacts() {
        return database.contactDao().getAllContacts();
    }
}
