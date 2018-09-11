package com.tsurkis.appinionmvvm.custom

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor

class MainThreadExecutor : Executor {
    private val mainThread: Handler = Handler(Looper.getMainLooper())

    override fun execute(command: Runnable?) {
        mainThread.post(command)
    }

}