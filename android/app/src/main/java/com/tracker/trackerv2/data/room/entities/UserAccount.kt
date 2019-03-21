package com.tracker.trackerv2.data.room.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/*
 *   Created by Android Developer : Birsasteanu Bogdan Andrei Alexandru on Date: 12/04/2018
 */

@Entity
data class UserAccount(
    @PrimaryKey(autoGenerate = true) @SerializedName(KEY_ID) var id: Long? = null,
    @ColumnInfo(name = KEY_EMAIL) @SerializedName(KEY_EMAIL) var email: String,
    @ColumnInfo(name = KEY_FIRST_NAME) @SerializedName(KEY_FIRST_NAME) var firstName: String,
    @ColumnInfo(name = KEY_LAST_NAME) @SerializedName(KEY_LAST_NAME) var lastName: String,
    @ColumnInfo(name = KEY_USERNAME) @SerializedName(KEY_USERNAME) var userName: String,
    @ColumnInfo(name = KEY_ICON_IMAGE) @SerializedName(KEY_ICON_IMAGE) var iconImage: String? = null,
    @ColumnInfo(name = KEY_PHONE_NO) @SerializedName(KEY_PHONE_NO) var phoneNo: String? = null,
    @ColumnInfo(name = KEY_ORG_POSITION) @SerializedName(KEY_ORG_POSITION) var orgPosition: String
) {

    companion object {
        const val KEY_ID = "id"
        const val KEY_EMAIL = "email"
        const val KEY_FIRST_NAME = "firstName"
        const val KEY_LAST_NAME = "lastName"
        const val KEY_USERNAME = "userName"
        const val KEY_ICON_IMAGE = "iconImage"
        const val KEY_PHONE_NO = "phoneNo"
        const val KEY_ORG_POSITION = "orgPosition"
    }

}