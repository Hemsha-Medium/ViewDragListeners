package hemsha.exploring.dragandroid.utils.dragListeners

import android.graphics.Point
import android.util.Log
import android.view.DragEvent
import android.view.View
import android.widget.FrameLayout

/**
 * @author Hemant Sharma
 * @createdOn 15-01-2021
 **/
class GridDragListener : View.OnDragListener {

    val TAG = "GridDragListener"
    var started = false
    var oldPoint = Point()
    var newPoint = Point()

    override fun onDrag(v: View?, event: DragEvent?): Boolean {
        val params = v!!.layoutParams as FrameLayout.LayoutParams
        when (event!!.action) {
            DragEvent.ACTION_DRAG_ENTERED -> {
                Log.d(
                    TAG,
                    " DragEvent.ACTION_DRAG_ENTERED " + event.y.toString() + " " + event.x.toInt()
                        .toString()
                )
            }
            DragEvent.ACTION_DRAG_STARTED -> {
                oldPoint.x = event.x.toInt()
                oldPoint.y = event.y.toInt()
                started = true
                Log.d(
                    TAG,
                    " DragEvent.ACTION_DRAG_STARTED " + event.y.toString() + " " + event.x.toInt()
                        .toString()
                )
            }
            DragEvent.ACTION_DRAG_LOCATION -> {
                if (started) {
                    started = false
                }
                newPoint.x = event.x.toInt()
                newPoint.y = event.y.toInt()

                params.topMargin = (params.topMargin + (newPoint.y-oldPoint.y) )
                params.leftMargin = (params.leftMargin + (newPoint.x-oldPoint.x))
                v.layoutParams = params
                v.postInvalidate()
                Log.d(
                    TAG,
                    " DragEvent.ACTION_DRAG_LOCATION " + event.y.toString() + " " + event.x.toInt()
                        .toString()
                )
            }

            DragEvent.ACTION_DRAG_ENDED -> {
                Log.d(
                    TAG,
                    " DragEvent.ACTION_DRAG_ENDED " + event.y.toString() + " " + event.x.toInt()
                        .toString()
                )
            }
            DragEvent.ACTION_DRAG_EXITED -> {
                //v!!.visibility = View.VISIBLE
                Log.d(
                    TAG,
                    " DragEvent.ACTION_DRAG_EXITED " + event.y.toString() + " " + event.x.toInt()
                        .toString()
                )

            }
            DragEvent.ACTION_DROP -> {
                //v!!.visibility = View.VISIBLE
                Log.d(
                    TAG,
                    " DragEvent.ACTION_DROP " + event.y.toString() + " " + event.x.toInt()
                        .toString()
                )
            }
        }
        return true
    }

    private fun getXMoveDistance(): Int {
        val xDis = newPoint.x - oldPoint.x
        Log.d(TAG, "getXMoveDistance$xDis")
        return when {
            xDis > 7 -> {
                15
            }
            xDis < -7 -> {
                -15
            }
            else -> {
                0
            }
        }
    }

    private fun getYMoveDistance(): Int {
        val yDis = newPoint.y - oldPoint.y
        Log.d(TAG, "getYMoveDistance$yDis")
        return when {
            yDis > 7 -> {
                15
            }
            yDis < -7 -> {
                -15
            }
            else -> {
                0
            }
        }
    }

}