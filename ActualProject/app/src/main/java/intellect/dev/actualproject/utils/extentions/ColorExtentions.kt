package intellect.dev.actualproject.utils.extentions

import android.graphics.Color

fun Int.toDarkenColor(): Int {
    val hsv = FloatArray(3)
    Color.colorToHSV(this, hsv)
    hsv[2] *= 0.8F
    return Color.HSVToColor(hsv)
}