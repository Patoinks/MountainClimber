package ipca.grupo2.backend.models

import com.google.firebase.firestore.QueryDocumentSnapshot

class Utilizador {

    // Attributes
    private var id: String? = null;
    private var email: String? = null;
    private var password: String? = null;
    private var isGuia: Boolean? = null;

    // Constructors
    constructor()
    constructor(id: String?, email: String?, password: String?, isGuia: Boolean?) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.isGuia = isGuia;
    }

    public fun getId() : String?{
        return this.id;
    }

    public fun getIsGuia() : Boolean?{
        return this.isGuia;
    }
}