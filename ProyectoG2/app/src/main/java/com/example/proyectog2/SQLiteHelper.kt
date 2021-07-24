package com.example.proyectog2

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
            FECUENCIA VARCHAR(5),
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
    fun CrearUsuario(){

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}