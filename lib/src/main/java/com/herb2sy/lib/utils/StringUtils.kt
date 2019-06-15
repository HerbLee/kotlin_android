package com.herb2sy.lib.utils

import com.herb2sy.lib.exception.StringTransformationException
import java.net.URLDecoder
import java.util.regex.Pattern

object StringUtils {

    /**
     * obj转为字符串
     * @param obj 传入的obj类型
     * @return 字符串或者null
     */
    fun obj2Str(obj: Any?): String? {
        if (obj == null) {
            return null
        }
        try {
            return URLDecoder.decode(obj.toString().trim { it <= ' ' }, "UTF-8")
        } catch (e: Exception) {
            e.message?.let { throwException(it) }
        }

        return null

    }


    /**
     * obj转字符串
     * @param obj obj类型
     * @param defVal 默认值
     * @return 正常转换或返回默认类型
     */
    fun obj2Str(obj: Any, defVal: String): String {
        return obj2Str(obj) ?: return defVal
    }


    /**
     * obj转Integer 包括null
     * @param obj
     * @return 原来内容或者null
     */
    fun obj2Int(obj: Any?): Int? {
        var iRet: Int? = null
        try {
            if (obj == null)
                return iRet
            else
                iRet = Integer.valueOf(obj.toString()) as Int
        } catch (ex: Exception) {
            ex.message?.let { throwException(it) }

        }

        return iRet
    }

    /**
     * obj转Integer 如果为null 或者异常，为def
     * @param obj
     * @param def
     * @return
     */
    fun obj2Int(obj: Any, def: Int?): Int {
        return obj2Int(obj) ?: return def!!
    }


    /**
     * obj转long类型
     * @param obj
     * @return 默认为null
     */
    fun obj2Long(obj: Any?): Long? {
        if (obj == null) {
            return null
        }
        try {
            return java.lang.Long.valueOf(obj2Str(obj)!!)
        } catch (e: Exception) {
            e.message?.let { throwException(it) }

        }

        return null
    }

    /**
     * obj 转Long类型
     * @param obj
     * @param def
     * @return
     */
    fun obj2Long(obj: Any, def: Long?): Long? {
        return obj2Long(obj) ?: return def
    }

    /**
     * obj 转float
     * @param obj
     * @return
     */
    fun obj2Float(obj: Any?): Float? {
        if (obj == null) {
            return null
        }
        try {
            return java.lang.Float.valueOf(obj2Str(obj)!!)
        } catch (e: Exception) {
            e.message?.let { throwException(it) }
        }

        return null
    }

    /**
     * obj 转float
     * @param obj
     * @param defValue
     * @return
     */
    fun obj2Float(obj: Any, defValue: Float?): Float? {
        return obj2Float(obj) ?: return defValue
    }

    /**
     * 判断字符串是否为空
     * @param str
     * @return
     */
    fun isEmpty(str: String?): Boolean {
        return str == null || str === "undefined" || str === "null" || str.isEmpty()
    }

    /**
     * 判断多个字符串是否为空
     * @param strs
     * @return 传入的所有字符串都不为空返回false，有一个为空则返回true
     */
    fun isEmptys(vararg strs: String): Boolean {

        for (str in strs) {
            if (isEmpty(str)) {
                return true
            }
        }
        return false

    }

    fun isIntegers(vararg strs:String):Boolean{
        for (str in strs){
            if (!isInteger(str)){
                return false
            }
        }
        return true
    }


    fun isInteger(str: String?): Boolean {
        if (str == null || str.isEmpty()){
            return false
        }
        val pattern = Pattern.compile("^[-\\+]?[\\d]*$")
        return pattern.matcher(str).matches()
    }

    /**
     * 判断obj是否为空
     * @param object
     * @return
     */
    fun isNull(`object`: Any?): Boolean {
        return `object` == null
    }

    /**
     * 判断多个数据是否都不为空
     * @param objects
     * @return
     */
    fun isNullS(vararg objects: Any): Boolean {
        for (item in objects) {
            if (isNull(item)) {
                return true
            }
        }
        return false
    }


    private fun throwException(msg: String) {
        try {
            throw StringTransformationException(obj2Str(msg, "数据错误"))
        } catch (e1: StringTransformationException) {
            e1.printStackTrace()
        }

    }

}