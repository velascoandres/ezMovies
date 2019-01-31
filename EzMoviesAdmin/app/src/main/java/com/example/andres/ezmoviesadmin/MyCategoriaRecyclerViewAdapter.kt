package com.example.andres.ezmoviesadmin

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView


import com.example.andres.ezmoviesadmin.CategoriaFragment.OnListFragmentInteractionListener

import kotlinx.android.synthetic.main.fragment_categoria.view.*

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class MyCategoriaRecyclerViewAdapter(
        private val mValues: List<Categoria>,
        private val mListener: OnListFragmentInteractionListener?,private val context: Context)
    : RecyclerView.Adapter<MyCategoriaRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Categoria
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_categoria, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mIdView.text = item.id
        holder.mContentView.text = item.nombre_categoria
        holder.mBotonMod.setOnClickListener {
            val categoria = Categoria(item.id,item.nombre_categoria)
            irActividadModificar(categoria)
        }

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    fun irActividadModificar(categoria: Categoria){
        val intentActividadIntent = Intent(
                context,
                ModificarCategoriaActivity::class.java
        )

        intentActividadIntent.putExtra("categoria",categoria)
        ContextCompat.startActivity(context, intentActividadIntent, null)
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView = mView.id_comentario
        val mContentView: TextView = mView.contenido
        val mBotonMod:Button = mView.btn_mod_categoria

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}
