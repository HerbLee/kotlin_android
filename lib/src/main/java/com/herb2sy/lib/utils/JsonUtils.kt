package com.herb2sy.lib.utils

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.herb2sy.lib.exception.JsonException
import org.json.JSONArray
import org.json.JSONObject
import java.text.SimpleDateFormat


object JsonUtils {

    var objectMapper:ObjectMapper = ObjectMapper()

//    companion object {
//        val instance: JsonUtils by lazy { JsonUtils() }
//    }

    init {
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS)
        //取消默认转换timestaps
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)

        //忽略空bean 转json 错误
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
        //统一日期格式
        objectMapper.dateFormat = SimpleDateFormat(TimeUtils.STANDARD_FORMAT)
        //或略json字符串中存在但是在bean中不存在的
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    }

    fun <T> obj2Str(obj: T?): String? {
        if (obj == null)
            return null
        return try {
            if (obj is String) obj else objectMapper.writeValueAsString(obj)
        } catch (e: Exception) {
            e.message?.let { throwException(it) }
            null
        }

    }

    fun <T> obj2StrPretty(obj: T?): String? {
        if (obj == null)
            return null
        try {
            return if (obj is String) obj else objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj)
        } catch (e: Exception) {
            e.message?.let { throwException(it) }
            return null
        }

    }

    fun <T> str2Obj(json: String, clazz: Class<T>?): T? {
        if (StringUtils.isEmpty(json) || clazz == null) {
            return null
        }

        return try {
            if (clazz == String::class.java) json as T else objectMapper.readValue(json, clazz)
        } catch (e: Exception) {
            e.message?.let { throwException(it) }
            null
        }

    }

    /**
     * @param json
     * @param clazz TypeReference<List></List><User>>
     * @param <T>
     * @return
    </T></User> */
    fun <T> str2Obj(json: String, clazz: TypeReference<T>?): T? {
        if (StringUtils.isEmpty(json) || clazz == null) {
            return null
        }

        try {
            return if (clazz!!.type == String::class.java) json as T else objectMapper.readValue(
                json,
                clazz
            ) as T
        } catch (e: Exception) {
            e.message?.let { throwException(it) }
            return null
        }

    }

    /**
     * @param json
     * @param collectionClass List
     * @param elementClass    User
     * @param <T>
     * @return
    </T> */
//    fun <T> str2Obj(json: String, collectionClass: Class<*>,  vararg elementClass: Class<*>): T? {
////
////
////        val type = objectMapper.typeFactory.constructParametricType(collectionClass, elementClass)
////
////        try {
////            return objectMapper.readValue(json, type)
////        } catch (e: Exception) {
////            e.message?.let { throwException(it) }
////            return null
////        }
////
////    }


    fun getJsonObj(json: String): JSONObject? {
        try {
            return JSONObject(json)
        } catch (e: Exception) {
            e.message?.let { throwException(it) }
            return null
        }

    }

    fun getJsonArr(json: String): JSONArray? {
        try {
            return JSONArray(json)
        } catch (e: Exception) {
            e.message?.let { throwException(it) }
            return null
        }

    }

    fun getJsonObj(json: String, data: String): JSONObject? {
        try {
            val jsonObj = getJsonObj(json)
            if (jsonObj != null) {
                return jsonObj!!.getJSONObject(data)
            }
        } catch (e: Exception) {
            e.message?.let { throwException(it) }
        }

        return null
    }


    private fun throwException(msg: String) {
        try {
            throw JsonException(StringUtils.obj2Str(msg, "数据错误"))
        } catch (e1: JsonException) {
            e1.printStackTrace()
        }

    }


}