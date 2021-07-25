package com.example.proyectog2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_registrar_usuario.*

class registrarUsuario : AppCompatActivity() {
    private lateinit var fecha: EditText
    val datos = SQLiteHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar()?.hide();
        setContentView(R.layout.activity_registrar_usuario)
        BaseDeDatos.TablaUsuario = SQLiteHelper(this)
        btn_cancelar_registroUsuario.setOnClickListener {
            val intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        fecha = findViewById<EditText>(R.id.txtFecha)

        fecha.setOnClickListener{ShowDatePickerDialog()}

        val botonRegistrar= findViewById<Button>(R.id.btn_ingresarUsuario)
        botonRegistrar.setOnClickListener {
            if(BaseDeDatos.TablaUsuario!=null){
                agregarNuevoUsuario()
                etNombre.setText("")
                edi_nombreUsuario.setText("")
                txtFecha.setText("")
                editTextTextPassword.setText("")
                editTextTextPassword2.setText("")
            }
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

        val txtNombreAAgregar = etNombre.text.toString()
        val txtNombreUsuario = edi_nombreUsuario.text.toString()
        val txtfechaNac= txtFecha.text.toString()
        val txtclave1 = editTextTextPassword.text.toString()
        val lista = datos.crearUsuarioFormulario(txtNombreAAgregar,txtNombreUsuario,txtfechaNac,txtclave1)

        if(lista != null){
            Toast.makeText(this,"Usuario registrado correctamente", Toast.LENGTH_SHORT).show()
            Log.i("anadir-usuario","${txtNombreAAgregar} ---> ${txtNombreUsuario}, ${txtfechaNac}, ${txtclave1}")
        }else{
            Toast.makeText(this,"Usuario no Registrado", Toast.LENGTH_SHORT).show()

        }
    }
}