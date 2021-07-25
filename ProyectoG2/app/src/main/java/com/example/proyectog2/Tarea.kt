package com.example.proyectog2

import android.os.Parcel
import android.os.Parcelable
import java.util.*

class Tarea (var Idtarea:Int, var Id_usuario:Int, var categoria: String?, var nombreTarea: String?,
             var fechaRecordatorio:String?, var horaRecordatorio:String?,var prioridad:String?):Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(Idtarea)
        parcel.writeInt(Id_usuario)
        parcel.writeString(categoria)
        parcel.writeString(nombreTarea)
        parcel.writeString(fechaRecordatorio)
        parcel.writeString(horaRecordatorio)
        parcel.writeString(prioridad)

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