package com.example.proyectog2

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.util.ArrayList

class SQLiteHelper(context: Context?): SQLiteOpenHelper(context,"habitos",null,1) {

    override fun onCreate(db: SQLiteDatabase?) {
        val scriptCrearTablaUsuario=
            """
            CREATE TABLE USUARIO(
            ID_USUARIO INTEGER PRIMARY KEY AUTOINCREMENT,
            NOMBRE VARCHAR(50),
            NOMBREUSUARIO VARCHAR(50),
            CONTRASENIA VARCHAR(16),
            FECHANACIMIENTO VARCHAR(15)
            );
            """.trimIndent()
        Log.i("bdd", "Creacion tabla usuario")
        db?.execSQL(scriptCrearTablaUsuario)

        val scriptCrearTablaTareas=
            """
            CREATE TABLE TAREA(
            ID_TAREA INTEGER PRIMARY KEY AUTOINCREMENT,
            ID_USUARIO INTEGER,
            CATEGORIA VARCHAR(50),
            NOMBRE VARCHAR(50),
            FECHA VARCHAR(15),
            HORA VARCHAR(10),
            PRIORIDAD VARCHAR(15),
            FOREIGN KEY(ID_USUARIO) REFERENCES USUARIO(ID_USUARIO) 
            );
            """.trimIndent()
        Log.i("bdd", "Creacion tabla Tarea")
        db?.execSQL(scriptCrearTablaTareas)

        val scriptCrearTablaHabitos=
            """
            CREATE TABLE HABITO(
            ID_HABITO INTEGER PRIMARY KEY AUTOINCREMENT,
            ID_USUARIO INTEGER,
            CATEGORIA VARCHAR(20),
            NOMBRE VARCHAR(50),
            DESCRIPCION VARCHAR(50),
            FRECUENCIA VARCHAR(50),
            FECHAINICIO VARCHAR(15),
            FECHAFIN VARCHAR(15),
            HORA VARCHAR(5),
            PRIORIDAD VARCHAR(15),
            FOREIGN KEY(ID_USUARIO) REFERENCES USUARIO(ID_USUARIO) 
            );
            """.trimIndent()
        Log.i("bdd", "Creacion tabla Habito")
        db?.execSQL(scriptCrearTablaHabitos)
    }

    //Funcion para consultar el Usuario
        fun consultarUsuario(usuario:String, clave:String): UsuarioBDD{
            val scriptConsultarUsuario = "SELECT NOMBREUSUARIO, CONTRASE??A FROM USUARIO WHERE NOMBREUSUARIO = ${usuario} AND CONTRASE??A =${clave}"
            val baseDatosLectura = readableDatabase
            val resultadoConsultaLectura = baseDatosLectura.rawQuery(
                scriptConsultarUsuario,
                null
            )
            val existeUsuario = resultadoConsultaLectura.moveToFirst()
            //val arregloUsuario = arrayListOf<EUsuarioBDD>()       //En caso de3 necesitar un arreglo de registros
            val usuarioEncontrado = UsuarioBDD(0,null,"","",null)
            if (existeUsuario){
                do{

                    val nombreUsuario = resultadoConsultaLectura.getString(1) //Columna indice 1 -> NOMBRE
                    val claveUsuario = resultadoConsultaLectura.getString(3) //Columna indice 3 -> clave

                    if(nombreUsuario!=null){

                        usuarioEncontrado.nombre = nombreUsuario
                        usuarioEncontrado.contrase??a =claveUsuario
                    }
                }while (resultadoConsultaLectura.moveToNext())
            }
            resultadoConsultaLectura.close()
            baseDatosLectura.close()
            return usuarioEncontrado
        }
    //Funcion

    fun crearUsuarioFormulario(
        nombre: String,
        nombreUsuario: String,
        contrasenia: String,
        FechaNac: String
    ): Boolean{
        val conexionEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("nombre", nombre)
        valoresAGuardar.put("nombreUsuario", nombreUsuario)
        valoresAGuardar.put("contrasenia", contrasenia)
        valoresAGuardar.put("fechaNacimiento", FechaNac)

        val resultadoEscritura: Long = conexionEscritura
            .insert(
                "USUARIO",
                null,
                valoresAGuardar
            )
        conexionEscritura.close()

        return if (resultadoEscritura.toInt() == -1) false else true

    }


