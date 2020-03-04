package com.spica27.todayhistory.persistence.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.spica27.todayhistory.persistence.entity.Record


@Dao
interface RecordDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insert(element: Record)

  @Insert
  fun insertAll(users: List<Record>)


  @Delete
  fun delete(element: Record)

  @Query("SELECT * FROM record Order by recordID DESC")
  fun getAllRecords(): LiveData<List<Record>>


  @Query("delete from record")
  fun deleteAll()

}