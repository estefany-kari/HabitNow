package com.example.proyectog2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.ButtonBarLayout


class NuevaTarea : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nueva_tarea)
        val botonFecha = findViewById<TextView>(R.id.btn_fechaTarea)
        val botonHoraTarea = findViewById<Button>(R.id.btn_HoraRecordatorio)
        botonFecha.setOnClickListener{
            var intent:Intent = Intent(this, CalendarioView::class.java)
            startActivity(intent)
        }
        botonHoraTarea.setOnClickListener{
            var intent:Intent = Intent(this, PruebaFechaHora::class.java)
            startActivity(intent)
        }

    }
}