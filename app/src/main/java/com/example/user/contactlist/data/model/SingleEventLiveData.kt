package com.example.user.contactlist.data.model


import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer

import java.util.concurrent.atomic.AtomicBoolean

/**
 * Custom wrapper for [MutableLiveData] that calls observer only one time
 * @param <T>
</T> */
class SingleEventLiveData<T> : MutableLiveData<T>() {

    private val mPending = AtomicBoolean(false)

    override fun setValue(value: T) {
        mPending.set(true)
        super.setValue(value)
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<T>) {
        super.observe(owner, Observer { t ->
            if (mPending.compareAndSet(true, false)) {
                observer.onChanged(t)
            }
        })
    }
}