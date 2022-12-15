package ipca.grupo2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ipca.grupo2.backend.tables.BackendEvento
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ipca.grupo2.backend.Backend
import ipca.grupo2.backend.models.Evento
import ipca.grupo2.databinding.FragmentLeituraBinding


class LeituraFragment : Fragment() {

    private var _binding: FragmentLeituraBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_leitura, container, false)
        class EmentaFragment : Fragment() {

            override fun onCreateView(
                inflater: LayoutInflater, container: ViewGroup?,
                savedInstanceState: Bundle?
            ): View? {

                //Variaveis
                val view: View = inflater.inflate(R.layout.fragment_leitura, container, false)
                var backendERef = BackendEvento()
                var itens : MutableList<Evento>? = backendERef.getAllEventosByUserID()
                var pratos = backend.


                //Popular a reciclerview
                val adapta = EmentaRecyclerAdapter(itens, requireActivity())
                val recicla : RecyclerView = view.findViewById(R.id.EmentaRecycler)
                recicla.layoutManager = GridLayoutManager(requireActivity(),1)
                recicla.adapter = adapta
                for (prato in pratos) {
                    itens.add(prato)
                }


        return view
    }
}