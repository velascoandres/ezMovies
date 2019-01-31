package com.example.andres.ezmoviesadmin

import android.graphics.BitmapFactory
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView


import com.example.andres.ezmoviesadmin.ComentarioFragment.OnListFragmentInteractionListener
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.fragment_comentario.view.*
import java.net.URL

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class MyComentarioRecyclerViewAdapter(
        private val mValues: List<Comentario>,
        private val mListener: OnListFragmentInteractionListener?)
    : RecyclerView.Adapter<MyComentarioRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Comentario
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_comentario, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        //val url = URL("http://image10.bizrate-images.com/resize?sq=60&uid=2216744464")
        //val bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream())

        holder.mIdView.text = item.fecha_creacion.toString()
        holder.mContentView.text = item.contenido

        Picasso.get()
                .load(item.imagen)
                .resize(256,256)
                .centerCrop()
                .into(holder.mImgView)

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mImgView:ImageView = mView.img_co
        val mIdView: TextView = mView.id_comentario
        val mContentView: TextView = mView.contenido

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}
