package com.example.andres.ezmoviesadmin.dummy

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

    private fun addItem(item: Pelicula) {
        ITEMS.add(item)
        ITEM_MAP.put(item.id, item)
    }


    private fun makeDetails(position: Int): String {
        val builder = StringBuilder()
        builder.append("Details about Item: ").append(position)
        for (i in 0..position - 1) {
            builder.append("\nMore categoria_id information here.")
        }
        return builder.toString()
    }

    /**
     * A dummy item representing a piece of nombre.
     */
    data class Pelicula(val id: String, val nombre: String, val categoria_id: String,val categoria_nombre: String) {
        override fun toString(): String = nombre
    }
}
