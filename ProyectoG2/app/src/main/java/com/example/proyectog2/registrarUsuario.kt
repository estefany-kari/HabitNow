package com.example.proyectog2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.EditText

class registrarUsuario : AppCompatActivity() {
    private lateinit var fecha: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar()?.hide();
        setContentView(R.layout.activity_registrar_usuario)

        fecha = findViewById<EditText>(R.id.txtFecha)

        fecha.setOnClickListener{ShowDatePickerDialog()}

    }
    fun ShowDatePickerDialog() {
        val datePicker = DateIckerFragment{ day, month, year -> OnDateSelected(day, month, year)}
        datePicker.show(supportFragmentManager, "datePicker")
    }

    fun OnDateSelected(day:Int, month:Int, year:Int){
        fecha.setText("${day}/${month}/${year}")
    }
}