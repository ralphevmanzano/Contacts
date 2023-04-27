package com.codev.recruitment.ralphemersonmanzano.data.datasource.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.codev.recruitment.ralphemersonmanzano.mylibrary.model.Contact

@Entity(tableName = "contact", indices = [Index(value = ["id"], unique = true)])
data class ContactEntity(
    @PrimaryKey(true)
    val id: Long = 0,
    @ColumnInfo(name = "first_name")
    val firstName: String,
    @ColumnInfo(name = "last_name")
    val lastName: String,
    @ColumnInfo(name = "phone_number")
    val phoneNumber: String,
    @ColumnInfo(name = "is_favorite")
    val isFavorite: Boolean = false,
    @ColumnInfo(name = "avatar_color")
    val avatarColor: Int,
) {

    fun toContact(): Contact {
        return Contact(id, firstName, lastName, phoneNumber, isFavorite, avatarColor)
    }
}
