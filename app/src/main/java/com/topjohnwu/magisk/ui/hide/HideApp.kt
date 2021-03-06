package com.topjohnwu.magisk.ui.hide

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import com.topjohnwu.magisk.core.utils.currentLocale
import com.topjohnwu.magisk.ktx.getLabel

class HideTarget(line: String) {
    val packageName: String
    val process: String
    val uid: Int

    init {
        val split = line.split(Regex("\\|"), 3)
        uid = split[0].toInt()
        packageName = split[1]
        process = split.getOrElse(2) { packageName }
    }
}

class HideAppInfo(info: ApplicationInfo, pm: PackageManager)
    : ApplicationInfo(info), Comparable<HideAppInfo> {

    val label = info.getLabel(pm)
    val iconImage: Drawable = info.loadIcon(pm)

    override fun compareTo(other: HideAppInfo) = comparator.compare(this, other)

    companion object {
        private val comparator = compareBy<HideAppInfo>(
            { it.label.toLowerCase(currentLocale) },
            { it.packageName }
        )
    }
}

data class HideProcessInfo(
    val name: String,
    val packageName: String,
    val uid: Int,
    val isHidden: Boolean
)

class HideAppTarget(
    val info: HideAppInfo,
    val processes: List<HideProcessInfo>
) : Comparable<HideAppTarget> {
    override fun compareTo(other: HideAppTarget) = compareValuesBy(this, other) { it.info }
}
