package ipca.grupo2.room.dao

import androidx.room.*
import ipca.grupo2.room.entities.LeituraEntity

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

    @Query("SELECT * FROM leituraentity WHERE id=id")

    suspend fun upload(){

    }
}