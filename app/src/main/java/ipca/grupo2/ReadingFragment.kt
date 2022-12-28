package ipca.grupo2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.navigation.fragment.findNavController

class ReadingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_reading, container, false)

        val voltar = view.findViewById<ImageView>(R.id.voltarMenu2)

        voltar.setOnClickListener {
            findNavController().navigate(R.id.action_readingFragment2_to_menuFragment2)
        }

        return view
    }

}