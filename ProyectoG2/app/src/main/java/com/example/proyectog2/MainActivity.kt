package com.example.proyectog2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    val datos = SQLiteHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar()?.hide();
        setContentView(R.layout.activity_main)

        val textViewRegistrarse = findViewById<TextView>(R.id.txt_registrarse)

        textViewRegistrarse.setOnClickListener { abrirActividad(registrarUsuario::class.java) }

        val btnIngresarUsuario = findViewById<Button>(R.id.btn_ingresarUsuario)


        btnIngresarUsuario.setOnClickListener {
            val usuario =edit_usuario.text.toString()
            val passw = edit_contraseña.text.toString()
            if(usuario.isBlank()&&passw.isBlank()){
                Toast.makeText(this,"Debe llenar todos los campos", Toast.LENGTH_SHORT).show()
            }else{
                validarUsuario()
             }
            }

        btnIngresarUsuario.setOnClickListener { abrirActividad(Inicio::class.java) }

    }

    fun abrirActividad(clase: Class<*>){
        val intentExplicito = Intent(
            this,
            clase
        )
        startActivity(intentExplicito)
    }
    fun validarUsuario():Boolean{
        val usuario = findViewById<EditText>(R.id.edit_usuario)
        val claveT = findViewById<EditText>(R.id.edit_contraseña)

        BaseDeDatos.TablaUsuario = SQLiteHelper(this)
        val lista = datos.consultarUsuario(usuario.text.toString(), claveT.text.toString())
        if(lista!=null){
            abrirActividad(NuevaTarea::class.java)
        }else{
            Toast.makeText(this,"Usuario o contraseña incorrectos", Toast.LENGTH_LONG).show()
        }
        usuario.setText("")
        claveT.setText("")
        usuario.findFocus()
        return true
    }

}