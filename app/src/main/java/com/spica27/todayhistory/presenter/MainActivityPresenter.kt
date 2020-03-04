package com.spica27.todayhistory.presenter

import android.util.Log
import com.spica27.todayhistory.base.BasePresenter
import com.spica27.todayhistory.contract.MainContract
import com.spica27.todayhistory.model.repository.Repository
import kotlinx.coroutines.launch


class MainActivityPresenter : MainContract.Presnter, BasePresenter<MainContract.View>() {


  override fun queryList() {
    presenterScope.launch {
      //记录初始时间
      val time = System.currentTimeMillis()
      view.showLoadView()
      try {
        val sentences = Repository.adapterCoroutineQuery()
        Log.e("query：", sentences.size.toString())
        view.ShowLoadingSucessView(sentences)
      } catch (e: Exception) {
        e.printStackTrace()
        Log.e("query错误", e.message)
        view.showLoadingErrorView(e)
      } finally {
        Log.d("网络请求：-->", "耗时：${System.currentTimeMillis() - time}")
      }
    }
  }

}