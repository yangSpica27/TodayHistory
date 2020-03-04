package com.spica27.todayhistory.presenter

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.spica27.todayhistory.base.BasePresenter
import com.spica27.todayhistory.contract.RecordContract
import com.spica27.todayhistory.persistence.viewmodel.RecordViewModel

class RecordPresenter(val activity: AppCompatActivity) : RecordContract.Presenter, BasePresenter<RecordContract.View>() {

  private var recordViewModel: RecordViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(activity.application)
    .create(RecordViewModel::class.java)


  override fun loadData() {
    recordViewModel.allRecord?.observe(activity, Observer {
      view.getData(it)
    })
  }

}