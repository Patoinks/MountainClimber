package ipca.grupo2.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import ipca.grupo2.R
import ipca.grupo2.backend.tables.BackendUtilizador
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        // Inflate the layout for this fragment, bind and get FireBase authentication methods into "auth"

        auth = Firebase.auth



        // On click login
        view.findViewById<Button>(R.id.btLogin).setOnClickListener {
            val email = view.findViewById<TextView>(R.id.email).text.toString()
            val password = view.findViewById<TextView>(R.id.password).text.toString()

            val mainScope = CoroutineScope(Dispatchers.Main);

            mainScope.launch {
                var success = BackendUtilizador.login(email, password);
                if (success){
                    findNavController().navigate(R.id.action_loginFragment_to_menuFragment2)
                }

                else {
                        Toast.makeText(
                            context, "Utilizador ou password errado!",
                            Toast.LENGTH_SHORT
                        ).show();
                    }
                }
            }
        return view
    }


    companion object {
        const val TAG = "LoginActivity"
    }
}

