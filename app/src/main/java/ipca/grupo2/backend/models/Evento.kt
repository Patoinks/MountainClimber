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
    private var dem: Int? = null
    private var elevation: Int? = null
    private var latitude: Float? = null
    private var longitude: Float? = null
    private var name: String? = null
    private var registrations: Int? = null

    // Constructors
    constructor()

    constructor(
        id: String?,
        idGuia: String?,
        location: String?,
        dateStart: Date?,
        dateFinish: Date?,
        image: String?,
        dem: Int?,
        elevation: Int?,
        latitude: Float?,
        longitude: Float?,
        name: String?,
        registrations: Int?
    ) {
        this.id = id
        this.idGuia = idGuia
        this.location = location
        this.dateStart = dateStart
        this.dateFinish = dateFinish
        this.image = image
        this.dem = dem
        this.elevation = elevation
        this.latitude = latitude
        this.longitude = longitude
        this.name = name
        this.registrations = registrations
    }

    // Functions / gets sets
    fun setId(value: String){
        this.id = value
    }

    fun setIdGuia(value: String){
        this.idGuia = value
    }

    fun setLocation(value: String){
        this.location = value
    }

    fun setDateStart(value: Date){
        this.dateStart = value
    }

    fun setDateFinish(value: Date){
        this.dateFinish = value

    }

    fun setImage(value: String){
        this.image = value
    }

    fun setLongitude(value: Float){
        this.longitude = value
    }

    fun setRegistrations(value: Int){
        this.registrations = value
    }


    fun setLatitude(value: Float){
        this.latitude = value
    }

    fun setName(value: String){
        this.name = value
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


    fun getDem() : Int?{
        return this.dem;
    }

    fun getElevation() : Int?{
        return this.elevation
    }

    fun getLatitude() : Float?{
        return this.latitude
    }

    fun getLongitude() : Float? {
        return this.longitude
    }

    fun getName() : String?{
        return this.name
    }

    fun getRegistrations() : Int?{
        return this.registrations
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
                evento.getDem()!!,
                evento.getElevation()!!,
                evento.getLatitude()!!,
                evento.getLongitude()!!,
                evento.getName()!!,
                evento.getRegistrations()!!
            )
        }
    }
}
