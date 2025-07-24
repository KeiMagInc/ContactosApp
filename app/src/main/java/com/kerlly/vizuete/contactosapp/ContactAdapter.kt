package com.kerlly.vizuete.contactosapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter (private val contactos: ArrayList<Contact>)
    : RecyclerView.Adapter<ContactAdapter.ViewHolder>() {
    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        val textViewUserId = view.findViewById<TextView>(R.id.textViewUserId)
        val textViewName = view.findViewById<TextView>(R.id.textViewName)
        val textViewPhoneNumber = view.findViewById<TextView>(R.id.textViewPhoneNumber)
        val textViewEmailAddress = view.findViewById<TextView>(R.id.textViewEmailAddress)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.contact_list, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with (holder) {
            textViewUserId.text = "${contactos[position].id}"
            textViewName.text = "${contactos[position].name}"
            textViewPhoneNumber.text = "${contactos[position].phone}"
            textViewEmailAddress.text = "${contactos[position].email}"
        }
    }
    override fun getItemCount() : Int {
        return contactos.size
    }
}
