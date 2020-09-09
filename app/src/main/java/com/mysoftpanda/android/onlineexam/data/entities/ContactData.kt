package com.mysoftpanda.android.onlineexam.data.entities

import androidx.recyclerview.widget.DiffUtil

data class ContactData(
    val id: Int = 0,
    var lastName: String,
    var firstName: String,
    var phoneNumber: String


) {
    /*   companion object{
           val ITEM_CALLBACK = object : DiffUtil.ItemCallback<ContactData>() {
               override fun areItemsTheSame(oldItem: ContactData, newItem: ContactData) = oldItem.id == newItem.id
               override fun areContentsTheSame(oldItem: ContactData, newItem: ContactData) = oldItem.name == newItem.name && oldItem.phoneNumber == newItem.phoneNumber
           }
       }*/
}