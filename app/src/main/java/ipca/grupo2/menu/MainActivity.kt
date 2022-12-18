package ipca.grupo2.menu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import ipca.grupo2.EventosFragment

import ipca.grupo2.menu.InicioFragment
import ipca.grupo2.R
import ipca.grupo2.auth.LoginActivity
import ipca.grupo2.databinding.ActivityMainBinding
import ipca.grupo2.room.Dados

class MainActivity : AppCompatActivity() {

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

        val inicioFragment = InicioFragment()
        val eventoFragment = EventosFragment()
        replaceFragment(inicioFragment)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        //  val listViewContacts = findViewById<ListView>(R.id.listViewContacts)
        //  listViewContacts.adapter = contactsAdapter

        /* AppDatabase
            .getDatabase(this@MainActivity)
            ?.dadosDao()
            ?.getAllLive()?.observe(this) {
                this@MainActivity.dados = it

                findViewById<FloatingActionButton>(R.id.floatingActionButton)
                    .setOnClickListener {
                        val intent = Intent(this@MainActivity, DadosDetailActivity::class.java)
                        startActivity(intent)
                    } */


        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.page_1 -> signOut()
                R.id.page_2 -> replaceFragment(eventoFragment)
                R.id.page_3 -> replaceFragment(eventoFragment)
            }
            true
        }

    }

    private fun replaceFragment(fragment: Fragment) {
        if (fragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frame_layout, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    private fun  signOut(){
        auth.signOut()
        val intent = Intent (this@MainActivity, LoginActivity::class.java)
        startActivity(intent)
    }

}





