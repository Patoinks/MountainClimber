package ipca.grupo2.backend.models

import java.util.*

class Utilizador {

    // Attributes
    private var id: String? = null;
    private var contact: String? = null;
    private var height: Float? = null;
    private var weight: Float? = null;
    private var name: String? = null;
    private var birthDate: Date? = null;
    private var email: String? = null;
    private var password: String? = null;
    private var isGuia: Int? = null;

    // Constructors
    constructor()

    constructor(id: String?, email: String?, password: String?, isGuia: Int?) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.isGuia = isGuia;

        // get rest of non-collection values

    }

    constructor(
        id: String?,
        contact: String?,
        height: Float?,
        weight: Float?,
        name: String?,
        birthDate: Date?,
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

    public fun getId() : String?{
        return this.id;
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

    public fun getEmail() : String?{
        return this.email;
    }

    // CAREFUL CALLING THIS
    public fun getPassword() : String?{
        return null; // not getting password unless 100% necessary
        //return this.password;
    }

    public fun getIsGuia() : Boolean?{
        return (this.isGuia == 1);
    }
}