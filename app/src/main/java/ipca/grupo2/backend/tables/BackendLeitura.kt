package ipca.grupo2.backend.tables

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.toObject
import ipca.grupo2.backend.Backend
import ipca.grupo2.backend.models.Leitura
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object BackendLeitura {
    private const val ref = "leituras";
    private val collection = Backend.getFS().collection(ref);

    public suspend fun getAllLeituras() : MutableList<Leitura>? = suspendCoroutine {
            continuation ->
        collection.get().addOnSuccessListener { result ->
            val leituras = mutableListOf<Leitura>()
            for (document in result) {
                var leitura = document.toObject<Leitura>();
                leitura.setId(document.id);
                leituras.add(leitura);
            }
            continuation.resume(leituras);
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