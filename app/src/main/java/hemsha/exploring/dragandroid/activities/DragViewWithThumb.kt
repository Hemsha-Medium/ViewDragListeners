package hemsha.exploring.dragandroid.activities

import android.content.ClipData
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hemsha.exploring.dragandroid.R
import hemsha.exploring.dragandroid.utils.dragListeners.GridDragListener
import hemsha.exploring.dragandroid.utils.MyDragShadowBuilder
import kotlinx.android.synthetic.main.activity_drag_view_with_thumb.*

class DragViewWithThumb : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drag_view_with_thumb)
        ivDragView.setOnDragListener(GridDragListener())
        ivDragView.setOnLongClickListener {
            val data = ClipData.newPlainText("", "")
            val shadowBuilder = MyDragShadowBuilder(it)
            ivDragView.startDrag(data, shadowBuilder, it, 0)
            true
        }
    }
}