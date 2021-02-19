package hemsha.exploring.dragandroid.utils.dragListeners

import android.graphics.Point
import android.graphics.Rect
import android.util.Log
import android.view.DragEvent
import android.view.View
import android.widget.FrameLayout
import hemsha.exploring.dragandroid.utils.Utility

/**
 * @author Hemant Sharma
 * @createdOn 15-01-2021
 **/
class GridDragDeleteListener(private val rect : Rect, private val listener : OnIntersect) : View.OnDragListener {

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
                Log.d(TAG, "onDrag: ${v.left}  ${v.top}  ${v.right}  ${v.bottom}")
                Log.d(TAG, "onDrag: ${rect.left}  ${rect.top}  ${rect.right}  ${rect.bottom}")
                v.postInvalidate()

                if(rectIntersect(rect,
                        Utility.getBoundingViewRect(
                            v
                        )
                    )){
                    Log.d(TAG, "onDrag: true")
                    listener.intersectionStatus(true)
                }else{
                    Log.d(TAG, "onDrag: false")
                    listener.intersectionStatus(false)
                }
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
                //Todo : Delete Layout
                if(rectIntersect(rect,
                        Utility.getBoundingViewRect(
                            v
                        )
                    )){
                    Log.d(TAG, "onDrag: true")
                    listener.delete()
                }
                Log.d(
                    TAG,
                    " DragEvent.ACTION_DROP " + event.y.toString() + " " + event.x.toInt()
                        .toString()
                )
            }
        }
        return true
    }

    fun rectIntersect(r1 : Rect, r2 : Rect): Boolean {
        return !((r1.left>r2.right) || (r1.top>r2.bottom) || (r1.bottom<r2.top) || (r1.right<r2.left))
    }

    interface OnIntersect{
        fun intersectionStatus(status : Boolean)
        fun delete()
    }

}