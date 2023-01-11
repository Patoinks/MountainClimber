package ipca.grupo2.backend.models

import ipca.grupo2.room.entities.EventoEntity
import java.util.*

class Evento {
    // Attributes
    private var id: String? = null;
    private var idGuia: String? = null;
    private var location: String? = null;
    private var dateStart: Date? = null;
    private var dateFinish: Date? = null;
    private var image: String? = null;
    private var description: String? = null;

    // Constructors
    constructor()

    constructor(
        id: String?,
        idGuia: String?,
        location: String?,
        dateStart: Date?,
        dateFinish: Date?,
        image: String?,
        description: String?
    ) {
        this.id = id
        this.idGuia = idGuia
        this.location = location
        this.dateStart = dateStart
        this.dateFinish = dateFinish
        this.image = image
        this.description = description
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

    public fun getImage() : String?{
        return this.image;
    }

    public fun getDescription(): String?{
        return this.description;
    }

    public fun isValid() : Boolean{
        if (this.dateFinish == null || this.dateStart == null)
            return false;

        return !(this.dateFinish!!.after(Date()));
    }

    companion object{
        public fun toEntity(evento: Evento) : EventoEntity{
            return EventoEntity(
                evento?.getId()!!,
                evento.getIdGuia()!!,
                evento.getLocation()!!,
                evento.getDateStart()!!.toString(),
                evento.getDateFinish()!!.toString(),
                evento.getImage()!!,
                evento.getDescription()!!
            )
        }
    }
}
