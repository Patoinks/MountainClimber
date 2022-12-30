package ipca.grupo2

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.UiThread
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import ipca.grupo2.backend.models.Utilizador
import ipca.grupo2.backend.tables.BackendEventoUtilizador
import ipca.grupo2.backend.tables.BackendUtilizador
import ipca.grupo2.room.AppDatabase
import ipca.grupo2.room.entities.UtilizadorEntity
import kotlinx.coroutines.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class EventoDetalheFragment() : Fragment() {

    // Class variables
    private lateinit var eventoID: String;
    private lateinit var eventoUsers: MutableList<Utilizador>;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_evento_detalhe, container, false)
        eventoID = arguments?.getString("uid")!!;

        // Setup Fragment using backend data
        val mainScope = CoroutineScope(Dispatchers.Main);
        mainScope.launch {
            // User List
            eventoUsers = withContext(Dispatchers.IO) {
                BackendUtilizador.getAllUtilizadoresByEvento(eventoID!!);
            }

            // fazes a lista aqui))

            // User Count
            view.findViewById<TextView>(R.id.textView5).text = eventoUsers.size.toString()

            // Event Handling
            view.findViewById<Button>(R.id.downUser).setOnClickListener {
                getData();
                findNavController().navigate(R.id.action_eventoDetalheFragment_to_menuFragment2)
            }
        }



        return view
    }

    private fun getData() {
        // get data from backend
        GlobalScope.launch {
            AppDatabase.getDatabase(requireContext())!!.eventoDao().joinEvento(eventoID, requireContext());
        }

        Toast.makeText(
            context, "Evento downloaded!",
            Toast.LENGTH_SHORT
        ).show();
    }
}
