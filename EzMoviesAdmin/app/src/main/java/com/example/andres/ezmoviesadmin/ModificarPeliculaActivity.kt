package com.example.andres.ezmoviesadmin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_modificar_pelicula.*
import java.lang.reflect.Array

class ModificarPeliculaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modificar_pelicula)
        val helper = SqliteHelper(this)
        helper.getCategorias(BDD.categorias)
        var id_categoria:String = "1"

        val pelicula = intent.getParcelableExtra<Pelicula_S?>("pelicula")

        txt_nombre.setText(pelicula?.nombre)


        var arregloCategorias = BDD.categorias
        val adaptadorCategorias=ArrayAdapter<Categoria>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                arregloCategorias

        )



        spinner_categoria.adapter = adaptadorCategorias
        if (pelicula != null) {
            spinner_categoria.setSelection(setSpinnerPosition(pelicula.nombre_categoria,arregloCategorias))
        }
        spinner_categoria
                .onItemSelectedListener=
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                            parent: AdapterView<*>?,
                            view: View?,
                            position: Int,
                            id: Long) {
                        val categoria = arregloCategorias[position]
                        id_categoria = categoria.id
                    }

                    override fun onNothingSelected(
                            parent: AdapterView<*>?) {
                        Log.i("adaptador", "${parent}")
                    }
                }


            btn_update.setOnClickListener {
                if (pelicula != null) {
                    if(helper.actualizarPelicula(pelicula.id,txt_nombre.text.toString(),id_categoria)){
                        Log.i("UPDATE","Exito")
                        regresarNavegacion()
                    }else{
                        Log.i("UPDATE","Fallo")
                    }

                }
            }

            btn_crear.setOnClickListener{
                if(helper.crearPeliculaFormulario(txt_nombre.text.toString(),id_categoria)){
                    Log.i("CREAR","Exito")
                    regresarNavegacion()
                }else{
                    Log.i("CREAR","Fallo")
                }
            }

        btn_borrar.setOnClickListener {
            if (pelicula != null) {
                if(helper.eliminarPelicula(pelicula.id)){
                    Log.i("DELETE","Exito")
                    regresarNavegacion()
                }else{
                    Log.i("DELETE","Fallo")
                }

            }
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
