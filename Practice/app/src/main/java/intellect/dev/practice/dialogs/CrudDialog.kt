package intellect.dev.practice.dialogs

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import intellect.dev.practice.R
import intellect.dev.practice.data.model.ContactData
import intellect.dev.practice.utils.SingleBlock
import kotlinx.android.synthetic.main.crud_dialog.view.*

class CrudDialog(context: Context, action: String) : AlertDialog(context) {

    private val contentView =
        LayoutInflater.from(context).inflate(R.layout.crud_dialog, null, false)
    private var listener: SingleBlock<ContactData>? = null
    private var data: ContactData? = null

    init {
        setView(contentView)
        setButton(BUTTON_POSITIVE, action) { _, _ ->
            val data = ContactData("", "")
            data.title = contentView.editText1.text.toString()
            data.description = contentView.editText2.text.toString()
            listener?.invoke(data)
        }
        setButton(BUTTON_NEGATIVE, "Cancel") { _, _ -> }
    }

    fun setData(data: ContactData) = with(contentView) {
        this@CrudDialog.data = data
        editText1.setText(data.title)
        editText2.setText(data.description)
    }

    fun setOnclickListener(block: SingleBlock<ContactData>){
        listener = block
    }
}