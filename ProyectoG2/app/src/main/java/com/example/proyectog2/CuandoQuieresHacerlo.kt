package com.example.proyectog2

import android.accessibilityservice.GestureDescription
import android.app.Dialog
import android.app.PendingIntent.getActivity
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.Color.parseColor
import android.graphics.Typeface
import android.icu.text.CaseMap
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.View
import android.view.Window
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.set


class CuandoQuieresHacerlo : AppCompatActivity() {
    private lateinit var fecha: EditText
    private lateinit var fechaFin: EditText
    private lateinit var Recordatorio: ImageButton
    private lateinit var prioridad: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar()?.hide();
        setContentView(R.layout.activity_cuando_quieres_hacerlo)
        fecha = findViewById<EditText>(R.id.txtHoy)
        fechaFin = findViewById<EditText>(R.id.txtFecha2)
        prioridad = findViewById<EditText>(R.id.txtPrioridad)

        fecha.setOnClickListener{ShowDatePickerDialog()}
        fechaFin.setOnClickListener{ShowDatePickerDialog1()}

        Recordatorio = findViewById<ImageButton>(R.id.icRecordatorio)
        prioridad .setOnClickListener{withItems()}


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

            val positiveButtonClick = null
            setPositiveButton("CANCELAR", positiveButtonClick)
            show()
        }
    }

    fun ShowDatePickerDialog() {
        val datePicker = DateIckerFragment{ day, month, year -> OnDateSelected(day, month, year)}
        datePicker.show(supportFragmentManager, "datePicker")
    }

    fun OnDateSelected(day:Int, month:Int, year:Int){
        fecha.setText("${day}/${month}/${year}")
    }
    fun ShowDatePickerDialog1() {
        val datePicker = DateIckerFragment{ day, month, year -> OnDateSelected1(day, month, year)}
        datePicker.show(supportFragmentManager, "datePicker")
    }

    fun OnDateSelected1(day:Int, month:Int, year:Int){
        fechaFin.setText("${day}/${month}/${year}")
    }
}