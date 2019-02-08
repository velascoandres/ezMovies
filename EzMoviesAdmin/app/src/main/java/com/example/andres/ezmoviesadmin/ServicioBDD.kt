package com.example.andres.ezmoviesadmin

import com.example.andres.ezmoviesadmin.dummy.PeliculaContent

class BDD{
    companion object {
        val ip = "192.168.1.51:80"
        val peliculas = ArrayList<PeliculaContent.Pelicula>()
        val categorias = ArrayList<CategoriaAPI>()
        val actores = ArrayList<ActorAPI>()
        var comentarios = ArrayList<Comentario>()
    }
}