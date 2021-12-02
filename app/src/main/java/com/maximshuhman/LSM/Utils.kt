package com.maximshuhman.LSM

import android.app.Activity
import android.content.Intent


object Utils {
    private var sTheme = 0
    const val THEME_MATERIAL_LIGHT = 0
    const val THEME_MATERIAL_NIGHT = 1
    const val THEME_MATERIAL_PINK = 2
    fun changeToTheme(activity: Activity, theme: Int) {
        sTheme = theme
        activity.finish()
        onActivityCreateSetTheme(activity)
        activity.startActivity(Intent(activity, activity.javaClass))

    }

    fun onActivityCreateSetTheme(activity: Activity) {
        when (sTheme) {
            /*THEME_MATERIAL_LIGHT -> activity.setTheme(R.style.Day)
            THEME_MATERIAL_NIGHT -> activity.setTheme(R.style.Night)
            THEME_MATERIAL_PINK -> activity.setTheme(R.style.Pink)*/
        }
    }
}