package com.mysoftpanda.android.onlineexam.auth


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.mysoftpanda.android.onlineexam.R
import com.mysoftpanda.android.onlineexam.data.retrofit.register
import com.mysoftpanda.android.onlineexam.data.retrofit.showMessage
import com.mysoftpanda.android.onlineexam.utils.*
import com.mysoftpanda.android.onlineexam.utils.extensions.didClickButton
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : Fragment(R.layout.fragment_register) {
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
        back_arrow.setOnClickListener { onBackPressed() }
        sign_up.setOnClickListener(this::signUp)
    }

    private fun signUp(view: View) {

        when {
            first_name.text.length<3||password.text.length>15->{
                didClickButton(first_name)
                first_name.error = "first name length must be between 3 to 15"
            }
            last_name.text.length<3||password.text.length>15->{
                didClickButton(last_name)
                last_name.error = "last name length must be between 3 to 15"
            }
            phoneNumber.text.length!=9 -> {
                didClickButton(phoneNumber_card)
                phoneNumber.error = "+998${phoneNumber.text} is not phone number"
            }
            password.text.length<6||password.text.length>20->{
                didClickButton(password)
                password.error = "password length must be between 6 to 20"
            }
            verify_password.text.toString() != password.text.toString() -> {
                didClickButton(verify_password)
                verify_password.error = "password is not same"
            }
            else -> {
                didClickButton(view)
                loader_progress.visible()
                register(
                    phoneNumber.text.toString(),
                    password.text.toString(),
                    last_name.text.toString(),
                    first_name.text.toString()
                ) {
                    loader_progress.gone()
                    it.onData { next(VerifyFragment()) }
                        .onMessageData(this::showMessage)
                }
            }
        }
    }


}