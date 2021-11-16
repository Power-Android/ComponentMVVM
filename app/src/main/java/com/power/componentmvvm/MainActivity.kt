package com.power.componentmvvm

import android.view.View
import androidx.lifecycle.lifecycleScope
import com.power.common.base.BindingActivity
import com.power.componentmvvm.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : BindingActivity<ActivityMainBinding, MainViewModel>() {

    override fun initView() {
        mViewModel.osOver()
    }

    override fun initListener() {

    }

    override fun onViewClicked(view: View?) {

    }

    override fun dataObservable() {
        mViewModel.osLiveData.observe(this){

        }
    }
}