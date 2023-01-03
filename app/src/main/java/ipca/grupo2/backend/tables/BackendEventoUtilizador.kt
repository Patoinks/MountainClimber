package ipca.grupo2.backend.tables

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.toObject
import ipca.grupo2.backend.Backend
import ipca.grupo2.backend.models.EventoUtilizador
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object BackendEventoUtilizador {
    private const val ref = "eventosUtilizadores";
    private val collection = Backend.getFS().collection(ref);

    public suspend fun getAllEventosUtilizadores() : MutableList<EventoUtilizador>? = suspendCoroutine {
        continuation ->
            collection.get().addOnSuccessListener { result ->
                val eventosUtilizadores = mutableListOf<EventoUtilizador>()
                for (document in result) {
                    var eventoUtilizador = document.toObject<EventoUtilizador>();
                    eventoUtilizador.setId(document.id);
                    eventosUtilizadores.add(eventoUtilizador);
                }
                continuation.resume(eventosUtilizadores);
            }.addOnFailureListener { exception ->
                continuation.resumeWithException(exception);
            }
    }

    public fun getCollection() : CollectionReference {
        return this.collection;
    }

    public fun getRef() : String{
        return this.ref;
    }
}