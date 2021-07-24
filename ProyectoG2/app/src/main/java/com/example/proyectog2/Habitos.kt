package com.example.proyectog2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat

class Habitos : AppCompatActivity() {
    private var calendario = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_habitos)
    }
    private fun calendarIcon(menuItem: MenuItem?){
        val id = if (calendario) R.drawable.ic_baseline_calendar_today_24;
        else R.drawable.ic_baseline_calendar_today_24;

        if (menuItem != null) {
            menuItem.icon = ContextCompat.getDrawable(this, id)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_inicio, menu)

        calendarIcon(menu?.findItem(R.id.IcCalendario))

        return super.onCreateOptionsMenu(menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item?.itemId) {
            //Editar
            R.id.icHoy -> {
                //Log.i(
                //  "list-view", "Editar ${
                //BBaseDatosMemoria.arregloBEntrenador[
                //      posicionItemSeleccionado
                //]
                //}"
                //)
                return true
            }
            //Eliminar
            R.id.icHabitos -> {

                return true
            }
            R.id.IcCalendario ->{
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }
}