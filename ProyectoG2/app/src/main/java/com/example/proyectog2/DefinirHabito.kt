package com.example.proyectog2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class DefinirHabito : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar()?.hide();
        setContentView(R.layout.activity_definir_habito)

        val nombreH = findViewById<EditText>(R.id.etDefinirHabito)
        val descripcionH = findViewById<EditText>(R.id.etDescripcionHabito)

        val btnAnterior= findViewById<Button>(R.id.btn_cancelarCuando)
        btnAnterior .setOnClickListener { abrirActividad(CategoriasHabitos::class.java) }
        val btnSiguiente= findViewById<Button>(R.id.btn_confirmarCuando)
        btnSiguiente .setOnClickListener {
            val nombre = nombreH.text.toString()
            val desc = descripcionH.text.toString()

            if (nombre.isEmpty() || desc.isEmpty()) {
                Toast.makeText(this, "Selecciones una categoria", Toast.LENGTH_LONG).show()
            } else {
                val estado = BaseDeDatos.TablaHabito!!.crearNombreHabito(nombre,desc)

                if (estado != null) {
                    Log.i("añadir Estudiante", "CATEGORIA: ${nombre} --> ${desc}")
                } else {
                    Toast.makeText(this, "Categoria no agregada", Toast.LENGTH_LONG).show()

                }
            }
            abrirActividad(CuandoQuieresHacerlo::class.java) }
    }

    fun abrirActividad(clase: Class<*>){
        val intentExplicito = Intent(
            this,
            clase
        )
        startActivity(intentExplicito)
    }
}