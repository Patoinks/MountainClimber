package ipca.grupo2

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.graphics.toColor
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ipca.grupo2.backend.Location

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.Hashtable

class MedicaoFragment : Fragment() {

    private var location: ipca.grupo2.backend.Location? = null;

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("MissingPermission", "SetTextI18n")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_medicao, container, false)
        var color : Color
        val o2 = view.findViewById<TextView>(R.id.oxigenioSangue)
        val resultado = view.findViewById<TextView>(R.id.resultadoPos)
        val textoEscalada = view.findViewById<TextView>(R.id.continuarEscalada)
        val circleView = view.findViewById<HeartRateView>(R.id.bpm)

        location = ipca.grupo2.backend.Location(requireContext(), requireActivity());

        //Botão de Simular
        view.findViewById<Button>(R.id.Simular).setOnClickListener {

            var rnds = (70..100).random()
            var rnds2 = (110..120).random()
            circleView.setValueAnimated(rnds.toFloat() / 100, 1500)
            o2.text = rnds.toString() + "%"

            //Altitude
            if (location != null){
                GlobalScope.launch {
                    var altitude = Math.round(location!!.getAltitude() - 56)
                    requireActivity().runOnUiThread{
                        view.findViewById<TextView>(R.id.altitudes).text = "Altitude: " + altitude + " m";

                    }
                }
            }

            view.findViewById<Button>(R.id.Simular).setBackgroundColor(Color.parseColor("#440123"))
            view.findViewById<Button>(R.id.Simular).isEnabled = false
        }


        //Botão de Repetir
        view.findViewById<Button>(R.id.repetirMedicao).setOnClickListener{
            var oxigenioRandom = (70..100).random()

            GlobalScope.launch {
                // Backend Calls
                var altitude = location!!.getLastLocation()!!.altitude

                // Ui Threads
                requireActivity().runOnUiThread {
                    o2.text = oxigenioRandom.toString() + "%"
                    circleView.setValueAnimated(oxigenioRandom.toFloat() / 100, 1500, )
                }
            }
        }


        // Botão de voltar
        view.findViewById<ImageView>(R.id.voltarUser).setOnClickListener{
            findNavController().navigate(R.id.action_userReadFragment_to_readingFragment2)
        }

        return view
    }
}
