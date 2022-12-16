package com.grupo2.plusorder

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import ipca.grupo2.R
import ipca.grupo2.backend.models.Evento


class EmentaRecyclerAdapter(val evento: MutableList<Evento>, val context: Context) :
    RecyclerView.Adapter<EmentaRecyclerAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        //Declarar ItemView como RecyclerView
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.fragment_leitura,
            parent,false)
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        //Variaveis
        val currentitem = evento[position]

        //Popular RecyclerView
        holder.


        //Clicar numa row da RecyclerView
       /* holder.itemView.setOnClickListener{
            val fragmentManager = (context as FragmentActivity).supportFragmentManager
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.frame_layout2, DetalheFragment())
            fragmentTransaction.addToBackStack(null).commit() */
        }

    override fun getItemCount(): Int {

        //Tamanho
        return evento.size
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        //Declarar Variaveis de Views

        val local : TextView = itemView.findViewById(R.id.)

    }
}