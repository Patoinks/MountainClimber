package ipca.grupo2.backend.tables

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ktx.toObject
import ipca.grupo2.auth.LoginActivity
import ipca.grupo2.backend.models.Evento
import ipca.grupo2.backend.Backend
import kotlinx.coroutines.tasks.await

object BackendEvento {
    private var ref = "eventos";

    public suspend fun getAllEventosByUserID() : MutableList<Evento>?{
        var mutableList : MutableList<Evento> = arrayListOf();
        val collection = Backend.getFS().collection(ref);

        return try{
            collection.get().addOnSuccessListener { result ->
                for (doc in result) {
                    // this can throw an exception if document handled
                    // incorrectly (missing keys/wrong data types)
                    try {
                        var tempEvento = doc.toObject<Evento>();
                        if (tempEvento.getIdGuia() == Backend.getCurrentUser()!!.uid)
                            mutableList.add(tempEvento);
                    } catch (e: Exception){
                        // pls frontend
                    }
                }
            }.await()
            mutableList;
        } catch (e: FirebaseFirestoreException){
            Log.e(LoginActivity.TAG, "In getAllEventosByUserID() -> ", e);
            mutableList;
        }
    }

    public fun getRef() : String{
        return this.ref;
    }
}