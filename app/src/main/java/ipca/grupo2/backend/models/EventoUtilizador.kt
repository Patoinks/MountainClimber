package ipca.grupo2.backend.models

class  EventoUtilizador {
    // Attributes
    private var id: String? = null
    private var idEvento: String? = null
    private var idUtilizador: String? = null

    // Constructors
    constructor()

    constructor(id: String?, idEvento: String?, idUtilizador: String?) {
        this.id = id
        this.idEvento = idEvento
        this.idUtilizador = idUtilizador
    }

    // Functions / gets sets
    fun setId(value : String){
        this.id = value
    }

    fun setIdEvento(value: String){
        this.idEvento = value
    }

    fun setIdUtilizador(value: String){
        this.idUtilizador = value
    }

    fun getId() : String?{
        return this.id
    }

    fun getIdEvento() : String?{
        return this.idEvento
    }

    fun getIdUtilizador() : String?{
        return this.idUtilizador
    }
}