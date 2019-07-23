package com.rightpoint.experiments.demo.component

import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import androidx.arch.core.util.Function
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class TextInput(
    lifecycleOwner: LifecycleOwner,
    model: Model
) : Component<TextInput.State, TextInput.Action, TextInput.Model>(lifecycleOwner, model) {
    private var layout: TextInputLayout? = null
    private var input: TextInputEditText? = null
    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            actions.postValue(Action.OnTextChange(p0))
        }
    }
    private val internalState = MutableLiveData<State>()
    val state: LiveData<State> = internalState

    override fun attach(view: View) {
        super.attach(view)
        layout = view as? TextInputLayout
        input = layout?.editText as? TextInputEditText
        input?.addTextChangedListener(textWatcher)
    }

    override fun detach(view: View) {
        super.detach(view)
        input?.removeTextChangedListener(textWatcher)
        input = null
        layout = null
    }

    override fun onChanged(state: State?) {
        internalState.postValue(state)
        layout?.error = when (state) {
            State.EmptyEmail -> "Please enter your email address"
            State.InvalidEmail -> "This is not a valid email address"
            else -> null
        }
    }

    sealed class Action : Component.Action {
        data class OnTextChange(val text: CharSequence?) : Action()
    }

    sealed class State : Component.State {
        object EmptyEmail : State()
        object InvalidEmail : State()
        object ValidEmail : State()
    }

    class Model(private val validator: Validator) : Function<Action, State> {
        override fun apply(input: Action?): State {
            return when(input) {
                is Action.OnTextChange -> validate(input.text)
                else -> State.EmptyEmail
            }
        }

        private fun validate(text: CharSequence?): State {
            return if (TextUtils.isEmpty(text)) {
                State.EmptyEmail
            } else {
                if (validator.isValid(text!!)) {
                    State.ValidEmail
                } else {
                    State.InvalidEmail
                }
            }
        }
    }

    interface Validator {
        fun isValid(text: CharSequence): Boolean
    }
}