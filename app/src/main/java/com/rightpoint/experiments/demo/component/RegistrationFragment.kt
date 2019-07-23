package com.rightpoint.experiments.demo.component

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squareup.coordinators.Coordinators
import kotlinx.android.synthetic.main.include_email_input.*

class RegistrationFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_registration, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Bind views here
        Coordinators.bind(emailLayout) {
            TextInput(this, TextInput.Model(EmailValidator()))
        }
    }
}