package com.rightpoint.experiments.demo.component

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squareup.coordinators.Coordinators
import kotlinx.android.synthetic.main.include_email_input.*

class ForgotPasswordFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_forgot_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Bind views here
        Coordinators.bind(emailLayout) {
            TextInput(
                lifecycleOwner = this,
                model = TextInput.Model(EmailValidator()),
                emptyTextError = "Please enter your email address",
                invalidTextError = "This is not a valid email address"
            )
        }
    }
}