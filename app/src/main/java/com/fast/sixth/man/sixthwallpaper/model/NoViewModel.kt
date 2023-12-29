package com.fast.sixth.man.sixthwallpaper.model

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.fast.sixth.man.sixthwallpaper.base.BaseViewModel
import com.fast.sixth.man.sixthwallpaper.view.SettingActivity

class NoViewModel:BaseViewModel() {
    val jumpToMain = MutableLiveData<Boolean>()

}