package com.example.proyectog2

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_inicio.*

class Inicio : AppCompatActivity() {
    companion object{
        var idProf = 0

    }
    private var calendario = false

    var posicionItem = 0
    var adapter: ArrayAdapter<HabitoBDD>? = null
    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 401
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)
        val actionBar = supportActionBar
        actionBar!!.title = "G2"
        BaseDeDatos.TablaHabito= SQLiteHelper(this)

        if(BaseDeDatos.TablaHabito != null) {
            val profesor = BaseDeDatos.TablaHabito!!.consultarHabito()
            adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, profesor)
            val listViewUsuario = findViewById<ListView>(R.id.lv_inicio)
            listViewUsuario.adapter = adapter
            registerForContextMenu(listViewUsuario)

        }
        btn_agregar.setOnClickListener {
            var intent: Intent = Intent(this, CategoriasHabitos::class.java)
            startActivity(intent)
        }
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
                abrirActividad(CalendarioView::class.java)
                return true
            }
            R.id.icEditar ->{
                    //abrirActividadConParametros(CuandoQuieresHacerlo::class.java)
                return true
            }
            R.id.icEliminar ->{
                abrirActividad(CalendarioView::class.java)
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)

        val inflater = menuInflater
        inflater.inflate(R.menu.menu_lv,menu)

        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        posicionItem = id
        idProf = adapter!!.getItem(posicionItem)!!.idHabito
    }

     fun onContextItemSelected12(item: MenuItem): Boolean {

        var Aprofesor = adapter!!.getItem(posicionItem)
        return when(item?.itemId){

            //Editar
            R.id.editar-> {

                if (Aprofesor != null) {
                    abrirActividadConParametros(CuandoQuieresHacerlo::class.java,Aprofesor) }
                return true }

            //Eliminar
            R.id.eliminar -> {
                if(BaseDeDatos.TablaHabito!=null){

                    AlertDialog.Builder(this).apply {
                        setTitle("Alerta")
                        setMessage("¿Desea eliminar el registro?")
                        setPositiveButton("Si"){ _: DialogInterface, _: Int ->
                            BaseDeDatos.TablaHabito!!.eliminarHabito(Aprofesor!!.idHabito)
                            adapter?.remove(adapter!!.getItem(posicionItem));
                        }
                        setNegativeButton("No", null)
                    }.show()
                }
                return true }

            else -> super.onContextItemSelected(item)
        }
    }

    fun abrirActividad(clase: Class<*>){
        val intentExplicito = Intent(
            this,
            clase
        )
        startActivity(intentExplicito)
    }

    fun abrirActividadConParametros(clase: Class<*>, habito: HabitoBDD ){
        val intentExplicito = Intent(
            this,
            clase
        )
        intentExplicito.putExtra("habito",habito)
        startActivityForResult(intentExplicito,CODIGO_RESPUESTA_INTENT_EXPLICITO)

    }
}