package com.herb2sy.lib.utils

import android.app.Activity
import android.content.Context
import android.view.WindowManager

object Utils{
    fun getScreenWidth(context: Context): Int {
        val systemService = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        return systemService.defaultDisplay.width
    }
    fun getScreenHeight(context: Context): Int {
        val systemService = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        return systemService.defaultDisplay.height
    }

    fun showDialog(context: Context?,ss:Float){
        val activity = context as Activity
        val lp = activity.window.attributes
        lp.alpha = ss
        activity.window.attributes = lp
        activity.window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
    }
}