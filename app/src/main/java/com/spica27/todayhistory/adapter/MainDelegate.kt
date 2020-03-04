package com.spica27.todayhistory.adapter

import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.signature.ObjectKey
import com.drakeet.multitype.ItemViewDelegate
import com.google.android.material.textview.MaterialTextView
import com.spica27.todayhistory.R
import com.spica27.todayhistory.model.Sentence
import com.spica27.todayhistory.persistence.entity.Record
import com.spica27.todayhistory.persistence.repository.RecordRepository
import com.spica27.todayhistory.utils.FileTools
import java.util.*

class MainDelegate(application: Application, val context: Context) : ItemViewDelegate<Sentence, MainDelegate.ViewHolder>() {


  val recordRepository = RecordRepository(application)

  override fun onBindViewHolder(holder: ViewHolder, item: Sentence) {


    Glide.with(context)
      .load("https://api.ixiaowai.cn/api/api.php")
      .placeholder(R.drawable.ic_image_black_24dp)
      .signature(ObjectKey(UUID.randomUUID().toString()))
      .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
      .error(R.drawable.ic_warning_black_24dp)
      .into(holder.imageView)
    holder.titleView.text = item.hitokoto
    holder.subTitle.text = "——  ${item.from}"
    holder.ivFav.setOnClickListener {
      recordRepository.insertRecord(Record(null, item.from,Calendar.getInstance().timeInMillis, item.hitokoto))
      Toast.makeText(context, "已收藏", Toast.LENGTH_LONG).show()
    }
    holder.ivDownload.setOnClickListener {
      val bitmap = holder.imageView.drawable.toBitmap()
      if (bitmap != null) {
        val path = FileTools.saveBitmap(context, bitmap)
        Toast.makeText(context, "完成,${path}", Toast.LENGTH_LONG).show()
      } else {
        Toast.makeText(context, "不能保存null对象", Toast.LENGTH_LONG).show()
      }
    }

  }

  override fun onCreateViewHolder(context: Context, parent: ViewGroup): ViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
    return ViewHolder(view)
  }

  class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
    val imageView: ImageView = itemView.findViewById(R.id.card_detail_background)
    val titleView: MaterialTextView = itemView.findViewById(R.id.card_title)
    val subTitle: MaterialTextView = itemview.findViewById(R.id.card_subtitle)
    val ivDownload: View = itemview.findViewById(R.id.iv_downLoad)
    val ivFav: View = itemview.findViewById(R.id.iv_fav)
  }

}
