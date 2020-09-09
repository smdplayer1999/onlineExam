package com.mysoftpanda.android.onlineexam.contact.add

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.mysoftpanda.android.onlineexam.R
import com.mysoftpanda.android.onlineexam.contact.CardVerifyFragment
import com.mysoftpanda.android.onlineexam.contact.utils.ExpiryDateTextWatcher
import com.mysoftpanda.android.onlineexam.data.entities.AddCardData
import com.mysoftpanda.android.onlineexam.data.local.LocalStorage
import com.mysoftpanda.android.onlineexam.data.retrofit.addCard
import com.mysoftpanda.android.onlineexam.data.retrofit.showMessage
import com.mysoftpanda.android.onlineexam.utils.*
import com.mysoftpanda.android.onlineexam.utils.extensions.didClickButton
import kotlinx.android.synthetic.main.fragment_add_card.*
import kotlinx.android.synthetic.main.fragment_add_card.indicator
import kotlinx.android.synthetic.main.fragment_add_card.pager

class CardAddFragment : Fragment(R.layout.fragment_add_card) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        animateSlide()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadView()
        clickListener()
    }

    private fun loadView() {
        loader_progress.gone()
        var adapter = IntoPagerAdapter(requireActivity())
        pager.adapter = adapter
        indicator.setViewPager2(pager)
    }

    private fun clickListener() {
        date_card.addTextChangedListener(ExpiryDateTextWatcher())
        card_add.setOnClickListener(this::cardAdd)
    }

    private fun cardAdd(it: View?) {
        when {
            date_card.text.length < 4 -> {
                didClickButton(date_card)
                date_card.setError("Date must be MM/YY")
            }
            pan_card.textWithoutSeparator.length != 16 -> {
                didClickButton(pan_card)
            }
            else -> {
                LocalStorage.instance.cardPan = pan_card.textWithoutSeparator
                LocalStorage.instance.added = "0"
                val newCard = AddCardData(
                    pan_card.textWithoutSeparator,
                    numberSeperator(date_card.text.toString()),
                    name_card.text.toString(),
                    LocalStorage.instance.colorCard.toInt()
                )
                loader_progress.visible()
                addCard(newCard) {
                    loader_progress.gone()
                    it.onData { next(CardVerifyFragment(),"cad") }.onMessageData(this::showMessage)
                }
            }
        }
    }



}