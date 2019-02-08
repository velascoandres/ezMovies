package com.example.andres.ezmoviesadmin

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.util.Log
import android.view.View
import com.beust.klaxon.Klaxon
import com.example.andres.ezmoviesadmin.BDD.Companion.actores
import com.example.andres.ezmoviesadmin.BDD.Companion.categorias
import com.example.andres.ezmoviesadmin.BDD.Companion.ip
import com.example.andres.ezmoviesadmin.BDD.Companion.peliculas
import com.example.andres.ezmoviesadmin.dummy.PeliculaContent
import com.github.kittinunf.fuel.*
import com.github.kittinunf.fuel.core.Method
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

fun cargarDatosGenero(url:String,funcion_intent: () -> Unit){
    url.httpGet().responseString{request, response, result ->
        when (result) {
            is Result.Failure -> {
                val ex = result.getException()
                Log.i("http", ex.toString())
            }
            is Result.Success -> {
                val data = result.get()
                categorias.clear()
                val wordDict = Klaxon().parseArray<CategoriaAPI>(data)
                Log.i("http", "Datos: ${wordDict.toString()}")
                if (wordDict != null) {
                    for ( item in wordDict.iterator()){
                        categorias.add(item)
                    }
                }

                funcion_intent()

            }
        }
    }
}

fun cargarDatosActor(url:String,funcion_intent: () -> Unit){
    url.httpGet().responseString{request, response, result ->
        when (result) {
            is Result.Failure -> {
                val ex = result.getException()
                Log.i("http", ex.toString())
            }
            is Result.Success -> {
                val data = result.get()
                actores.clear()
                val wordDict = Klaxon().parseArray<ActorAPI>(data)
                Log.i("http", "Datos: ${wordDict.toString()}")
                if (wordDict != null) {
                    for ( item in wordDict.iterator()){
                        actores.add(item)
                    }
                }

                funcion_intent()

            }
        }
    }
}


fun actualizarPelicula(parametros:String,id:String,funcion_intent: () -> Unit){
    val direccion = "http://$ip/pelicula/api/pelicula/${id}/update"
    Log.i("actualizar ",direccion)
    val url = direccion.httpPut().body(parametros, Charsets.UTF_8)
            .header("Content-Type" to "application/json")
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


fun mensaje_dialogo(actividad:Activity,contenido:String,funcion: () -> Unit){
    val builder = AlertDialog.Builder(actividad)

    builder
            .setMessage(contenido)
            .setPositiveButton(
                    "Si",
                    DialogInterface.OnClickListener { dialog, which ->
                        funcion()
                    }
            )
            .setNegativeButton(
                    "No",null
            )


    val dialogo = builder.create()
    dialogo.show()
    dialogo.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLUE)
    dialogo.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.BLUE)
}

fun mensaje(actividad:Activity,tipo: String,contenido:String){

    com.tapadoo.alerter.Alerter.create(actividad)
            .setTitle(tipo)
            .setText(contenido)
            .show()
}

fun borrarElemento(contexto:Activity,url:String,funcion_intent: () -> Unit){
    mensaje_dialogo(contexto,"Desea eliminar el elemento",
            fun (){

                val direccion = url
                Log.i("http",direccion)
                val url = direccion
                        .httpDelete()
                        .responseString { request, response, result ->
                            when (result) {
                                is Result.Failure -> {
                                    val ex = result.getException()
                                    Log.i("http-p", ex.toString())
                                    mensaje(contexto,"error","Datos no validos")

                                }
                                is Result.Success -> run {
                                    val data = result.get()
                                    Log.i("http-p", data)
                                    mensaje(contexto,"Aceptado","Datos validos, espere...")
                                    funcion_intent()
                                }
                            }
                        }




            })
}

fun actualizar(parametros:String,
               url:String,
               url_success:String,
               funcion_intent: () -> Unit,
               funcion_load_data: (redirect_url:String,funcion:()->Unit) -> Unit
){
    val direccion = url
    Log.i("actualizar ",direccion)
    val url = direccion.httpPut().body(parametros, Charsets.UTF_8)
            .header("Content-Type" to "application/json")
            .responseString { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("http-p", ex.toString())
                    }
                    is Result.Success -> run {
                        val data = result.get()
                        Log.i("http-p", data)
                        val redire = url_success
                        funcion_load_data(redire,funcion_intent)
                    }
                }
            }
}

fun crear(parametros:String,
          url:String,url_success:String,
          funcion_intent: () -> Unit,
          funcion_load_data: (redirect_url:String,funcion:()->Unit) -> Unit){
    val direccion = url
    Log.i("crear ",direccion)
    val url = direccion.httpPost().body(parametros, Charsets.UTF_8)
            .header("Content-Type" to "application/json")
            .responseString { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("http-p", ex.toString())
                    }
                    is Result.Success -> run {
                        val data = result.get()
                        Log.i("http-p", data)
                        val redire = url_success
                        funcion_load_data(redire,funcion_intent)
                    }
                }
            }
}