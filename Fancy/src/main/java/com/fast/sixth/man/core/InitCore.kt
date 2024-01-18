package com.fast.sixth.man.core

import android.content.Context
import com.anythink.core.api.ATDebuggerConfig
import com.anythink.core.api.ATSDK
import com.anythink.network.pangle.PangleATConst
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Date：2024/1/17
 * Describe:
 */
class InitCore(val context: Context) {

    fun startInit() {
        //todo delete
        CoroutineScope(Dispatchers.IO).launch {
            runCatching {
                AdvertisingIdClient.getAdvertisingIdInfo(context).apply {
                    val mGaidInfo = id ?: "53dd390b-763a-426a-93ed-46953d88fbc0"
                    ATSDK.setDebuggerConfig(
                        context,
                        mGaidInfo,
                        ATDebuggerConfig.Builder(PangleATConst.DEBUGGER_CONFIG.Pangle_NETWORK)
                            .build()
                    )
                    ATSDK.setNetworkLogDebug(true)
                    ATSDK.init(context, "a65a7acc732871", "a6a7b489ca4c9ee069041e6bf9332dd4c")
                }
            }
        }
        // todo open
//        ATSDK.init(context, "a65a7acc732871", "a6a7b489ca4c9ee069041e6bf9332dd4c")
    }

}