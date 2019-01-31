package com.example.andres.ezmoviesadmin.dummy

import com.example.andres.ezmoviesadmin.BDD.Companion.categorias
import com.example.andres.ezmoviesadmin.Categoria
import java.util.ArrayList
import java.util.HashMap

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object CategoriaContent {

    /**
     * An array of sample (dummy) items.
     */
    val ITEMS: MutableList<Categoria>

    /**
     * A map of sample (dummy) items, by ID.
     */
    val ITEM_MAP: MutableMap<String,Categoria> = HashMap()

    private val COUNT = 25

    init {
        ITEMS = categorias
        // Add some sample items.

    }
/*
    private fun addItem(item: Categoria) {
        ITEMS.add(item)
        ITEM_MAP.put(item.id, item)
    }


    private fun makeDetails(position: Int): String {
        val builder = StringBuilder()
        builder.append("Details about Item: ").append(position)
        for (i in 0..position - 1) {
            builder.append("\nMore details information here.")
        }
        return builder.toString()
    }*/

    /**
     * A dummy item representing a piece of content.
     */

}
