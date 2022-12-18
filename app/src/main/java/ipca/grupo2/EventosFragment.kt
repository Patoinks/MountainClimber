package ipca.grupo2

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.grupo2.plusorder.EventoRecyclerAdapter
import ipca.grupo2.auth.LoginActivity
import ipca.grupo2.backend.models.Evento
import ipca.grupo2.backend.tables.BackendEvento
import ipca.grupo2.backend.tables.BackendEvento.getAllEventosByUserID
import ipca.grupo2.backend.tables.BackendEventoUtilizador
import ipca.grupo2.databinding.FragmentEventosBinding
import kotlinx.android.synthetic.main.fragment_inicio.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class EventosFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view: View = inflater.inflate(R.layout.fragment_eventos, container, false)

        var itens : MutableList<Evento> = arrayListOf()
        var lista : MutableList<Evento> = arrayListOf()

        GlobalScope.launch{

            lista = getAllEventosByUserID()!!
            var allEvents = BackendEventoUtilizador.getAllEventosUtilizadores();
            if (allEvents != null) {
                for (lista in allEvents)
                {
                    itens.add(e)
                }
            }
        }

        val adapter = EventoRecyclerAdapter(itens, requireActivity())
        val recicla : RecyclerView = view.findViewById(R.id.RviewEvento)
        recicla.layoutManager = GridLayoutManager(requireActivity(),1)
        recicla.adapter = adapter




        // Inflate the layout for this fragment
        return view
    }


}