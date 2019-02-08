package com.example.andres.ezmoviesadmin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.CheckBox
import kotlinx.android.synthetic.main.activity_modificar_actor.*
import kotlinx.android.synthetic.main.activity_modificar_categoria.*
import kotlinx.android.synthetic.main.activity_modificar_pelicula.*
import java.util.ArrayList


class ModificarActorActivity : AppCompatActivity() {



    var lista = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        val helper =  SqliteHelper(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modificar_actor)

        val actor = intent.getParcelableExtra<Actor?>("actor")
        txt_actor.setText(actor?.nombre)
        txt_id_actor.text = actor?.id



        for (pelicula in BDD.peliculas) {

            val checkBox = CheckBox(this)
            checkBox.text = "${pelicula.nombre}"
            checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
                if(isChecked){
                    lista.add(pelicula.id)
                }else{
                    lista.remove(pelicula.id)
                }
                Log.i("check",lista.toString())
            }

            //var p = listOf("a" to "b", "n" to listOf("a" to "b"))
            linearLayoutActor.addView(checkBox)
        }


        btn_bo_actor.setOnClickListener {
            if(txt_id_actor.text.toString()!=""){
                val url = "http://${BDD.ip}/pelicula/api/actor/${txt_id_actor.text}/delete"
                borrarElemento(this,url,::regresarNavegacion)
            }else{
                regresarNavegacion()
            }
        }



        btn_modi_actor.setOnClickListener {
            if(txt_id_actor.text.toString()!=""){
                actualizarActor(txt_id_actor.text.toString())
            }else{
                crearActor()
            }
        }


    }
    fun actualizarActor(id:String){
        val nombre = txt_actor.text.toString()
        val parametro = """{"nombre":"$nombre", "peliculas": $lista}"""
        val url = "http://${BDD.ip}/pelicula/api/actor/$id/update"
        val url_success = "http://${BDD.ip}/pelicula/api/actor"

        actualizar(
                parametro,
                url,
                url_success,
                ::regresarNavegacion,
                ::cargarDatosActor
        )
    }

    fun crearActor(){
        val nombre = txt_actor.text.toString()
        val parametro = """{"nombre":"$nombre", "peliculas": $lista}"""
        val url = "http://${BDD.ip}/pelicula/api/actor"
        val url_success = "http://${BDD.ip}/pelicula/api/actor"

        crear(
                parametro,
                url,
                url_success,
                ::regresarNavegacion,
                ::cargarDatosActor
        )
    }


    fun regresarNavegacion(){
        val intent = Intent(
                this,
                NavegationActivity::class.java
        )
        startActivity(intent)
    }
}
