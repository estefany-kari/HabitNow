package com.example.proyectog2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Button

class DefinirHabito : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar()?.hide();
        setContentView(R.layout.activity_definir_habito)
        val btnAnterior= findViewById<Button>(R.id.btn_cancelarCuando)
        btnAnterior .setOnClickListener { abrirActividad(CategoriasHabitos::class.java) }
        val btnSiguiente= findViewById<Button>(R.id.btn_confirmarCuando)
        btnSiguiente .setOnClickListener { abrirActividad(CuandoQuieresHacerlo::class.java) }
    }

    fun abrirActividad(clase: Class<*>){
        val intentExplicito = Intent(
            this,
            clase
        )
        startActivity(intentExplicito)
    }
}