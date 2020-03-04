package com.spica27.todayhistory.base

import androidx.annotation.UiThread

interface MvpPresenter<V: MvpView> {

    @UiThread
    fun attachView(view: V)

    @UiThread
    fun detachView()
}