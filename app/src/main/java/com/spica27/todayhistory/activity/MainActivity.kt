package com.spica27.todayhistory.activity

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.PagerSnapHelper
import com.drakeet.multitype.MultiTypeAdapter
import com.google.android.material.transition.MaterialContainerTransformSharedElementCallback
import com.spica27.todayhistory.R
import com.spica27.todayhistory.adapter.MainDelegate
import com.spica27.todayhistory.contract.MainContract
import com.spica27.todayhistory.model.Sentence
import com.spica27.todayhistory.presenter.MainActivityPresenter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), MainContract.View {

  private val adapter = MultiTypeAdapter()

  private val items = ArrayList<Sentence>()



  private val presenter = MainActivityPresenter()
  override fun onCreate(savedInstanceState: Bundle?) {
    window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
    setExitSharedElementCallback(MaterialContainerTransformSharedElementCallback())
    window.sharedElementsUseOverlay = false
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    initView()
    presenter.attachView(this)
    presenter.queryList()
  }




  private fun initView() {
    adapter.register(MainDelegate(application,this))
    val snapHelper = PagerSnapHelper()
    snapHelper.attachToRecyclerView(rcv_sentence)
    rcv_sentence.adapter = adapter



    fab_fav.setOnClickListener(View.OnClickListener {


      val intent = Intent(this, FavoritesActivity::class.java)
      val options = ActivityOptions.makeSceneTransitionAnimation(
        this,
        it,
        "shared_element_container"
      )

      startActivity(intent, options.toBundle())
    })
  }

  override fun onResume() {
    super.onResume()
    fab_fav.visibility = View.VISIBLE
  }

  override fun onDestroy() {
    super.onDestroy()
    presenter.detachView()
  }

  override fun showLoadView() {
    //加载状态
  }

  override fun ShowLoadingSucessView(sentences: List<Sentence>) {
    //加载完成
    Log.e("加载item:", sentences.size.toString())
    items.addAll(sentences)
    adapter.items = items
    adapter.notifyDataSetChanged()
  }

  override fun showLoadingErrorView(e: Exception) {
    //加载错误
    Toast.makeText(this, "错误：${e.message}", Toast.LENGTH_LONG).show()
  }

}
