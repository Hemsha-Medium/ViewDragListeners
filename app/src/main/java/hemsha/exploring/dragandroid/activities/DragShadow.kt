package hemsha.exploring.dragandroid.activities

import android.content.ClipData
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import hemsha.exploring.dragandroid.R
import kotlinx.android.synthetic.main.activity_drag_shadow.*

class DragShadow : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drag_shadow)
        ivDragView.setOnLongClickListener {
            val data = ClipData.newPlainText("", "")
            val shadowBuilder = View.DragShadowBuilder(it)
            ivDragView.startDrag(data, shadowBuilder, it, 0)
            true
        }
    }
}