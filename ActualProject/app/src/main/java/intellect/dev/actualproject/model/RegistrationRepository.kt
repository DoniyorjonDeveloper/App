package intellect.dev.actualproject.model

import intellect.dev.actualproject.app.App
import intellect.dev.actualproject.contracts.RegistrationContract
import intellect.dev.actualproject.room.AppDatabase
import intellect.dev.actualproject.room.entities.UserData

class RegistrationRepository : RegistrationContract.Model {
    private var userDao = AppDatabase.getDatabase(App.instance).userDao()

    override fun getUser(): List<UserData> {
        return userDao.getAllUsers()
    }

    override fun insertUser(userData: UserData) {
        userDao.insert(userData)
    }

}