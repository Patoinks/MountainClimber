package ipca.grupo2.backend.models

import com.google.firebase.firestore.PropertyName
import io.grpc.okhttp.internal.Util
import ipca.grupo2.room.entities.EventoEntity
import ipca.grupo2.room.entities.UtilizadorEntity
import java.util.Date

class Utilizador {
    // Attributes
    private var id: String? = null

    @PropertyName("Contact")
    private var contact: Long? = null

    @PropertyName("Height")
    private var height: Float? = null

    @PropertyName("Weight")
    private var weight: Float? = null

    @PropertyName("Name")
    private var name: String? = null

    @PropertyName("BirthDate")
    private var birthDate: Date? = null

    @PropertyName("isGuia")
    private var isGuia: Int? = null

    // Constructors
    constructor()

    constructor(
        id: String?,
        contact: Long?,
        height: Float?,
        weight: Float?,
        name: String?,
        birthDate: Date?,
        isGuia: Int?
    ) {
        this.id = id
        this.contact = contact
        this.height = height
        this.weight = weight
        this.name = name
        this.birthDate = birthDate
        this.isGuia = isGuia
    }

    // Functions / gets sets
    fun setId(value: String){
        this.id = value
    }

    fun setContact(value: Long){
        this.contact = value
    }

    fun setHeight(value: Float){
        this.height = value
    }

    fun setWeight(value: Float){
        this.weight = value
    }

    fun setName(value: String){
        this.name = value
    }

    fun setBirthDate(value: Date){
        this.birthDate = value
    }

    fun setIsGuia(value: Int){
        this.isGuia = value
    }

    fun getId() : String?{
        return this.id
    }

    fun getContact() : Long?{
        return this.contact
    }

    fun getHeight() : Float?{
        return this.height
    }

    fun getWeight() : Float?{
        return this.weight
    }

    fun getName() : String?{
        return this.name
    }

    fun getBirthDate() : Date?{
        return this.birthDate
    }

    fun getIsGuia() : Int?{
        return this.isGuia
    }

    fun isGuia() : Boolean{
        return (this.isGuia == 1)
    }


    companion object{
        fun toEntity(user: Utilizador) : UtilizadorEntity {
            return UtilizadorEntity(
                user.getId()!!,
                user.getContact(),
                user.getHeight(),
                user.getWeight(),
                user.getName(),
                user.getBirthDate().toString(),
                user.getIsGuia()!!
            )
        }
    }
}