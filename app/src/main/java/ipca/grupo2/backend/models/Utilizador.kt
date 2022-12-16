package ipca.grupo2.backend.models

import com.google.firebase.firestore.QueryDocumentSnapshot

class Utilizador {

    // Attributes
    private var id: String? = null;
    // todo atributes
    private var isGuia: Boolean? = null;

    // Constructors
    constructor()
    constructor(id: String?, isGuia: Boolean?) {
        this.id = id;
        this.isGuia = isGuia;
    }

    // Functions
    companion object {
        fun queryDocToObj(query: QueryDocumentSnapshot): Utilizador {
            return Utilizador(
                query.data.getValue("id") as String?,
                query.data.getValue("isGuia") as Boolean?
            );
        }
    }

}