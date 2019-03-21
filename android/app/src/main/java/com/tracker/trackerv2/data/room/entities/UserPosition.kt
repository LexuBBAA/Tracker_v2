package com.tracker.trackerv2.data.room.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/*
 *   Created by Android Developer : Birsasteanu Bogdan Andrei Alexandru on Date: 12/04/2018
 */

@Entity
data class UserPosition(
    @PrimaryKey(autoGenerate = true) @SerializedName(KEY_ID) var id: Long?,
    @ColumnInfo(name = KEY_TITLE) @SerializedName(KEY_TITLE) var title: String,
    @ColumnInfo(name = KEY_DEPARTMENT) @SerializedName(KEY_DEPARTMENT) var department: String,
    @ColumnInfo(name = KEY_CATEGORY) @SerializedName(KEY_CATEGORY) var category: String
) {

    companion object {
        const val KEY_ID = "id"
        const val KEY_TITLE = "title"
        const val KEY_DEPARTMENT = "department"
        const val KEY_CATEGORY = "category"
    }

}