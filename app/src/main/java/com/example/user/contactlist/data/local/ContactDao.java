package com.example.user.contactlist.data.local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.user.contactlist.data.model.Contact;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface ContactDao {

    @Query("SELECT * FROM contact")
    LiveData<List<Contact>> getAllContacts();

    @Query("SELECT * FROM contact")
    List<Contact> getContacts();


    @Insert(onConflict = REPLACE)
    void insert(Contact contact);

    @Delete
    void delete(Contact contact);

    @Update
    void update(Contact contact);
}
