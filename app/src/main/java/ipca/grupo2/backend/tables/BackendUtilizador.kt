package ipca.grupo2.backend.tables

import android.content.Intent
import android.util.Log
import android.widget.Toast
import ipca.grupo2.auth.LoginActivity
import ipca.grupo2.backend.Backend
import ipca.grupo2.menu.MainActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

object BackendUtilizador {
    private var ref = "utilizadores";

    suspend fun login(email: String, password: String) : Boolean{
        var isSuccessful = false;

        Backend.getAU().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                isSuccessful = it.isSuccessful
            }

        return isSuccessful;
    }
}