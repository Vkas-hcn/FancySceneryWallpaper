package com.fast.sixth.man.open

import com.fast.sixth.man.tools.spFancy
import kotlin.reflect.KProperty

/**
 * Date：2024/1/18
 * Describe:
 */
class FSSssImpl(val def: String = "", val tag: String = "Fancy") {
    operator fun getValue(me: Any?, p: KProperty<*>): String {
        return spFancy.getString("$tag-${p.name}", def) ?: def
    }

    operator fun setValue(me: Any?, p: KProperty<*>, value: String) {
        spFancy.edit().putString("$tag-${p.name}", value).apply()
    }
}