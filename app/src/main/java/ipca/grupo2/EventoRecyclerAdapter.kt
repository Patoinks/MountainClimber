package ipca.grupo2

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.graphics.toColor
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ipca.grupo2.backend.models.Evento
import ipca.grupo2.backend.models.Utilizador
import ipca.grupo2.backend.tables.BackendUtilizador
import ipca.grupo2.room.AppDatabase
import kotlinx.coroutines.*

class EventoRecyclerAdapter(val eventos: ArrayList<Evento>, val context: Context) :
    RecyclerView.Adapter<EventoRecyclerAdapter.ViewHolder>() {
    private lateinit var eventoID: String;
    private lateinit var eventoUsers: MutableList<Utilizador>;


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.row_eventos,
            parent,false);


        return ViewHolder(itemView);
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Retrieve the data for the current position
        holder.data = eventos[position];
        var ImageURL = holder.data.getImage()

        eventoID = holder.data.getId().toString();

        // Set the data to the views
        holder.textViewLocal.text = holder.data.getLocation()
        var curEventoId = AppDatabase.getDatabase(context)?.eventoDao()?.getCurEventId()?.id
        val mainScope = CoroutineScope(Dispatchers.Main);

        mainScope.launch {
            // User List

            eventoUsers = withContext(Dispatchers.IO)
            {
                BackendUtilizador.getAllUtilizadoresByEvento(eventoID!!);
            }

            if(eventoUsers.size > 1)
                holder.textViewTotal.text = eventoUsers.size.toString() + " Inscritos"
            else
                holder.textViewTotal.text = eventoUsers.size.toString() + " Inscrito"

            holder.textViewInicio.text = "Data Inicio:  " + holder.data.getDateStart().toString()
            holder.textViewFim.text =  "Data Fim:  " + holder.data.getDateFinish().toString()


            Picasso.get().load(ImageURL).resize(400,200).into(holder.imagemEvento)
        }

        if (curEventoId == eventoID){
            holder.buttonEventos.isEnabled = false
            holder.buttonEventos.setBackgroundColor(Color.parseColor("#440123"))
            holder.buttonEventos.text  = "Selecionado"
        }

        holder.buttonEventos.setOnClickListener {
            val mainScope = CoroutineScope(Dispatchers.Main);
            eventoID = holder.data.getId().toString()
            mainScope.launch {
                var navController: NavController? = null
                navController = Navigation.findNavController(holder.itemView)
                getData()
                navController!!.navigate(R.id.action_eventosFragment_to_menuFragment2)
            }


        }


    }

    override fun getItemCount(): Int {
        return eventos.size;
    }

    // Define the ViewHolder class
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var data: Evento;

        val textViewLocal : TextView = itemView.findViewById(R.id.nomeEvento);
        val textViewTotal : TextView = itemView.findViewById(R.id.rowEventoTotalUser)
        val textViewInicio : TextView = itemView.findViewById(R.id.rowEventoDataInicio)
        val textViewFim : TextView = itemView.findViewById(R.id.rowEventoDataFim)
        val buttonEventos : Button = itemView.findViewById(R.id.rowEventoDown)
        val imagemEvento: ImageView = itemView.findViewById(R.id.imagemMontanha)
    }

    private fun getData() {
        // get data from backend
        GlobalScope.launch {
            AppDatabase.getDatabase(context)!!.eventoDao().joinEvento(eventoID, context);
        }

        Toast.makeText(
            context, "Evento downloaded!",
            Toast.LENGTH_SHORT
        ).show();
    }

}