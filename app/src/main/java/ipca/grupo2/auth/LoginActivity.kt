package ipca.grupo2.auth

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
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

    // Class variables
    private lateinit var auth: FirebaseAuth
    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout for this fragment, bind and get FireBase authentication methods into "auth"
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth


        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val windowInsetsController = ViewCompat.getWindowInsetsController(window.decorView) ?: return
            windowInsetsController.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
        }else{
            val flags =
                (View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
            window!!.decorView.systemUiVisibility = flags
        }

        // On click login
        binding.btLogin.setOnClickListener {
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()

            GlobalScope.launch {
                var success = BackendUtilizador.login(email, password);
                if (success){
                    val intent = Intent(baseContext, MainActivity::class.java);
                    startActivity(intent);
                }

                else {
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