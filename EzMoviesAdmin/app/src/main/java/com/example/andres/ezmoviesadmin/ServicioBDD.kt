package com.example.andres.ezmoviesadmin

import com.example.andres.ezmoviesadmin.dummy.PeliculaContent

class BDD{
    companion object {
        val peliculas = ArrayList<PeliculaContent.Pelicula>()
        val categorias = ArrayList<Categoria>()
        val actores = ArrayList<Actor>()
        var comentarios = ArrayList<Comentario>()
    }
}