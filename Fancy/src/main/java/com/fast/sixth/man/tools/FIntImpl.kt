package com.fast.sixth.man.tools

import kotlin.reflect.KProperty

/**
 * Date：2024/1/19
 * Describe:
 */
class FIntImpl(val def: Int = 0, val tag: String = "Scenery") {
    operator fun getValue(me: Any?, p: KProperty<*>): Int {
        return spFancy.getInt("$tag-${p.name}", def)
    }

    operator fun setValue(me: Any?, p: KProperty<*>, value: Int) {
        spFancy.edit().putInt("$tag-${p.name}", value).apply()
    }
}