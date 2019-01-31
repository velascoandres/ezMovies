package com.example.andres.ezmoviesadmin

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_navegation.*
import java.util.*
import kotlin.concurrent.schedule
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import java.net.URL


class NavegationActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                crearFragmentoUno()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                crearFragmentoDos()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                crearFragmentoTres()
                return@OnNavigationItemSelectedListener true
            }
            R.id.comments_notifications ->{
                getComentarios(BDD.comentarios)
                crearFragmentoCuatro()

                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
    lateinit var fragmentoActual: Fragment
    lateinit var contexto: NavegationActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navegation)
        fragmentoActual = PeliculaFragment()
        crearFragmentoUno()
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    override fun onResume() {
        super.onResume()
        fragmentoActual = PeliculaFragment()
        crearFragmentoUno()

    }
    fun destruirFragmentoActual() {
        val fragmentManager = supportFragmentManager
        // Transacciones
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.remove(fragmentoActual)
        fragmentTransaction.commit()
    }

    fun crearFragmentoUno() {

        destruirFragmentoActual()


        // Manager
        val fragmentManager = supportFragmentManager

        // Transacciones
        val fragmentTransaction = fragmentManager.beginTransaction()

        // Crear instancia de fragmento
        val primerFragmento = PeliculaFragment()



        // Anadir fragmento

        // fragmentTransaction.remove(fragmentoActual)
        fragmentTransaction.replace(R.id.relativeLayout, primerFragmento)

        fragmentoActual = primerFragmento


        // Commit
        fragmentTransaction.commit()
    }

    fun crearFragmentoDos() {
        destruirFragmentoActual()
        // Manager
        val fragmentManager = supportFragmentManager

        // Transacciones
        val fragmentTransaction = fragmentManager.beginTransaction()

        // Crear instancia de fragmento
        val primerFragmento = CategoriaFragment()



        // Anadir fragmento

        // fragmentTransaction.remove(fragmentoActual)
        fragmentTransaction.replace(R.id.relativeLayout, primerFragmento)

        fragmentoActual = primerFragmento


        // Commit
        fragmentTransaction.commit()

    }
    fun crearFragmentoTres() {
        destruirFragmentoActual()

        val fragmentManager = supportFragmentManager

        val fragmentTransaction = fragmentManager.beginTransaction()

        val primerFragmento = ActorFragment()
        fragmentTransaction.replace(R.id.relativeLayout, primerFragmento)
        fragmentoActual = primerFragmento
        fragmentTransaction.commit()

    }

    fun crearFragmentoCuatro() {
        destruirFragmentoActual()

        val fragmentManager = supportFragmentManager

        val fragmentTransaction = fragmentManager.beginTransaction()

        val primerFragmento = ComentarioFragment()
        fragmentTransaction.replace(R.id.relativeLayout, primerFragmento)
        fragmentoActual = primerFragmento
        fragmentTransaction.commit()

    }
    fun getComentarios(comentarios: ArrayList<Comentario>){
        val url = "http://172.29.50.237:80/comentario/api/"
        url.httpGet().responseString{request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                }
                is Result.Success -> {
                    val data = result.get()
                    comentarios.clear()
                    val wordDict = Klaxon().parseArray<Comentario>(data)
                    Log.i("http", "Datos: ${wordDict.toString()}")
                    if (wordDict != null) {
                        for ( coment in wordDict.iterator()){
                            comentarios.add(coment)
                        }
                    }

                }
            }




        }
    }
}
