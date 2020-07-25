package intellect.dev.actualproject.iu.screens

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import intellect.dev.actualproject.R
import intellect.dev.actualproject.utils.extentions.changeNavigationBarColor
import intellect.dev.actualproject.utils.extentions.changeStatusBarColor
import kotlinx.android.synthetic.main.activity_about.*

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        changeStatusBarColor((Color.parseColor("#221004")))
        changeNavigationBarColor((Color.parseColor("#221004")))
        title="Information and Contact"
        Call.setOnClickListener {
            startActivity(Intent(Intent.ACTION_DIAL).setData(Uri.parse("tel:" + "+998900107712")))
        }
        mail.setOnClickListener {
            var intent = Intent(Intent.ACTION_SEND).setData(Uri.parse("mailto:"))
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_CC, "developerdoni@gmail.com")
            intent.putExtra(Intent.EXTRA_SUBJECT, "Murojaat")
            intent.putExtra(Intent.EXTRA_TEXT, "Bu yerga taklif va mulohazalaringizni yozing")
            startActivity(intent)
        }
    }
}
