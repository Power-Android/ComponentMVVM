package com.power.common.http

import com.power.common.ToastView
import com.power.common.provide.AppContext
import rxhttp.wrapper.annotation.Parser
import rxhttp.wrapper.entity.ParameterizedTypeImpl
import rxhttp.wrapper.exception.ParseException
import rxhttp.wrapper.parse.AbstractParser
import java.io.IOException
import java.lang.reflect.Type

/**
 * @author power
 * @date 12/1/20 3:26 PM
 * @project RunElephant
 * @description: 这个解析器返回带有code的data，如需判断特殊code请用此解析器
 */
@Parser(name = "CodeResponse")
open class ResponseCodeParser<T> : AbstractParser<Response<T>> {
    protected constructor() : super()
    constructor(type: Type?) : super(type!!)

    @Throws(IOException::class)
    override fun onParse(response: okhttp3.Response): Response<T> {
        val type: Type = ParameterizedTypeImpl[Response::class.java, mType] //获取泛型类型
        val data: Response<T> = convert(response, type)
        if (data.code != 1) {
            ToastView.get(AppContext.context())
                .toast(data.msg?.let { data.msg } ?: "网络请求错误", isError = true)
            throw ParseException(data.code.toString(), data.msg, response)
        }
        return data
    }
}