package ipca.grupo2.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ipca.grupo2.room.dao.UtilizadorDAO
import ipca.grupo2.room.entities.UtilizadorEntity

@Database(entities = [UtilizadorEntity::class, Dados::class], version = 2)
abstract class  AppDatabase : RoomDatabase()  {
    // Signatures for frontend access to room
    abstract fun utilizadorDao() : UtilizadorDAO;

    // wtf queisto
    /*companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            AppDatabase::class.java,
                            "db_grupo2"
                        ).fallbackToDestructiveMigration().build()
                    }
                }
            }
            return INSTANCE
        }
    }*/
}