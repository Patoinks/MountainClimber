package ipca.grupo2.room.dao

import androidx.room.*
import ipca.grupo2.backend.models.Utilizador
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

    // Used to start an event
    fun downloadData(arrUser: MutableList<Utilizador>){
        // Remove current downloaded event, if any
        deleteAll();

        // Register new event
        for (user in arrUser){
            // Force casting to entity
            var userEntity = UtilizadorEntity(
                user.getId()!!,
                user.getContact(),
                user.getHeight(),
                user.getWeight(),
                user.getName(),
                user.getBirthDate(),
                user.getIsGuia()!!
            );
            insert(userEntity);
        }
    }
}