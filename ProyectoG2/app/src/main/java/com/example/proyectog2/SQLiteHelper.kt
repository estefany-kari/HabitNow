package com.example.proyectog2

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.util.ArrayList

class SQLiteHelper(context: Context?): SQLiteOpenHelper(context,"habitos.db",null,1) {

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
            NOMBRE VARCHAR(50),
            FECHA VARCHAR(15),
            HORA VARCHAR(5),
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
            val scriptConsultarUsuario = "SELECT NOMBREUSUARIO, CONTRASEÑA FROM USUARIO WHERE NOMBREUSUARIO = ${usuario} AND CONTRASEÑA =${clave}"
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
                        usuarioEncontrado.contraseña =claveUsuario
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
        NombreTarea: String,
        FechaTarea: String,
        horaTarea: String,
        prioridad: String
    ): Boolean{
        val conexionEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("id_usuario", idUsuario)
        valoresAGuardar.put("nombre", NombreTarea)
        valoresAGuardar.put("fecha", FechaTarea)
        valoresAGuardar.put("hora", horaTarea)
        valoresAGuardar.put("prioridada", prioridad)

        val resultadoEscritura: Long = conexionEscritura
            .insert(
                "TAREA",
                null,
                valoresAGuardar
            )
        conexionEscritura.close()
        return if (resultadoEscritura.toInt() == -1) false else true
    }

    //crear hábitos
    fun crearHabitoCategoria(
        categoria: String
    ): Boolean{
        val conexionEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("categoria", categoria)

        val resultadoEscritura: Long = conexionEscritura
            .insert(
                "HABITO",
                null,
                valoresAGuardar
            )
        conexionEscritura.close()
        return if (resultadoEscritura.toInt() == -1) false else true}

    fun crearFrecuenciaHabito(
        frecuenciaHabito: String
    ): Boolean{
        val conexionEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("frecuencia", frecuenciaHabito)

        val resultadoEscritura: Long = conexionEscritura
            .insert(
                "HABITO",
                null,
                valoresAGuardar
            )
        conexionEscritura.close()
        return if (resultadoEscritura.toInt() == -1) false else true}
    fun crearNombreHabito(
        NombreHabito: String,
        descripcion: String,
    ): Boolean{
        val conexionEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("nombre", NombreHabito)
        valoresAGuardar.put("descripcion", descripcion)
        val resultadoEscritura: Long = conexionEscritura
            .insert(
                "HABITO",
                null,
                valoresAGuardar
            )
        conexionEscritura.close()
        return if (resultadoEscritura.toInt() == -1) false else true
    }

    fun crearHabitoFormulario(
        idUsuario: Int,
        FechaInicio: String,
        fechaFin: String,
        horaHábito: String,
        prioridad: String
    ): Boolean{
        val conexionEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("id_usuario", idUsuario)
        valoresAGuardar.put("fechaInicio", FechaInicio)
        valoresAGuardar.put("fechaFin", fechaFin)
        valoresAGuardar.put("hora", horaHábito)
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
        val scriptConsultarProf = "SELECT * FROM HABITO"
        val baseDatosLectura = readableDatabase
        val resultaConsultaLectura = baseDatosLectura.rawQuery(scriptConsultarProf, null)
        val existeUsuario = resultaConsultaLectura.moveToFirst()
        val arregloProfesor = arrayListOf<HabitoBDD>()

        if(existeUsuario){
            do{
                val id = resultaConsultaLectura.getInt(0)
                if(id!=null){
                    arregloProfesor.add(
                        HabitoBDD(id,
                            resultaConsultaLectura.getString(1),
                            resultaConsultaLectura.getString(2),
                            resultaConsultaLectura.getString(3),
                            resultaConsultaLectura.getString(4),
                            resultaConsultaLectura.getString(5),
                            resultaConsultaLectura.getString(6),
                            resultaConsultaLectura.getString(7),
                            resultaConsultaLectura.getString(8)
                        ))
                }
                println("${id}")
            }while(resultaConsultaLectura.moveToNext())
        }

        resultaConsultaLectura.close()
        baseDatosLectura.close()
        return arregloProfesor
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
}