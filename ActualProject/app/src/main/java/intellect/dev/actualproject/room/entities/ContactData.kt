package intellect.dev.actualproject.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ContactData(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var userId: Long = 0,
    var name: String = "",
    var number: String = ""
)