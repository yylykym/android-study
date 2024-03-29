package com.yylykym.jetpacktest.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yylykym.jetpacktest.User

@Database(version = 1, entities = [User::class])
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {

        private var instance: AppDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): AppDatabase {
            instance?.let {
                return it
            }
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, "app_database"
            )
                .build().apply {
                    instance = this
                }
        }
    }
}