package intellect.dev.actualproject.model

import intellect.dev.actualproject.app.App
import intellect.dev.actualproject.contracts.LoginContract
import intellect.dev.actualproject.room.AppDatabase
import intellect.dev.actualproject.room.entities.UserData

class LoginRepository : LoginContract.Model {
    private var userDao = AppDatabase.getDatabase(App.instance).userDao()

    override fun getUsers(): List<UserData> {
        return userDao.getAllUsers()
    }

}