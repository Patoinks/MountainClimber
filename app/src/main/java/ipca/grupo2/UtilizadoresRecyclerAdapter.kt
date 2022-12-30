package ipca.grupo2

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import ipca.grupo2.backend.models.Evento
import ipca.grupo2.room.dao.UtilizadorDAO
import ipca.grupo2.room.entities.UtilizadorEntity

class UtilizadoresRecyclerAdapter(val utilizadores: List<UtilizadorEntity>, val context: Context) :
    RecyclerView.Adapter<UtilizadoresRecyclerAdapter.ViewHolder>() {

    // Define the ViewHolder class
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewLocal : Button = itemView.findViewById(R.id.nome);
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.row_utilizadores,
            parent,false);
        return ViewHolder(itemView);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Retrieve the data for the current position
        val data = utilizadores[position];

        val bundle = bundleOf("uid" to data.id);

        // Debug purposes again
        Log.d("eventoAdapter", holder.textViewLocal.text.toString() + " text");

        holder.textViewLocal.setOnClickListener {
            var navController = Navigation.findNavController(holder.itemView);
            navController.navigate(R.id.action_eventosFragment_to_eventoDetalheFragment, bundle);
        }

        // Set the data to the views
        holder.textViewLocal.text = data.name
    }

    override fun getItemCount(): Int {
        return utilizadores.size;
    }
}