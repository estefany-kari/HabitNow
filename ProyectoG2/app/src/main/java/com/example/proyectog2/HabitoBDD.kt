package com.example.proyectog2

import android.os.Parcel
import android.os.Parcelable

class HabitoBDD(var idHabito:Int, var idUsuario:Int , var categoria:String?, var nombreHabito:String?, var descripcion:String?,
               var frecuencia:String?, var fechaInicio:String?,
               var fechaFin:String?, var hora:String?, var prioridad:String?):Parcelable   {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun describeContents(): Int {
            return 0
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(idHabito)
        parcel.writeInt(idUsuario)
        parcel.writeString(categoria)
        parcel.writeString(nombreHabito)
        parcel.writeString(descripcion)
        parcel.writeString(frecuencia)
        parcel.writeString(fechaInicio)
        parcel.writeString(fechaFin)
        parcel.writeString(hora)
        parcel.writeString(prioridad)

    }
    override fun toString(): String {
        return "Habito:  $idHabito \n" +
                "Categoria: $categoria \n" +
                "Nombre: $nombreHabito \n"+
                "Desc: $descripcion\n"+
                "Frecuencia: $frecuencia\n"+
                "f I: $fechaInicio\n"+
                "f f: $fechaFin\n"+
                "hora: $hora \n" +
                "fecuencia : $prioridad"
    }

    companion object CREATOR : Parcelable.Creator<HabitoBDD> {
        override fun createFromParcel(parcel: Parcel): HabitoBDD {
            return HabitoBDD(parcel)
        }

        override fun newArray(size: Int): Array<HabitoBDD?> {
            return arrayOfNulls(size)
        }
    }


}