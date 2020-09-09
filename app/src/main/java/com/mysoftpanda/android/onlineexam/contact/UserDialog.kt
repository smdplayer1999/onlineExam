package com.mysoftpanda.android.onlineexam.contact

//import android.content.Context
//import android.view.LayoutInflater
//import androidx.appcompat.app.AlertDialog
//import com.mysoftpanda.android.onlineexam.R
//import com.mysoftpanda.android.onlineexam.data.entities.ContactReturnedData
//import com.mysoftpanda.android.onlineexam.utils.SingleBlock
//import kotlinx.android.synthetic.main.edit_item_dialog.view.*
//
//
//
//
//class UserDialog(context: Context, actionName:String) : AlertDialog(context) {
//
//    private val contentView = LayoutInflater.from(context).inflate(R.layout.edit_item_dialog, null, false)
//    private var listener: SingleBlock<ContactReturnedData>? = null
//    private var id1=0
//    private var ContactReturnedData: ContactReturnedData? = null
//
//
//    init {
//        setView(contentView)
//        setButton(BUTTON_POSITIVE, actionName) { _, _ ->
//
//            val data = ContactReturnedData
//            if (data != null){
//                data.lastName=contentView.last_name.text.toString()
//                data.firstName = contentView.first_name.text.toString()
//                data.phoneNumber = contentView.phoneNumber.text.toString()
//
//                listener?.invoke(data)
//            }
//        }
//        setTitle(actionName)
//        contentView.apply {
//
//        }
//        setButton(BUTTON_NEGATIVE, "Cancel" ){_,_->}
//    }
//
//    fun setStudentData(ContactReturnedData: ContactReturnedData) = with(contentView) {
//        this@UserDialog.ContactReturnedData = ContactReturnedData
//        last_name.setText(ContactReturnedData.lastName)
//        first_name.setText(ContactReturnedData.firstName)
//        phoneNumber.setText(ContactReturnedData.phoneNumber)
//    }
//
//    fun setOnClickListener(block: SingleBlock<ContactReturnedData>) {
//        listener = block
//    }
//}