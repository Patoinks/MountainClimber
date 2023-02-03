package ipca.grupo2

import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.StructuredName
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
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
import java.util.concurrent.TimeUnit

class EventoDetalheFragment : Fragment() {

    private lateinit var myAdapter: UtilizadoresRecyclerAdapter2

    private fun populateRecyleView(view: View, utilizadores : MutableList<Utilizador>, eventoID : String) {
        // Get a reference to the RecyclerView
        val recyclerView = view.findViewById<RecyclerView>(R.id.userRecicla2)
        recyclerView?.layoutManager = LinearLayoutManager(requireActivity())
        recyclerView?.setHasFixedSize(true)

        val mainScope = CoroutineScope(Dispatchers.Main)

        // Handle async code
        mainScope.launch {
            val db = AppDatabase.getDatabase(requireContext())

            // Create an instance of the Adapter and set it to the RecyclerView
            if(db != null){
                requireActivity().runOnUiThread{


                    // Create an instance of the Adapter and set it to the RecyclerView
                    myAdapter = UtilizadoresRecyclerAdapter2(utilizadores, requireActivity(), eventoID)
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
        val imagemMontanha : ImageView = view.findViewById<ImageView>(R.id.imagemMontanhadetalhe)
        val duracao : TextView  = view.findViewById<TextView>(R.id.detalheDuracao)
        val elevacao : TextView  = view.findViewById<TextView>(R.id.detalheElevacao)
        val localizacao : TextView  = view.findViewById<TextView>(R.id.detalheLocal)
        val total : TextView  = view.findViewById<TextView>(R.id.totalDetalhe)
        val bundle = this.arguments
        val eventoID = bundle?.getString("eventoid", "")
        val mainScope = CoroutineScope(Dispatchers.Main)


        //Buscar dados do evento
        mainScope.launch {
            val evento = BackendEvento.getEventoByID(eventoID!!)
            val utilizadores = BackendUtilizador.getAllUtilizadoresByEvento(eventoID)

            Picasso.get().load(evento?.getImage()).resize(400,200).into(imagemMontanha)
            localizacao.text = evento?.getName()
            elevacao.text = "Elevação: " + evento?.getElevation().toString() + " m"

            var long : Long? = evento?.getDateFinish()!!.time - evento.getDateStart()!!.time
            var datadiff = TimeUnit.MILLISECONDS.toDays(long!!)

            duracao.text = "Duração do evento: " + datadiff.toString() + " dias"

            view.findViewById<TextView>(R.id.totalDetalhe).text = utilizadores.size.toString() + if
                    (utilizadores.size > 1) " Utilizadores" else " Utilizador"
            populateRecyleView(view, utilizadores, eventoID)
        }


        //Voltar com navigation
        view.findViewById<ImageView>(R.id.voltarEvento).setOnClickListener {
            findNavController().navigate(R.id.action_eventoDetalheFragment_to_eventosFragment)
        }


        return view
    }
}