package com.example.proyectog2

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class SQLiteHelper(context: Context?): SQLiteOpenHelper(context,"habitos.db",null,1) {

    override fun onCreate(db: SQLiteDatabase?) {
        val scriptCrearTablaUsuario=
            """
            CREATE TABLE USUARIO(
            ID_USUARIO INTEGER PRIMARY KEY AUTOINCREMENT,
            NOMBRE VARCHAR(50),
            NOMBREUSUARIO VARCHAR(50),
            CONTRASEÑA VARCHAR(16),
            FECHANACIMIENTO VARCHAR(15),
            );
            """.trimIndent()
        Log.i("bdd", "Creacion tabla usuario")
        db?.execSQL(scriptCrearTablaUsuario)

        val scriptCrearTablaTareas=
            """
            CREATE TABLE TAREA(
            ID_TAREA INTEGER PRIMARY KEY AUTOINCREMENT,
            ID_PROF INTEGER,
            NOMBRE VARCHAR(50),
            FECHA INTEGER,
            HORA VARCHAR(5),
            PRIORIDAD VARCHAR(15),
            FOREIGN KEY(ID_PROF) REFERENCES PROFESOR(ID_PROFESOR) 
            );
            """.trimIndent()
        Log.i("bdd", "Creacion tabla Tarea")
        db?.execSQL(scriptCrearTablaTareas)

        val scriptCrearTablaHabitos=
            """
            CREATE TABLE HABITO(
            ID_HABITO INTEGER PRIMARY KEY AUTOINCREMENT,
            ID_PROF INTEGER,
            NOMBRE VARCHAR(50),
            DESCRIPCION VARCHAR(50),
            FRECUENCIA VARCHAR(5),
            FECHAINICIO VARCHAR(15),
            FECHAFIN VARCHAR(15)
            HORA VARCHAR(5),
            PRIORIDAD VARCHAR(15),
            FOREIGN KEY(ID_PROF) REFERENCES PROFESOR(ID_PROFESOR) 
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
            val usuarioEncontrado = UsuarioBDD(0,"","","","")
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
    

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
}