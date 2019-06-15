package com.herb2sy.lib

import android.app.Application
import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.DiskLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy

class LibManager private constructor(){

    lateinit var mContext:Context
    lateinit var application:Application

    companion object {
        val instance:LibManager by lazy { LibManager() }
    }

    /**
     * 程序启动时初始化
     */
    fun init(context: Context,application: Application){
        mContext = context
        this.application = application
        initArouter() //路由
        initLogger()//日志
    }

    private fun initLogger() {
        if(BuildConfig.DEBUG){ //如果在debug模式下
            // 打印日志,默认关闭
            ARouter.openLog()
            // 开启调试模式，默认关闭(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
            ARouter.openDebug()
            // 打印日志的时候打印线程堆栈
            ARouter.printStackTrace()
        }
        ARouter.init(application)

    }

    private fun initArouter() {
//         val build = PrettyFormatStrategy.newBuilder().showThreadInfo(true).tag("herb2sy").build()
        Logger.addLogAdapter(AndroidLogAdapter())
//         Logger.addLogAdapter(DiskLogAdapter())

    }


}
