package intellect.dev.actualproject.iu.screens

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import intellect.dev.actualproject.R
import intellect.dev.actualproject.contracts.RegistrationContract
import intellect.dev.actualproject.model.RegistrationRepository
import intellect.dev.actualproject.presenter.RegistrationPresenter
import intellect.dev.actualproject.utils.extentions.changeNavigationBarColor
import intellect.dev.actualproject.utils.extentions.changeStatusBarColor
import kotlinx.android.synthetic.main.activity_registration.*

class RegistrationActivity : AppCompatActivity(), RegistrationContract.View {
    private lateinit var presenter: RegistrationPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        changeStatusBarColor((Color.parseColor("#221004")))
        changeNavigationBarColor((Color.parseColor("#221004")))
        presenter = RegistrationPresenter(RegistrationRepository(), this)
        buttonReg.setOnClickListener { presenter.clickRegistration() }
        title = "Registration"
    }

    override fun close() {
        finish()
    }

    override fun setToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun toLogIn() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun getLogin(): String {
        return inputRegName.text.toString()
    }

    override fun getPassword(): String {
        return inputRegPassword.text.toString()
    }

    override fun getConfirmPassword(): String {
        return inputConfirmPassword.text.toString()
    }
}
