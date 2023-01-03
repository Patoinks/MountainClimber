package ipca.grupo2.room.entities

import androidx.annotation.NonNull
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
    @NonNull
    val id: String,
    val contact: Int?,
    val height: Float?,
    val weight: Float?,
    val name: String?,
    val birthDate: String?, // error: Cannot figure out how to save this field into database (Date())
    val isGuia: Int?
);


