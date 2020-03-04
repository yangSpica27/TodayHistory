package com.spica27.todayhistory.contract

import com.spica27.todayhistory.model.Sentence
import com.spica27.todayhistory.base.MvpPresenter
import com.spica27.todayhistory.base.MvpView

class MainContract {

  interface View : MvpView {
    //加载中
    fun showLoadView()

    //请求成功
    fun ShowLoadingSucessView(sentences: List<Sentence>)

    //请求失败
    fun showLoadingErrorView(e: Exception)
  }

  interface Presnter : MvpPresenter<View> {
    fun queryList()
  }


}