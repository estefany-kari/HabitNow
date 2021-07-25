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
import kotlinx.android.synthetic.main.activity_mis_tareas.*

class MisTareas : AppCompatActivity() {
    companion object{
        var id_usuario = 0
    }
    private var calendario = false

    var posicionItem = 0
    var adapterTarea: ArrayAdapter<Tarea>? = null
    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 401
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mis_tareas)
        BaseDeDatos.TablaTarea= SQLiteHelper(this)

        val actionBar = supportActionBar
        actionBar!!.title = "New Title"

        if(BaseDeDatos.TablaTarea != null) {
            val Tarea = BaseDeDatos.TablaTarea!!.consultarTarea()
            adapterTarea = ArrayAdapter(this, android.R.layout.simple_list_item_1, Tarea)
            val listViewUsuario = findViewById<ListView>(R.id.lv_tareas)
            listViewUsuario.adapter = adapterTarea
            registerForContextMenu(listViewUsuario)
        }
        btn_agregarMisTareas.setOnClickListener{
            var intent: Intent = Intent(this, NuevaTarea::class.java)
            startActivity(intent)        }

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
        id_usuario = adapterTarea!!.getItem(posicionItem)!!.Idtarea
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {

        var ATarea = adapterTarea!!.getItem(posicionItem)
        return when(item?.itemId){

            //Editar
            R.id.editar-> {

                if (ATarea != null) {
                    abrirActividadConParametros(NuevaTarea::class.java,ATarea) }
                return true }

            //Eliminar
            R.id.eliminar -> {
                if(BaseDeDatos.TablaTarea!=null){

                    AlertDialog.Builder(this).apply {
                        setTitle("Alerta")
                        setMessage("¿Desea eliminar el registro?")
                        setPositiveButton("Si"){ _: DialogInterface, _: Int ->
                            BaseDeDatos.TablaTarea!!.eliminarTarea(ATarea!!.Idtarea)
                            adapterTarea?.remove(adapterTarea!!.getItem(posicionItem));
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