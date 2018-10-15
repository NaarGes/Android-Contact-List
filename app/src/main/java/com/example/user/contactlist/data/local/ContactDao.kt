package com.example.user.contactlist.data.local

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update

import com.example.user.contactlist.data.model.Contact

import android.arch.persistence.room.OnConflictStrategy.REPLACE

@Dao
interface ContactDao {

    @get:Query("SELECT * FROM contact")
    val allContacts: LiveData<List<Contact>>

    @Query("SELECT * FROM contact WHERE phone_number = :phoneNo")
    fun contains(phoneNo: String): Contact

    @Insert(onConflict = REPLACE)
    fun insert(contact: Contact)

    @Delete
    fun delete(contact: Contact)

    @Update
    fun update(contact: Contact)
}
