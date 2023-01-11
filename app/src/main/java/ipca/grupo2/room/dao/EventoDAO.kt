package ipca.grupo2.room.dao

import android.content.Context
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import ipca.grupo2.backend.tables.BackendEvento
import ipca.grupo2.backend.tables.BackendUtilizador
import ipca.grupo2.room.AppDatabase
import ipca.grupo2.room.entities.EventoEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Dao
interface EventoDAO {
    @Query("SELECT * FROM EventoEntity")
    fun getAll(): List<EventoEntity>

    @Query("SELECT * FROM EventoEntity LIMIT 1")
    fun getCurEventId() : EventoEntity;

    @Insert
    fun insert(evento: EventoEntity)

    @Update
    fun update(evento: EventoEntity)

    @Delete
    fun delete(evento: EventoEntity)

    @Query("DELETE FROM EventoEntity WHERE id=id;")
    fun deleteAll()

    fun isInEvento() : Boolean {
        return true;
    }

    suspend fun joinEvento(idEvento : String, context: Context) {
        // Remove any evento if registered
        deleteAll()

        val mainScope = CoroutineScope(Dispatchers.Main);

        mainScope.launch {
            var evento = BackendEvento.getEventoByID(idEvento);

            // get all users
            var users = BackendUtilizador.getAllUtilizadoresByEvento(idEvento);
            AppDatabase.getDatabase(context)!!.utilizadorDao().downloadData(users);

            var eventoEntity = EventoEntity(
                evento?.getId()!!,
                evento.getIdGuia()!!,
                evento.getLocation()!!,
                evento.getDateStart()!!.toString(),
                evento.getDateFinish()!!.toString(),
                evento.getImage()!!,
                evento.getDescription()!!
            )

            insert(eventoEntity);
        }

    }

}

