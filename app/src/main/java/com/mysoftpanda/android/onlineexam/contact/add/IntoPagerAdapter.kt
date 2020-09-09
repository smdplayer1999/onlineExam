package com.mysoftpanda.android.onlineexam.contact.add

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mysoftpanda.android.onlineexam.utils.putArguments

class IntoPagerAdapter( activity: FragmentActivity) :
    FragmentStateAdapter(activity) {


    override fun getItemCount(): Int = 6
    override fun createFragment(position: Int): Fragment = IntoFragment()

        .putArguments {
            putInt("position",position)
        }
}