package com.mysoftpanda.android.onlineexam.contact

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.mysoftpanda.android.onlineexam.R
import com.mysoftpanda.android.onlineexam.data.local.LocalStorage
import com.mysoftpanda.android.onlineexam.data.retrofit.*
import com.mysoftpanda.android.onlineexam.utils.*

import kotlinx.android.synthetic.main.fragment_verify_card.*


class CardVerifyFragment:Fragment(R.layout.fragment_verify_card) {
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
                verifyCard(code) {
                    loader_progress.gone()
                    it.onData {
                        next(ContactFragment())
                        LocalStorage.instance.added="1"
                    }.onMessageData(this::showMessage)
                }
            }
        }
//        resend_code.setOnClickListener {
//            loader_progress.visible()
//            resend {
//                loader_progress.gone()
//                it.onMessageData(this::showMessage)
//            }
//        }
    }
}