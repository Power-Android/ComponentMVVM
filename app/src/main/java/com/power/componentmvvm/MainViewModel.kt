package com.power.componentmvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.power.common.base.BaseViewModel
import com.power.common.http.Response
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * @author power
 * @date 2021/8/30 3:30 下午
 * @project ComponentMVVM
 * @description:
 */
class MainViewModel : BaseViewModel<MainRepository>() {
    val osLiveData: MutableLiveData<Boolean> = liveData{
        emit(mRepository.osOver())
    } as MutableLiveData<Boolean>

    fun osOver() = liveData{
        launch {
            emit(mRepository.osOver())
        }
    }
}