package ipca.grupo2.backend.models

import com.google.firebase.firestore.PropertyName
import java.util.Date

class Utilizador {
    // Attributes
    private var id: String? = null;
    @PropertyName("Contact")
    private var contact: Long? = null;
    @PropertyName("Height")
    private var height: Float? = null;
    @PropertyName("Weight")
    private var weight: Float? = null;
    @PropertyName("Name")
    private var name: String? = null;
    @PropertyName("BirthDate")
    private var birthDate: Date? = null;
    @PropertyName("isGuia")
    private var isGuia: Int? = null;

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

    public fun setId(value: String){
        this.id = value;
    }

    public fun setContact(value: Long){
        this.contact = value;
    }

    public fun setHeight(value: Float){
        this.height = value;
    }

    public fun setWeight(value: Float){
        this.weight = value;
    }

    public fun setName(value: String){
        this.name = value;
    }

    public fun setBirthDate(value: Date){
        this.birthDate = value;
    }

    public fun setIsGuia(value: Int){
        this.isGuia = value;
    }

    public fun getId() : String?{
        return this.id;
    }

    public fun getContact() : Long?{
        return this.contact;
    }

    public fun getHeight() : Float?{
        return this.height;
    }

    public fun getWeight() : Float?{
        return this.weight;
    }

    public fun getName() : String?{
        return this.name;
    }

    public fun getBirthDate() : Date?{
        return this.birthDate;
    }

    public fun getIsGuia() : Int?{
        return this.isGuia;
    }

    public fun isGuia() : Boolean{
        return (this.isGuia == 1);
    }
}