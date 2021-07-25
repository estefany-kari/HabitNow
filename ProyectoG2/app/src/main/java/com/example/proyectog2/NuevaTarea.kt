
package com.example.proyectog2

import android.app.TimePickerDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.ButtonBarLayout
import java.util.*


class NuevaTarea : AppCompatActivity() {

    private lateinit var fechaTarea: EditText
    private lateinit var prioridad: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nueva_tarea)
        val botonHoraTarea = findViewById<Button>(R.id.btn_HoraRecordatorio)
        fechaTarea = findViewById<EditText>(R.id.btn_fechaTarea)

        fechaTarea.setOnClickListener{ShowDatePickerDialog()}
        prioridad=findViewById(R.id.btn_PrioridadTarea)
        prioridad .setOnClickListener{withItems()}


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

    fun withItems() {

        val items = arrayOf("Alta", "Normal", "Baja")
        val builder = AlertDialog.Builder(this)
        val Title = SpannableString("                     PRIORIDAD ")

        with(builder)
        {
            //setTitle("PRIORIDAD")
            Title.setSpan(
                ForegroundColorSpan(Color.parseColor("#00D9C4")),
                14,
                Title.length,
                Spannable.SPAN_EXCLUSIVE_INCLUSIVE

            )
            Title.setSpan(
                StyleSpan(Typeface.BOLD),
                8,
                Title.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )

            setTitle(Title)
            setItems(items) { dialog, which ->
                Toast.makeText(applicationContext, items[which] + " is clicked", Toast.LENGTH_SHORT).show()
                prioridad.setText("${items[which]}")
            }

            setNegativeButton("CANCELAR", null)
            show()
        }
    }

}