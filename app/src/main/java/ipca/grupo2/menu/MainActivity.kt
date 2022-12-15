package ipca.grupo2.menu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import ipca.grupo2.auth.LoginActivity
import ipca.grupo2.databinding.ActivityMainBinding
import ipca.grupo2.room.Dados

class MainActivity : AppCompatActivity() {

    var dados : List<Dados> = arrayListOf() //Array do Room
    lateinit var binding: ActivityMainBinding //Bind ao xml activity_main

    private lateinit var analytics: FirebaseAnalytics
    private lateinit var auth: FirebaseAuth

    //Ao criar pagina
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        analytics = Firebase.analytics
        auth = Firebase.auth

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

                binding.signOut.setOnClickListener {
                    auth.signOut()
                    val intent = Intent(this@MainActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }



