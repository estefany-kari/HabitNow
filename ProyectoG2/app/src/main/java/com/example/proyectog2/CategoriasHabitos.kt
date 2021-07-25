package com.example.proyectog2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.TextureView
import android.view.Window
import android.widget.TextView
import android.widget.Toast

class CategoriasHabitos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar()?.hide();
        setContentView(R.layout.activity_categorias_habitos)
        BaseDeDatos.TablaHabito = SQLiteHelper(this)

        val cat1 = findViewById<TextView>(R.id.txtDejarHabito)
        cat1 .setOnClickListener{
            val categoria1 = cat1.text.toString()

            if (categoria1.isEmpty()) {
                Toast.makeText(this, "Selecciones una categoria", Toast.LENGTH_LONG).show()
            } else {
                val estado = BaseDeDatos.TablaHabito!!.crearHabitoCategoria(categoria1)

                if (estado != null) {
                    Log.i("añadir Estudiante", "CATEGORIA: ${categoria1}")
                } else {
                    Toast.makeText(this, "Categoria no agregada", Toast.LENGTH_LONG).show()

                }
            }
            abrirActividad(frecuencia_habito::class.java)}
        val cat2 = findViewById<TextView>(R.id.Meditacion)
        cat2 .setOnClickListener{
            abrirActividad(frecuencia_habito::class.java)}
        val cat3 = findViewById<TextView>(R.id.Estudio)
        cat3 .setOnClickListener{abrirActividad(frecuencia_habito::class.java)}
        val cat4 = findViewById<TextView>(R.id.Deportes)
        cat4 .setOnClickListener{abrirActividad(frecuencia_habito::class.java)}
        val cat5 = findViewById<TextView>(R.id.Entretenimiento)
        cat5 .setOnClickListener{abrirActividad(frecuencia_habito::class.java)}
        val cat6 = findViewById<TextView>(R.id.Social)
        cat6 .setOnClickListener{abrirActividad(frecuencia_habito::class.java)}
        val cat7 = findViewById<TextView>(R.id.Salud)
        cat7 .setOnClickListener{abrirActividad(frecuencia_habito::class.java)}
        val cat8 = findViewById<TextView>(R.id.Nutricion)
        cat8 .setOnClickListener{abrirActividad(frecuencia_habito::class.java)}
        val cat9 = findViewById<TextView>(R.id.Hogar)
        cat9 .setOnClickListener{abrirActividad(frecuencia_habito::class.java)}
        val cat10 = findViewById<TextView>(R.id.Trabajo)
        cat10 .setOnClickListener{abrirActividad(frecuencia_habito::class.java)}
        val cat11 = findViewById<TextView>(R.id.AireLibre)
        cat11 .setOnClickListener{abrirActividad(frecuencia_habito::class.java)}
        val cat12 = findViewById<TextView>(R.id.Otros)
        cat12 .setOnClickListener{abrirActividad(frecuencia_habito::class.java)}
    }

    fun abrirActividad(clase: Class<*>){
        val intentExplicito = Intent(
            this,
            clase
        )
        startActivity(intentExplicito)
    }
}