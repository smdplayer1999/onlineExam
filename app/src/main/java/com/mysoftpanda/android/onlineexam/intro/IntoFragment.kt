package com.mysoftpanda.android.onlineexam.intro

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.mysoftpanda.android.onlineexam.R
import kotlinx.android.synthetic.main.into_pages.*

class IntoFragment : Fragment(R.layout.into_pages) {



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val bundle = requireArguments()
        val data = bundle.getSerializable("DATA") as IntoData

        text_header.text = data.header
        text_info.text = data.text
        image_background.setImageResource(data.background)
        image.setImageResource(data.image)
    }


}