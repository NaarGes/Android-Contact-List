package com.example.user.contactlist.data

import android.arch.lifecycle.LiveData
import android.content.Context
import android.provider.ContactsContract

import com.example.user.contactlist.data.local.AppDatabase
import com.example.user.contactlist.data.model.Contact


class ContactRepository(private val context: Context, private val database: AppDatabase) {

    val contacts: LiveData<List<Contact>>
        get() = database.contactDao().allContacts

    fun saveContactsInDataBase() {

        val cursor = context.contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null, null, null,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC")

        if (cursor?.count ?: 0 > 0) {
            while (cursor!!.moveToNext()) {

                val name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                val phoneNo = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                var photoUri = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI))
                if(photoUri == null)
                    photoUri = ""

                val contact = Contact(name, phoneNo, photoUri)
                if (database.contactDao().contains(phoneNo) == null) // fixme why is always false
                    database.contactDao().insert(contact)
            }
        }
        cursor?.close()
    }
}
