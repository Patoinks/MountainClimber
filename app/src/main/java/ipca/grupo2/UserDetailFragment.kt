package ipca.grupo2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import ipca.grupo2.room.AppDatabase

class UserDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_user_detail, container, false)

        var curEventoId = context?.let { AppDatabase.getDatabase(it)?.eventoDao()?.getCurEventId()?.id }

        view.findViewById<ImageView>(R.id.voltarEventoDet).setOnClickListener {
            val bundle = bundleOf("eventoid" to curEventoId)
            var navController: NavController? = null
            findNavController().navigate(R.id.action_userDetailFragment_to_eventoDetalheFragment, bundle)
        }


        return view
    }
}