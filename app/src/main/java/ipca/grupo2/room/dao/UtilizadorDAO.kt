package ipca.grupo2.room.dao

import androidx.room.*
import ipca.grupo2.room.entities.UtilizadorEntity

@Dao
interface UtilizadorDAO {
    @Query("SELECT * FROM UtilizadorEntity")
    fun getAll(): List<UtilizadorEntity>

    @Insert
    fun insert(utilizador: UtilizadorEntity)

    @Update
    fun update(user: UtilizadorEntity)

    @Delete
    fun delete(user: UtilizadorEntity)

    @Query("DELETE FROM UtilizadorEntity WHERE id=id;")
    fun deleteAll()
}