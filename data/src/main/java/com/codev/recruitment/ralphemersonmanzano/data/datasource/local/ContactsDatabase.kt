package com.codev.recruitment.ralphemersonmanzano.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.codev.recruitment.ralphemersonmanzano.data.datasource.local.dao.ContactsDao
import com.codev.recruitment.ralphemersonmanzano.data.datasource.local.entity.ContactEntity

@Database(entities = [ContactEntity::class], version = 1)
abstract class ContactsDatabase: RoomDatabase() {
    abstract fun contactsDao(): ContactsDao
}