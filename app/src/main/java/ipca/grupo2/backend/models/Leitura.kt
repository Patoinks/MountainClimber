package ipca.grupo2.backend.models

import ipca.grupo2.room.entities.LeituraEntity

class Leitura {
    // Attributes
    private var id: String? = null
    private var o2: Int? = null
    private var altitude: Int? = null

    // constructors
    constructor()

    constructor(id: String?, o2: Int?, altitude: Int?) {
        this.id = id
        this.o2 = o2
        this.altitude = altitude
    }

    // Functions / gets sets
    fun setId(value: String){
        this.id = value
    }

    fun setO2(value: Int){
        this.o2 = value
    }

    fun setAltitude(value: Int){
        this.altitude = value
    }

    fun getId() : String?{
        return this.id
    }

    fun getO2() : Int?{
        return this.o2
    }

    fun getAltitude() : Int?{
        return this.altitude
    }

    companion object{
        fun toEntity(leitura: Leitura) : LeituraEntity {
            return LeituraEntity(
                leitura.getId()!!,
                leitura.getO2()!!,
                leitura.getAltitude()!!
            )
        }
    }
}