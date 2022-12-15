package ipca.grupo2.backend.models

import com.google.firebase.firestore.QueryDocumentSnapshot
import org.json.JSONObject
import java.util.*

class Evento {

    // Attributes
    var id: UUID? = null
    var idGuia: UUID? = null
    var location: String? = null
    var dateStart: Date? = null
    var dateFinish: Date? = null

    // Constructors
    constructor(id: UUID?, idGuia: UUID?, location: String?, dateStart: Date?, dateFinish: Date?) {
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
                UUID.fromString(query.id),
                query.data.getValue("idGuia") as UUID?,
                query.data.getValue("location") as String?,
                query.data.getValue("dateBeggining") as Date?,
                query.data.getValue("dateFinish") as Date?,
            )
        }
    }
}
