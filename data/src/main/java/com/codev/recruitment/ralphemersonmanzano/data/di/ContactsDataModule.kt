package com.codev.recruitment.ralphemersonmanzano.data.di

import android.content.Context
import androidx.room.Room
import com.codev.recruitment.ralphemersonmanzano.data.datasource.local.ContactsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ContactsDataModule {

    private companion object {
        private const val CONTACTS_DB = "contacts.db"
        private const val DB_ASSET_PATH = "db/contacts.db"
    }

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): ContactsDatabase {
        return Room.databaseBuilder(
            context,
            ContactsDatabase::class.java,
            CONTACTS_DB
        ).createFromAsset(DB_ASSET_PATH).build()
    }
}