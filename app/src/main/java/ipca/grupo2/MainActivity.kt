package ipca.grupo2

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import ipca.grupo2.auth.LoginActivity
import ipca.grupo2.databinding.ActivityMainBinding
import ipca.grupo2.room.AppDatabase
import ipca.grupo2.room.Dados
import java.io.File

class MainActivity : AppCompatActivity() {

    var dados : List<Dados> = arrayListOf()
    lateinit var binding: ActivityMainBinding
    private lateinit var analytics: FirebaseAnalytics
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        analytics = Firebase.analytics
        auth = Firebase.auth

        val listViewContacts = findViewById<ListView>(R.id.listViewContacts)
        val contactsAdapter = DadosAdapter()
        listViewContacts.adapter = contactsAdapter

        AppDatabase
            .getDatabase(this@MainActivity)
            ?.dadosDao()
            ?.getAllLive()?.observe(this) {
                this@MainActivity.dados = it
                contactsAdapter.notifyDataSetChanged()

                findViewById<FloatingActionButton>(R.id.floatingActionButton)
                    .setOnClickListener {
                        val intent = Intent(this@MainActivity, DadosDetailActivity::class.java)
                        startActivity(intent)
                    }

                binding.signOut.setOnClickListener {
                    auth.signOut()
                    val intent = Intent(this@MainActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }

        inner class DadosAdapter : BaseAdapter() {
            override fun getCount(): Int {
                return dados.size
            }

            override fun getItem(position: Int): Any {
                return dados[position]
            }

            override fun getItemId(position: Int): Long {
                return 0
            }

            override fun getView(position: Int, view: View?, viewGroup: ViewGroup?): View {
                val rootView = layoutInflater.inflate(R.layout.row_contact, viewGroup, false)

                val textViewName = rootView.findViewById<TextView>(R.id.textViewName)
                val textViewContact = rootView.findViewById<TextView>(R.id.textViewContact)
                textViewName.text = dados[position].name
                textViewContact.text = dados[position].phone

                rootView.setOnClickListener {
                    val intent = Intent(this@MainActivity, DadosDetailActivity::class.java)
                    intent.putExtra(DadosDetailActivity.DADOS_ID, dados[position].uid)
                    startActivity(intent)
                }

                return rootView
            }
        }
    }

