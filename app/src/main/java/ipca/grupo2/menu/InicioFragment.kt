package ipca.grupo2.menu

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import ipca.grupo2.R
import ipca.grupo2.auth.LoginActivity
import kotlinx.android.synthetic.main.fragment_inicio.view.*

class InicioFragment : Fragment() {

    private lateinit var analytics: FirebaseAnalytics
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        analytics = Firebase.analytics
        auth = Firebase.auth

        // Inflate the layout for this fragment
        var view: View = inflater.inflate(R.layout.fragment_inicio, container, false)


        // Inflate the layout for this fragment
        return view
    }
}