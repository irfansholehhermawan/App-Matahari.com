package com.matahari.test.apps.network

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.matahari.test.apps.model.ResponseModel
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Irfan Sholeh Hermawan on 30/07/19.
 * @author
 */

class ApiClient {

    private val myAppService: GetDataServices

    init {
        val gson = GsonBuilder()
                .setLenient()
                .create()

        val clientBuilder = OkHttpClient.Builder()
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        clientBuilder.addInterceptor(loggingInterceptor)

        val retrofit = Retrofit.Builder().baseUrl(API_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

        myAppService = retrofit.create(GetDataServices::class.java)
    }

    companion object {
        private const val API_URL = "https://jsonplaceholder.typicode.com/"
        private var apiClient: ApiClient? = null
        /**
         * Gets my app client.
         *
         * @return the my app client
         */
        val instance: ApiClient get() {
                if (apiClient == null) {
                    apiClient = ApiClient()
                }
                return apiClient as ApiClient
            }
    }

    /**
     * Gets list of user.
     *
     * @return the list of user
     */
    fun getListOfUser(): Observable<List<ResponseModel>> {
        return myAppService.getList()
    }
}
