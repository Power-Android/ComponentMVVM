package com.power.common.base

import android.graphics.Color
import android.view.Gravity
import androidx.lifecycle.*
import com.power.common.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import per.goweii.anylayer.AnyLayer
import java.lang.reflect.ParameterizedType

/**
 * @author power
 * @date 2021/8/13 4:33 下午
 * @project ComponentMVVM
 * @description:
 */
open class BaseViewModel<M : BaseRepository> : ViewModel(), LifecycleObserver {

    var mRepository: M = createModel()

    private val loadingLiveData: MutableLiveData<Boolean> = MutableLiveData()

    private fun createModel(): M {
        val modelClass: Class<*>
        val type = javaClass.genericSuperclass
        modelClass = if (type is ParameterizedType) {
            type.actualTypeArguments[0] as Class<*>
        } else {
            //如果没有指定泛型参数，则默认使用BaseModel
            BaseRepository::class.java
        }
        try {
            mRepository = modelClass.newInstance() as M
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: InstantiationException) {
            e.printStackTrace()
        }
        return mRepository
    }

    /**
     * 请求
     *
     * @param isLoading 进度条
     * @param onError 错误回调
     * @param block 请求的主函数体，onSuccess方法
     */
    protected fun launch(
        isLoading: Boolean = false,
        onError: ((Throwable) -> Unit)? = null,
        block: suspend CoroutineScope.() -> Unit
    ) {
        viewModelScope.launch{
            try {
                //请求开始
                if (isLoading) loadingLiveData.value = true
                block()
            }catch (e: Exception){
                //错误回调
                onError?.invoke(e) ?: AnyLayer.toast()
                    .duration(2000)
                    .icon(R.drawable.ic_fail)
                    .message(e.message.toString())
                    .textColorInt(Color.WHITE)
                    .backgroundResource(R.drawable.shape_dialog_bg_loading)
                    .gravity(Gravity.CENTER)
                    .show()
            }finally {
                //请求结束
                if (isLoading) loadingLiveData.value = false
            }
        }
    }

    fun loadingView(): LiveData<Boolean> =
        loadingLiveData

    override fun onCleared() {
        super.onCleared()
    }
}