package com.example.proyectog2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class registrarUsuario : AppCompatActivity() {
    private lateinit var fecha: EditText
    val datos = SQLiteHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar()?.hide();
        setContentView(R.layout.activity_registrar_usuario)

        fecha = findViewById<EditText>(R.id.txtFecha)

        fecha.setOnClickListener{ShowDatePickerDialog()}

        val botonRegistrar= findViewById<Button>(R.id.btn_ingresarUsuario)
        botonRegistrar.setOnClickListener {
            agregarNuevoUsuario()


        }
    }
    fun ShowDatePickerDialog() {
        val datePicker = DateIckerFragment{ day, month, year -> OnDateSelected(day, month, year)}
        datePicker.show(supportFragmentManager, "datePicker")
    }

    fun OnDateSelected(day:Int, month:Int, year:Int){
        fecha.setText("${day}/${month}/${year}")
    }
    fun agregarNuevoUsuario(){

        val nombre= findViewById<EditText>(R.id.etNombre).toString()
        val editNombreUsuario = findViewById<EditText>(R.id.edi_nombreUsuario).toString()
        val fechaNac = findViewById<EditText>(R.id.txtFecha).toString()
        val clave1= findViewById<EditText>(R.id.editTextTextPassword).toString()


        val listaUsuario = datos.crearUsuarioFormulario(nombre,editNombreUsuario,clave1, fechaNac)


        if(listaUsuario != null){
            Toast.makeText(this,"Usuario registrado correctamente", Toast.LENGTH_SHORT).show()
            Log.i("anadir-usuario","${nombre} ---> ${editNombreUsuario}, $fechaNac, $clave1")
        }else{
            Toast.makeText(this,"Usuario no Registrado", Toast.LENGTH_SHORT).show()

        }
    }
}