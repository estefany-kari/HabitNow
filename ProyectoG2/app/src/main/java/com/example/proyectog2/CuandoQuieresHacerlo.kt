package com.example.proyectog2

import android.app.TimePickerDialog
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class CuandoQuieresHacerlo : AppCompatActivity() {
    private lateinit var fecha: EditText
    private lateinit var fechaFin: EditText
    private lateinit var Recordatorio: ImageButton
    private lateinit var prioridad: EditText
    private lateinit var hora: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar()?.hide();
        setContentView(R.layout.activity_cuando_quieres_hacerlo)
        fecha = findViewById<EditText>(R.id.btn_fechaTarea)
        fechaFin = findViewById<EditText>(R.id.txtFecha2)
        prioridad = findViewById<EditText>(R.id.txtPrioridad)
        prioridad .setOnClickListener{withItems()}

        hora = findViewById(R.id.EtHora)
        hora .setOnClickListener{hora()}

        fecha.setOnClickListener{ShowDatePickerDialog()}
        fechaFin.setOnClickListener{ShowDatePickerDialog1()}

        Recordatorio = findViewById<ImageButton>(R.id.icRecordatorio)
        Recordatorio .setOnClickListener{RecordatorioVentana()}

    }

    fun RecordatorioVentana(){
        val items = arrayOf("Alta", "Normal", "Baja")
        val builder = AlertDialog.Builder(this)
        val Title = SpannableString("         TIEMPO Y RECORDATORIO ")

        with(builder)
        {
            Title.setSpan(
                ForegroundColorSpan(Color.parseColor("#00D9C4")),
                1,
                Title.length,
                Spannable.SPAN_EXCLUSIVE_INCLUSIVE

            )
            Title.setSpan(
                StyleSpan(Typeface.BOLD),
                8,
                Title.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )

            setTitle(Title)
            setItems(items) { dialog, which ->
                Toast.makeText(applicationContext, items[which] + " is clicked", Toast.LENGTH_SHORT).show()
                prioridad.setText("${items[which]}")
            }

            setPositiveButton("CANCELAR", null)
            setNegativeButton("AÑADIR",  DialogInterface.OnClickListener { dialog, which ->
                hora()
                Log.i("AÑADIR HORA", "HORA AGREGADA")
            })
            show()
        }
    }

    fun hora(){
        val cal = Calendar.getInstance()
        val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
            cal.set(Calendar.HOUR_OF_DAY, hour)
            cal.set(Calendar.MINUTE, minute)
            hora.setText("${hour} : ${minute}")
        }
        TimePickerDialog(
            this,
            timeSetListener,
            cal.get(Calendar.HOUR_OF_DAY),
            cal.get(Calendar.MINUTE),
            true
        ).show()
    }


    fun withItems() {

        val items = arrayOf("Alta", "Normal", "Baja")
        val builder = AlertDialog.Builder(this)
        val Title = SpannableString("                     PRIORIDAD ")

        with(builder)
        {
            //setTitle("PRIORIDAD")
            Title.setSpan(
                ForegroundColorSpan(Color.parseColor("#00D9C4")),
                14,
                Title.length,
                Spannable.SPAN_EXCLUSIVE_INCLUSIVE

            )
            Title.setSpan(
                StyleSpan(Typeface.BOLD),
                8,
                Title.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )

            setTitle(Title)
            setItems(items) { dialog, which ->
                Toast.makeText(applicationContext, items[which] + " is clicked", Toast.LENGTH_SHORT).show()
                prioridad.setText("${items[which]}")
            }

            setNegativeButton("CANCELAR", null)
            show()
        }
    }

    fun ShowDatePickerDialog() {
        val datePicker = DateIckerFragment{ day, month, year -> OnDateSelected(day, month, year)}
        datePicker.show(supportFragmentManager, "datePicker")
    }

    fun OnDateSelected(day:Int, month:Int, year:Int){
        fecha.setText("${day}/${month}/${year}")
    }
    fun ShowDatePickerDialog1() {
        val datePicker = DateIckerFragment{ day, month, year -> OnDateSelected1(day, month, year)}
        datePicker.show(supportFragmentManager, "datePicker")
    }

    fun OnDateSelected1(day:Int, month:Int, year:Int){
        fechaFin.setText("${day}/${month}/${year}")
    }
}