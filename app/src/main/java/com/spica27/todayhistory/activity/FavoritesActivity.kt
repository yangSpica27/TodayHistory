package com.spica27.todayhistory.activity


import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.drakeet.multitype.MultiTypeAdapter
import com.google.android.material.transition.MaterialContainerTransform
import com.google.android.material.transition.MaterialContainerTransformSharedElementCallback
import com.spica27.todayhistory.R
import com.spica27.todayhistory.adapter.RecordDelegate
import com.spica27.todayhistory.contract.RecordContract
import com.spica27.todayhistory.persistence.entity.Record
import com.spica27.todayhistory.presenter.RecordPresenter
import kotlinx.android.synthetic.main.activity_favorites.*

class FavoritesActivity : AppCompatActivity(), RecordContract.View {

  private val configurationHelper = ContainerTransformConfigurationHelper()
  private val adapter = MultiTypeAdapter()

  private lateinit var presenter: RecordPresenter


  override fun onCreate(savedInstanceState: Bundle?) {
    window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
    val view = findViewById<View>(android.R.id.content)
    view.transitionName = "shared_element_container"
    setEnterSharedElementCallback(MaterialContainerTransformSharedElementCallback())
    window.sharedElementEnterTransition = buildContainerTransform(true)
    window.sharedElementReturnTransition = buildContainerTransform(false)
    super.onCreate(savedInstanceState)
    presenter = RecordPresenter(this)
    setContentView(R.layout.activity_favorites)
    setSupportActionBar(toolbar)
    initView()
  }


  private fun initView() {
    presenter.attachView(this)
    presenter.loadData()
    adapter.register(RecordDelegate(this))
    rcv_fav.adapter = adapter
  }

  override fun onDestroy() {
    super.onDestroy()
    presenter.detachView()
    adapter == null
  }

  private fun buildContainerTransform(entering: Boolean): MaterialContainerTransform? {
    val transform = MaterialContainerTransform(this)
    transform.addTarget(android.R.id.content)
    configurationHelper.configure(transform, entering)
    return transform
  }

  override fun getData(items: List<Record>) {
    adapter.items = items
    adapter.notifyDataSetChanged()
  }

}
