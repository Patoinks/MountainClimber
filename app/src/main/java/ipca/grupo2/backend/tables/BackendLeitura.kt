package ipca.grupo2.backend.tables

import android.content.Context
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.toObject
import ipca.grupo2.backend.Backend
import ipca.grupo2.backend.models.Leitura
import ipca.grupo2.room.AppDatabase
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object BackendLeitura {
    private const val ref = "leituras"
    private val collection = Backend.getFS().collection(ref)

    suspend fun getAllLeituras() : MutableList<Leitura>? = suspendCoroutine {
            continuation ->
        collection.get().addOnSuccessListener { result ->
            val leituras = mutableListOf<Leitura>()
            for (document in result) {
                var leitura = document.toObject<Leitura>()
                leitura.setId(document.id)
                leituras.add(leitura)
            }
            continuation.resume(leituras)
        }.addOnFailureListener { exception ->
            continuation.resumeWithException(exception)
        }
    }

    suspend fun uploadAllLeituras(context: Context) : Boolean {
        var leituras = ArrayList<Leitura>();
        var leiturasEntity = AppDatabase.getDatabase(context)!!.leituraDao().getAll();

        leiturasEntity.forEach{
            leituras.add(Leitura.fromEntity(it));
        }

        try {
            leituras.forEach{
                collection.add(mapOf(
                    "o2" to it.getO2(),
                    "altitude" to it.getAltitude(),
                    "data" to it.getData(),
                    "idEvento" to it.getIdEvento(),
                    "idUtilizador" to it.getIdUtilizador(),
                    "nausea" to it.getNausea(),
                    "cabeca" to it.getCabeca(),
                    "apetite" to it.getApetite(),
                    "noite" to it.getNoite()
                ));
            }

            AppDatabase.getDatabase(context)!!.leituraDao().deleteAll();
            AppDatabase.getDatabase(context)!!.eventoDao().deleteAll();
            AppDatabase.getDatabase(context)!!.utilizadorDao().deleteAll();

            // SE ESTIVERES A VER ISTO TENS QUE TIRAR O EVENTO DE SELECIONADO E OU POR NA PAG PRINCIAPL!
        } catch (e: java.lang.Exception) {
            // frontend erro
        }

        return true;
    }

    fun getCollection() : CollectionReference {
        return this.collection
    }

    fun getRef() : String{
        return this.ref
    }
}