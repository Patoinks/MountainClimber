package ipca.grupo2.room.dao

import android.content.Context
import androidx.room.*
import ipca.grupo2.room.AppDatabase
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

    // use this instead of @Insert because we need to handle "readings"
    fun _insert(leitura: LeituraEntity, context: Context){
        var uDAO = AppDatabase.getDatabase(context)!!.utilizadorDao();

        if (uDAO.get(leitura.idUtilizador).readingDone) {
            throw Exception("Cannot do a reading while its done, se estas a ver isto fizeste merda");
        }

        uDAO.triggerReading(leitura.idUtilizador);
        insert(leitura);

        if (uDAO.areAllReadingsDone())
            uDAO.resetLidos()
    }

    // Check if a user has a leitura today
    fun hasLeituraToday(idUtilizador: String) : Boolean{
        val today = Date(System.currentTimeMillis());
        val leituras = getByDate(today, idUtilizador);
        return leituras.isNotEmpty();
    }

    // Check how much leituras a user did today
    fun numLeituraToday(idUtilizador: String) : Int{
        val today = Date(System.currentTimeMillis());
        val leituras = getByDate(today, idUtilizador);
        return leituras.size;
    }

    // Returns current num of leitura(most likely 1 or 2, 1 for first)
    fun findHighestNumLeitura(): Int {
        var highestNumLeitura = 0;
        val allLeitura = getAll();

        for (leitura in allLeitura) {
            val numLeitura = numLeituraToday(leitura.idUtilizador);
            if (numLeitura > highestNumLeitura) {
                highestNumLeitura = numLeitura;
            }
        }
        return highestNumLeitura;
    }

    // Returns list of users who are left to do a leitura
    fun findUsersWithLowerNumLeitura(): List<String> {
        val maxNumLeitura = findHighestNumLeitura();
        val usersWithLowerNumLeitura = ArrayList<String>();
        val allLeitura = getAll();

        for (leitura in allLeitura) {
            val numLeitura = numLeituraToday(leitura.idUtilizador);
            if (numLeitura < maxNumLeitura) {
                usersWithLowerNumLeitura.add(leitura.idUtilizador);
            }
        }
        return usersWithLowerNumLeitura;
    }

    suspend fun upload(){
        // WHERE??????? FRONTEND PLS?
    }
}