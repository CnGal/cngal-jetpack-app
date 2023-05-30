package com.cngal.app.helper

import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.net.URLDecoder
import java.net.URLEncoder
import kotlin.reflect.javaType
import kotlin.reflect.typeOf

class JsonHelper
{
    companion object
    {
        inline fun <reified TModel> toJson(model: TModel): String
        {
            val moshi = Moshi.Builder()
                .addLast(KotlinJsonAdapterFactory())//使用kotlin反射处理，要加上这个
                .build()
            val jsonAdapter = moshi.adapter<TModel>(TModel::class.java).lenient()
            return URLEncoder.encode(jsonAdapter.toJson(model), "utf-8")
        }

        @OptIn(ExperimentalStdlibApi::class)
        inline fun <reified TModel> fromJson(json: String): TModel
        {
            val mainType = typeOf<TModel>()
            val finalType = Types.newParameterizedType(
                TModel::class.java,
                *mainType.arguments.map { it.type!!.javaType }.toTypedArray()
            )
            val moshi = Moshi.Builder()
                .addLast(KotlinJsonAdapterFactory())//使用kotlin反射处理，要加上这个
                .build()
            val jsonAdapter = moshi.adapter<TModel>(finalType).lenient()
            return jsonAdapter.fromJson(
                URLDecoder.decode(json.replace(Regex("%(?![0-9a-fA-F]{2})"), "%25"), "utf-8")
            )!!
        }
    }
}

