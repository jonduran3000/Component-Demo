package com.rightpoint.experiments.demo.component

import androidx.fragment.app.Fragment

enum class Screen(val screenName: String, val newInstance: () -> Fragment) {
    LOGIN("Login", { LoginFragment() }),
    FORGOT_PASSWORD("Forgot Password", { ForgotPasswordFragment() }),
    REGISTRATION("Registration", { RegistrationFragment() }),
    PROFILE("Profile", { ProfileFragment() })
}