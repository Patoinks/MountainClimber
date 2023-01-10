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
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MedicaoFragment : Fragment() {
    private var location: ipca.grupo2.backend.Location? = null;

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("MissingPermission")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_medicao, container, false)

        val o2 = view.findViewById<TextView>(R.id.oxigenioSangue)
        val resultado = view.findViewById<TextView>(R.id.resultadoPos)
        val textoEscalada = view.findViewById<TextView>(R.id.continuarEscalada)
        val circleView = view.findViewById<HeartRateView>(R.id.bpm)


        location = ipca.grupo2.backend.Location(requireContext(), requireActivity());

        view.findViewById<Button>(R.id.Simular).setOnClickListener {
            var rnds = (70..100).random()
            var rnds2 = (110..120).random()
            circleView.setValueAnimated(rnds.toFloat() / 100, 1500)

            o2.text = rnds.toString() + "%"
            if (rnds < 75) {
                o2.setTextColor(Color.parseColor("#FF7878"))
                resultado.text = "Negativo"
                resultado.setTextColor(Color.parseColor("#FF7878"))

                textoEscalada.text = "Não pode continuar a escalada"
            }
            else
            {
                o2.setTextColor(Color.parseColor("#64A19D"))
                resultado.text = "Positivo"
                resultado.setTextColor(Color.parseColor("#64A19D"))

                textoEscalada.text = "Pode continuar a escalada"
            }

            if (location != null){
                GlobalScope.launch {
                    var altitude = Math.round(location!!.getAltitude()).toString();
                    requireActivity().runOnUiThread{
                        view.findViewById<TextView>(R.id.altitudes).text = "Altitude: " + altitude + " m";
                    }
                }
            }


            view.findViewById<Button>(R.id.Simular).setBackgroundColor(Color.parseColor("#440123"))
            view.findViewById<Button>(R.id.Simular).isEnabled = false
        }



        view.findViewById<Button>(R.id.repetirMedicao).setOnClickListener{
            var rnds = (70..100).random()
            var rnds2 = (110..120).random()
            circleView.setValueAnimated(rnds.toFloat() / 100, 1500)

            o2.text = rnds.toString() + "%"
            if (rnds < 75) {
                o2.setTextColor(Color.parseColor("#FF7878"))
                resultado.text = "Negativo"
                resultado.setTextColor(Color.parseColor("#FF7878"))

                textoEscalada.text = "Não pode continuar a escalada"
            }
            else
            {
                o2.setTextColor(Color.parseColor("#64A19D"))
                resultado.text = "Positivo"
                resultado.setTextColor(Color.parseColor("#64A19D"))

                textoEscalada.text = "Pode continuar a escalada"
            }
        }



        view.findViewById<ImageView>(R.id.voltarUser).setOnClickListener{
            findNavController().navigate(R.id.action_userReadFragment_to_readingFragment2)
        }

        return view
    }
}
