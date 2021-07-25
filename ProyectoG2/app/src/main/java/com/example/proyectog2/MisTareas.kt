package com.example.proyectog2

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AlertDialog

class MisTareas : AppCompatActivity() {
    companion object{
        var idProf = 0
    }
    private var calendario = false

    var posicionItem = 0
    var adapter: ArrayAdapter<Tarea>? = null
    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 401
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mis_tareas)
        val actionBar = supportActionBar
        actionBar!!.title = "New Title"
        BaseDeDatos.TablaTarea= SQLiteHelper(this)

        if(BaseDeDatos.TablaTarea != null) {
            val profesor = BaseDeDatos.TablaTarea!!.consultarTarea()
            adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, profesor)
            val listViewUsuario = findViewById<ListView>(R.id.lv_tareas)
            listViewUsuario.adapter = adapter
            registerForContextMenu(listViewUsuario)
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
        Inicio.idProf = adapter!!.getItem(posicionItem)!!.Idtarea
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {

        var Aprofesor = adapter!!.getItem(posicionItem)
        return when(item?.itemId){

            //Editar
            R.id.editar-> {

                if (Aprofesor != null) {
                    abrirActividadConParametros(NuevaTarea::class.java,Aprofesor) }
                return true }

            //Eliminar
            R.id.eliminar -> {
                if(BaseDeDatos.TablaTarea!=null){

                    AlertDialog.Builder(this).apply {
                        setTitle("Alerta")
                        setMessage("¿Desea eliminar el registro?")
                        setPositiveButton("Si"){ _: DialogInterface, _: Int ->
                            BaseDeDatos.TablaTarea!!.eliminarTarea(Aprofesor!!.Idtarea)
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

    fun abrirActividadConParametros(clase: Class<*>, Tarea: Tarea ){
        val intentExplicito = Intent(
            this,
            clase
        )
        intentExplicito.putExtra("Tarea", Tarea)
        startActivityForResult(intentExplicito,CODIGO_RESPUESTA_INTENT_EXPLICITO)

    }
}