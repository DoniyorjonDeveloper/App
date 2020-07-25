package intellect.dev.practice.ui.screens

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import intellect.dev.practice.R
import intellect.dev.practice.utils.extentions.changeStatusBarColor
import java.util.concurrent.Executors

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        changeStatusBarColor(Color.parseColor("#7E807D"))
        Executors.newSingleThreadExecutor().execute {
            Thread.sleep(2000)
            startActivity(Intent(this, IntoActivity::class.java))
            finish()
        }
    }
}

