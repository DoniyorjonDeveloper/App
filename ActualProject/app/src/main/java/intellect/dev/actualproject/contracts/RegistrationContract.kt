package intellect.dev.actualproject.contracts

import intellect.dev.actualproject.room.entities.UserData

interface RegistrationContract {
    interface Model {
        fun getUser(): List<UserData>
        fun insertUser(userData: UserData)
    }

    interface View {
        fun close()
        fun setToast(message: String)
        fun toLogIn()
        fun getLogin(): String
        fun getPassword(): String
        fun getConfirmPassword(): String
    }

    interface Presenter {
        fun clickRegistration()
    }
}