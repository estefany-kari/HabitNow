package com.example.proyectog2

import android.os.Parcel
import android.os.Parcelable

class UsuarioBDD(var idUsuario:Int, var nombre:String?,
                 var nombreUsuario:String?, var contraseña:String?, var fechaNacimiento:String?):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
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
        parcel.writeInt(idUsuario)
        parcel.writeString(nombre)
        parcel.writeString(nombreUsuario)
        parcel.writeString(contraseña)
        parcel.writeString(fechaNacimiento)
    }


    companion object CREATOR : Parcelable.Creator<UsuarioBDD> {
        override fun createFromParcel(parcel: Parcel): UsuarioBDD {
            return UsuarioBDD(parcel)
        }

        override fun newArray(size: Int): Array<UsuarioBDD?> {
            return arrayOfNulls(size)
        }
    }
}