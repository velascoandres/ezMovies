package com.example.andres.ezmoviesadmin

import com.example.andres.ezmoviesadmin.dummy.PeliculaContent

class BDD{
    companion object {
        val ip = "172.29.23.198:80"
        val peliculas = ArrayList<PeliculaContent.Pelicula>()
        val categorias = ArrayList<CategoriaAPI>()
        val actores = ArrayList<Actor>()
        var comentarios = ArrayList<Comentario>()
    }
}