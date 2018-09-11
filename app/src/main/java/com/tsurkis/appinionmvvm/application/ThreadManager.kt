package com.tsurkis.appinionmvvm.application

import com.tsurkis.appinionmvvm.custom.MainThreadExecutor
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class ThreadManager(
        val backThread: Executor = Executors.newSingleThreadExecutor(),
        val mainThread: Executor = MainThreadExecutor()
)

