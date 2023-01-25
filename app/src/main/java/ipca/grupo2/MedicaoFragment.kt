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
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.toColor
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ipca.grupo2.backend.Location

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

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
        val o2 = view.findViewById<TextView>(R.id.oxigenioSangue)
        val o2T = view.findViewById<TextView>(R.id.oxigenioSangue2)
        val o2T2 = view.findViewById<TextView>(R.id.oxigenioSangue3)
        val resultado = view.findViewById<TextView>(R.id.resultadoPos)
        val textoEscalada = view.findViewById<TextView>(R.id.continuarEscalada)
        val circleView = view.findViewById<HeartRateView>(R.id.bpm)
        val dataMed =  view.findViewById<TextView>(R.id.dataMed)
        val altitudes = view.findViewById<TextView>(R.id.altitudes)
        val fingerprint = view.findViewById<ImageView>(R.id.Simular)
        val barra = view.findViewById<ImageView>(R.id.barra)
        val questoes = view.findViewById<Button>(R.id.questoes)

        o2.isVisible = false
        barra.isVisible = false
        o2T.isVisible = false
        resultado.isVisible = false
        textoEscalada.isVisible = false
        circleView.isVisible = false
        dataMed.isVisible = false
        altitudes.isVisible = false
        o2T2.isVisible = false
        questoes.isVisible = false


        var location = ipca.grupo2.backend.Location(requireContext(), requireActivity());

        if(location!!.CheckPermission() == false)
        {
            location!!.RequestPermission()
        }

        else
        {
            location = ipca.grupo2.backend.Location(requireContext(), requireActivity());
        }





        fingerprint.setOnClickListener {
            var rnds = (97..100).random()
            o2.text = rnds.toString() + "%"

            //Altitude
            if (location != null){
                GlobalScope.launch {
                    var altitude = Math.round(location!!.getAltitude() - 56)
                    requireActivity().runOnUiThread{
                        altitudes.text = "" + altitude + " m";
                        circleView.setValueAnimated(rnds.toFloat() / 100, 1500, altitude.toFloat())
                        if (rnds in 98 .. 100 && altitude.toFloat() in 0F .. 562F)
                        {
                            resultado.text = "Positivo"
                            textoEscalada.text = "Pode continuar viagem"
                            resultado.setTextColor((Color.parseColor("#64A19D")))
                            o2.setTextColor((Color.parseColor("#64A19D")))
                        }
                        else
                        {
                            resultado.text = "Negativo"
                            textoEscalada.text = "Não pode continuar viagem"
                            resultado.setTextColor((Color.parseColor("#FF7878")))
                            o2.setTextColor((Color.parseColor("#FF7878")))
                        }

                    }

                    val sdf = SimpleDateFormat("dd/M/yyyy")
                    val currentDate = sdf.format(Date())

                    dataMed.text = currentDate.toString()
                }

            }
            o2.isVisible = true
            o2T.isVisible = true
            resultado.isVisible = true
            textoEscalada.isVisible = true
            circleView.isVisible = true
            dataMed.isVisible = true
            altitudes.isVisible = true
            o2T2.isVisible = true
            fingerprint.isVisible = false
            questoes.isVisible = true
            barra.isVisible = true
        }



        // Botão de voltar
        view.findViewById<ImageView>(R.id.voltarUser).setOnClickListener{
            findNavController().navigate(R.id.action_userReadFragment_to_readingFragment2)
        }

        questoes.setOnClickListener {
            val showPopUp = QuestionsFragment()
            showPopUp.show((activity as AppCompatActivity).supportFragmentManager, "showPopuUp")
        }




        return view
    }
}
