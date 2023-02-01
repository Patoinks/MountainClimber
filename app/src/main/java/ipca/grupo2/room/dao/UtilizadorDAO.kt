package ipca.grupo2.room.dao

import androidx.room.*
import ipca.grupo2.backend.models.Utilizador
import ipca.grupo2.room.entities.UtilizadorEntity

@Dao
interface UtilizadorDAO {
    @Query("SELECT * FROM UtilizadorEntity")
    fun getAll(): List<UtilizadorEntity>

    @Query("SELECT * FROM UtilizadorEntity Where id = :id")
    fun get(id: String) : UtilizadorEntity

    @Insert
    fun insert(utilizador: UtilizadorEntity)

    @Update
    fun update(user: UtilizadorEntity)

    @Delete
    fun delete(user: UtilizadorEntity)

    @Query("DELETE FROM UtilizadorEntity WHERE id=id;")
    fun deleteAll()

    @Query("UPDATE UtilizadorEntity SET readingDone = 1 WHERE id = :id")
    fun triggerReading(id: String)

    @Query("SELECT * FROM UtilizadorEntity WHERE readingDone == 1")
    fun getAllLidos() : List<UtilizadorEntity>

    @Query("SELECT * FROM UtilizadorEntity WHERE readingDone == 0")
    fun getAllNaoLidos() : List<UtilizadorEntity>

    fun areAllReadingsDone(): Boolean {
        val allUsers = getAll()
        return allUsers.all { it.readingDone }
    }

    @Query("UPDATE UtilizadorEntity SET readingDone = 0 WHERE id==id")
    fun resetLidos()

    // Used to start an event
    fun downloadData(arrUser: MutableList<Utilizador>){
        // Remove current downloaded event, if any
        deleteAll()

        // Register new event
        for (user in arrUser){
            // Force casting to entity
            var userEntity = Utilizador.toEntity(user)
            insert(userEntity)
        }
    }
}