package ipca.grupo2.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import ipca.grupo2.backend.Backend
import ipca.grupo2.backend.tables.BackendEvento
import ipca.grupo2.backend.tables.BackendUtilizador
import ipca.grupo2.menu.MainActivity
import ipca.grupo2.databinding.ActivityLoginBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        binding.btLogin.setOnClickListener {

            val email = binding.email.text.toString()
            val password = binding.password.text.toString()

            GlobalScope.launch {
                var success = BackendUtilizador.login(email, password);
                if (success){
                    val intent = Intent(baseContext, MainActivity::class.java);
                    startActivity(intent);
                } else {
                    runOnUiThread{
                        Toast.makeText(
                            baseContext, "Utilizador ou password errado!",
                            Toast.LENGTH_SHORT
                        ).show();
                    }
                }
            }

        }
    }
    companion object {
        const val TAG = "LoginActivity"
    }
}