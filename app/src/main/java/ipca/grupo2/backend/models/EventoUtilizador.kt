package ipca.grupo2.backend.models

class  EventoUtilizador {

    // Attributes
    private var id: String? = null;
    private var idEvento: String? = null;
    private var idUtilizador: String? = null;

    // Constructors
    constructor()

    constructor(id: String?, idEvento: String?, idUtilizador: String?) {
        this.id = id;
        this.idEvento = idEvento;
        this.idUtilizador = idUtilizador;
    }

    public fun setId(value : String){
        this.id = value;
    }

    public fun setIdEvento(value: String){
        this.idEvento = value;
    }

    public fun setIdUtilizador(value: String){
        this.idUtilizador = value;
    }

    public fun getId() : String?{
        return this.id;
    }

    public fun getIdEvento() : String?{
        return this.idEvento;
    }

    public fun getIdUtilizador() : String?{
        return this.idUtilizador;
    }
}