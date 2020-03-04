package com.spica27.todayhistory.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

import com.spica27.todayhistory.persistence.dao.RecordDao
import com.spica27.todayhistory.persistence.entity.Record


@Suppress("DEPRECATION")
@Database(entities = [Record::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun recordDao(): RecordDao



    companion object {

        // 数据库单例
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room
                .databaseBuilder(context.applicationContext, AppDatabase::class.java, "Spica_db")
                .allowMainThreadQueries()
                .build()
        }
    }


}