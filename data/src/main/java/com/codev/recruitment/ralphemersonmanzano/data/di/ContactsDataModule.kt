package com.codev.recruitment.ralphemersonmanzano.data.di

import android.content.Context
import androidx.room.Room
import com.codev.recruitment.ralphemersonmanzano.data.BuildConfig.*
import com.codev.recruitment.ralphemersonmanzano.data.datasource.local.ContactsDatabase
import com.codev.recruitment.ralphemersonmanzano.data.datasource.local.ContactsLocalDataSourceImpl
import com.codev.recruitment.ralphemersonmanzano.data.datasource.local.dao.ContactsDao
import com.codev.recruitment.ralphemersonmanzano.data.repository.ContactsRepositoryImpl
import com.codev.recruitment.ralphemersonmanzano.mylibrary.datasource.local.ContactsLocalDataSource
import com.codev.recruitment.ralphemersonmanzano.mylibrary.repository.ContactsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import net.sqlcipher.BuildConfig
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
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
        val factory = SupportFactory(SQLiteDatabase.getBytes(DB_PASSPHRASE.toCharArray()))
        return Room.databaseBuilder(
            context,
            ContactsDatabase::class.java,
            CONTACTS_DB
        ).openHelperFactory(factory)
            .createFromAsset(DB_ASSET_PATH)
            .build()
    }

    @Provides
    @Singleton
    fun provideContactsDao(contactsDatabase: ContactsDatabase) = contactsDatabase.contactsDao()

    @Provides
    @Singleton
    fun provideContactsLocalDataSource(
        contactsDao: ContactsDao,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): ContactsLocalDataSource {
        return ContactsLocalDataSourceImpl(contactsDao, dispatcher)
    }

    @Provides
    @Singleton
    fun provideContactsRepository(localDataSource: ContactsLocalDataSource): ContactsRepository {
        return ContactsRepositoryImpl(localDataSource)
    }
}