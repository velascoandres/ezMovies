package com.example.andres.ezmoviesadmin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_modificar_categoria.*
import kotlinx.android.synthetic.main.activity_modificar_pelicula.*


class ModificarCategoriaActivity : AppCompatActivity() {
    val helper =  SqliteHelper(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modificar_categoria)
        val categoria = intent.getParcelableExtra<Categoria?>("categoria")
        txt_categoria.setText(categoria?.nombre_categoria)
        txt_id_genero.text = categoria?.id


        btn_guardar_genero.setOnClickListener{
            if(txt_id_genero.text.toString()!=""){
                actualizarGenero(txt_id_genero.text.toString())
            }else{
                crearGenero(txt_categoria.text.toString())
            }
        }

        btn_borrar_categoria.setOnClickListener {
            if(txt_id_genero.text.toString()!=""){
                val url = "http://${BDD.ip}/pelicula/api/generos/${txt_id_genero.text}/delete"
                borrarElemento(this,url,::regresarNavegacion)
            }else{
                regresarNavegacion()
            }
        }
    }

    fun crearGenero(nombre:String){
        val parametro = """{"nombre":"$nombre"}"""
        Log.i("crear",parametro)
        val url = "http://${BDD.ip}/pelicula/api/generos"
        val url_success = "http://${BDD.ip}/pelicula/api/generos"

        crear(
                parametro,
                url,
                url_success,
                ::regresarNavegacion,
                ::cargarDatosGenero
        )
    }

    fun actualizarGenero(id:String){
        val nombre = txt_categoria.text.toString()
        val parametro = """{"nombre":"$nombre"}"""
        val url = "http://${BDD.ip}/pelicula/api/generos/$id/update"
        val url_success = "http://${BDD.ip}/pelicula/api/generos"

        actualizar(
                parametro,
                url,
                url_success,
                ::regresarNavegacion,
                ::cargarDatosGenero
        )
    }


    fun regresarNavegacion(){
        finish()
        val intent = Intent(
                this,
                NavegationActivity::class.java
        )
        startActivity(intent)
    }


}
