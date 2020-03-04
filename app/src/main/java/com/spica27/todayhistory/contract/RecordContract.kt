package com.spica27.todayhistory.contract

import com.spica27.todayhistory.base.MvpPresenter
import com.spica27.todayhistory.base.MvpView
import com.spica27.todayhistory.persistence.entity.Record

class RecordContract{
  interface View : MvpView {

    //获取数据
    fun getData(items: List<Record>)
  }

  interface Presenter : MvpPresenter<View> {
    fun loadData()
  }
}