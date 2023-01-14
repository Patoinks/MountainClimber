package ipca.grupo2

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ipca.grupo2.room.AppDatabase
import kotlinx.coroutines.*

class UserListFragment : Fragment() {

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
        val view = inflater.inflate(R.layout.fragment_user_list, container, false)
        val voltar = view.findViewById<ImageView>(R.id.voltarMenu2)

        voltar.setOnClickListener {
            findNavController().navigate(R.id.action_readingFragment2_to_menuFragment2)
        }

        populateRecyleView(view);

        return view
    }

}