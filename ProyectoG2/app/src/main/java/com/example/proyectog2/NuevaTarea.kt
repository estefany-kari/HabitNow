
package com.example.proyectog2

import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.TimePicker
import androidx.appcompat.widget.ButtonBarLayout
import java.util.*


class NuevaTarea : AppCompatActivity() {

    private lateinit var fechaTarea: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nueva_tarea)
        val botonHoraTarea = findViewById<Button>(R.id.btn_HoraRecordatorio)
        fechaTarea = findViewById<EditText>(R.id.btn_fechaTarea)

        fechaTarea.setOnClickListener{ShowDatePickerDialog()}


        val timePicker: TimePicker
        val hour: Int
        val minute: Int

        botonHoraTarea. setOnClickListener {

            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                botonHoraTarea.setText("${hour} : ${minute}")
            }
            TimePickerDialog(
                this,
                timeSetListener,
                cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE),
                true
            ).show()
        }
    }

    fun ShowDatePickerDialog() {
        val datePicker = DateIckerFragment{ day, month, year -> OnDateSelected(day, month, year)}
        datePicker.show(supportFragmentManager, "datePcker")
    }

    fun OnDateSelected(day:Int, month:Int, year:Int){
        fechaTarea.setText("${day}/${month}/${year}")
    }

}