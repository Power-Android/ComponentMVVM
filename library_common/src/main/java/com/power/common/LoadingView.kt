package com.power.common

import android.animation.Animator
import android.annotation.SuppressLint
import android.content.Context
import android.view.Gravity
import android.view.View
import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import per.goweii.anylayer.AnyLayer
import per.goweii.anylayer.Layer
import per.goweii.anylayer.dialog.DialogLayer
import per.goweii.anylayer.utils.AnimatorHelper

/**
 * @author power
 * @date 2021/8/27 2:54 下午
 * @project ComponentMVVM
 * @description:
 */
class LoadingView(context: Context) {
    private var keyBackCancelable = true
    private var outSideCancelable = false

    private var mLoadingDialog = AnyLayer.dialog(context)
        .contentView(R.layout.loading_layout)
        .gravity(Gravity.CENTER)
        .contentAnimator(object : Layer.AnimatorCreator {
            override fun createInAnimator(target: View): Animator {
                return AnimatorHelper.createAlphaInAnim(target)
            }

            override fun createOutAnimator(target: View): Animator {
                return AnimatorHelper.createAlphaOutAnim(target)
            }
        })
        .cancelableOnClickKeyBack(keyBackCancelable)
        .cancelableOnTouchOutside(outSideCancelable)

    fun setKeyBackCancelable(cancelable: Boolean): LoadingView {
        keyBackCancelable = cancelable
        mLoadingDialog.cancelableOnClickKeyBack(keyBackCancelable)
        return this
    }

    fun setOutsideCancelable(cancelable: Boolean): LoadingView{
        outSideCancelable = cancelable
        mLoadingDialog.cancelableOnTouchOutside(outSideCancelable)
        return this
    }

    /**
     * 显示loading
     */
    fun showLoading(): LoadingView {
        mLoadingDialog.show()
        return this
    }

    /**
     * 隐藏loading
     */
    fun dismissLoading(): LoadingView {
        mLoadingDialog.dismiss()
        return this
    }

    /**
     * 单例 用于非activity内调用
     */
    companion object {
        @SuppressLint("StaticFieldLeak")
        private lateinit var mInstance: LoadingView

        @MainThread
        fun get(context: Context): LoadingView {
            mInstance =
                if (::mInstance.isInitialized)
                    mInstance
                else
                    LoadingView(context)
            return mInstance
        }
    }

}