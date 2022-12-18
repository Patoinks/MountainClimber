package ipca.grupo2.backend.models

class Utilizador {

    // Attributes
    private var id: String? = null;
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
    }

    public fun getId() : String?{
        return this.id;
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