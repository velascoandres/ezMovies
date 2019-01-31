package com.example.andres.ezmoviesadmin

import android.os.Parcel
import android.os.Parcelable

class Categoria(val id: String, val nombre_categoria: String):Parcelable{
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(nombre_categoria)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return this.nombre_categoria
    }

    companion object CREATOR : Parcelable.Creator<Categoria> {
        override fun createFromParcel(parcel: Parcel): Categoria {
            return Categoria(parcel)
        }

        override fun newArray(size: Int): Array<Categoria?> {
            return arrayOfNulls(size)
        }
    }

}