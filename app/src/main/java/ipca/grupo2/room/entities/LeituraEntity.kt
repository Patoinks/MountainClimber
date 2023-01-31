package ipca.grupo2.room.entities

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

/*
    Not using Backend models, instead creating
    new classes making them entities to use in
    room.
*/

@Entity
data class LeituraEntity(
    @PrimaryKey
    @NonNull
    val id: String,
    val idEvento: String,
    val idUtilizador: String,
    val o2: Int,
    val altitude: Int,
    val data: Date,
    val nausea: Int,
    val cabeca: Int,
    val apetite: Int,
    val noite: Int
)