package com.mysoftpanda.android.onlineexam.auth

//import android.os.Bundle
//import android.view.View
//import androidx.fragment.app.Fragment
//import com.mysoftpanda.android.onlineexam.R
//import com.mysoftpanda.android.onlineexam.utils.animateSlide
//import com.mysoftpanda.android.onlineexam.utils.extensions.animateView
//import com.mysoftpanda.android.onlineexam.utils.extensions.didClickButton
//import com.mysoftpanda.android.onlineexam.utils.next
//import kotlinx.android.synthetic.main.fragment_start.*

//class StartFragment : Fragment(R.layout.fragment_start) {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        animateSlide()
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        clickHandler()
//        animateView(image_smd)
//    }
//
//    private fun clickHandler() {
//        log_in.setOnClickListener {
//            didClickButton(log_in)
//            next(LoginFragment(), "s")
//        }
//        register_log.setOnClickListener {
//            didClickButton(register_log)
//            next(RegisterFragment(), "s")
//        }
//    }
//}