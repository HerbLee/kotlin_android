package com.herb2sy.lib.net

class Http {
    companion object {
        fun request(block: GetDataByNet.()->Unit){
            val flag = GetDataByNet()
            flag.block()
            flag.initData()
        }
    }


}

fun main(){
    Http.request {
        config {
            method=HttpConfig.Method.POST

        }
        onSuccess {

        }
        onError {

        }
    }
}