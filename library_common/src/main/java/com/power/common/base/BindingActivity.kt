package com.power.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

/**
 * @author power
 * @date 2021/8/13 4:32 下午
 * @project ComponentMVVM
 * @description:
 */
abstract class BindingActivity<V : ViewBinding, VM : BaseViewModel<*>> : BaseActivity() {
    protected lateinit var mBinding: V
    protected lateinit var mViewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        initView()
        initListener()
        //页面事件监听的方法，一般用于ViewModel层转到View层的事件注册
        dataObservable()
    }

    /**
     * 初始化viewModel
     */
    open fun initViewModel() {
        val type = javaClass.genericSuperclass
        if (type is ParameterizedType) {
            mBinding = (type.actualTypeArguments[0] as Class<V>)
                .getMethod("inflate", LayoutInflater::class.java)
                .invoke(null, layoutInflater) as V
            setContentView(mBinding.root)
        }
        val modelClass = if (type is ParameterizedType) {
            type.actualTypeArguments[1] as Class<VM>
        } else {
            //如果没有指定泛型参数，则默认使用BaseViewModel
            BaseViewModel::class.java as Class<VM>
        }
        mViewModel = ViewModelProvider(this).get(modelClass)

        //让ViewModel绑定View的生命周期
        lifecycle.addObserver(mViewModel)
    }

    /**
     * 页面数据初始化方法
     */
    protected abstract fun initView()

    /**
     * 初始化监听
     */
    protected abstract fun initListener()

    /**
     * @param view 处理点击事件
     */
    protected abstract fun onViewClicked(view: View?)

    /**
     * 处理网络请求回调
     */
    protected abstract fun dataObservable()
}