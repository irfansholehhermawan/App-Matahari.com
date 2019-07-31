package com.matahari.test.apps.model

import com.matahari.test.apps.network.ApiClient
import com.matahari.test.apps.view_presenter.ViewPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

/**
 * Created by Irfan Sholeh Hermawan on 30/07/19.
 * @author
 */

class PresenterImplementation : ViewPresenter.MainPresenter {

    private var mainView: ViewPresenter.MainView? = null

    @NonNull
    var disposable: Disposable? = null

    constructor(mainView: ViewPresenter.MainView?) {
        this.mainView = mainView
    }

    override fun loadData() {
        mainView!!.showProgressbar()

        if (mainView!!.checkInternet()) {

            disposable = ApiClient.instance
                    .getListOfUser()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ listResponse ->
                        mainView!!.hideProgressbar()
                        mainView!!.onSuccess(listResponse)
                    }, { error ->
                        mainView!!.hideProgressbar()
                        if (error is HttpException) {
                            val response = error.response()
                            when (response.code()) {
                            //Responce Code
                            }
                        } else {
                            //Handle Other Errors
                        }
                        mainView!!.onError(error)
                    })
        } else {
            mainView!!.hideProgressbar()
            mainView!!.validateError()
        }
    }

    override fun onStop() {
        if (disposable != null) {
            disposable!!.dispose()
        }
    }
}
