package ipca.grupo2.backend.tables

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ktx.toObject
import ipca.grupo2.auth.LoginActivity
import ipca.grupo2.backend.Backend
import ipca.grupo2.backend.models.EventoUtilizador
import kotlinx.coroutines.tasks.await

object BackendEventoUtilizador {
    private var ref = "eventosUtilizadores";

    public suspend fun getAllEventosUtilizadores() : MutableList<EventoUtilizador>?{
        var mutableList : MutableList<EventoUtilizador> = arrayListOf();
        val collection = Backend.getFS().collection(ref);

        return try{
            collection.get().addOnSuccessListener { result ->
                for (doc in result) {
                    try{
                        var eventoUtilizador = doc.toObject<EventoUtilizador>();
                        mutableList.add(eventoUtilizador);
                    } catch (e: Exception){
                        // pls frontend
                    }
                }
            }.await()
            mutableList;
        } catch (e: FirebaseFirestoreException){
            Log.e(LoginActivity.TAG, "In getAllEventosUtilizadores() -> ", e);
            mutableList;
        }
    }

    public fun getRef() : String{
        return this.ref;
    }
}