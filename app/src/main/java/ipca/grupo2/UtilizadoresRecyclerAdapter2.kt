package ipca.grupo2

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
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import ipca.grupo2.backend.models.Evento
import ipca.grupo2.room.dao.UtilizadorDAO
import ipca.grupo2.room.entities.UtilizadorEntity

class UtilizadoresRecyclerAdapter2(val utilizadores: List<UtilizadorEntity>, val context: Context) :
    RecyclerView.Adapter<UtilizadoresRecyclerAdapter2.ViewHolder>() {

    // Define the ViewHolder class
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ButtonNome : Button = itemView.findViewById(R.id.nome);
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
        val bundle = bundleOf("userid" to data.id);

        holder.ButtonNome.setOnClickListener {
            var navController = Navigation.findNavController(holder.itemView);
            navController.navigate(R.id.action_readingFragment2_to_userReadFragment, bundle);
        }

        if (position % 2 != 0)
            holder.ButtonNome.setBackgroundColor(Color.parseColor("#466563"))

        holder.ButtonNome.text = data.name
    }

    override fun getItemCount(): Int {
        return utilizadores.size;
    }
}