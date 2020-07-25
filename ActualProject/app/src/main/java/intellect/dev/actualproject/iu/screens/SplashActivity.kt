package intellect.dev.actualproject.iu.screens

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import intellect.dev.actualproject.R
import intellect.dev.actualproject.utils.extentions.changeNavigationBarColor
import intellect.dev.actualproject.utils.extentions.changeStatusBarColor
import kotlinx.android.synthetic.main.activity_splash.*
import java.util.concurrent.Executors

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        changeStatusBarColor((Color.parseColor("#221004")))
        changeNavigationBarColor((Color.parseColor("#221004")))
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        val executor1 = Executors.newSingleThreadExecutor().execute {
            Thread.sleep(1900)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                mainExecutor.execute {
                    textProgress.text = "Complete"
                    done.visibility = View.VISIBLE
                    progress_bar.alpha = 0f
                }
            }
            Executors.newSingleThreadExecutor().execute {
                Thread.sleep(200)
                startActivity(Intent(this, IntroActivity::class.java))
                finish()
            }
        }
    }
}