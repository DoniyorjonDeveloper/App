package intellect.dev.actualproject.model

import intellect.dev.actualproject.app.App
import intellect.dev.actualproject.contracts.AdminContract
import intellect.dev.actualproject.room.AppDatabase
import intellect.dev.actualproject.room.entities.UserData

class AdminRepository : AdminContract.Model {
    private val database = AppDatabase.getDatabase(App.instance)
    private val userDao = database.userDao()
    private val contactDao = database.contactDao()

    override fun getUsers(): List<UserData> {
        return userDao.getAllUsers()
    }

    override fun updateUser(userData: UserData) {
        userDao.update(userData)
    }

    override fun deleteUser(userData: UserData) {
        userDao.delete(userData)
        contactDao.deleteAllByUserId(userData.id)
    }

    override fun insertUser(userData: UserData) {
        val id = userDao.insert(userData)
        userData.id = id
    }
}