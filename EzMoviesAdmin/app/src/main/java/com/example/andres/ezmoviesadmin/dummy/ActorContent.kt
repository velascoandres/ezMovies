package com.example.andres.ezmoviesadmin.dummy

import com.example.andres.ezmoviesadmin.Actor
import com.example.andres.ezmoviesadmin.BDD.Companion.actores
import java.util.ArrayList
import java.util.HashMap

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object ActorContent {


    val ITEMS: MutableList<Actor>
    val ITEM_MAP: MutableMap<String, Actor> = HashMap()
    private val COUNT = 25
    init {
        ITEMS = actores
    }

}
