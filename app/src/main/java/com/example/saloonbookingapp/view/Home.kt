package com.example.saloonbookingapp.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.saloonbookingapp.R
import com.example.saloonbookingapp.adapter.ServicesAdapter
import com.example.saloonbookingapp.databinding.ActivityHomeBinding
import com.example.saloonbookingapp.model.Services

class Home : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var servicesAdapter: ServicesAdapter
    private val ListServices: MutableList<Services> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        val name = intent.extras?.getString("name")

        binding.UserName.text = "Welcome, $name!"
        val recycleViewServices = binding.recycleViewService
        recycleViewServices.layoutManager = GridLayoutManager(this,2)
        servicesAdapter = ServicesAdapter(this,ListServices)
        recycleViewServices.setHasFixedSize(true)
        recycleViewServices.adapter=servicesAdapter
        getServices()

        binding.btAgenda.setOnClickListener {
            val intent = Intent(this, Scheduling::class.java)
            intent.putExtra("name", name)
            startActivity(intent)
        }
    }

    private fun getServices(){

        val service1 = Services(R.drawable.img1, "Haircut")
        ListServices.add(service1)

        val service2 = Services(R.drawable.img2, "Beard Cut")
        ListServices.add(service2)

        val service3 = Services(R.drawable.img3, "Hair Washing")
        ListServices.add(service3)

        val service4 = Services(R.drawable.img4, "Hair Treatment")
        ListServices.add(service4)
    }
}