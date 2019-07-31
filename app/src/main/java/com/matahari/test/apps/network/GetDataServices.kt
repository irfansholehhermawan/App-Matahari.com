package com.matahari.test.apps.network

import com.matahari.test.apps.model.ResponseModel
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by Irfan Sholeh Hermawan on 30/07/19.
 * @author
 */

interface GetDataServices {

    @GET("users")
    fun getList(): Observable<List<ResponseModel>>
}