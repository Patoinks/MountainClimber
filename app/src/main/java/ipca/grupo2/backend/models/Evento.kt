package ipca.grupo2.backend.models

import com.google.firebase.firestore.QueryDocumentSnapshot
import org.json.JSONObject
import java.util.*

class Evento {

    // Attributes
    var id: String? = null
    var idGuia: String? = null
    var location: String? = null
    var dateStart: Date? = null
    var dateFinish: Date? = null

    // Constructors
    constructor()

    constructor(id: String?, idGuia: String?, location: String?, dateStart: Date?, dateFinish: Date?) {
        this.id = id
        this.idGuia = idGuia
        this.location = location
        this.dateStart = dateStart
        this.dateFinish = dateFinish
    }

    // Functions
    companion object {
        fun queryDocToObj(query: QueryDocumentSnapshot): Evento {
            return Evento(
                query.data.getValue("id") as String?,
                query.data.getValue("idGuia") as String?,
                query.data.getValue("location") as String?,
                query.data.getValue("dateStart") as Date?,
                query.data.getValue("dateFinish") as Date?,
            )
        }
    }
}
