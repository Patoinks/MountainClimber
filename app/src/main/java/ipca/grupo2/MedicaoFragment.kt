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
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import ipca.grupo2.backend.Location
import ipca.grupo2.backend.models.Leitura
import ipca.grupo2.backend.tables.BackendEvento
import ipca.grupo2.backend.tables.BackendUtilizador
import ipca.grupo2.room.AppDatabase
import ipca.grupo2.room.dao.LeituraDAO
import ipca.grupo2.room.entities.LeituraEntity
import kotlinx.android.synthetic.main.fragment_medicao.*
import kotlinx.coroutines.*

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

class MedicaoFragment : Fragment() {

    private var location: ipca.grupo2.backend.Location? = null

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("MissingPermission", "SetTextI18n")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_medicao, container, false)
        val o2 = view.findViewById<TextView>(R.id.oxigenioSangue)
        val o2T2 = view.findViewById<TextView>(R.id.oxigenioSangue3)
        val resultado = view.findViewById<TextView>(R.id.resultadoPos)
        val textoEscalada = view.findViewById<TextView>(R.id.continuarEscalada)
        val circleView = view.findViewById<HeartRateView>(R.id.bpm)
        val altitudes = view.findViewById<TextView>(R.id.altitudes)
        val fingerprint = view.findViewById<ImageView>(R.id.Simular)
        val barra = view.findViewById<ImageView>(R.id.barra)
        val questoes = view.findViewById<Button>(R.id.questoes)
        val proximo = view.findViewById<Button>(R.id.proximo)
        val dedoTexto = view.findViewById<TextView>(R.id.dedoInserir)
        val bundle = this.arguments
        val userid = bundle?.getString("userid", "")
        val nome = view.findViewById<TextView>(R.id.nomeUtilizadorMed)


        o2.isVisible = false
        barra.isVisible = false
        resultado.isVisible = false
        textoEscalada.isVisible = false
        circleView.isVisible = false
        altitudes.isVisible = false
        o2T2.isVisible = false
        questoes.isVisible = false
        proximo.isVisible = false


        var location = ipca.grupo2.backend.Location(requireContext(), requireActivity())
        if(location.CheckPermission() == false)
        {
            location.RequestPermission()
        }
        else
        {
            location = ipca.grupo2.backend.Location(requireContext(), requireActivity())
        }


        //Leitura
        fingerprint.setOnClickListener {
            var rnds = (97..100).random()
            var rnd = Random(System.nanoTime())
            var rnds2 = (rnd.nextInt(1000000000)..999999999 + rnd.nextInt(1000000000)).random()
            o2.text = rnds.toString() + "%"

            //Altitude
            if (location != null){
                GlobalScope.launch {
                    var altitude = Math.round(location.getAltitude() - 56)
                    requireActivity().runOnUiThread{
                        altitudes.text = "Altitude: " + altitude.toString() + " m"
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
                            resultado.setTextColor((Color.parseColor("#FFFF5252")))
                            o2.setTextColor((Color.parseColor("#FFFF5252")))
                        }
                    }


                    //Insert leitura
                    val db = AppDatabase.getDatabase(requireContext())
                    val currentSqlDate = java.sql.Date(System.currentTimeMillis())
                    var leitura: Leitura = Leitura()

                    leitura.setIdUtilizador(userid!!)
                    leitura.setO2(rnds)
                    leitura.setData(currentSqlDate)
                    leitura.setApetite(0)
                    leitura.setAltitude(altitude.toInt())
                    leitura.setCabeca(0)
                    leitura.setNoite(0)
                    leitura.setNausea(0)
                    leitura.setId((rnds2).toString())
                    leitura.setIdEvento(db?.eventoDao()?.getCurEventId().toString())

                    db?.leituraDao()?._insert(Leitura.toEntity(leitura),)
                }
            }

            o2.isVisible = true
            resultado.isVisible = true
            textoEscalada.isVisible = true
            circleView.isVisible = true
            altitudes.isVisible = true
            o2T2.isVisible = true
            fingerprint.isVisible = false
            questoes.isVisible = true
            barra.isVisible = true
            dedoTexto.isVisible = false
        }


        //Questionario
        questoes.setOnClickListener {
            val showPopUp = QuestionsFragment()
            showPopUp.show((activity as AppCompatActivity).supportFragmentManager, "showPopuUp")
            questoes.isGone = true
            proximo.isVisible = true
        }


        //Proxima leitura
        proximo.setOnClickListener {
            findNavController().navigate(R.id.action_userReadFragment_to_readingFragment2)
        }


        val mainScope = CoroutineScope(Dispatchers.Main)
        //Buscar dados do evento
        mainScope.launch {
            val utilizador = BackendUtilizador.getUtilizadorById(userid!!)
             nome.text = utilizador?.getName()
        }

        return view
    }
}

