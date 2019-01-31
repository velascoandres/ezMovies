package com.example.andres.ezmoviesadmin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import kotlinx.android.synthetic.main.activity_nav.*

class NavActivity : AppCompatActivity() {
    lateinit var fragmentoActual: Fragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav)
        fragmentoActual = PeliculaFragment()
        crearFragmentoUno()

        btn_movies
                .setOnClickListener{
                    crearFragmentoUno()
                }

        btn_cate
                .setOnClickListener{
                    crearFragmentoDos()
                }

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
        fragmentTransaction.replace(R.id.relative_layout, primerFragmento)

        fragmentoActual = primerFragmento


        // Commit
        fragmentTransaction.commit()
    }

    fun crearFragmentoDos() {
        destruirFragmentoActual()

    }
}
