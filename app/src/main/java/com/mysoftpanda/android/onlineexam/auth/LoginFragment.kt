package com.mysoftpanda.android.onlineexam.auth


import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View

import androidx.fragment.app.Fragment
import com.mysoftpanda.android.onlineexam.R
import com.mysoftpanda.android.onlineexam.contact.ContactFragment
import com.mysoftpanda.android.onlineexam.data.retrofit.login
import com.mysoftpanda.android.onlineexam.data.retrofit.saveData
import com.mysoftpanda.android.onlineexam.data.retrofit.showMessage
import com.mysoftpanda.android.onlineexam.utils.*
import com.mysoftpanda.android.onlineexam.utils.extensions.didClickButton
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.loader_progress
import kotlinx.android.synthetic.main.fragment_login.password
import kotlinx.android.synthetic.main.fragment_login.phoneNumber
import kotlinx.android.synthetic.main.fragment_login.phoneNumber_card

class LoginFragment : Fragment(R.layout.fragment_login) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        animateSlide()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loader_progress.gone()

        clickHandler()

    }

    private fun clickHandler() {
        show_button.setOnClickListener {
            if (password.transformationMethod != HideReturnsTransformationMethod.getInstance()){
                password.transformationMethod = HideReturnsTransformationMethod.getInstance()
                show_button.setImageResource(R.drawable.ic_twotone_visibility_off)}
            else{
                password.transformationMethod = PasswordTransformationMethod.getInstance()
                show_button.setImageResource(R.drawable.ic_twotone_visibility)}
        }
        forgot_password.setOnClickListener { next(ForgotFragment(), "l") }
        register_log.setOnClickListener { next(RegisterFragment(),"l") }
//        back_arrow.setOnClickListener { onBackPressed() }
        log_in.setOnClickListener(this::logIn)
    }

    private fun logIn(view: View) {

        when {

            phoneNumber.text.length != 9 -> {
                didClickButton(phoneNumber_card)
                phoneNumber.error = "+998${phoneNumber.text} is not phone number"
            }
            password.text.length < 6 || password.text.length > 20 -> {
                didClickButton(password)

            }
            else -> {
                didClickButton(view)
                loader_progress.visible()
                login(phoneNumber.text.toString(), password.text.toString()) {
                    loader_progress.gone()
                    it.onData {
                        saveData(it.toString())
                        next(ContactFragment())
                    }.onMessageData(this::showMessage)
                }
            }
        }
    }


}