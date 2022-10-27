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
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import ipca.grupo2.databinding.ActivityMainBinding
import java.io.File

class MainActivity : AppCompatActivity() {

    var dados : List<Dados> = arrayListOf()
    lateinit var binding: ActivityMainBinding
    private lateinit var analytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listViewContacts = findViewById<ListView>(R.id.listViewContacts)
        val contactsAdapter = DadosAdapter()
        listViewContacts.adapter = contactsAdapter

        findViewById<FloatingActionButton>(R.id.floatingActionButton)
            .setOnClickListener {
                val intent = Intent(this@MainActivity, DadosDetailActivity::class.java)
                startActivity(intent)
            }

        AppDatabase
            .getDatabase(this@MainActivity)
            ?.dadosDao()
            ?.getAllLive()?.observe(this){
                this@MainActivity.dados = it
                contactsAdapter.notifyDataSetChanged()
            }

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

        analytics = Firebase.analytics

        val storageRef = FirebaseStorage.getInstance().reference.child("image/images.jpg")
        val localfile = File.createTempFile("tempImage", "jpg")

        storageRef.getFile(localfile).addOnSuccessListener {

            /*if (progressDialog.isShowing)
                progressDialog.dismiss()*/

            val bitmap = BitmapFactory.decodeFile(localfile.absolutePath)
            //val bitmap = Bitmap.createScaledBitmap(image, 1200, 628, false)

            //binding.imageViewURL.setImageBitmap(bitmap)
            /*val image = bitmap
            Picasso.get().load(image).resize(10, 10).into(bind.imageViewURL)*/

        }.addOnFailureListener {

        }

    }

    inner class DadosAdapter : BaseAdapter(){
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
            val rootView = layoutInflater.inflate(R.layout.row_contact,viewGroup,false)

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