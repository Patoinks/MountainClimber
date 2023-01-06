package ipca.grupo2

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.location.Criteria
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.os.SystemClock
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.getSystemService
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ipca.grupo2.backend.models.Utilizador
import kotlinx.android.synthetic.main.fragment_user_read.*
import kotlinx.android.synthetic.main.fragment_user_read.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.launch


class UserReadFragment : Fragment() {
    private var location: ipca.grupo2.backend.Location? = null;

    @SuppressLint("MissingPermission")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_user_read, container, false)
        val bpm = view.findViewById<TextView>(R.id.batimentoCardiaco)
        val o2 = view.findViewById<TextView>(R.id.oxigenioSangue)

        location = ipca.grupo2.backend.Location(requireContext(), requireActivity());

        view.findViewById<Button>(R.id.Simular).setOnClickListener {
            var rnds = (90..100).random()
            var rnds2 = (110..120).random()

            bpm.text = rnds2.toString()
            o2.text = rnds.toString()
            if (location != null){
                GlobalScope.launch {
                    var altitude = Math.round(location!!.getAltitude()).toString();
                    requireActivity().runOnUiThread{
                        view.findViewById<TextView>(R.id.altitudes).text = altitude;
                    }
                }
            }


            view.findViewById<Button>(R.id.Simular).setBackgroundColor(Color.parseColor("#440123"))
            view.findViewById<Button>(R.id.Simular).isEnabled = false
        }

        view.findViewById<Button>(R.id.repetirMedicao).setOnClickListener{
            var rnds = (90..100).random()
            var rnds2 = (110..120).random()
            bpm.text = rnds2.toString()
            o2.text = rnds.toString()
        }

        view.findViewById<ImageView>(R.id.voltarUser).setOnClickListener{
            findNavController().navigate(R.id.action_userReadFragment_to_readingFragment2)
        }


        return view
    }
}
