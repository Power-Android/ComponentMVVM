package com.power.componentmvvm

import com.power.common.base.BaseRepository
import com.power.common.constants.ApiService
import com.power.common.http.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import rxhttp.asFlow
import rxhttp.awaitResult
import rxhttp.wrapper.param.RxHttp
import rxhttp.wrapper.param.toCodeResponse
import rxhttp.wrapper.param.toResponse

/**
 * @author power
 * @date 2021/8/30 3:30 下午
 * @project ComponentMVVM
 * @description:
 */
class MainRepository: BaseRepository() {

    suspend fun osOver(): Boolean{
        return RxHttp.postForm(ApiService.GET_OS_OVERALL)
            .toResponse<Boolean>()
            .await()
    }

    suspend fun isNewUser(): Boolean {
        return RxHttp.postForm(ApiService.GET_OS_OVERALL)
            .toResponse<Boolean>()
            .await()
    }
}