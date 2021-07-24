package com.example.proyectog2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar()?.hide();
        setContentView(R.layout.activity_main)
        val textViewRegistrarse = findViewById<TextView>(R.id.txt_registrarse)

        textViewRegistrarse.setOnClickListener { abrirActividad(registrarUsuario::class.java) }

        val btn = findViewById<Button>(R.id.btn_ingresar)
        btn.setOnClickListener { abrirActividad(CuandoQuieresHacerlo::class.java) }
    }

    fun abrirActividad(clase: Class<*>){
        val intentExplicito = Intent(
            this,
            clase
        )
        startActivity(intentExplicito)
    }
}