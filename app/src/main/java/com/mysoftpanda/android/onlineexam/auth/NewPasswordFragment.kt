package com.mysoftpanda.android.onlineexam.auth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.mysoftpanda.android.onlineexam.R
import com.mysoftpanda.android.onlineexam.data.retrofit.newPassword
import com.mysoftpanda.android.onlineexam.data.retrofit.resend
import com.mysoftpanda.android.onlineexam.data.retrofit.showMessage
import com.mysoftpanda.android.onlineexam.utils.*
import kotlinx.android.synthetic.main.fragment_new_password.*

class NewPasswordFragment : Fragment(R.layout.fragment_new_password) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        animateSlide()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loader_progress.gone()
        clickHandler()
        cetMyCode.setOnCodeChangedListener { (code, completed) ->
            if (completed) {
                loader_progress.visible()
                newPassword(password.text.toString(), code) {
                    loader_progress.gone()
                    it.onData { next(LoginFragment()) }.onMessageData(this::showMessage)
                }
            }
        }
    }

    private fun clickHandler() {
        back_arrow.setOnClickListener { onBackPressed() }
        resend_code.setOnClickListener {
            loader_progress.visible()
            resend {
                loader_progress.gone()
                it.onMessageData(this::showMessage)
            }
        }
    }


}
