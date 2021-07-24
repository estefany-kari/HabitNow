package com.example.proyectog2

import android.database.sqlite.SQLiteOpenHelper

class BaseDeDatos {
    companion object{
        var TablaUsuario: SQLiteOpenHelper? = null
        var TablaTarea: SQLiteOpenHelper? = null
        var TablaHabito: SQLiteOpenHelper? = null

    }
}