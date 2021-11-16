package com.power.common.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.blankj.utilcode.util.BarUtils
import com.power.common.LoadingView
import com.power.common.ToastView

/**
 * @author power
 * @date 2021/8/13 1:12 下午
 * @project ComponentMVVM
 * @description:
 */
abstract class BaseActivity : FragmentActivity() {

    private lateinit var mLoadingView: LoadingView
    private lateinit var mToastView: ToastView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        ARouter.getInstance().inject(this)  // Start auto inject.
        BarUtils.setStatusBarLightMode(this, true)
        mLoadingView = LoadingView(this)
        mToastView = ToastView(this)
    }

    /**
     * @param view toolbar设置margin
     * @param isDark 默认为深色
     * 设置透明状态栏 -设置状态栏的margin -设置状态栏字体颜色（默认为深色字体）
     */
    fun setTransparentStatusBar(view: View? = null, isDark: Boolean = true) {
        BarUtils.transparentStatusBar(this)
        view?.let { BarUtils.addMarginTopEqualStatusBarHeight(it) }
        BarUtils.setStatusBarLightMode(this, isDark)
    }

    /**
     * 展示读取的 dialog
     */
    open fun showLoading() =
        mLoadingView.showLoading()

    /**
     * 隐藏读取的 dialog
     */
    open fun dismissLoading() =
        mLoadingView.dismissLoading()

    open fun showToast(
        msg: String?,
        isSuccess: Boolean = false,
        isError: Boolean = false
    ) {
        if (msg.isNullOrEmpty()) return
        mToastView.toast(msg, isSuccess, isError)
    }
}