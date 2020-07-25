package intellect.dev.actualproject.iu.dialogs

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import intellect.dev.actualproject.R
import intellect.dev.actualproject.room.entities.ContactData
import intellect.dev.actualproject.utils.SingleBlock
import kotlinx.android.synthetic.main.crud_dialog.view.*

class ContactDialog(context: Context, actionName: String) : AlertDialog(context) {
    private val contentView =
        LayoutInflater.from(context).inflate(R.layout.crud_dialog, null, false)
    private var listener: SingleBlock<ContactData>? = null
    private var contactData: ContactData? = null

    init {
        setView(contentView)
        setButton(BUTTON_POSITIVE, actionName) { _, _ ->
            val data = contactData ?: ContactData(0, 0, "", "")
            data.name = contentView.editText1.text.toString()
            data.number = contentView.editText2.text.toString()
            listener?.invoke(data)
        }
        setButton(BUTTON_NEGATIVE, "Cancel") { _, _ -> }
    }

    fun setContactData(contactData: ContactData) = with(contentView) {
        this@ContactDialog.contactData = contactData
        editText1.setText(contactData.name)
        editText2.setText(contactData.number)
    }

    fun setOnclickListener(block: SingleBlock<ContactData>) {
        listener = block
    }
}