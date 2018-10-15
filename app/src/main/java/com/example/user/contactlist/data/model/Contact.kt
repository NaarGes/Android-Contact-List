package com.example.user.contactlist.data.model


import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


@Entity(tableName = "contact")
class Contact(val name: String, @field:ColumnInfo(name = "phone_number")
val phoneNumber: String, @field:ColumnInfo(name = "photo_uri")
              val photoUri: String) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

