package com.herb2sy.lib.net

class HttpConfig {
    interface Method{
        companion object{
            val GET = 0
            val POST = 1
        }

    }
    interface Type{
        companion object{
            val DEF = 0
            val JSON = 1
        }

    }
}