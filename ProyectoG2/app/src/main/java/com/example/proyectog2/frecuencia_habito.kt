package com.example.proyectog2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.Toast
import java.io.FileReader

class frecuencia_habito : AppCompatActivity() {
    companion object{
        var Frecuencia = " "
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frecuencia_habito)

        val btnAnterior= findViewById<Button>(R.id.id_anterior)
        btnAnterior .setOnClickListener { abrirActividad(CategoriasHabitos::class.java) }
        val btnSiguiente= findViewById<Button>(R.id.btn_siguiente2)
        btnSiguiente .setOnClickListener {
            abrirActividad(DefinirHabito::class.java) }
    }
    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view.getId()) {
                R.id.rdo_btn_todosLosDias ->
                    if (checked) {
                        Frecuencia="Todos lod dias"
                    }
                R.id.btn_AlgunosDiasSemana ->
                    if (checked) {
                        Frecuencia="Algunos Días"
                    }
                R.id.btnAlgunasVecesMes ->
                    if (checked) {
                        Frecuencia="Algunas veces"
                    }
            }
        }
    }
    fun abrirActividad(clase: Class<*>){
        val intentExplicito = Intent(
            this,
            clase
        )
        startActivity(intentExplicito)
    }
}