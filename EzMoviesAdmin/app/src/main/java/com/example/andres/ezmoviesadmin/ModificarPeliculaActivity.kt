package com.example.andres.ezmoviesadmin

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.LinearLayout
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_modificar_pelicula.*
import java.lang.reflect.Array

class ModificarPeliculaActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modificar_pelicula)
        val helper = SqliteHelper(this)
        helper.getCategorias(BDD.categorias)
        var id_categoria:String = "1"

        val pelicula = intent.getParcelableExtra<Pelicula_S?>("pelicula")

        txt_nombre.setText(pelicula?.nombre)
        txt_desc.setText(pelicula?.descripcion)
        txt_costo.setText(pelicula?.costo)

        Picasso.get()
                .load(pelicula?.caratula)
                .resize(256,256)
                .centerCrop()
                .into(caratula_img)

        var lista = ArrayList<Int>()
        for (i in 0 until 5) {

            val checkBox = CheckBox(this)
            checkBox.text = "titulo"
            checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
                if(isChecked){
                    lista.add(i)
                }else{
                    lista.remove(i)
                }
                Log.i("check",lista.toString())
            }

            //var p = listOf("a" to "b", "n" to listOf("a" to "b"))
            GenerosLayout.addView(checkBox)
        }
        var arregloCategorias = BDD.categorias
        val adaptadorCategorias=ArrayAdapter<Categoria>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                arregloCategorias

        )

        btn_update.setOnClickListener {

        }

        btn_borrar.setOnClickListener {
        }

        btn_load.setOnClickListener{
            val intent = Intent()
                    .setType("*/*")
                    .setAction(Intent.ACTION_GET_CONTENT)
            startActivityForResult(Intent.createChooser(intent, "Select a file"), 111)
        }
        /*
            File f = new File(Environment.getExternalStorageDirectory()
                 + File.separator + "test.jpg");
     f.createNewFile();
     //write the bytes in file
     FileOutputStream fo = new FileOutputStream(f);
     fo.write(outStream.toByteArray());
     // remember close the FileOutput
     fo.close();

         */


    }

    fun setSpinnerPosition(categoria: String,arreglo:ArrayList<Categoria>):Int{
        var i=0;
        for (item in arreglo){
            if (categoria.equals(item.nombre_categoria)){
                return i
            }
            i++
        }
        return i
    }

    fun regresarNavegacion(){
        val intent = Intent(
                this,
                NavegationActivity::class.java
        )
        startActivity(intent)
    }
}
