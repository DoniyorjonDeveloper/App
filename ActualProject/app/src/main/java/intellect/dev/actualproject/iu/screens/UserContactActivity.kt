package intellect.dev.actualproject.iu.screens

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import intellect.dev.actualproject.R
import intellect.dev.actualproject.contracts.ContactContract
import intellect.dev.actualproject.iu.adapters.ContactAdapter
import intellect.dev.actualproject.iu.dialogs.ContactDialog
import intellect.dev.actualproject.model.ContactRepository
import intellect.dev.actualproject.presenter.ContactPresenter
import intellect.dev.actualproject.room.entities.ContactData
import intellect.dev.actualproject.utils.extentions.changeNavigationBarColor
import intellect.dev.actualproject.utils.extentions.changeStatusBarColor
import kotlinx.android.synthetic.main.activity_admin.*

class UserContactActivity : AppCompatActivity(), ContactContract.View {
    private val userId: Long by lazy { intent.getLongExtra("USER_ID", 0) }
    private val userName: String by lazy { intent.getStringExtra("TITLE") }

    private lateinit var presenter: ContactPresenter
    private val adapter = ContactAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)
        changeStatusBarColor((Color.parseColor("#221004")))
        changeNavigationBarColor((Color.parseColor("#221004")))
        presenter = ContactPresenter(ContactRepository(), this, userId)
        list.layoutManager = LinearLayoutManager(this)
        list.adapter = adapter
        adapter.setOnEditClickListener(presenter::editContact)
        adapter.setOnDeleteClickListener(presenter::deleteContact)
        adapter.setOnItemClickListener(presenter::clickContact)
        title = userName
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuAdd -> presenter.openAddContact()
            R.id.menuBack -> finish()
        }
        return true
    }

    override fun loadData(data: List<ContactData>) {
        adapter.submitList(data)
    }

    override fun openEditDialog(contactData: ContactData) {
        val dialog = ContactDialog(this, "Edit")
        dialog.setContactData(contactData)
        dialog.setOnclickListener(presenter::confirmEditContact)
        dialog.show()
    }

    override fun openInsertDialog() {
        val dialog = ContactDialog(this, "Add")
        dialog.setOnclickListener(presenter::createContact)
        dialog.show()
    }

    override fun deleteItem(contactData: ContactData) {
        adapter.removeItem(contactData)
    }

    override fun updateItem(contactData: ContactData) {
        adapter.updateItem(contactData)
    }

    override fun insertItem(contactData: ContactData) {
        adapter.insertItem(contactData)
    }

    override fun openCall(contactData: ContactData) {
        val intent = Intent(Intent.ACTION_DIAL).setData(Uri.parse("tel:" + contactData.number))
        startActivity(intent)
    }

}