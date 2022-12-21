package ipca.grupo2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.auth.User
import ipca.grupo2.backend.models.Evento
import ipca.grupo2.backend.tables.BackendEvento.getAllEventosByUserID
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class EventosFragment : Fragment() {

    private lateinit var db : FirebaseFirestore
    private lateinit var itens : ArrayList<Evento>
    private lateinit var myAdapter: EventoRecyclerAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view: View = inflater.inflate(R.layout.fragment_eventos, container, false)


        recyclerView = view.findViewById(R.id.eventosRecicla)
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        recyclerView.setHasFixedSize(true)
        itens = arrayListOf()
        myAdapter = EventoRecyclerAdapter(itens, requireActivity())
        recyclerView.adapter = myAdapter


        db = FirebaseFirestore.getInstance()

        EventChangeListener()


        // Inflate the layout for this fragment
        return view
    }

    private fun EventChangeListener() {

        db.collection("eventos")
            .addSnapshotListener(object : EventListener<QuerySnapshot> {
                    override fun onEvent(
                        value: QuerySnapshot?,
                        error: FirebaseFirestoreException?
                    ) {
                        if (error != null)
                        {
                            Log.e("Erro", error.message.toString())
                            return
                        }

                        for (dc : DocumentChange in value?.documentChanges!!) {

                            if (dc.type == DocumentChange.Type.ADDED) {
                                itens.add(dc.document.toObject(Evento::class.java))
                            }

                        }
                        myAdapter.notifyDataSetChanged()
                    }

            })
        
    }


}