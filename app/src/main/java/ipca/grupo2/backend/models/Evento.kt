package ipca.grupo2.backend.models

import com.google.firebase.firestore.QueryDocumentSnapshot
import org.json.JSONObject
import java.util.*

class Evento {

    // Attributes
    private var id: String? = null;
    private var idGuia: String? = null;
    private var location: String? = null;
    private var dateStart: Date? = null;
    private var dateFinish: Date? = null;

    // Constructors
    constructor()

    constructor(id: String?, idGuia: String?, location: String?, dateStart: Date?, dateFinish: Date?) {
        this.id = id;
        this.idGuia = idGuia;
        this.location = location;
        this.dateStart = dateStart;
        this.dateFinish = dateFinish;
    }

    public fun getIdGuia() : String?{
        return this.idGuia;
    }
}
