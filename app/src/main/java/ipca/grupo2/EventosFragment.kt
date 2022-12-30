package ipca.grupo2

import android.media.metrics.Event
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ipca.grupo2.backend.tables.BackendEvento
import kotlinx.coroutines.*

class EventosFragment : Fragment() {
    private lateinit var myAdapter: EventoRecyclerAdapter;
    private lateinit var navController: NavController

    // Function to handle async functions in backend
    private fun populateRecyleView(view: View){
        // Get a reference to the RecyclerView
        val recyclerView = view.findViewById<RecyclerView>(R.id.eventosRecicla);
        recyclerView?.layoutManager = LinearLayoutManager(requireActivity());
        recyclerView?.setHasFixedSize(true);

        // mainScope to handle UI calls(this is needed because globalScope has
        // trouble working with UI
        val mainScope = CoroutineScope(Dispatchers.Main);

        // Handle async code
        mainScope.launch {
            // Get data from backend
            val dataList = withContext(Dispatchers.IO) {
                BackendEvento.getAllEventosByUserID();
            };



            // Create an instance of the Adapter and set it to the RecyclerView
            myAdapter = EventoRecyclerAdapter(ArrayList(dataList), requireActivity());
            myAdapter.notifyDataSetChanged();
            recyclerView?.adapter = myAdapter;
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View = inflater.inflate(R.layout.fragment_eventos, container, false);
        navController = findNavController()

        val voltar = view.findViewById<ImageView>(R.id.voltarMenu1)

        voltar.setOnClickListener {
            findNavController().navigate(R.id.action_eventosFragment_to_menuFragment2)
        }
        populateRecyleView(view);

        return view;
    }
}