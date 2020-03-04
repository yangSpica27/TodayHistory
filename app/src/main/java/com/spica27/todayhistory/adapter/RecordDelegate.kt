package com.spica27.todayhistory.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.ItemViewDelegate
import com.google.android.material.textview.MaterialTextView
import com.spica27.todayhistory.R
import com.spica27.todayhistory.persistence.entity.Record
import java.text.SimpleDateFormat

class RecordDelegate(context: Context) : ItemViewDelegate<Record, RecordDelegate.ViewHolder>() {


  override fun onCreateViewHolder(context: Context, parent: ViewGroup): ViewHolder {
    val view = LayoutInflater.from(context).inflate(R.layout.item_record, parent, false)
    return ViewHolder(view)
  }

  override fun onBindViewHolder(holder: ViewHolder, item: Record) {
    holder.tvFrom.text = item.from
    holder.tvTime.text =  SimpleDateFormat("yy-MM-dd HH-mm-ss").format(item.recordDate)
    holder.tvInfo.text = item.recordInfo
  }

  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvTime = itemView.findViewById<MaterialTextView>(R.id.tv_time)
    val tvFrom = itemView.findViewById<MaterialTextView>(R.id.tv_from)
    val tvInfo = itemView.findViewById<MaterialTextView>(R.id.tv_body)
  }

}