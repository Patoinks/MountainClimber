package ipca.grupo2

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import ipca.grupo2.auth.LoginActivity
import kotlinx.android.synthetic.main.fragment_menu.view.*

class MenuFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_menu, container, false)

        auth = Firebase.auth
        val btMedir = view.findViewById<Button>(R.id.btMedicoes)
        val btEventos = view.findViewById<Button>(R.id.btEventos)
        val voltarLogin = view.findViewById<ImageView>(R.id.voltarLogin)

        btMedir.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment2_to_readingFragment2)
        }

        btEventos.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment2_to_eventosFragment)
        }

        voltarLogin.setOnClickListener {
            auth.signOut()
            val intent = Intent (getActivity(), LoginActivity::class.java)
            getActivity()?.startActivity(intent)
        }
            return view
    }



}