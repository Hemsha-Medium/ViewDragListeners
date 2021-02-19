package hemsha.exploring.dragandroid.activities

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.ClipData
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import hemsha.exploring.dragandroid.R
import hemsha.exploring.dragandroid.utils.dragListeners.GridDragDeleteListener
import hemsha.exploring.dragandroid.utils.MyDragShadowBuilder
import kotlinx.android.synthetic.main.activity_drag_to_delete.*

class DragToDelete : AppCompatActivity(), GridDragDeleteListener.OnIntersect {

    var animated = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drag_to_delete)

        ivDragView.setOnLongClickListener {
            val data = ClipData.newPlainText("", "")
            val shadowBuilder = MyDragShadowBuilder(it)
            ivDragView.startDrag(data, shadowBuilder, it, 0)
            true
        }

    }

    override fun intersectionStatus(status: Boolean) {
        if (status && !animated) {
            animated = true
            animateZoomIn()
            Log.d("TAG", "intersectionStatus: Inside")
        }
        if (!status && animated) {
            animated = false
            animateZoomOut()
            Log.d("TAG", "intersectionStatus: Outside")
        }
    }

    override fun delete() {
        flRoot.removeView(ivDragView)
        animateZoomOut()
    }

    private fun animateZoomOut() {
        var objX = ObjectAnimator.ofFloat(ivDragDelete, "scaleX", 1.5f, 1.0f)

        var objY = ObjectAnimator.ofFloat(ivDragDelete, "scaleY", 1.5f, 1.0f)

        var animatorSet = AnimatorSet()
        animatorSet.playTogether(objX, objY)
        animatorSet.duration = 100
        animatorSet.interpolator = AccelerateDecelerateInterpolator()
        animatorSet.start()
    }

    private fun animateZoomIn() {
        var objX = ObjectAnimator.ofFloat(ivDragDelete, "scaleX", 1.0f, 1.5f)

        var objY = ObjectAnimator.ofFloat(ivDragDelete, "scaleY", 1.0f, 1.5f)

        var animatorSet = AnimatorSet()
        animatorSet.playTogether(objX, objY)
        animatorSet.duration = 100
        animatorSet.interpolator = AccelerateDecelerateInterpolator()
        animatorSet.start()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        val l = IntArray(2)
        ivDragDelete.getLocationOnScreen(l)
        val rect = Rect(
            l[0],
            l[1],
            l[0] + ivDragDelete.width,
            l[1] + ivDragDelete.height
        )

        ivDragView.setOnDragListener(
            GridDragDeleteListener(
                rect,
                this
            )
        )
    }

}