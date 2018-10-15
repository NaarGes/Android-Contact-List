package com.example.user.contactlist.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

import com.example.user.contactlist.data.model.Contact

@Database(entities = [Contact::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun contactDao(): ContactDao

    companion object {

        private var instance: AppDatabase? = null

        fun getAppDatabase(context: Context): AppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder<AppDatabase>(context.applicationContext,
                        AppDatabase::class.java!!,
                        "app-db")
                        .allowMainThreadQueries()
                        .build()
            }
            return instance as AppDatabase
        }
    }
}
