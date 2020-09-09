package com.mysoftpanda.android.onlineexam.auth


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.mysoftpanda.android.onlineexam.R
import com.mysoftpanda.android.onlineexam.data.retrofit.reset
import com.mysoftpanda.android.onlineexam.data.retrofit.showMessage
import com.mysoftpanda.android.onlineexam.utils.*
import com.mysoftpanda.android.onlineexam.utils.extensions.didClickButton
import kotlinx.android.synthetic.main.fragment_forgot.*

class ForgotFragment : Fragment(R.layout.fragment_forgot) {
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
        send_phone.setOnClickListener(this::send)
    }

    private fun send(view: View) {
        didClickButton(view)
        loader_progress.visible()
        reset(phoneNumber.text.toString()) {
            loader_progress.gone()
            it.onData { next(NewPasswordFragment()) }.onMessageData(this::showMessage)
        }
    }



}