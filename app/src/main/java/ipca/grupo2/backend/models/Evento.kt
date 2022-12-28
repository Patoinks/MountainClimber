package ipca.grupo2.backend.models

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

    public fun setId(value: String){
        this.id = value;
    }

    public fun getId() : String?{
        return this.id;
    }

    public fun getIdGuia() : String?{
        return this.idGuia;
    }

    public fun getLocation() : String?{
        return this.location;
    }

    public fun getDateStart() : Date?{
        return this.dateStart;
    }

    public fun getDateFinish() : Date?{
        return this.dateFinish;
    }

    public fun isValid() : Boolean{
        if (this.dateFinish == null || this.dateStart == null)
            return false;

        return !(this.dateFinish!!.after(Date()));
    }
}
