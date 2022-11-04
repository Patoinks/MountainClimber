package ipca.grupo2

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import ipca.grupo2.auth.LoginActivity
import ipca.grupo2.databinding.ActivitySplashBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch(Dispatchers.IO){
            delay(2000)
            lifecycleScope.launch(Dispatchers.Main){
                val auth = Firebase.auth
                val currentUser = auth.currentUser
                if (currentUser != null){
                    startActivity(
                        Intent(this@SplashActivity, MainActivity::class.java)
                    )
                    finish()
                }else{
                    startActivity(
                        Intent(this@SplashActivity, LoginActivity::class.java)
                    )
                    finish()
                }
            }
        }
    }
}