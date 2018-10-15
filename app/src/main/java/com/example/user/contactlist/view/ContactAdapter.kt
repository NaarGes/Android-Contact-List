package com.example.user.contactlist.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.user.contactlist.R
import com.example.user.contactlist.data.model.Contact

class ContactAdapter : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    private var contacts: List<Contact>? = null

    fun setContacts(contacts: List<Contact>) {
        this.contacts = contacts
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ContactViewHolder {

        val result = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_contact,
                viewGroup, false)
        return ContactViewHolder(result)
    }

    override fun onBindViewHolder(contactViewHolder: ContactViewHolder, i: Int) {

        contactViewHolder.onBind(contacts!![i])
    }

    override fun getItemCount(): Int {
        return if (contacts != null) contacts!!.size else 0
    }


    inner class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val name: TextView = itemView.findViewById(R.id.contact_name)
        private val phoneNo: TextView = itemView.findViewById(R.id.contact_number)
        private val photo: ImageView = itemView.findViewById(R.id.contact_photo)

        fun onBind(contact: Contact) {

            name.text = contact.name
            phoneNo.text = contact.phoneNumber
            if (contact.photoUri != "")
                Glide.with(itemView)
                        .load(contact.photoUri)
                        .apply(RequestOptions.circleCropTransform())
                        .into(photo)
            else
                Glide.with(itemView)
                        .load(R.drawable.ic_business_card_of_a_woman)
                        .apply(RequestOptions.circleCropTransform())
                        .into(photo)
        }
    }
}
