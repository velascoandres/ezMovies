package com.example.andres.ezmoviesadmin

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView


import com.example.andres.ezmoviesadmin.PeliculaFragment.OnListFragmentInteractionListener
import com.example.andres.ezmoviesadmin.dummy.PeliculaContent.Pelicula

import kotlinx.android.synthetic.main.fragment_pelicula.view.*

/**
 * [RecyclerView.Adapter] that can display a [Pelicula] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class MyPeliculaRecyclerViewAdapter(
        private val mValues: List<Pelicula>,
        private val mListener: OnListFragmentInteractionListener?,private val context: Context)
    : RecyclerView.Adapter<MyPeliculaRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Pelicula
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_pelicula, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        //holder.mIdView.text = item.id
        holder.mContentView.text = item.nombre
        holder.mCategoryView.text = item.categoria_nombre
        holder.mBotonView.setOnClickListener {
            val pelicula_s = Pelicula_S(item.id,item.nombre,item.categoria_id,item.categoria_nombre)
            irActividadModificar(pelicula_s)
        }



        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
            Log.i("click-evento","$item");
        }
    }

    fun irActividadModificar(pelicula:Pelicula_S){
        val intentActividadIntent = Intent(
                context,
                ModificarPeliculaActivity::class.java
        )

        intentActividadIntent.putExtra("pelicula",pelicula)
        startActivity(context,intentActividadIntent,null)
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        //val mIdView: TextView = mView.item_number
        val mContentView: TextView = mView.contenido
        val mCategoryView:TextView = mView.categoria
        val mBotonView:Button = mView.boton_modificar


        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}



