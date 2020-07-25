package intellect.dev.actualproject.room.daos

import androidx.room.Dao
import androidx.room.Query
import intellect.dev.actualproject.room.entities.ContactData

@Dao
interface ContactDao : BaseDao<ContactData> {
    @Query("SELECT * FROM ContactData")
    fun getAllContact(): List<ContactData>

    @Query("DELETE FROM ContactData WHERE userId=:id")
    fun deleteAllByUserId(id: Long)

    @Query("SELECT * FROM ContactData WHERE userId=:userId")
    fun getContactsByUserId(userId: Long?): List<ContactData>
}