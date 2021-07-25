package com.example.proyectog2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class frecuencia_habito : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frecuencia_habito)

        val btnAnterior= findViewById<Button>(R.id.id_anterior)
        btnAnterior .setOnClickListener { abrirActividad(CategoriasHabitos::class.java) }
        val btnSiguiente= findViewById<Button>(R.id.btn_siguiente2)
        btnSiguiente .setOnClickListener { abrirActividad(DefinirHabito::class.java) }
    }
    fun abrirActividad(clase: Class<*>){
        val intentExplicito = Intent(
            this,
            clase
        )
        startActivity(intentExplicito)
    }
}