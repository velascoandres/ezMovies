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
import com.example.andres.ezmoviesadmin.BDD.Companion.categorias
import com.example.andres.ezmoviesadmin.dummy.PeliculaContent
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_modificar_pelicula.*
import java.lang.reflect.Array
import com.example.andres.ezmoviesadmin.R.id.imageView
import android.graphics.drawable.BitmapDrawable
import android.graphics.Bitmap
import android.content.ContentValues
import android.content.Context
import android.content.ContextWrapper
import android.net.Uri
import android.provider.MediaStore
import java.io.*
import java.util.*


class ModificarPeliculaActivity : AppCompatActivity() {
    var id = 0
    var lista = ArrayList<Int>()
    var caratula = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modificar_pelicula)
        val helper = SqliteHelper(this)
        var id_categoria:String = "1"

        val pelicula = intent.getParcelableExtra<Pelicula_S?>("pelicula")

        txt_nombre.setText(pelicula?.nombre)
        txt_desc.setText(pelicula?.descripcion)
        txt_costo.setText(pelicula?.costo)
        txt_id_pelicula.text = pelicula?.id.toString()
        caratula = pelicula?.caratula!!

        Picasso.get()
                .load(pelicula.caratula)
                .resize(256,512)
                .centerCrop()
                .into(caratula_img)

        for (genero in categorias) {

            val checkBox = CheckBox(this)
            checkBox.text = "${genero.nombre}"
            checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
                if(isChecked){
                    lista.add(genero.id.toInt())
                }else{
                    lista.remove(genero.id.toInt())
                }
                Log.i("check",lista.toString())
            }

            //var p = listOf("a" to "b", "n" to listOf("a" to "b"))
            GenerosLayout.addView(checkBox)
        }
        var arregloCategorias = BDD.categorias

        btn_update.setOnClickListener {
            actualizar()
        }

        btn_borrar.setOnClickListener {
            var url = "http://${BDD.ip}/pelicula/api/pelicula/${txt_id_pelicula.text}/delete"
            borrarElemento(this,url,::regresarNavegacion)

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

    fun actualizar(){
        val id = txt_id_pelicula.text.toString().toInt()
        val nombre = txt_nombre.text.toString()
        val descripcion = txt_desc.text.toString()
        val costo = txt_costo.text.toString()
        val caratula = caratula
        val pelicula = PeliculaContent.Pelicula(id=id,nombre = nombre,descripcion =  descripcion,costo = costo,caratula =  caratula, generos = lista)


        val parametros = listOf("id" to pelicula.id, "nombre" to pelicula.nombre, "descripcion" to pelicula.descripcion,
                "costo" to pelicula.costo.toDouble(), "generos" to lista)

        val para = """{"nombre": "${pelicula.nombre}","descripcion": "${pelicula.descripcion}",
            |"costo": "${pelicula.costo}", "generos": ${pelicula.generos}}""".trimMargin()
        Log.i("parametros", para)
        actualizarPelicula(parametros = para,id = id.toString(), funcion_intent = ::regresarNavegacion)
    }
    fun regresarNavegacion(){
        val intent = Intent(
                this,
                NavegationActivity::class.java
        )
        startActivity(intent)
    }

    private fun bitmapToFile(bitmap:Bitmap): Uri {
        // Get the context wrapper
        val wrapper = ContextWrapper(applicationContext)

        // Initialize a new file instance to save bitmap object
        var file = wrapper.getDir("Images", Context.MODE_PRIVATE)
        file = File(file,"${UUID.randomUUID()}.jpg")

        try{
            // Compress the bitmap and save in jpg format
            val stream:OutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream)
            stream.flush()
            stream.close()
        }catch (e: IOException){
            e.printStackTrace()
        }

        // Return the saved bitmap uri
        return Uri.parse(file.absolutePath)
    }
}
