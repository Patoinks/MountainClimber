package ipca.grupo2

import androidx.annotation.NonNull
import androidx.lifecycle.LiveData
import androidx.room.*
import java.util.*

@Entity
class Dados {

    @NonNull
    @PrimaryKey var uid: String
    var name: String? =  null
    var phone : String? =  null

    constructor(name: String?, phone: String?) {
        this.uid = UUID.randomUUID().toString()
        this.name = name
        this.phone = phone
    }
}

@Dao
interface DadosDao {

    @Query("SELECT * FROM dados")
    fun getAll(): List<Dados>

    @Query("SELECT * FROM dados")
    fun getAllLive(): LiveData<List<Dados>>

    @Query("SELECT * FROM dados WHERE uid = :uid")
    fun getById(uid:String ): Dados

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(dados: Dados)

    @Update
    fun update(dados: Dados)


    @Delete
    fun delete(dados: Dados)
}

