package com.herb2sy.lib.net

import okhttp3.*
import java.io.IOException
import java.io.UnsupportedEncodingException
import java.net.URLEncoder

class Kokhttp {

    var client:OkHttpClient
    val UTF_8 = "UTF-8"


    init {
        val builder = OkHttpClient.Builder()
        client = builder.build()
    }

    companion object{
        val instant:Kokhttp by lazy { Kokhttp() }
    }

    private fun mapToQueryString(map: Map<String, String>): String{

        val string = StringBuilder()

        try {
            for ((key, value) in map) {
                string.append(key)
                string.append("=")
                string.append(URLEncoder.encode(value, UTF_8))
                string.append("&")
            }
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }

        return string.toString()
    }

    fun initData(bean:HRequest,onSuccess:(response: Response)->Unit,onError:(str:String?)->Unit,onComplete:()->Unit){

        when(bean.method){
            //开始初始化各种
            HttpConfig.Method.GET ->get(bean,onSuccess,onError,onComplete)
            HttpConfig.Method.POST -> post(bean,onSuccess,onError,onComplete)
        }

    }

    fun get(bean: HRequest,onSuccess:(response: Response)->Unit,onError:(str:String?)->Unit,onComplete:()->Unit){

        var param:String = ""

        if (bean._params.isNotEmpty()){
            param = mapToQueryString(bean._params)
        }
        if (bean.paramsMap != null){
            if (bean.paramsMap?.isNotEmpty()!!){
                param += mapToQueryString(bean.paramsMap!!)
            }
        }

        val builder = Request.Builder()

        for (ss in bean._headers.keys) {
            builder.addHeader(ss, URLEncoder.encode(bean._headers[ss], UTF_8))
        }
        if (bean.headerMap != null){
            for (ss in bean.headerMap?.keys!!){
                builder.addHeader(ss, URLEncoder.encode(bean._headers[ss], UTF_8))
            }
        }
        var url = bean.url
        if (param != ""){
            url = "$url?$param"
        }

        val request = builder.url(url).build()
        get(request,onSuccess,onError,onComplete)

    }
    fun get(request: Request,onSuccess:(response: Response)->Unit,onError:(str:String?)->Unit,onComplete:()->Unit){
        client.newCall(request).enqueue(object : Callback{
            override fun onFailure(call: Call, e: IOException) {
                onError(e.message)
                onComplete()
            }

            override fun onResponse(call: Call, response: Response) {
                onSuccess(response)
                onComplete()
            }
        })

    }




    private fun post(bean: HRequest,onSuccess:(response: Response)->Unit,onError:(str:String?)->Unit,onComplete:()->Unit){
        //暂时分为json 和 表单
        when(bean.type){
            HttpConfig.Type.DEF -> postByFrom(bean,onSuccess,onError,onComplete)
            HttpConfig.Type.JSON -> postByJson(bean,onSuccess,onError,onComplete)
        }
    }

    private fun postByFrom(bean: HRequest,onSuccess:(response: Response)->Unit,onError:(str:String?)->Unit,onComplete:()->Unit){
        val builder = FormBody.Builder()
        val params = HashMap<String,String>()
        if (bean._params.isNotEmpty()){
            params.putAll(bean._params)
        }
        if (bean.paramsMap != null){
            params.putAll(bean.paramsMap!!)
        }


        val requestBody = builder.build()
        val builder1 = Request.Builder()
        for (ss in bean._headers.keys) {
            builder1.addHeader(ss, URLEncoder.encode(bean._headers[ss], UTF_8))
        }
        if (bean.headerMap != null){
            for (ss in bean.headerMap?.keys!!){
                builder1.addHeader(ss, URLEncoder.encode(bean._headers[ss], UTF_8))
            }
        }
        if (bean.url == null){
            bean.url = "http://www.baidu.com"
        }
        val result = builder1.url(bean.url).post(requestBody).build()
        get(result,onSuccess,onError,onComplete)

    }
    private fun postByJson(bean: HRequest,onSuccess:(response: Response)->Unit,onError:(str:String?)->Unit,onComplete:()->Unit){

        if (bean.jsonParams == null){
            bean.jsonParams = "{'user':'error'}"
        }

        val requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8"), bean.jsonParams)
        val builder1 = Request.Builder()
        for (ss in bean._headers.keys) {
            builder1.addHeader(ss, URLEncoder.encode(bean._headers[ss], UTF_8))
        }
        if (bean.headerMap != null){
            for (ss in bean.headerMap?.keys!!){
                builder1.addHeader(ss, URLEncoder.encode(bean._headers[ss], UTF_8))
            }
        }
        if (bean.url == null){
            bean.url = "http://www.baidu.com"
        }
        val result = builder1.url(bean.url).post(requestBody).build()
        get(result,onSuccess,onError,onComplete)
    }

    fun showDetail(){

        System.out.println("显示详情")
    }
    fun showMain(){
        System.out.println("显示主要的")
    }

}