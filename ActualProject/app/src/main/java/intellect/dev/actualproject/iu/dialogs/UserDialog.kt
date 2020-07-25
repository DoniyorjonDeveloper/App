package intellect.dev.actualproject.iu.dialogs

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import intellect.dev.actualproject.R
import intellect.dev.actualproject.room.entities.UserData
import intellect.dev.actualproject.utils.SingleBlock
import kotlinx.android.synthetic.main.crud_dialog.view.*

class UserDialog(context: Context, actionName: String) : AlertDialog(context) {
    private val contentView =
        LayoutInflater.from(context).inflate(R.layout.crud_dialog2, null, false)
    private var listener: SingleBlock<UserData>? = null
    private var userData: UserData? = null

    init {
        setView(contentView)
        setButton(BUTTON_POSITIVE, actionName) { _, _ ->
            val data = userData ?: UserData(0, "", "")
            data.login = contentView.editText1.text.toString()
            data.password = contentView.editText2.text.toString()
            listener?.invoke(data)
        }
        setButton(BUTTON_NEGATIVE, "Cancel") { _, _ -> }
    }

    fun setUserData(userData: UserData) = with(contentView) {
        this@UserDialog.userData = userData
        editText1.setText(userData.login)
        editText2.setText(userData.password)
    }

    fun setOnClickListener(block: SingleBlock<UserData>) {
        listener = block
    }
}