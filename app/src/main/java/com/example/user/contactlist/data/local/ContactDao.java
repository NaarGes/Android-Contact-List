package com.example.user.contactlist.data.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.user.contactlist.data.model.Contact;

import java.util.List;

@Dao
public interface ContactDao {

    @Query("SELECT * FROM contact")
    List<Contact> getContact();

    @Insert
    void insert(ContactEntity contact);

    @Delete
    void delete(ContactEntity contact);

    @Update
    void update(ContactEntity contact);
}
