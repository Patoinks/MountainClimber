package ipca.grupo2.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/*
    Not using Backend models, instead creating
    new classes making them entities to use in
    room.
*/

@Entity
data class UtilizadorEntity(
    @PrimaryKey
    val id: String?,
    val contact: String?,
    val height: Float?,
    val weight: Float?,
    val name: String?,
    val birthDate: Date?,
    val email: String?,
    val password: String?,
    val isGuia: Int?
);


