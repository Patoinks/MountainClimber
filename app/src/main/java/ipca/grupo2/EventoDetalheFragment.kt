package ipca.grupo2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ipca.grupo2.backend.models.Utilizador
import ipca.grupo2.backend.tables.BackendEvento
import ipca.grupo2.backend.tables.BackendUtilizador
import ipca.grupo2.room.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Date
import java.util.concurrent.TimeUnit

class EventoDetalheFragment : Fragment() {
    private lateinit var eventoUsers: MutableList<Utilizador>;
    private lateinit var myAdapter: UtilizadoresRecyclerAdapter;


    private fun populateRecyleView(view: View){
        // Get a reference to the RecyclerView
        val recyclerView = view.findViewById<RecyclerView>(R.id.userRecicla);
        recyclerView?.layoutManager = LinearLayoutManager(requireActivity());
        recyclerView?.setHasFixedSize(true);

        // mainScope to handle UI calls(this is needed because globalScope has
        // trouble working with UI
        val mainScope = CoroutineScope(Dispatchers.Main);


        // Handle async code
        mainScope.launch {
            val db = AppDatabase.getDatabase(requireContext())

            // Create an instance of the Adapter and set it to the RecyclerView
            if(db != null){
                requireActivity().runOnUiThread{
                    val utilizadores = db.utilizadorDao().getAll()

                    // Create an instance of the Adapter and set it to the RecyclerView
                    myAdapter = UtilizadoresRecyclerAdapter(utilizadores, requireActivity())
                    myAdapter.notifyDataSetChanged()
                    recyclerView?.adapter = myAdapter
                }
            }
        }.start()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_evento_detalhe, container, false)
        val imagemMontanha : ImageView = view.findViewById<ImageView>(R.id.imagemMontanhaDetalhe)
        val duracao : TextView  = view.findViewById<TextView>(R.id.detalheDuracao)
        val elevacao : TextView  = view.findViewById<TextView>(R.id.detalheElevacao)
        val localizacao : TextView  = view.findViewById<TextView>(R.id.detalheLocal)
        val total : TextView  = view.findViewById<TextView>(R.id.totalDetalhe)


        val bundle = this.arguments

        val eventoID = bundle?.getString("eventoid", "")


        val mainScope = CoroutineScope(Dispatchers.Main);

        mainScope.launch {
            val evento = BackendEvento.getEventoByID(eventoID!!)

            Picasso.get().load(evento?.getImage()).resize(400,200).into(imagemMontanha)
            localizacao.text = evento?.getLocation()

            var long : Long? = evento?.getDateFinish()!!.time - evento?.getDateStart()!!.time
            var datadiff = TimeUnit.MILLISECONDS.toDays(long!!)

            duracao.text = "Duração do evento: " + datadiff.toString() + " dias"
        }
        return view
    }
}