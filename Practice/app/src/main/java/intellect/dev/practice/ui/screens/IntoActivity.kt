package intellect.dev.practice.ui.screens

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import intellect.dev.practice.R
import intellect.dev.practice.data.model.IntoData
import intellect.dev.practice.ui.adapters.IntoAdapter
import intellect.dev.practice.utils.extentions.changeNavigationBarColor
import intellect.dev.practice.utils.extentions.changeStatusBarColor
import intellect.dev.practice.utils.extentions.toDarkenColor
import kotlinx.android.synthetic.main.activity_into.*

class IntoActivity : AppCompatActivity() {
    private val data: ArrayList<IntoData> = arrayListOf(
        IntoData(
            R.drawable.saver,
            "Mustaxkam himoya",
            "Endi siz kuchli ximoyalangan tizim orqali ishlash imkoniyatiga egasiz!",
            Color.parseColor("#8A1D22")
        ),
        IntoData(
            R.drawable.easy,
            "Qulay Interface",
            "Dastur bilan ishlashda hech qanday muammosiz holatda foydalanish ",
            Color.parseColor("#638603")
        ),
        IntoData(
            R.drawable.avatar,
            "Xush kelibsiz",
            "Dasturning asosiy oynasiga o'tish uchun keyingi tugmasini bosing",
            Color.parseColor("#7D5516")
        )
    )
    private val adapter = IntoAdapter(data)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_into)
        pager.adapter = adapter
        TabLayoutMediator(tabLayout, pager) { _, _ -> }.attach()
        pager.registerOnPageChangeCallback(pageChangeCallback)
        buttonNext.setOnClickListener {
            if (pager.currentItem != data.size - 1) {
                pager.setCurrentItem(pager.currentItem + 1, true)
            } else {
                finish()
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
    }

    private val pageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            layoutContent.setBackgroundColor(data[position].color)
            changeStatusBarColor(data[position].color.toDarkenColor())
            changeNavigationBarColor(data[position].color.toDarkenColor())
            tabLayout.setSelectedTabIndicatorColor(data[position].color)
            if (position==data.size-1){buttonNext.text="Boshlash"}else{
                buttonNext.text="Keyingisi"
            }
        }
    }
}
