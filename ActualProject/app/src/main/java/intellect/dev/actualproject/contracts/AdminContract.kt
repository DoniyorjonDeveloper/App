package intellect.dev.actualproject.contracts

import intellect.dev.actualproject.room.entities.UserData

interface AdminContract {

    interface Model {
        fun getUsers(): List<UserData>
        fun updateUser(userData: UserData)
        fun deleteUser(userData: UserData)
        fun insertUser(userData: UserData)
    }

    interface View {
        fun loadData(data: List<UserData>)
        fun openEditDialog(userData: UserData)
        fun openInsertDialog()
        fun openContact(UserId: Long, userName: String)
        fun deleteItem(userData: UserData)
        fun updateItem(userData: UserData)
        fun insertItem(userData: UserData)
    }

    interface Presenter {
        fun openUser(userData: UserData)
        fun deleteUser(userData: UserData)
        fun confirmEditUser(userData: UserData)
        fun editUser(userData: UserData)
        fun openAddUser()
        fun createUser(userData: UserData)
    }
}