package com.tracker.trackerv2.data.room.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.tracker.trackerv2.data.room.dao.UserAccountDao
import com.tracker.trackerv2.data.room.entities.UserAccount

/*
 *   Created by Android Developer : Birsasteanu Bogdan Andrei Alexandru on Date: 12/04/2018
 */
 
@Database(entities = [UserAccount::class], version = 1, exportSchema = false)
public abstract class UserAccountDatabase: RoomDatabase() {

    abstract fun userAccountDataDao(): UserAccountDao

    companion object {

        private var INSTANCE: UserAccountDatabase? = null

        fun getInstance(context: Context): UserAccountDatabase? {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    UserAccountDatabase::class.java,
                    "user_accounts.db"
                ).build()
            }
        }

        fun destroyInstance() {
            INSTANCE = null
        }

    }

}