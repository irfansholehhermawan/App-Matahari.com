package com.matahari.test.apps

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.matahari.test.apps.adapter.ListItemAdapter
import com.matahari.test.apps.model.PresenterImplementation
import com.matahari.test.apps.model.ResponseModel
import com.matahari.test.apps.network.NetWorkConection
import com.matahari.test.apps.view_presenter.ViewPresenter
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by Irfan Sholeh Hermawan on 30/07/19.
 */

class MainActivity : AppCompatActivity(), ViewPresenter.MainView {

    private var presenterImplementation: PresenterImplementation? = null
    private var linearLayoutManager: LinearLayoutManager? = null
    private var mAdapter: ListItemAdapter? = null
    private var arrayList: List<ResponseModel>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()
        presenterImplementation = PresenterImplementation(this)
        presenterImplementation!!.loadData()

        btn_try.setOnClickListener { recreate() }
    }

    private fun initUI() {
        linearLayoutManager = LinearLayoutManager(applicationContext)
        rv_list.setHasFixedSize(true)
        rv_list.itemAnimator = DefaultItemAnimator()
        rv_list.layoutManager = linearLayoutManager
        rv_list.fitsSystemWindows = true
    }

    override fun onDestroy() {
        super.onDestroy()
        presenterImplementation!!.onStop()
    }

    override fun validateError() {
        btn_try.visibility = View.VISIBLE
        Toast.makeText(applicationContext,getString(R.string.error_connection),Toast.LENGTH_SHORT).show()
    }

    override fun showProgressbar() {
        pb_loading.visibility = View.VISIBLE
        tv_please_wait.visibility = View.VISIBLE
    }

    override fun hideProgressbar() {
        pb_loading.visibility = View.GONE
        tv_please_wait.visibility = View.GONE
    }

    override fun onSuccess(responseModel: List<ResponseModel>) {
        if (responseModel.isNotEmpty()) {
            btn_try.visibility = View.GONE
            arrayList = responseModel
            mAdapter = ListItemAdapter(arrayList!!, this)
            rv_list.adapter = mAdapter
        }
    }

    override fun onError(throwable: Throwable) {
        btn_try.visibility = View.VISIBLE
        Toast.makeText(applicationContext,getString(R.string.on_error),Toast.LENGTH_SHORT).show()
    }

    override fun checkInternet(): Boolean {
        return NetWorkConection.isNEtworkConnected(applicationContext)
    }
}
