package ipca.grupo2

import android.content.Context
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import ipca.grupo2.backend.models.Utilizador
import ipca.grupo2.backend.tables.BackendUtilizador
import kotlinx.android.synthetic.main.fragment_user_read.*
import kotlinx.android.synthetic.main.fragment_user_read.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserReadFragment : Fragment() {

    private lateinit var utilizadorID: String;
    private lateinit var user: Utilizador;
    private var requisitos = 6;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_user_read, container, false)



        val bpm = view.findViewById<TextView>(R.id.batimentoCardiaco)
        val o2 = view.findViewById<TextView>(R.id.oxigenioSangue)

        view.findViewById<Button>(R.id.Simular).setOnClickListener {
            var rnds = (90 .. 100).random()
            var rnds2 = (110 .. 120).random()
            bpm.text = rnds2.toString()
            o2.text = rnds.toString()

        }







        //utilizadorID = arguments?.getString("userid")!!;





      /*  val alertDialog = AlertDialog(requireActivity())

        alertDialog.startLoadingDialog()

        Handler().postDelayed({
            alertDialog.dismissDialog()
            findNavController().navigate(R.id.action_userReadFragment_to_readingFragment2)
        }, 1000) */

        /*Setup Fragment using backend data
        val mainScope = CoroutineScope(Dispatchers.Main);
        mainScope.launch {
            // User List

            user = withContext(Dispatchers.IO) {
                BackendUtilizador.getUtilizadorById(utilizadorID!!)!!;
            }

            view.findViewById<TextView>(R.id.nomeResultado).text = user.getName().toString()
        } */

        view.findViewById<ImageView>(R.id.voltarUser).setOnClickListener {
            findNavController().navigate(R.id.action_userReadFragment_to_readingFragment2)
        }
        return view
    }
}