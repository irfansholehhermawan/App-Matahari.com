package com.matahari.test.apps.view_presenter

import com.matahari.test.apps.model.ResponseModel

/**
 * Created by Irfan Sholeh Hermawan on 30/07/19.
 * @author
 */

interface ViewPresenter {

    interface MainView {

        fun validateError()
        fun showProgressbar()
        fun hideProgressbar()
        fun onSuccess(responseModel: List<ResponseModel>)
        fun onError(throwable: Throwable)
        fun checkInternet(): Boolean
    }

    interface MainPresenter {
        fun loadData()
        fun onStop()
    }
}