package ipca.grupo2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.findNavController
import ipca.grupo2.backend.tables.BackendEvento
import ipca.grupo2.backend.tables.BackendUtilizador
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class EventoDetalheFragment(val uid : String) : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_evento_detalhe, container, false)

        GlobalScope.launch {

        val users= withContext(Dispatchers.IO) {
            BackendUtilizador.getAllUtilizadoresByEvento(uid);
        };

        val numusers = users.size
            val userCount = view.findViewById<TextView>(R.id.textView5)

            userCount.text = numusers.toString()
    }

        val downButton = view.findViewById<Button>(R.id.downUser)

        downButton.setOnClickListener {
            findNavController().navigate(R.id.action_eventosFragment_to_menuFragment2)
        }
        return view
    }

}