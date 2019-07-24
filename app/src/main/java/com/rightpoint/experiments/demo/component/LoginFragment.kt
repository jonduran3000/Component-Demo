package com.rightpoint.experiments.demo.component

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.squareup.coordinators.Coordinators.bind
import kotlinx.android.synthetic.main.include_email_input.*

class LoginFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Bind views here
        val input = TextInput(
            lifecycleOwner = this,
            model = TextInput.Model(EmailValidator()),
            emptyTextError = "Please enter your email address",
            invalidTextError = "This is not a valid email address"
        )
        input.state.observe(this, Observer {
            // You can observe the component's current state here
        })
        bind(emailLayout) { input }
    }
}