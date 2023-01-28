package ipca.grupo2.room.dao

import androidx.room.*
import ipca.grupo2.room.entities.LeituraEntity
import java.sql.Date

@Dao
interface LeituraDAO {
    @Query("SELECT * FROM LeituraEntity")
    fun getAll(): List<LeituraEntity>

    @Insert
    fun insert(leitura: LeituraEntity)

    @Update
    fun update(leitura: LeituraEntity)

    @Delete
    fun delete(leitura: LeituraEntity)

    @Query("DELETE FROM LeituraEntity WHERE id=id;")
    fun deleteAll()

    @Query("SELECT * FROM LeituraEntity WHERE data = :date AND idUtilizador = :id")
    fun getByDate(date: Date, id: String): List<LeituraEntity>

    fun hasLeituraToday(idUtilizador: String) : Boolean{
        val today = Date(System.currentTimeMillis());
        val leituras = getByDate(today, idUtilizador);
        return leituras.isNotEmpty();
    }

    // 1 or 2
    fun numLeituraToday(idUtilizador: String) : Int{
        return if (hasLeituraToday(idUtilizador)) 2 else 1
    }

    suspend fun upload(){

    }
}