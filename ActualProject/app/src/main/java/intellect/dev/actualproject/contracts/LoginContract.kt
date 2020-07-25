package intellect.dev.actualproject.contracts

import intellect.dev.actualproject.room.entities.UserData

interface LoginContract {
    interface Model {
        fun getUsers(): List<UserData>
    }

    interface View {
        fun setToast(message: String)
        fun toRegistration()
        fun toAdminScreen()
        fun getLogin(): String
        fun getPassword(): String
        fun toInfoActivity()
        fun toUserScreen(id: Long, userName: String)
    }

    interface Presenter {
        fun clickRegistration()
        fun clickLogin()
        fun clickInfo()
    }
}