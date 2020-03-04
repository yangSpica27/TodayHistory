package com.spica27.todayhistory.persistence.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "record")
data class Record constructor(
  //ID
  @PrimaryKey(autoGenerate = true)
  var recordID: Int?,
  //来源
  @ColumnInfo(name = "r_from")
  var from: String,
  //日期
  @ColumnInfo(name = "r_date")
  var recordDate: Long,
  //内容
  @ColumnInfo(name = "r_info")
  var recordInfo: String?

)

