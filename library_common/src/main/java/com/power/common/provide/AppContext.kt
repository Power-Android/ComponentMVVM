package com.power.common.provide

import android.app.Application
import android.content.Context
import androidx.viewbinding.BuildConfig
import com.blankj.utilcode.util.CrashUtils
import com.blankj.utilcode.util.ToastUtils
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.xxjy.common.http.HttpManager

object AppContext {
    private lateinit var application: Context

    init {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context: Context?, _: RefreshLayout? ->
            ClassicsHeader(context) //.setTimeFormat(new DynamicTimeFormat("更新于 %s"));
        }
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator { context: Context?, _: RefreshLayout? ->
            ClassicsFooter(context).setFinishDuration(0) //.setDrawableSize(20);
        }
    }

    fun init(context: Context) {
        application = context
        initSDK()
    }

    fun context(): Context {
        return application
    }

    private fun initSDK() {
        if (BuildConfig.DEBUG) {
            CrashUtils.init { crashInfo: CrashUtils.CrashInfo? ->
                ToastUtils.showLong(
                    "崩溃日志已存储至目录！"
                )
            }
        }
        //网络请求Rxhttp
        HttpManager.init(application as Application)
    }
}