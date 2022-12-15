package ipca.grupo2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ipca.grupo2.room.AppDatabase
import ipca.grupo2.room.Dados
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DadosDetailActivity : AppCompatActivity() {

    var dados : Dados? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_detail)

        val uid = intent.getStringExtra(DADOS_ID)

        val editTextDetailName = findViewById<TextView>(R.id.editTextDetailName)
        val editTextDetailContact = findViewById<TextView>(R.id.editTextDetailContact)
        val fabSave = findViewById<FloatingActionButton>(R.id.fabSave)
        val fabDelete = findViewById<FloatingActionButton>(R.id.fabDelete)


        uid?.let {
            GlobalScope.launch (Dispatchers.IO) {
                dados = AppDatabase.getDatabase(this@DadosDetailActivity)?.dadosDao()?.getById(it)
                GlobalScope.launch (Dispatchers.Main) {
                    editTextDetailName.text = dados?.name
                    editTextDetailContact.text =  dados?.phone
                }
            }
        }




        fabSave.setOnClickListener {
            dados?.let {
                it.name =  editTextDetailName.text.toString()
                it.phone = editTextDetailContact.text.toString()
                GlobalScope.launch (Dispatchers.IO){
                    AppDatabase.getDatabase(this@DadosDetailActivity)?.dadosDao()?.update(it)
                    GlobalScope.launch (Dispatchers.Main) {
                        finish()
                    }
                }
            }?: run {
                val dados = Dados(
                    editTextDetailName.text.toString(),
                    editTextDetailContact.text.toString()
                )
                GlobalScope.launch (Dispatchers.IO){
                    AppDatabase.getDatabase(this@DadosDetailActivity)?.dadosDao()?.insert(dados)
                    GlobalScope.launch (Dispatchers.Main) {
                        finish()
                    }
                }
            }
        }

        fabDelete.setOnClickListener {
            dados?.let {
                GlobalScope.launch(Dispatchers.IO) {
                    AppDatabase.getDatabase(this@DadosDetailActivity)?.dadosDao()
                        ?.delete(it)
                    GlobalScope.launch(Dispatchers.Main) {
                        finish()
                    }
                }
            }?:run{
                finish()
            }
        }

    }

    companion object{
        const val DADOS_ID = "dados_id"
    }
}