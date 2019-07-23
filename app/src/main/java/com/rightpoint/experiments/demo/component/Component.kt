package com.rightpoint.experiments.demo.component

import android.view.View
import androidx.annotation.CallSuper
import androidx.arch.core.util.Function
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import com.squareup.coordinators.Coordinator

abstract class Component<S : Component.State, A : Component.Action, M : Function<A, S>>(
    private val lifecycleOwner: LifecycleOwner,
    private val model: M
) : Coordinator(), Observer<S> {
    protected val actions = MutableLiveData<A>()

    @CallSuper
    override fun attach(view: View) {
        super.attach(view)
        Transformations.map(actions, model).observe(lifecycleOwner, this)
    }

    interface Action
    interface State
}