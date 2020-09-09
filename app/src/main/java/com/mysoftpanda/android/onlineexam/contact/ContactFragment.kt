package com.mysoftpanda.android.onlineexam.contact

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.mysoftpanda.android.onlineexam.R
import com.mysoftpanda.android.onlineexam.contact.add.CardAddFragment
import com.mysoftpanda.android.onlineexam.data.retrofit.*
import com.mysoftpanda.android.onlineexam.utils.*
import com.mysoftpanda.android.onlineexam.utils.extensions.didClickButton
import kotlinx.android.synthetic.main.fragment_contact.*

class ContactFragment : Fragment(R.layout.fragment_contact) {
    private val adapter = ContactAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        animateSlide()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        customBottomBar.inflateMenu(R.menu.bottom_menu)

        loadData()
        loadNavigationView()
        clickHandler()

    }

    private fun loadData() {
        loader_progress.visible()
        contact_fr.setBackgroundColor(Color.TRANSPARENT)
//        contact_header.text = localStorage.phoneNumber
        list.adapter = adapter
        list.layoutManager = HorizontalLayoutManager(requireContext())
        cardLoader  {
            loader_progress.gone()
            image_card.gone()
            it.onData {
                if (it.data.isNullOrEmpty()){
                    image_card.visible()
                }else
                    image_card.gone()
                adapter.submitList(it.data)
            }.onMessageData(this::showMessage)
        }


    }



    private fun clickHandler() {

        image_card.setOnClickListener { next(CardAddFragment(),"cf") }
//        back_arrow.setOnClickListener { drawerLayout.openDrawer(GravityCompat.START) }
        add_contact.setOnClickListener {
            didClickButton(it)
            next(CardAddFragment(),"cf")
//            showAlertDialog()
        }
//        adapter.setOnItemDeleteListener {
//            val newContact = ContactData(it.id, it.firstName, it.lastName, it.phoneNumber)
//            deleteFromServer(newContact)
//        }
//        adapter.setOnItemEditListener { it ->
//            val contact = ContactReturnedData(it.id, it.phoneNumber, it.lastName, it.firstName)
//            val dialog = UserDialog(requireContext(), "Edit contact")
//            dialog.setStudentData(contact)
//            dialog.setOnClickListener {
//                updateInServer(it)
//            }
//            dialog.show()
//        }
    }








    private fun loadNavigationView() {
//        val headerView = navigationView.getHeaderView(0)
        val navigationMenu = customBottomBar.menu
        navigationMenu.findItem(R.id.log_out).setOnMenuItemClickListener {
            showToast("Logged out uccessfully!")
            localStorage.remember = "0"
            if (localStorage.added=="1"){
                requireActivity().finish()
            }else
            onBackPressed()
            true
        }
        navigationMenu.findItem(R.id.add_card).setOnMenuItemClickListener {
            didClickButton(add_contact)
            next(CardAddFragment(),"cf")
            true
        }
        navigationMenu.findItem(R.id.delete_account).setOnMenuItemClickListener {

            remove {
                it.onData {
                    localStorage.remember = "0"
                    showToast("Account deleted successfully!")
                    if (localStorage.added=="1"){
                        requireActivity().finish()
                    }else
                    onBackPressed()
                }.onMessageData(this::showMessage)
            }


            true
        }

//        val textUser = headerView.findViewById<TextView>(R.id.textUserName)
//        textUser.text = localStorage.phoneNumber

    }












//    private fun showAlertDialog() {
//        val mDialogView = LayoutInflater.from(requireContext()).inflate(R.layout.edit_item, null)
//        val mBuilder = AlertDialog.Builder(requireContext())
//            .setView(mDialogView)
//            .setTitle("Create new contact")
//        val mAlertDialog = mBuilder.show()
//
//        mAlertDialog.addBtn.text = "Add"
//
//        mAlertDialog.addBtn.setOnClickListener {
//            val firstName = mAlertDialog.first_name.text.toString()
//            val lastName = mAlertDialog.last_name.text.toString()
//            val phoneNumber = mAlertDialog.phoneNumber.text.toString()
//
//            if (firstName.isEmpty() || phoneNumber.isEmpty()) {
//                showToast("Please, enter name and phone!")
//            } else {
//                loader_progress.visible()
//                val newContact = ContactData(0, lastName, firstName, phoneNumber)
//                addContact(newContact) {
//                    loader_progress.gone()
//                    it.onData { addToAdapter(it) }.onMessageData(this::showMessage)
//                }
//                mAlertDialog.dismiss()
//            }
//        }
//        mAlertDialog.cancelBtn.setOnClickListener { mAlertDialog.dismiss() }
//    }



//    fun addToAdapter(contactData: ContactReturnedData) {
//        val ls = adapter.currentList.toMutableList()
//        ls.add(contactData)
//        adapter.submitList(ls)
//        list.smoothScrollToPosition(adapter.currentList.size)
//        showToast("Contact added successful")
//    }

//    fun deleteFromServer(contact: ContactData) {
//        loader_progress.visible()
//        removeContact(contact) {
//            loader_progress.gone()
//            it.onData { loadData() }.onMessageData(this::showMessage)
//        }
//    }

//    fun updateInServer(contact: ContactReturnedData) {
//        loader_progress.visible()
//        val contactData =
//            ContactData(contact.id, contact.lastName, contact.firstName, contact.phoneNumber)
//        updateContact(contactData) {
//            loader_progress.gone()
//            it.onData { loadData() }.onMessageData(this::showMessage)
//        }
//    }

//     fun onBackPressed() {
//        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
//            drawerLayout.closeDrawer(GravityCompat.START)
//        } else {
//            super.onBackPressed()
//        }
//
//    }


}
