package intellect.dev.actualproject.iu.screens

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import intellect.dev.actualproject.R
import intellect.dev.actualproject.contracts.LoginContract
import intellect.dev.actualproject.model.LoginRepository
import intellect.dev.actualproject.presenter.LoginPresenter
import intellect.dev.actualproject.utils.extentions.changeNavigationBarColor
import intellect.dev.actualproject.utils.extentions.changeStatusBarColor
import intellect.dev.actualproject.utils.extentions.toDarkenColor
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), LoginContract.View {
    private lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        changeStatusBarColor((Color.parseColor("#221004").toDarkenColor()))
        changeNavigationBarColor((Color.parseColor("#221004").toDarkenColor()))
        presenter = LoginPresenter(LoginRepository(), this)
        buttonLogin.setOnClickListener { presenter.clickLogin() }
        btn_reg.setOnClickListener { presenter.clickRegistration() }
        info.setOnClickListener { presenter.clickInfo() }
    }

    override fun setToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun toRegistration() {
        startActivity(Intent(this, RegistrationActivity::class.java))
    }

    override fun toUserScreen(id: Long, userName: String) {
        startActivity(
            Intent(this, UserContactActivity::class.java).putExtra("USER_ID", id)
                .putExtra("TITLE", userName)
        )
    }

    override fun toAdminScreen() {
        startActivity(Intent(this, AdminActivity::class.java))
    }

    override fun getLogin(): String {
        return inputLogin.text.toString()
    }

    override fun getPassword(): String {
        return inputPassword.text.toString()
    }

    override fun toInfoActivity() {
        startActivity(Intent(this, AboutActivity::class.java))
    }
}
