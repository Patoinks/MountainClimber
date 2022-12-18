package ipca.grupo2.backend.tables

import com.google.firebase.firestore.ktx.toObject
import ipca.grupo2.backend.Backend
import ipca.grupo2.backend.models.EventoUtilizador
import kotlinx.coroutines.tasks.await

object BackendEventoUtilizador {
    private var ref = "eventosUtilizadores";

    public suspend fun getAllEventosUtilizadores() : MutableList<EventoUtilizador>?{
        var mutableList : MutableList<EventoUtilizador> = arrayListOf();

        Backend.getFS().collection(ref).get().addOnSuccessListener { result ->
            for (doc in result) {
                try{
                    var eventoUtilizador = doc.toObject<EventoUtilizador>();
                    mutableList.add(eventoUtilizador);
                } catch (e: Exception){
                    // pls frontend
                }
            }
        }.await()

        return mutableList;
    }

    public fun getRef() : String{
        return this.ref;
    }
}