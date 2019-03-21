package com.tracker.trackerv2.data.room.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.tracker.trackerv2.data.room.dao.UserPositionDao
import com.tracker.trackerv2.data.room.entities.UserPosition

/*
 *   Created by Android Developer : Birsasteanu Bogdan Andrei Alexandru on Date: 12/04/2018
 */
 
@Database(entities = [UserPosition::class], version = 1, exportSchema = false)
public abstract class UserPositionDatabase : RoomDatabase() {

    abstract fun userPositionDao(): UserPositionDao

    companion object {

        private var INSTANCE: UserPositionDatabase? = null

        fun getInstance(context: Context): UserPositionDatabase? {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    UserPositionDatabase::class.java,
                    "user_positions.db"
                ).build()
            }
        }

        fun destroyInstance() {
            INSTANCE = null
        }

    }

}