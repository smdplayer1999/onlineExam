package com.mysoftpanda.android.onlineexam.contact

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mysoftpanda.android.onlineexam.R
import com.mysoftpanda.android.onlineexam.data.entities.CardData
import com.mysoftpanda.android.onlineexam.utils.SingleBlock
import com.mysoftpanda.android.onlineexam.utils.decimal
import com.mysoftpanda.android.onlineexam.utils.extensions.bindItem
import com.mysoftpanda.android.onlineexam.utils.numberSeperate
import com.mysoftpanda.android.onlinelessonxiihomework.utils.extensions.inflate
import kotlinx.android.synthetic.main.item_contact.view.*



class ContactAdapter : ListAdapter<CardData, ContactAdapter.ViewHolder>(DiffUtillCallBack()) {
    private var listenerItem: SingleBlock<CardData>? = null
    private var listenerEdit: SingleBlock<CardData>? = null
    private var listenerDelete: SingleBlock<CardData>? = null
    fun setOnItemClickListener(block: SingleBlock<CardData>) {
        listenerItem = block
    }

    fun setOnItemEditListener(block: SingleBlock<CardData>) {
        listenerEdit = block
    }

    fun setOnItemDeleteListener(block: SingleBlock<CardData>) {
        listenerDelete = block
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        init {
            itemView.apply {
                setOnClickListener { listenerItem?.invoke(getItem(adapterPosition)) }

//                delete_button.setOnClickListener {  listenerDelete?.invoke(getItem(adapterPosition))}

//                edit_button.setOnClickListener {   listenerEdit?.invoke(getItem(adapterPosition))}
            }

        }

        @SuppressLint("SetTextI18n")
        fun bind() = bindItem {

            val d = getItem(adapterPosition)
            user_name_card.text = d.name
            amount_card_text.text = "${decimal(d.amount)} so'm"
            pan_card_text.text = numberSeperate(d.pan!!)
            date_card_text.text = d.expiredAt
        }

    }





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        parent.inflate(
            R.layout.item_contact
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind()

}

class DiffUtillCallBack : DiffUtil.ItemCallback<CardData>() {
    override fun areItemsTheSame(oldItem: CardData, newItem: CardData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CardData, newItem: CardData): Boolean {
        return oldItem == newItem && oldItem.name == newItem.name && oldItem.color == newItem.color
    }

}