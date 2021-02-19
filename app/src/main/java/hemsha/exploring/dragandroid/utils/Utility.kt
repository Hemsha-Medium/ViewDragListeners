package hemsha.exploring.dragandroid.utils

import android.graphics.Rect
import android.view.View

object Utility {
    fun getBoundingViewRect(view : View): Rect {
        val l = IntArray(2)
        view.getLocationOnScreen(l)
        return Rect(
            l[0],
            l[1],
            l[0] + view.width,
            l[1] + view.height
        )
    }
}