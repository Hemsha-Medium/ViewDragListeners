package hemsha.exploring.dragandroid.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hemsha.exploring.dragandroid.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initListeners()
    }

    private fun initListeners() {
        btnDragShadow.setOnClickListener {
            startActivity(Intent(this@MainActivity,DragShadow::class.java))
        }
        btnDragView.setOnClickListener {
            startActivity(Intent(this@MainActivity,DragViewWithThumb::class.java))
        }
        btnDragViewToDelete.setOnClickListener {
            startActivity(Intent(this@MainActivity,DragToDelete::class.java))
        }
    }
}