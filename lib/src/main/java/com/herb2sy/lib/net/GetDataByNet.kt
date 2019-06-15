package com.herb2sy.lib.net

import okhttp3.Response

class GetDataByNet {

    protected var baseConfig1:HRequest? = null
    protected lateinit var _onSuccess:(response: Response?)->Unit
    protected lateinit var _onError:(error:String?)->Unit
    protected lateinit var _onComplete:()->Unit

    inline fun config(config: HRequest.()-> Unit){
        var hd = HRequest()
        hd.config()
        baseConfig1 = hd
    }
    fun initData(){
        Kokhttp.instant.initData(baseConfig1!!,_onSuccess,_onError,_onComplete)
    }


    inline fun onSuccess(noinline onSuccess:(response: Response?)->Unit){
        _onSuccess = onSuccess

    }

    inline fun onError(noinline onError:(error:String?)->Unit){
        _onError = onError

    }
    inline fun onComplete(noinline onComplete:()->Unit){
        _onComplete = onComplete

    }




}