package ipca.grupo2

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ipca.grupo2.backend.tables.BackendEvento
import kotlinx.coroutines.*

class EventosFragment : Fragment() {
    private lateinit var myAdapter: EventoRecyclerAdapter;

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
            // (Isto é para debug, podem tirar se quiserem)
            Log.d("eventoFragmento", "populateData() called");

            // Get data from backend
            val dataList = withContext(Dispatchers.IO) {
                BackendEvento.getAllEventosByUserID();
            };

            // Create an instance of the Adapter and set it to the RecyclerView
            // ps: idk why u wanted mutable list bruv
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

        // ez
        populateRecyleView(view);

        return view;
    }
}