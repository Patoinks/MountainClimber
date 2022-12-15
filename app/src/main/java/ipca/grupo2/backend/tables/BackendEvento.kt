package ipca.grupo2.backend.tables

import ipca.grupo2.backend.models.Evento
import java.util.*
import ipca.grupo2.backend.Backend

class BackendEvento {
    private var backendRef : Backend = Backend()
    private var ref = "eventos"

    fun getAllEventosByUserID() : MutableList<Evento>?{
        var mutableList : MutableList<Evento> = arrayListOf()
        var curUser = backendRef.getCurrentUser()

        backendRef.getFS().collection(ref)
            .get()
            .addOnSuccessListener { result ->
                for (doc in result)
                {
                    var tempEvento = Evento.queryDocToObj(doc)
                    if (tempEvento.idGuia == UUID.fromString(curUser?.uid))
                        mutableList.add(Evento.queryDocToObj(doc))
                }
            }

        return mutableList
    }
}