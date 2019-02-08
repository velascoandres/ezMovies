package com.example.andres.ezmoviesadmin

import android.util.Log
import android.view.View
import com.beust.klaxon.Klaxon
import com.example.andres.ezmoviesadmin.BDD.Companion.ip
import com.example.andres.ezmoviesadmin.BDD.Companion.peliculas
import com.example.andres.ezmoviesadmin.dummy.PeliculaContent
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPut
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


fun cargarDatosPelicula(url:String,funcion_intent: () -> Unit){
    url.httpGet().responseString{request, response, result ->
        when (result) {
            is Result.Failure -> {
                val ex = result.getException()
                Log.i("http", ex.toString())
            }
            is Result.Success -> {
                val data = result.get()
                peliculas.clear()
                val wordDict = Klaxon().parseArray<PeliculaContent.Pelicula>(data)
                Log.i("http", "Datos: ${wordDict.toString()}")
                if (wordDict != null) {
                    for ( item in wordDict.iterator()){
                        peliculas.add(item)
                    }
                }

                funcion_intent()

            }
        }
    }
}


fun actualizarPelicula(parametros:List<Pair<String,Any>>,id:String,funcion_intent: () -> Unit){
    val direccion = "http://$ip/pelicula/api/pelicula/${id}/update"
    Log.i("actualizar ",direccion)
    val url = direccion
            .httpPut(parametros)
            .responseString { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("http-p", ex.toString())

                    }
                    is Result.Success -> run {
                        val data = result.get()
                        Log.i("http-p", data)
                        val redire = "http://$ip/pelicula/api/pelicula"
                        cargarDatosPelicula(redire,funcion_intent)
                    }
                }
            }
}