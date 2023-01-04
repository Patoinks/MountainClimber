package ipca.grupo2

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_menu.view.*

class MenuFragment : Fragment() {


    private lateinit var auth: FirebaseAuth

    @RequiresApi(Build.VERSION_CODES.M)
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
            findNavController().navigate(R.id.action_menuFragment2_to_loginFragment)

            /*view.findViewById<CardView>(R.id.nowifi).visibility = View.VISIBLE
                lifecycleScope.launch(Dispatchers.IO) {
                    delay(2000)
                    view.findViewById<CardView>(R.id.nowifi).visibility = View.GONE

                 */
        }
        return view
    }
}