package com.example.andres.ezmoviesadmin.dummy

import android.media.Image
import com.example.andres.ezmoviesadmin.BDD.Companion.peliculas
import java.util.HashMap

/**
 * Helper class for providing sample nombre for user interfaces created by
 * Android template wizards.
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object PeliculaContent {

    /**
     * An array of sample (dummy) items.
     */

    var ITEMS: MutableList<Pelicula>

    /**
     * A map of sample (dummy) items, by ID.
     */
    val ITEM_MAP: MutableMap<String, Pelicula> = HashMap()

    private val COUNT = 25

    init {
        ITEMS = peliculas
        // Add some sample items.

        /*for (i in 1..COUNT) {
            addItem(createDummyItem(i))
        }*/
    }



    /**
     * A dummy item representing a piece of nombre.
     */
    data class Pelicula(val id: Int, val caratula:String,val nombre: String, val descripcion: String,
                        val costo:String, val generos: ArrayList<Int>) {
        override fun toString(): String = nombre
    }
}
