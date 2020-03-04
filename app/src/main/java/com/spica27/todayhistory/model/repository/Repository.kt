package com.spica27.todayhistory.model.repository

import android.util.Log
import com.spica27.todayhistory.model.ApiSource
import com.spica27.todayhistory.model.Sentence
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


object Repository {
  suspend fun adapterCoroutineQuery(): List<Sentence> {
    return withContext(Dispatchers.Main) {
      try {
        //句子1
        val sentence1 = ApiSource.callAdapterInstance.getScence1().await()
        //句子2
        val sentence2 = ApiSource.callAdapterInstance.getScence2().await()
        //句子3
        val sentence3 = ApiSource.callAdapterInstance.getScence3().await()

        Log.e("lll", sentence1.hitokoto)
        Log.e("lll", sentence2.hitokoto)
        Log.e("lll", sentence3.hitokoto)

        val result = mutableListOf<Sentence>()
        result.add(sentence1)
        result.add(sentence2)
        result.add(sentence3)
        result
      } catch (e: Throwable) {
        Log.e("错误:", e.message)
        e.printStackTrace()
        throw e
      }
    }
  }
}