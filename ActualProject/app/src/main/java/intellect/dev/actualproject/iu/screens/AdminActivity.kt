package intellect.dev.actualproject.iu.screens

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import intellect.dev.actualproject.R
import intellect.dev.actualproject.contracts.AdminContract
import intellect.dev.actualproject.iu.adapters.UserAdapter
import intellect.dev.actualproject.iu.dialogs.UserDialog
import intellect.dev.actualproject.model.AdminRepository
import intellect.dev.actualproject.presenter.AdminPresenter
import intellect.dev.actualproject.room.entities.UserData
import intellect.dev.actualproject.utils.extentions.changeNavigationBarColor
import intellect.dev.actualproject.utils.extentions.changeStatusBarColor
import kotlinx.android.synthetic.main.activity_admin.*


class AdminActivity : AppCompatActivity(), AdminContract.View {

    private lateinit var presenter: AdminContract.Presenter
    private val adapter = UserAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)
        changeStatusBarColor((Color.parseColor("#221004")))
        changeNavigationBarColor((Color.parseColor("#221004")))
        title = "Admin"
        presenter = AdminPresenter(AdminRepository(), this)
        list.layoutManager = LinearLayoutManager(this)
        list.adapter = adapter
        adapter.setOnItemClickListener(presenter::openUser)
        adapter.setOnItemDeleteListener(presenter::deleteUser)
        adapter.setOnItemEditListener(presenter::editUser)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuAdd -> presenter.openAddUser()
            R.id.menuBack -> finish()
        }
        return true
    }

    override fun loadData(data: List<UserData>) {
        adapter.submitList(data)
    }

    override fun openEditDialog(userData: UserData) {
        val dialog = UserDialog(this, "Edit")
        dialog.setUserData(userData)
        dialog.setOnClickListener(presenter::confirmEditUser)
        dialog.show()
    }

    override fun openInsertDialog() {
        val dialog = UserDialog(this, "Add")
        dialog.setOnClickListener(presenter::createUser)
        dialog.show()
    }

    override fun openContact(UserId: Long, userName: String) {
        startActivity(Intent(this, UserContactActivity::class.java).apply {
            putExtra(
                "USER_ID",
                UserId
            ).putExtra("TITLE", userName)
        })
    }

    override fun deleteItem(userData: UserData) {
        adapter.removeItem(userData)
    }

    override fun updateItem(userData: UserData) {
        adapter.updateItem(userData)
    }

    override fun insertItem(userData: UserData) {
        adapter.insertItem(userData)
        list.smoothScrollToPosition(adapter.itemCount - 1)
    }
}