    //crear tareas
    fun crearTareaFormulario(
        idUsuario: Int,
        categoria: String,
        NombreTarea: String,
        FechaTarea: String,
        horaTarea: String,
        prioridad: String
    ): Boolean{
        val conexionEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("id_usuario", idUsuario)
        valoresAGuardar.put("categoria",categoria)
        valoresAGuardar.put("nombre", NombreTarea)
        valoresAGuardar.put("fecha", FechaTarea)
        valoresAGuardar.put("hora", horaTarea)
        valoresAGuardar.put("prioridad", prioridad)

        val resultadoEscritura: Long = conexionEscritura
            .insert(
                "TAREA",
                null,
                valoresAGuardar
            )
        conexionEscritura.close()
        return if (resultadoEscritura.toInt() == -1) false else true
    }

    fun consultarTarea(): ArrayList<Tarea> {
        val scriptConsultaProfesor = "SELECT * FROM TAREA "
        val baseDatosLectura = readableDatabase
        val resultaConsultaLectura = baseDatosLectura.rawQuery(scriptConsultaProfesor, null)
        val existeEstudiante = resultaConsultaLectura.moveToFirst()
        val arregloEstudiante = arrayListOf<Tarea>()

        if(existeEstudiante){
            do{
                val id = resultaConsultaLectura.getInt(0)
                if(id!=null){
                    arregloEstudiante.add(
                        Tarea(id,1,
                            resultaConsultaLectura.getString(2),
                            resultaConsultaLectura.getString(3),
                            resultaConsultaLectura.getString(4),
                            resultaConsultaLectura.getString(5),
                            resultaConsultaLectura.getString(6),

                            )
                    )
                }
            }while(resultaConsultaLectura.moveToNext())
        }
        resultaConsultaLectura.close()
        baseDatosLectura.close()
        return arregloEstudiante
    }
    fun eliminarTarea (id: Int): Boolean {

        val conexionEscritura = writableDatabase
        val resultadoEliminacion = conexionEscritura
            .delete(
                "TAREA",
                "ID_TAREA=?",
                arrayOf(id.toString())
            )
        conexionEscritura.close()
        return if (resultadoEliminacion.toInt() == -1) false else true
    }

    //crear h??bitos
    fun crearHabitoFormulario(
        idUsuario: Int,
        categoria: String,
        frecuenciaHabito: String,
        NombreHabito: String,
        descripcion: String,
        FechaInicio: String,
        fechaFin: String,
        horaH??bito: String,
        prioridad: String
    ): Boolean{
        val conexionEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("id_usuario", idUsuario)
        valoresAGuardar.put("categoria", categoria)
        valoresAGuardar.put("frecuencia", frecuenciaHabito)
        valoresAGuardar.put("nombre", NombreHabito)
        valoresAGuardar.put("descripcion", descripcion)
        valoresAGuardar.put("fechaInicio", FechaInicio)
        valoresAGuardar.put("fechaFin", fechaFin)
        valoresAGuardar.put("hora", horaH??bito)
        valoresAGuardar.put("prioridad", prioridad)

        val resultadoEscritura: Long = conexionEscritura
            .insert(
                "HABITO",
                null,
                valoresAGuardar
            )
        conexionEscritura.close()
        return if (resultadoEscritura.toInt() == -1) false else true
    }

    fun consultarHabito(): ArrayList<HabitoBDD> {
        val scriptConsultaProfesor = "SELECT * FROM HABITO "
        val baseDatosLectura = readableDatabase
        val resultaConsultaLectura = baseDatosLectura.rawQuery(scriptConsultaProfesor, null)
        val existeEstudiante = resultaConsultaLectura.moveToFirst()
        val arregloEstudiante = arrayListOf<HabitoBDD>()

        if(existeEstudiante){
            do{
                val id = resultaConsultaLectura.getInt(0)
                if(id!=null){
                    arregloEstudiante.add(
                        HabitoBDD(id,1,
                            resultaConsultaLectura.getString(2),
                            resultaConsultaLectura.getString(3),
                            resultaConsultaLectura.getString(4),
                            resultaConsultaLectura.getString(5),
                            resultaConsultaLectura.getString(6),
                            resultaConsultaLectura.getString(7),
                            resultaConsultaLectura.getString(8),
                            resultaConsultaLectura.getString(9)

                        )
                    )
                }
            }while(resultaConsultaLectura.moveToNext())
        }
        resultaConsultaLectura.close()
        baseDatosLectura.close()
        return arregloEstudiante
    }

    fun eliminarHabito (id: Int): Boolean {

        val conexionEscritura = writableDatabase
        val resultadoEliminacion = conexionEscritura
            .delete(
                "HABITO",
                "ID_HABITO=?",
                arrayOf(id.toString())
            )
        conexionEscritura.close()
        return if (resultadoEliminacion.toInt() == -1) false else true
    }

    fun actualizarHabito(){}
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
}