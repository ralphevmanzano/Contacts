package com.codev.recruitment.ralphemersonmanzano.mylibrary.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Contact(
    val id: Int? = null,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val isFavorite: Boolean,
    val avatarColor: Int
): Parcelable
