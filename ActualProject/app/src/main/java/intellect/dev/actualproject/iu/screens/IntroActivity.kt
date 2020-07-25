package intellect.dev.actualproject.iu.screens

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import intellect.dev.actualproject.R
import intellect.dev.actualproject.iu.adapters.IntroAdapter
import intellect.dev.actualproject.model.IntroData
import intellect.dev.actualproject.utils.extentions.changeNavigationBarColor
import intellect.dev.actualproject.utils.extentions.changeStatusBarColor
import kotlinx.android.synthetic.main.activity_intro.*

class IntroActivity : AppCompatActivity() {

    private val data: ArrayList<IntroData> = arrayListOf(
        IntroData(
            R.drawable.contact_bg2,
            "Juda ko'p ma'lumotlar ombori",
            "Bu ilova ko'p foydalanuvchili va cheksiz ma'lumot xotirasiga ega"
        ),
        IntroData(
            R.drawable.saver,
            "Mustaxkam himoya",
            "Endi siz kuchli ximoyalangan tizim orqali ishlash imkoniyatiga egasiz!"
        ),
        IntroData(
            R.drawable.easy,
            "Qulay Interface",
            "Dastur bilan ishlashda juda ko'plab qulayliklarga ega bo'ling "
        ),
        IntroData(
            R.drawable.contact_bg2,
            "Xush kelibsiz",
            "Dasturning asosiy oynasiga o'tish uchun BOSHLASH tugmasini bosing"
        )
    )
    private val adapter = IntroAdapter(data)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        changeStatusBarColor((Color.parseColor("#221004")))
        changeNavigationBarColor((Color.parseColor("#221004")))
        title="Introduction"
        pager.adapter = adapter
        TabLayoutMediator(tabLayout, pager) { _, _ -> }.attach()
        pager.registerOnPageChangeCallback(pageChangeCallBack)
        skip.setOnClickListener {
            pager.setCurrentItem(data.size - 1, true)
        }
        buttonNext.setOnClickListener {
            if (pager.currentItem != data.size - 1) {
                pager.setCurrentItem(pager.currentItem + 1, true)
            } else {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }

    private val pageChangeCallBack = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            if (position == data.size - 1) {
                buttonNext.text = "Boshlash"
                skip.visibility = View.GONE
            } else {
                buttonNext.text = "Keyingisi"
                skip.visibility = View.VISIBLE
            }
        }
    }
}
