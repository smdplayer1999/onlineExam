package com.mysoftpanda.android.onlineexam.auth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.mysoftpanda.android.onlineexam.R
import com.mysoftpanda.android.onlineexam.contact.ContactFragment
import com.mysoftpanda.android.onlineexam.data.retrofit.resend
import com.mysoftpanda.android.onlineexam.data.retrofit.saveData
import com.mysoftpanda.android.onlineexam.data.retrofit.showMessage
import com.mysoftpanda.android.onlineexam.data.retrofit.verify
import com.mysoftpanda.android.onlineexam.utils.*
import kotlinx.android.synthetic.main.fragment_verify.*

class VerifyFragment : Fragment(R.layout.fragment_verify) {
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
        cetMyCode.setOnCodeChangedListener { (code, completed) ->
            if (completed) {
                loader_progress.visible()
                verify(code) {
                    loader_progress.gone()
                    it.onData { token ->
                        saveData(token.toString())
                        next(ContactFragment())
                    }.onMessageData(this::showMessage)
                }
            }
        }
        resend_code.setOnClickListener {
            loader_progress.visible()
            resend {
                loader_progress.gone()
                it.onMessageData(this::showMessage)
            }
        }
    }



}
