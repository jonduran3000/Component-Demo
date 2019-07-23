package com.rightpoint.experiments.demo.component

import android.util.Patterns

class EmailValidator : TextInput.Validator {
    override fun isValid(text: CharSequence): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(text).matches()
    }
}