package ipca.grupo2.backend.tables

import android.util.Log
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ktx.toObject
import ipca.grupo2.auth.LoginActivity.Companion.TAG
import ipca.grupo2.backend.Backend
import ipca.grupo2.backend.Utils
import ipca.grupo2.backend.models.Utilizador
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

object BackendUtilizador {
    private const val ref = "utilizadores";
    private val collection = Backend.getFS().collection(ref);

    suspend fun login(email: String, password: String) : Boolean{
        var isSuccessful = false;

        // Null-Check and email-mask check in order to prevent firebase from
        // exploding
        if (email.isEmpty() || password.isEmpty() || !Utils.isValidEmail(email))
            return isSuccessful; // false

        return try {
            Backend.getAU().signInWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful){
                    isSuccessful = true;
                    GlobalScope.launch {
                        // Prevent non-guias from logging in
                        val user = getUtilizadorById(Backend.getCurrentUser()!!.uid);
                        if (user!!.getIsGuia() == false){
                            signOut();
                            isSuccessful = false;
                        }
                    }
                }
            }.await()
            isSuccessful;
        } catch (e: FirebaseFirestoreException){
            Log.e(TAG, "In login() -> ", e);
            isSuccessful;
        }
    }

    public suspend fun getUtilizadorById (uid: String) : Utilizador? {
        val document = collection.document(uid);

        return try {
            val snapshot = document.get().await();
            snapshot.toObject<Utilizador>();
        } catch (e: FirebaseFirestoreException) {
            Log.e(TAG, "In getUtilizadorById() -> ", e);
            null;
        }
    }

    public suspend fun getAllUtilizadoresByEvento(idEvento: String) : MutableList<Utilizador>{
        var mutableList : MutableList<Utilizador> = arrayListOf();

        return try{
            // get all events and filter out the ones
            // we don't want
            var allEvents = BackendEventoUtilizador.getAllEventosUtilizadores();
            if (allEvents != null){ for (event in allEvents){
                if (event.getIdEvento() == idEvento){
                    var tempUtilizador = getUtilizadorById(event.getIdUtilizador()!!);
                    mutableList.add(tempUtilizador!!);
                }
            }
            }
            mutableList;
        } catch (e: FirebaseFirestoreException){
            Log.e(TAG, "In getAllUtilizadoresByEvento() -> ", e);
            mutableList;
        }
    }

    public suspend fun signOut(){
        Backend.getAU().signOut();
    }

    public fun getCollection() : CollectionReference {
        return this.collection;
    }

    public fun getRef() : String{
        return this.ref;
    }
}