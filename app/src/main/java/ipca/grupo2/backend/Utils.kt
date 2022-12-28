package ipca.grupo2.backend

import android.util.Patterns

object Utils {
    fun isValidEmail(email: String): Boolean {

        // using pre-existing string email mask in android.util
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}