package com.example.andres.ezmoviesadmin

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView


import com.example.andres.ezmoviesadmin.ActorFragment.OnListFragmentInteractionListener
import kotlinx.android.synthetic.main.fragment_actor.view.*

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class MyActorRecyclerViewAdapter(
        private val mValues: List<Actor>,
        private val mListener: OnListFragmentInteractionListener?,private val context: Context)
    : RecyclerView.Adapter<MyActorRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Actor
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_actor, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mIdView.text = item.id_actor
        holder.mContentView.text = item.nombre_actor
        holder.mBtnView.setOnClickListener{
            Log.i("Actor","Click OK")
            irActividadModificar(Actor(holder.mIdView.text.toString(),holder.mContentView.text.toString()))
        }

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }
    fun irActividadModificar(actor: Actor){
        val intentActividadIntent = Intent(
                context,
                ModificarActorActivity::class.java
        )

        intentActividadIntent.putExtra("actor",actor)
        ContextCompat.startActivity(context, intentActividadIntent, null)
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView = mView.id_actor
        val mContentView: TextView = mView.nombre_actor
        val mBtnView:Button = mView.btn_mod_actor

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}
