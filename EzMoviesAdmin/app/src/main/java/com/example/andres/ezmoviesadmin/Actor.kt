package com.example.andres.ezmoviesadmin

import android.os.Parcel
import android.os.Parcelable

class ActorAPI(val id:Int,val nombre: String,val peliculas: ArrayList<Int>?)

class Actor(val id:String,val nombre:String,val peliculas: ArrayList<Int>?):Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readSerializable() as ArrayList<Int>?
            ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(nombre)
        parcel.writeSerializable(peliculas)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Actor> {
        override fun createFromParcel(parcel: Parcel): Actor {
            return Actor(parcel)
        }

        override fun newArray(size: Int): Array<Actor?> {
            return arrayOfNulls(size)
        }
    }
}