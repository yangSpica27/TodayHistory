package com.spica27.todayhistory.persistence.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.spica27.todayhistory.persistence.entity.Record
import com.spica27.todayhistory.persistence.repository.RecordRepository


class RecordViewModel(application: Application) : AndroidViewModel(application) {

  var recordRepository: RecordRepository = RecordRepository(application)
  var allRecord: LiveData<List<Record>>? = null


  init {
    allRecord = recordRepository.allRecords
  }


  fun sync() {
    allRecord = recordRepository.allRecords
  }


  fun insert(record: Record) {
    recordRepository.insertRecord(record)
  }


  fun delete(record: Record) {
    recordRepository.deleteRecord(record)
  }

}