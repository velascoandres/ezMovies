package com.example.andres.ezmoviesadmin

import android.util.Log
import android.view.View
import com.beust.klaxon.Klaxon
import com.example.andres.ezmoviesadmin.dummy.PeliculaContent
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_navegation.*
import java.util.ArrayList



fun getCategorias(categorias: ArrayList<CategoriaAPI>){
    val url = "http://${BDD.ip}/pelicula/api/generos"
    url.httpGet().responseString{request, response, result ->
        when (result) {
            is Result.Failure -> {
                val ex = result.getException()
            }
            is Result.Success -> {
                val data = result.get()
                categorias.clear()
                val wordDict = Klaxon().parseArray<CategoriaAPI>(data)
                Log.i("http", "Datos: ${wordDict.toString()}")
                if (wordDict != null) {
                    for ( categoria in wordDict.iterator()){
                        categorias.add(categoria)
                    }
                }
            }
        }
    }
}