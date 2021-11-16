package com.power.common.http

data class Response<T> (

    var code: Int = 0,

    var msg: String = "",

    var data: T,
)