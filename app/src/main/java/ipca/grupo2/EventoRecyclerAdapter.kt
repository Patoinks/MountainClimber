package ipca.grupo2

import android.app.PendingIntent.getActivity
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import ipca.grupo2.backend.models.Evento
import ipca.grupo2.room.AppDatabase

class EventoRecyclerAdapter(val eventos: ArrayList<Evento>, val context: Context) :
    RecyclerView.Adapter<EventoRecyclerAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.row_eventos,
            parent,false);
        return ViewHolder(itemView);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Retrieve the data for the current position
        holder.data = eventos[position];
        val bundle = bundleOf("uidUser" to holder.data.getId());

        // Set the data to the views
        holder.textViewLocal.text = holder.data.getLocation();

        if(position %2 != 0) {
            holder.textViewLocal.setBackgroundColor(Color.parseColor("#466563"))
        }

        // Handle events
        holder.textViewLocal.setOnClickListener {
            var navController = findNavController(holder.itemView);
            navController.navigate(R.id.action_eventosFragment_to_eventoDetalheFragment, bundle);
        }
    }

    override fun getItemCount(): Int {
        return eventos.size;
    }

    // Define the ViewHolder class
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var data: Evento;
        var id : String? =  null;
        val textViewLocal : Button = itemView.findViewById(R.id.local);
    }
}