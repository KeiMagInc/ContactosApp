package com.kerlly.vizuete.contactosapp


import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.kerlly.vizuete.contactosapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setOnClickListeners()
        binding.recyclerviewContacts.addItemDecoration(
            DividerItemDecoration(
                binding.recyclerviewContacts.getContext(),
                DividerItemDecoration.VERTICAL
            )
        )
        binding.recyclerviewContacts.layoutManager = LinearLayoutManager(this)
        binding.recyclerviewContacts.setHasFixedSize(true)
    }
    private fun setOnClickListeners() {
        binding.buttonQuery.setOnClickListener {
            val requestQueue = Volley.newRequestQueue(this)
            val url = "https://jsonplaceholder.typicode.com/users"
            val contacts = arrayListOf<Contact>()

            // Agrega el contacto estático aquí
            contacts.add(Contact(0, "Kerlly Vizuete", "999-999-999", "kerlly.vizuete@epn.edu.ec"))

            val jsonObjectRequest = JsonArrayRequest(
                Request.Method.GET, url, null,
                { response ->

                    for (i in 0 until response.length()) {
                        val user = response.getJSONObject(i)
                        val userId = user.getString("id").toInt()
                        val name = user.getString("name")
                        val emailAddress = user.getString("email")
                        val phone = user.getString("phone")
                        contacts.add(
                            Contact(userId, name, phone, emailAddress)
                        )

                    }
                    binding.recyclerviewContacts.adapter = ContactAdapter(contacts)
                },
                { error ->
                    Log.e("Volley", "Error: ${error.message}")
                    Toast
                        .makeText(this, "Error reading WS: ${error.message}", Toast.LENGTH_SHORT)
                        .show()
                }
            )
            requestQueue.add(jsonObjectRequest)
        }
        binding.buttonSave.setOnClickListener {

        }
    }
}

