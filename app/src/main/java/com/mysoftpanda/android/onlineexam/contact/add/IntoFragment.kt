package com.mysoftpanda.android.onlineexam.contact.add

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.mysoftpanda.android.onlineexam.R
import com.mysoftpanda.android.onlineexam.data.local.LocalStorage
import com.mysoftpanda.android.onlineexam.utils.animateSlide
import kotlinx.android.synthetic.main.item_contact.*


class IntoFragment : Fragment(R.layout.item_contact) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val bundle = requireArguments()
        LocalStorage.instance.colorCard=bundle.getInt("position").toString()
        when (bundle.getInt("position")) {

            1 -> { card_layout.setBackgroundResource(R.drawable.card_black) }
            2 -> { card_layout.setBackgroundResource(R.drawable.card_yellow)}
            3 -> { card_layout.setBackgroundResource(R.drawable.card_red)}
            4 -> {card_layout.setBackgroundResource(R.drawable.card_green) }
            5 -> { card_layout.setBackgroundResource(R.drawable.card_white)}
            else -> { card_layout.setBackgroundResource(R.drawable.card_blue)}
        }
    }


}