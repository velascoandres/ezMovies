package com.example.andres.ezmoviesadmin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_modificar_actor.*


class ModificarActorActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        val helper =  SqliteHelper(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modificar_actor)

        val actor = intent.getParcelableExtra<Actor?>("actor")
        txt_actor.setText(actor?.nombre_actor)

        btn_bo_actor.setOnClickListener {
            if (actor != null) {
                helper.eliminarActor(actor.id_actor)
                regresarNavegacion()
            }
        }

        btn_crear_actor.setOnClickListener {
            if(txt_actor.text.toString()!=""){
                helper.crearActorForumlario(txt_actor.text.toString())
                regresarNavegacion()
            }

        }

        btn_modi_actor.setOnClickListener {
            if(txt_actor.text.toString()!=""){
                if (actor != null) {
                    helper.actualizarActor(actor.id_actor,txt_actor.text.toString())
                }
                regresarNavegacion()
            }
        }


    }
    fun regresarNavegacion(){
        val intent = Intent(
                this,
                NavegationActivity::class.java
        )
        startActivity(intent)
    }
}
