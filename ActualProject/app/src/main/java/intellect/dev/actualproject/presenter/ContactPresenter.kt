package intellect.dev.actualproject.presenter

import android.os.Handler
import android.os.Looper
import intellect.dev.actualproject.contracts.ContactContract
import intellect.dev.actualproject.room.entities.ContactData
import java.util.concurrent.Executors

class ContactPresenter(
    private val model: ContactContract.Model,
    private val view: ContactContract.View, userId: Long
) : ContactContract.Presenter {
    private val userId: Long? = userId
    private var executor = Executors.newSingleThreadExecutor()
    private val handle = Handler(Looper.getMainLooper())

    init {
        runOnWorkerThread {
            val ls = model.getContactByUserId(userId)
            runOnUIThread {
                view.loadData(ls)
            }
        }
    }

    override fun deleteContact(contactData: ContactData) {
        runOnWorkerThread {
            model.deleteContact(contactData)
            runOnUIThread {
                view.deleteItem(contactData)
            }
        }
    }

    override fun confirmEditContact(contactData: ContactData) {
        runOnWorkerThread {
            model.updateContact(contactData)
            runOnUIThread {
                view.updateItem(contactData)
            }
        }
    }

    override fun editContact(contactData: ContactData) {
        view.openEditDialog(contactData)
    }

    override fun createContact(contactData: ContactData) {
        runOnWorkerThread {
            model.insertContact(contactData, userId)
            runOnUIThread {
                view.insertItem(contactData)
            }
        }
    }

    override fun openAddContact() {
        view.openInsertDialog()
    }

    override fun clickContact(contactData: ContactData) {
        view.openCall(contactData)
    }

    private fun runOnWorkerThread(f: () -> Unit) {
        executor.execute { f() }
    }

    private fun runOnUIThread(f: () -> Unit) {
        handle.post { f() }
    }
}