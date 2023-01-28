package ipca.grupo2.backend.models

import ipca.grupo2.room.entities.LeituraEntity
import java.sql.Date

class Leitura {
    // Attributes
    private var id: String? = null;
    private var o2: Int? = null;
    private var altitude: Int? = null;
    private var data: Date? = null;
    private var idEvento: String? = null;
    private var idUtilizador: String? = null;
    private var nausea: Int? = null;
    private var cabeca: Int? = null;
    private var apetite: Int? = null;
    private var noite: Int? = null;

    // constructors
    constructor()

    constructor(
        id: String?,
        o2: Int?,
        altitude: Int?,
        data: Date?,
        idEvento: String?,
        idUtilizador: String?,
        nausea: Int?,
        cabeca: Int?,
        apetite: Int?,
        noite: Int?
    ) {
        this.id = id
        this.o2 = o2
        this.altitude = altitude
        this.data = data
        this.idEvento = idEvento
        this.idUtilizador = idUtilizador
        this.nausea = nausea
        this.cabeca = cabeca
        this.apetite = apetite
        this.noite = noite
    }

    // Functions / gets sets
    public fun setId(value: String){
        this.id = value;
    }

    public fun setO2(value: Int){
        this.o2 = value;
    }

    public fun setAltitude(value: Int){
        this.altitude = value;
    }

    public fun setData(value: Date){
        this.data = value;
    }

    public fun setIdEvento(value: String){
        this.idEvento = value;
    }

    public fun setIdUtilizador(value: String){
        this.idUtilizador = value;
    }

    public fun setNausea(value: Int){
        this.nausea = value;
    }

    public fun setCabeca(value: Int){
        this.cabeca = value;
    }

    public fun setApetite(value: Int){
        this.apetite = value;
    }

    public fun setNoite(value: Int){
        this.noite = value;
    }

    public fun getId() : String?{
        return this.id;
    }

    public fun getO2() : Int?{
        return this.o2;
    }

    public fun getAltitude() : Int?{
        return this.altitude;
    }

    public fun getData() : Date?{
        return this.data;
    }

    public fun getIdEvento() : String?{
        return this.idEvento;
    }

    public fun getIdUtilizador() : String?{
        return this.idUtilizador;
    }

    public fun getNausea() : Int?{
        return this.nausea;
    }

    public fun getCabeca() : Int?{
        return this.cabeca;
    }

    public fun getApetite() : Int?{
        return this.apetite;
    }

    public fun getNoite() : Int?{
        return this.noite;
    }

    companion object{
        public fun toEntity(leitura: Leitura) : LeituraEntity {
            return LeituraEntity(
                leitura.getId()!!,
                leitura.getIdEvento()!!,
                leitura.getIdUtilizador()!!,
                leitura.getO2()!!,
                leitura.getAltitude()!!,
                leitura.getData()!!,
                leitura.getNausea()!!,
                leitura.getCabeca()!!,
                leitura.getApetite()!!,
                leitura.getNoite()!!
            );
        }
    }
}