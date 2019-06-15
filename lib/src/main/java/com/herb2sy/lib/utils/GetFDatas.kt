package com.herb2sy.lib.utils

object GetFDatas{

    fun UUID():String {
        return java.util.UUID.randomUUID().toString().replace("-","")
    }
}