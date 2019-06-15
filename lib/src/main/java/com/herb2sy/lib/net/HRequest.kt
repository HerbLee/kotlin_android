package com.herb2sy.lib.net

class HRequest {

    val _params: MutableMap<String, String> = mutableMapOf() // used for a POST or PUT request.
    val _fileParams: MutableMap<String, String> = mutableMapOf() // used for a POST or PUT request.
    val _headers: MutableMap<String, String> = mutableMapOf()

    var paramsMap:MutableMap<String,String>? = null
    var headerMap:MutableMap<String,String>? = null
    var jsonParams:String? = null
    var method:Int = HttpConfig.Method.GET
    var url:String? = null
    var type:Int = HttpConfig.Type.DEF




    /**
     * 头文件
     */
    inline fun header(headers: RequestPairs.()-> Unit){
        val hd = RequestPairs()
        hd.headers()
        _headers.putAll(hd.pairs)
    }

    /**
     * params
     */
    inline fun params(params: RequestPairs.()-> Unit){
        val hd = RequestPairs()
        hd.params()
        _params.putAll(hd.pairs)
    }

    fun getRequest():HRequest{
        return this
    }

}

class RequestPairs {
    var pairs: MutableMap<String, String> = HashMap()
    operator fun String.minus(value: String) {
        pairs.put(this, value)
    }
}