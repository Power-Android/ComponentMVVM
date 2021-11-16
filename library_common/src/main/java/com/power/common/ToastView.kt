package com.power.common

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.Gravity
import androidx.annotation.MainThread
import per.goweii.anylayer.AnyLayer

/**
 * @author power
 * @date 2021/8/27 4:11 下午
 * @project ComponentMVVM
 * @description:
 */
class ToastView(context: Context) {

    private var toastView = AnyLayer.toast(context)
        .duration(2000)
        .textColorInt(Color.WHITE)
        .backgroundResource(R.drawable.shape_dialog_bg_loading)
        .gravity(Gravity.CENTER)

    fun toast(
        msg: String,
        isSuccess: Boolean = false,
        isError: Boolean = false
    ) {
        toastView
            .icon(
                when {
                    isSuccess ->
                        R.drawable.ic_success
                    isError ->
                        R.drawable.ic_fail
                    else -> 0 //不显示icon
                }
            )
            .message(msg)
            .show()
    }

    /**
     * 单例 用于非activity内调用
     */
    companion object {
        @SuppressLint("StaticFieldLeak")
        private lateinit var mInstance: ToastView

        @MainThread
        fun get(context: Context): ToastView {
            mInstance =
                if (::mInstance.isInitialized)
                    mInstance
                else
                    ToastView(context)
            return mInstance
        }
    }
}