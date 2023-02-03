package ipca.grupo2

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import ipca.grupo2.room.AppDatabase
import kotlinx.android.synthetic.main.fragment_menu.view.*

class MenuFragment : Fragment() {


    private lateinit var auth: FirebaseAuth
    var toastDisplayed = false

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
        var curEventoId = AppDatabase.getDatabase(requireContext())?.eventoDao()?.getCurEventId()?.id

        btMedir.setOnClickListener {
          Singleton.currentID?.let {
              findNavController().navigate(R.id.action_menuFragment2_to_readingFragment2)
          }?: run {
              Toast.makeText(
                  context, "Selecione um evento primeiro",
                  Toast.LENGTH_SHORT
              ).show()
          }
        }

        btEventos.setOnClickListener {

            if(isOnline(requireContext()))
            findNavController().navigate(R.id.action_menuFragment2_to_eventosFragment)
            else {
            if (!toastDisplayed) {
                // show toast
                Toast.makeText(
                    context, "Ligue á internet primeiro",
                    Toast.LENGTH_SHORT
                ).show()


                // Reset the toastDisplayed flag after the toast length has passed
                Handler().postDelayed({ toastDisplayed = false }, Toast.LENGTH_SHORT.toLong())
            }
                toastDisplayed = true
          }
        }

        voltarLogin.setOnClickListener {

            if(isOnline(requireContext())) {
                auth.signOut()
                findNavController().navigate(R.id.action_menuFragment2_to_loginFragment)
            }
            else {
                Toast.makeText(
                    context, "Porfavor, ligue á internet",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        return view
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }
}