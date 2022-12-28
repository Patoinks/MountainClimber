package ipca.grupo2.menu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import ipca.grupo2.R

import ipca.grupo2.databinding.ActivityMainBinding
import ipca.grupo2.room.Dados
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.view.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.fragment_menu.view.*

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    var dados: List<Dados> = arrayListOf() //Array do Room
    private lateinit var analytics: FirebaseAnalytics
    private lateinit var auth: FirebaseAuth

    //Ao criar pagina
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root // view. = findviewbyid
        setContentView(view)

        analytics = Firebase.analytics
        auth = Firebase.auth

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment

        navController = navHostFragment.navController






        }


}




