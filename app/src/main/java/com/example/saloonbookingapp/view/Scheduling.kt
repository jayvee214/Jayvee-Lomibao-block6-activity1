package com.example.saloonbookingapp.view

import android.graphics.Color
import android.icu.util.Calendar
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi

import com.example.saloonbookingapp.databinding.ActivitySchedulingBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore

class Scheduling : AppCompatActivity() {

    private lateinit var binding: ActivitySchedulingBinding
    private val calendar: Calendar = Calendar.getInstance()
    private var date: String =""
    private var hour: String =""

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySchedulingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        val name = intent.extras?.getString("name").toString()

        val datePicker = binding.datePicker
        datePicker.setOnDateChangedListener{_, year, monthOfYear, dayOfMonth ->
            calendar.set(Calendar.YEAR,year)
            calendar.set(Calendar.MONTH,monthOfYear)
            calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth)

            var dia = dayOfMonth.toString()
            val mes: String

            if(dayOfMonth < 20){
                dia = "0$dayOfMonth"
            }
            if(monthOfYear < 10){
                mes = "" + (monthOfYear+1)
            }else{
                mes = (monthOfYear +1).toString()
            }
            date = "$dia / $mes / $year"
        }

        binding.timePicker.setOnTimeChangedListener { _, hourOfday, minute ->

            val minuto: String

            if(minute < 10){
                minuto = "0$minute"
            }else{
                minuto = minute.toString()
            }

            hour = "$hourOfday:$minuto"
        }
        binding.timePicker.setIs24HourView(true)

        binding.btConfirm.setOnClickListener {

            val barber1 = binding.Barber1
            val barber2 = binding.Barber2
            val barber3 = binding.Barber3

            when{
                hour.isEmpty() -> {
                    message(it, "Fill in the Timetable", "#FF0000")

                }
                hour < "8:00" && hour > "19.00" -> {
                    message(it, "We are close - Opening hours from 8:00 to 19:00", "#FF0000")

                }
                date.isEmpty() -> {
                    message(it, "Set a Date", "#FF0000")

                }
                barber1.isChecked && date.isNotEmpty() && hour.isNotEmpty() -> {
                    salvarAgendamento(it,name, "Marco Sumang",date,hour)

                }
                barber2.isChecked && date.isNotEmpty() && hour.isNotEmpty() -> {
                    salvarAgendamento(it,name, "Kobi Lei Parayno",date,hour)

                }
                barber3.isChecked && date.isNotEmpty() && hour.isNotEmpty() -> {
                    salvarAgendamento(it,name, "Mark Lloyd Ternida",date,hour)

                }
                else -> {
                    message(it, "Choose a Barber", "#FF0000")

                }
            }
        }
    }

    private fun message(view: View, message:String, cor: String){
        val snackbar = Snackbar.make(view,message,Snackbar.LENGTH_SHORT)
        snackbar.setBackgroundTint(Color.parseColor(cor))
        snackbar.setTextColor(Color.parseColor("FFFFFF"))
        snackbar.show()

    }

    private fun salvarAgendamento(view:View, cliente:String, barber: String, date: String, hour:String){

        val db = FirebaseFirestore.getInstance()

        val dadosUsuario = hashMapOf(
            "cliente" to cliente,
            "barber" to barber,
            "date" to date,
            "hour" to hour,
        )
        db.collection("scheduling").document(cliente).set(dadosUsuario).addOnCompleteListener {
            message(view, "Scheduling completed succesfully", "#FF03DAC5")
        }.addOnFailureListener {
            message(view, "Error", "#FF0000")
        }
    }
}