package ipca.grupo2.backend.tables

import android.util.Log
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Source
import com.google.firebase.firestore.ktx.toObject
import ipca.grupo2.backend.Backend
import ipca.grupo2.backend.models.Evento
import kotlinx.coroutines.tasks.await

object BackendEvento {
    private const val ref = "eventos"
    private val collection = Backend.getFS().collection(ref)

    suspend fun getEventoByID(idEvento: String) : Evento? {
        val document = collection.document(idEvento)

        return try {
            val snapshot = document.get().await()
            val evento = snapshot.toObject<Evento>()
            evento!!.setId(idEvento)
            evento
        } catch (e: FirebaseFirestoreException) {
            null
        }
    }

    suspend fun getAllEventosByUserID() : MutableList<Evento>?{
        var mutableList : MutableList<Evento> = arrayListOf()

        return try{
            // Getting from server ignoring cache:
            collection.get().addOnSuccessListener { result ->
                for (doc in result) {
                    // this can throw an exception if document handled
                    // incorrectly (missing keys/wrong data types)
                    try {
                        var tempEvento = doc.toObject<Evento>()
                        tempEvento.setId(doc.id)
                        if (tempEvento.getIdGuia() == Backend.getCurrentUser()!!.uid)
                            mutableList.add(tempEvento)
                    } catch (e: Exception){
                        // pls frontend
                    }
                }
            }.await()
            mutableList
        } catch (e: FirebaseFirestoreException){
            mutableList
        }
    }

    suspend fun getAllValidEventosByUserID() : MutableList<Evento>?{
        var mutableList : MutableList<Evento> = arrayListOf()

        return try{
            collection.get().addOnSuccessListener { result ->
                for (doc in result) {
                    try {
                        var tempEvento = doc.toObject<Evento>()
                        tempEvento.setId(doc.id)
                        if (tempEvento.getIdGuia() == Backend.getCurrentUser()!!.uid)
                            // Check valid
                            if (tempEvento.isValid())
                                mutableList.add(tempEvento)
                    } catch (e: Exception){
                        // pls frontend
                    }
                }
            }.await()
            mutableList
        } catch (e: FirebaseFirestoreException){
            mutableList
        }
    }

    fun getCollection() : CollectionReference{
        return this.collection
    }

    fun getRef() : String{
        return this.ref
    }
}