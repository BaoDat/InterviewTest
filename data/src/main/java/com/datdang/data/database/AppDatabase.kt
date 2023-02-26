package com.datdang.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.datdang.data.database.dao.CategoryDao
import com.datdang.data.database.entity.CategoryEntity

@Database(
    entities = [CategoryEntity::class], version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = INSTANCE ?: synchronized(LOCK) {
            buildDatabase(context).also {
                INSTANCE = it
            }
        }

        private fun buildDatabase(context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, "category_db")
                .fallbackToDestructiveMigration()
                .build()
    }
}