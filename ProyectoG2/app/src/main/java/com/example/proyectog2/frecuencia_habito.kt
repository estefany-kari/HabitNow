package com.example.proyectog2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.Toast

class frecuencia_habito : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frecuencia_habito)

        val btnAnterior= findViewById<Button>(R.id.id_anterior)
        btnAnterior .setOnClickListener { abrirActividad(CategoriasHabitos::class.java) }
        val btnSiguiente= findViewById<Button>(R.id.btn_siguiente2)
        btnSiguiente .setOnClickListener { abrirActividad(DefinirHabito::class.java) }
    }
    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view.getId()) {
                R.id.rdo_btn_todosLosDias ->
                    if (checked) {

                    val frecuencia1 = "Todos los días"

                        if (frecuencia1.isEmpty()) {
                            Toast.makeText(this, "Selecciones una categoria", Toast.LENGTH_LONG).show()
                        } else {
                            val estado = BaseDeDatos.TablaHabito!!.crearFrecuenciaHabito(frecuencia1)

                            if (estado != null) {

                                Log.i("añadir frecuencia", "FRECUENCIA: ${frecuencia1}")
                            } else {
                                Toast.makeText(this, "Categoria no agregada", Toast.LENGTH_LONG).show()

                            } } }
                R.id.btn_AlgunosDiasSemana ->
                    if (checked) {
                        val frecuencia1 = "Algunos días de la semana"

                        if (frecuencia1.isEmpty()) {
                            Toast.makeText(this, "Selecciones una frecuencia", Toast.LENGTH_LONG).show()
                        } else {
                            val estado = BaseDeDatos.TablaHabito!!.crearFrecuenciaHabito(frecuencia1)

                            if (estado != null) {
                                Log.i("añadir frecuencia", "FRECUENCIA: ${frecuencia1}")
                            } else {
                                Toast.makeText(this, "frecuencia no agregada", Toast.LENGTH_LONG).show()

                            } }
                    }
                R.id.btnAlgunasVecesMes ->
                    if (checked) {
                        val frecuencia1 = "Todos los días"

                        if (frecuencia1.isEmpty()) {
                            Toast.makeText(this, "Selecciones una frecuencia", Toast.LENGTH_LONG).show()
                        } else {
                            val estado = BaseDeDatos.TablaHabito!!.crearFrecuenciaHabito(frecuencia1)

                            if (estado != null) {

                                Log.i("añadir Estudiante", "FRECUENCIA: ${frecuencia1}")
                            } else {
                                Toast.makeText(this, "FRECUENCIA no agregada", Toast.LENGTH_LONG).show()

                            } }
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