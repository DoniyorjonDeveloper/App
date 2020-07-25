package intellect.dev.practice.ui.screens

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import intellect.dev.practice.R
import intellect.dev.practice.data.model.ContactData
import intellect.dev.practice.dialogs.CrudDialog
import intellect.dev.practice.ui.adapters.ContactAdapter
import intellect.dev.practice.utils.extentions.changeNavigationBarColor
import intellect.dev.practice.utils.extentions.changeStatusBarColor
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.activity_main_content.*
import kotlinx.android.synthetic.main.item_data.view.*

class MainActivity : AppCompatActivity() {
    private val adapter = ContactAdapter()
    private var data = ArrayList<ContactData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        changeNavigationBarColor(Color.parseColor("#6B8AA8"))
        changeStatusBarColor(Color.parseColor("#6B8AA8"))
        adapter.setOnDeleteClickListener { k -> deleteItem(k) }
        adapter.setOnEditClickListener { i -> editItem(i) }
        setSupportActionBar(toolBar)
        toolBar.setNavigationOnClickListener {
            drawerLayout.drawerLayout.openDrawer(GravityCompat.START)
        }
        toolBar.setTitleTextColor(Color.parseColor("#FFFFFF"))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        loadData()
        list.layoutManager = LinearLayoutManager(this)
        list.adapter = adapter
        add.setOnClickListener {
            addItem()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.my_menu, menu)
        return true
    }

    private fun loadData() {
        val headerView = navigationView.getHeaderView(0)
        val textUser = headerView.findViewById<TextView>(R.id.textUserName)
        val textEmail = headerView.findViewById<TextView>(R.id.textEmail)
        textUser.text = "Doniyorjon Turg'unboyev"
        textEmail.text = "developerdoni@gmail.com"
    }

    private fun deleteItem(position: Int) {
        data.removeAt(position)
        adapter.submitList(data)
    }

    private fun editItem(position: Int) {
        val dialog = CrudDialog(this, "Edit")
        dialog.setData(data[position])
        dialog.setOnclickListener { l ->
            val old =l.copy()
            data[position] = old
            adapter.submitList(data)
        }
        dialog.show()
    }

    private fun addItem() {
        val dialog = CrudDialog(this, "Add")
        dialog.setOnclickListener { newItem ->
            data.add(newItem)
            adapter.submitList(data)
        }
        dialog.show()
    }
}