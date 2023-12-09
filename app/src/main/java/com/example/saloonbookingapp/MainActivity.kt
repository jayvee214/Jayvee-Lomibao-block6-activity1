package com.example.saloonbookingapp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.saloonbookingapp.databinding.ActivityMainBinding
import com.example.saloonbookingapp.view.Home
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        supportActionBar?.hide()

        binding.btLogin.setOnClickListener {

            val name = binding.editName.text.toString()
            val password = binding.editPassword.text.toString()

            when{
                name.isEmpty() ->{
                    message(it,"Please Enter Your Name")
                }password.isEmpty() -> {
                    message(it,"Please Fill in the password")
                }password.length <=5 ->{
                    message(it,"The Password Must Be At Least 6 Characters Long")
                }else -> {
                    navigateToHome(name)
                }
            }
        }
    }
    private fun message(view: View, message: String){
        val snackbar = Snackbar.make(view, message,Snackbar.LENGTH_SHORT)
        snackbar.setBackgroundTint(Color.parseColor("#FF0000"))
        snackbar.setTextColor(Color.parseColor("FFFFFF"))
        snackbar.show()
    }

    private fun navigateToHome(name:String){
        val intent = Intent(this, Home::class.java)
        intent.putExtra("name",name)
        startActivity(intent)


    }
}