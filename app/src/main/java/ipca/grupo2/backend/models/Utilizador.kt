package ipca.grupo2.backend.models

import com.google.firebase.firestore.PropertyName

class Utilizador {
    // Attributes
    private var id: String? = null;
    @PropertyName("Contact")
    private var contact: String? = null;
    @PropertyName("Height")
    private var height: String? = null;
    @PropertyName("Weight")
    private var weight: String? = null;
    @PropertyName("Name")
    private var name: String? = null;
    @PropertyName("BirthDate")
    private var birthDate: String? = null;
    private var email: String? = null; // no acess
    private var password: String? = null; // no acess
    @PropertyName("isGuia")
    private var isGuia: Int? = null;

    // Constructors
    constructor()

    constructor(
        id: String?,
        contact: String?,
        height: String?,
        weight: String?,
        name: String?,
        birthDate: String?,
        email: String?,
        password: String?,
        isGuia: Int?
    ) {
        this.id = id
        this.contact = contact
        this.height = height
        this.weight = weight
        this.name = name
        this.birthDate = birthDate
        this.email = email
        this.password = password
        this.isGuia = isGuia
    }

    public fun setId(value: String){
        this.id = value;
    }

    public fun setContact(value: String){
        this.contact = value;
    }

    public fun setHeight(value: String){
        this.height = value;
    }

    public fun setWeight(value: String){
        this.weight = value;
    }

    public fun setName(value: String){
        this.name = value;
    }

    public fun setBirthDate(value: String){
        this.birthDate = value;
    }

    public fun setEmail(value: String){
        this.email = value;
    }

    public fun setPassword(value: String){
        this.email = value;
    }

    public fun setIsGuia(value: Int){
        this.isGuia = value;
    }

    public fun getId() : String?{
        return this.id;
    }

    public fun getContact() : String?{
        return this.contact;
    }

    public fun getHeight() : String?{
        return this.height;
    }

    public fun getWeight() : String?{
        return this.weight;
    }

    public fun getName() : String?{
        return this.name;
    }

    public fun getBirthDate() : String?{
        return this.birthDate;
    }

    public fun getEmail() : String?{
        return this.email;
    }

    // CAREFUL CALLING THIS
    public fun getPassword() : String?{
        return null; // not getting password unless 100% necessary
        //return this.password;
    }

    public fun getIsGuia() : Int?{
        return this.isGuia;
    }

    public fun isGuia() : Boolean{
        return (this.isGuia == 1);
    }

}