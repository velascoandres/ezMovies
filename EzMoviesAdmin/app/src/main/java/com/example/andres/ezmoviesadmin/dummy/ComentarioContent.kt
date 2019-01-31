package com.example.andres.ezmoviesadmin.dummy

import com.example.andres.ezmoviesadmin.BDD.Companion.comentarios
import com.example.andres.ezmoviesadmin.Comentario
import java.util.ArrayList
import java.util.HashMap

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object ComentarioContent {

    /**
     * An array of sample (dummy) items.
     */
    val ITEMS: MutableList<Comentario>

    /**
     * A map of sample (dummy) items, by ID.
     */
    val ITEM_MAP: MutableMap<String, Comentario> = HashMap()

    private val COUNT = 25

    init {
        ITEMS = comentarios
    }


}
