package ipca.grupo2.backend.models

import ipca.grupo2.room.entities.EventoEntity
import java.util.*

class Evento {
    // Attributes
    private var id: String? = null
    private var idGuia: String? = null
    private var location: String? = null
    private var dateStart: Date? = null
    private var dateFinish: Date? = null
    private var image: String? = null
    private var description: String? = null

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

    // Functions / gets sets
    fun setId(value: String){
        this.id = value
    }

    fun getId() : String?{
        return this.id
    }

    fun getIdGuia() : String?{
        return this.idGuia
    }

    fun getLocation() : String?{
        return this.location
    }

    fun getDateStart() : Date?{
        return this.dateStart
    }

    fun getDateFinish() : Date?{
        return this.dateFinish
    }

    fun getImage() : String?{
        return this.image
    }

    fun getDescription(): String?{
        return this.description
    }

    fun isValid() : Boolean{
        if (this.dateFinish == null || this.dateStart == null)
            return false

        return !(this.dateFinish!!.after(Date()))
    }

    companion object{
        fun toEntity(evento: Evento) : EventoEntity{
            return EventoEntity(
                evento.getId()!!,
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
