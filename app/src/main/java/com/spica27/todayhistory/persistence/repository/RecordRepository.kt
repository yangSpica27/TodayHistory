package com.spica27.todayhistory.persistence.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import com.spica27.todayhistory.persistence.AppDatabase
import com.spica27.todayhistory.persistence.dao.RecordDao
import com.spica27.todayhistory.persistence.entity.Record

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecordRepository constructor(application: Application) {

    private var recordDao: RecordDao
    var allRecords: LiveData<List<Record>>

    init {
        val appDatabase = AppDatabase.getInstance(application)
        recordDao = appDatabase.recordDao()
        allRecords = recordDao.getAllRecords()
    }

    fun insertRecord(record: Record) {
        val coroutineScope = CoroutineScope(Dispatchers.Main)
        coroutineScope.launch {
            insert(record)
        }
    }

    fun deleteRecord(record: Record) {
        val coroutineScope = CoroutineScope(Dispatchers.Main)
        coroutineScope.launch {
            delete(record)
        }
    }




    suspend fun queryAll(): LiveData<List<Record>> = withContext(Dispatchers.IO) {
        return@withContext recordDao.getAllRecords()
    }


    private suspend fun insert(record: Record) = withContext(Dispatchers.IO) {
        Log.e("插入数据:", record.recordInfo)
        recordDao.insert(record)
    }

    private suspend fun delete(record: Record) = withContext(Dispatchers.IO) {
        Log.e("删除数据:", record.recordInfo)
        recordDao.delete(record)
    }


}