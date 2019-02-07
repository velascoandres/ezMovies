package com.example.andres.ezmoviesadmin

import android.os.Parcel
import android.os.Parcelable


class Pelicula_S(
        val id: Int?,
        val nombre: String,
        val caratula: String,
        val descripcion: String,
        val generos:ArrayList<Int>?,
        val costo:String
        ):Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readSerializable() as ArrayList<Int>?,
            parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(nombre)
        parcel.writeString(caratula)
        parcel.writeString(descripcion)
        parcel.writeSerializable(generos)
        parcel.writeString(costo)
    }

    override fun describeContents(): Int {
        return 0
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