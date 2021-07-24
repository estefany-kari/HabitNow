package com.example.proyectog2

import android.os.Parcel
import android.os.Parcelable
import java.util.*

class Tarea (var Idtarea:Int, var nombreTarea: String?, var categoria: String?, var prioridad:String?,
             var fechaRecordatorio:Int, var horaRecordatorio:String?):Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(Idtarea)
        parcel.writeString(nombreTarea)
        parcel.writeString(categoria)
        parcel.writeString(prioridad)
        parcel.writeInt(fechaRecordatorio)
        parcel.writeString(horaRecordatorio)
    }
    override fun toString(): String {
        return "Tarea:  $nombreTarea \n" +
                "Tarea "+
                "Hora:     $horaRecordatorio \n"


    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Tarea> {
        override fun createFromParcel(parcel: Parcel): Tarea {
            return Tarea(parcel)
        }

        override fun newArray(size: Int): Array<Tarea?> {
            return arrayOfNulls(size)
        }
    }

}