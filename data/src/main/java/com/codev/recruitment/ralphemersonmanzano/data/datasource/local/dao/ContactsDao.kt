package com.codev.recruitment.ralphemersonmanzano.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.codev.recruitment.ralphemersonmanzano.mylibrary.model.Contact
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactsDao {
    @Query("SELECT * FROM contact ORDER BY first_name")
    fun getContacts(): Flow<List<Contact>>

    @Query("SELECT * FROM contact WHERE is_favorite = 1")
    fun getFavorites(): Flow<List<Contact>>

    @Query("SELECT * FROM contact WHERE id = :id")
    suspend fun getContactById(id: Int): Contact

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addContact(contact: Contact)

    @Update
    suspend fun updateContact(contact: Contact)

    @Query("DELETE FROM contact WHERE id = :id")
    suspend fun deleteContact(id: Int)
}