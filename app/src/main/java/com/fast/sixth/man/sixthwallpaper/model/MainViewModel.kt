package com.fast.sixth.man.sixthwallpaper.model

import androidx.lifecycle.MutableLiveData
import com.fast.sixth.man.sixthwallpaper.base.BaseViewModel

class MainViewModel: BaseViewModel() {
    val jumpToSetting = MutableLiveData<String>()
}