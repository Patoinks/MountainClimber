package ipca.grupo2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import ipca.grupo2.backend.models.Evento


class EventoRecyclerAdapter(val eventos: ArrayList<Evento>, val context: Context) :
    RecyclerView.Adapter<EventoRecyclerAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        //Declarar ItemView como RecyclerView
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.row_eventos,
            parent,false)
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        //Variaveis
        val evento : Evento = eventos[position]
        holder.local.text = evento.getLocation().toString()




        holder.itemView.setOnClickListener {
            val fragmentManager = (context as FragmentActivity).supportFragmentManager
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.frame_layout, EventosFragment())
            fragmentTransaction.addToBackStack(null).commit()
        }


        }

    override fun getItemCount(): Int {

        //Tamanho
        return eventos.size
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        //Declarar Variaveis de Views
        val local : TextView = itemView.findViewById(R.id.local)

    }
}