package com.mysoftpanda.android.onlineexam.intro

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mysoftpanda.android.onlineexam.utils.putArguments

class IntoPagerAdapter(private val data: List<IntoData>, activity: FragmentActivity) :
    FragmentStateAdapter(activity) {
//    private var listener: ((Int) -> Unit)? = null

    override fun getItemCount(): Int = data.size
    override fun createFragment(position: Int): Fragment = IntoFragment()
        /*.apply {
            listener?.let { this.setButtonClickListener(it) }
        }*/
        .putArguments {
            putSerializable("DATA", data[position])
        }
}