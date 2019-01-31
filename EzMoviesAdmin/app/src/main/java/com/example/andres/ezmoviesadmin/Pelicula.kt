package com.example.andres.ezmoviesadmin

import android.os.Parcel
import android.os.Parcelable

class Pelicula_S(val id: String, val nombre: String, val id_categoria: String,val nombre_categoria: String):Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(nombre)
        parcel.writeString(id_categoria)
        parcel.writeString(nombre_categoria)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return this.nombre
    }

    companion object CREATOR : Parcelable.Creator<Pelicula_S> {
        override fun createFromParcel(parcel: Parcel): Pelicula_S {
            return Pelicula_S(parcel)
        }

        override fun newArray(size: Int): Array<Pelicula_S?> {
            return arrayOfNulls(size)
        }
    }

}