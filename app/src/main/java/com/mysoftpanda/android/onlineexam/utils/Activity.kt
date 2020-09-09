package com.mysoftpanda.android.onlineexam.utils

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.google.android.material.transition.MaterialSharedAxis


fun Fragment.showToast(toast:String){
Toast.makeText(requireContext(),toast,Toast.LENGTH_SHORT).show()
}





