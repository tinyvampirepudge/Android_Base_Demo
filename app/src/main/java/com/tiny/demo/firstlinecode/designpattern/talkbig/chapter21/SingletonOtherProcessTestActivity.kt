package com.tiny.demo.firstlinecode.designpattern.talkbig.chapter21

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Process
import android.util.Log
import com.tiny.demo.firstlinecode.R
import com.tiny.demo.firstlinecode.common.utils.ProcessUtil
import com.tinytongtong.tinyutils.LogUtils
import com.tinytongtong.tinyutils.ThreadUtils

/**
 * @Description: 单例模式测试，其他进程
 *
 * @Author wangjianzhou@qding.me
 * @Date 2019-08-23 12:07
 * @Version
 */
class SingletonOtherProcessTestActivity : AppCompatActivity() {
    val TAG = this.javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_singleton_other_process_test)

        //进程名称
        val processName = ProcessUtil.getProcessNameByCtx(this, Process.myPid())
        LogUtils.e(TAG, "processName222:" + processName);

        Log.e(TAG, "SafeDoubleCheckedLocking.getInstance().getValue()222:" + SafeDoubleCheckedLocking.getInstance().value)
        Log.e(TAG, "InstanceFactory.getInstance().getValue()222:" + InstanceFactory.getInstance().value)
    }
}
