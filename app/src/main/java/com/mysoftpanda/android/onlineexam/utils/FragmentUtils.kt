package com.mysoftpanda.android.onlineexam.utils

import androidx.fragment.app.Fragment
import com.google.android.material.transition.MaterialSharedAxis
import com.mysoftpanda.android.onlineexam.R

fun Fragment.next(fragment: Fragment){
fragmentManager?.beginTransaction()
//?.setCustomAnimations(R.anim.slide_in,R.anim.fade_out)
?.replace(R.id.layoutFragment, fragment)

?.commit()
}
fun Fragment.next(fragment: Fragment,name:String){
    fragmentManager?.beginTransaction()
//        ?.setCustomAnimations(R.anim.slide_in,R.anim.fade_out)
        ?.replace(R.id.layoutFragment, fragment)
        ?.addToBackStack(name)
        ?.commit()
}
fun Fragment.last(fragment: Fragment){
    fragmentManager?.beginTransaction()
        ?.replace(R.id.layoutFragment, fragment)
        ?.addToBackStack(null)
        ?.commit()
}

fun Fragment.animateSlide(){
    val backward = MaterialSharedAxis(MaterialSharedAxis.Z, false)
    val forward = MaterialSharedAxis(MaterialSharedAxis.Z, true)
    reenterTransition = backward
    exitTransition = forward
    enterTransition = forward
    returnTransition = backward

}
fun Fragment.onBackPressed(){
    requireActivity().onBackPressed()
}

