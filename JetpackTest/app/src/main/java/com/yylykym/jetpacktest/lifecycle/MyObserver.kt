package com.yylykym.jetpacktest.lifecycle

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner

class MyObserver : LifecycleEventObserver {
    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_CREATE -> Log.d("MyObserver", "activityStart")
            Lifecycle.Event.ON_STOP -> Log.d("MyObserver", "activityStop")
            else -> Log.d("MyObserver", "nothing")
        }
    }


}