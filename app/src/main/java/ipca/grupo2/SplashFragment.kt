package ipca.grupo2

import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_splash, container, false)

        lifecycleScope.launch(Dispatchers.IO) {
            delay(2000)
            lifecycleScope.launch(Dispatchers.Main) {
                val auth = Firebase.auth
                val currentUser = auth.currentUser
                if (currentUser != null) {
                    findNavController().navigate(R.id.action_splashFragment_to_menuFragment2)
                } else {
                    findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
                }
            }
        }
        return view
    }
}