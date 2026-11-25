package com.example.fundlink.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fundlink.data.model.FundingOpportunity
import com.example.fundlink.data.model.StudentApplication
import com.example.fundlink.data.model.User

@Database(
    entities = [User::class, FundingOpportunity::class, StudentApplication::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun opportunityDao(): OpportunityDao
    abstract fun applicationDao(): ApplicationDao

    companion object {
<<<<<<< HEAD
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "fundlink.db"
                ).build().also { INSTANCE = it }
            }
        }
=======
        @Volatile private var INSTANCE: AppDatabase? = null
        fun get(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext, AppDatabase::class.java, "fundlink.db"
                ).build().also { INSTANCE = it }
            }
>>>>>>> origin/main
    }
}
