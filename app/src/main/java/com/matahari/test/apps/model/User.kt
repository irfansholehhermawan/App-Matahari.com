package com.matahari.test.apps.model

/**
 * Created by Irfan Sholeh Hermawan on 30/07/19.
 * @author
 */

data class ResponseModel(var id: Int, var name: String, var email: String, var address: Address, var phone: String){
    inner class Address(var street: String, var suite: String, var city: String)
}