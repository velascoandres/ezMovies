package com.example.andres.ezmoviesadmin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_modificar_categoria.*


class ModificarCategoriaActivity : AppCompatActivity() {
    val helper =  SqliteHelper(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modificar_categoria)
        val categoria = intent.getParcelableExtra<Categoria?>("categoria")
        txt_categoria.setText(categoria?.nombre_categoria)

        btn_crear_categoria.setOnClickListener {
            crearCategoria(txt_categoria.text.toString())
        }

        btn_modificar_categoria.setOnClickListener{
            if (categoria != null) {
                if(helper.actualizarCategoria(categoria.id,txt_categoria.text.toString())){
                    regresarNavegacion()
                }else{
                    Log.i("UPDATE","FALLO!!")
                }
            }
        }

        btn_borrar_categoria.setOnClickListener {
            if (categoria != null) {
                helper.eliminarCategoria(categoria.id)
                regresarNavegacion()
            }
        }
    }

    fun crearCategoria(nombre:String){
        if(helper.crearCategoriaForumlario(nombre)){
            this.regresarNavegacion();
        }else{
            Log.i("CREAR","Fallo")
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
