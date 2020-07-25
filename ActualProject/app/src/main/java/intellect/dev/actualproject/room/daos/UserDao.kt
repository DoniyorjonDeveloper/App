package intellect.dev.actualproject.room.daos

import androidx.room.Dao
import androidx.room.Query
import intellect.dev.actualproject.room.entities.UserData

@Dao
interface UserDao : BaseDao<UserData> {
    @Query("SELECT * FROM UserData")
    fun getAllUsers(): List<UserData>
}