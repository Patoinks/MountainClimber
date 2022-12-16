package ipca.grupo2.backend.tables

import com.google.firebase.firestore.ktx.toObject
import ipca.grupo2.backend.models.Evento
import java.util.*
import ipca.grupo2.backend.Backend
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

object BackendEvento {
    private var ref = "eventos";

    suspend public fun getAllEventosByUserID() : MutableList<Evento>?{
        var mutableList : MutableList<Evento> = arrayListOf();
        var curUser = Backend.getCurrentUser();

        Backend.getFS().collection(ref).get().addOnSuccessListener { result ->
            for (doc in result)
            {
                // this can throw an exception if document handled
                // incorrectly
                try {
                    var tempEvento = doc.toObject<Evento>();
                    if (tempEvento.idGuia == curUser?.uid)
                        mutableList.add(tempEvento);
                } catch (e: Exception){
                    // pls frontend
                }
            }
        }

        return mutableList;
    }
}