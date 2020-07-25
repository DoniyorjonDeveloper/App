package intellect.dev.actualproject.model

import intellect.dev.actualproject.app.App
import intellect.dev.actualproject.contracts.ContactContract
import intellect.dev.actualproject.room.AppDatabase
import intellect.dev.actualproject.room.entities.ContactData

class ContactRepository() : ContactContract.Model {
    private val database = AppDatabase.getDatabase((App.instance))
    private var contactDao = database.contactDao()

    override fun getContactByUserId(userId: Long?): List<ContactData> {
        return contactDao.getContactsByUserId(userId)
    }

    override fun insertContact(contactData: ContactData, userId: Long?) {
        contactData.userId = userId!!
        val id = contactDao.insert(contactData)
        contactData.id = id
    }

    override fun updateContact(contactData: ContactData) {
        contactDao.update(contactData)
    }

    override fun deleteContact(contactData: ContactData) {
        contactDao.delete(contactData)
    }
}
