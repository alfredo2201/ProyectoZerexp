package purple.team.zerexp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_feed.*

class FeedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)

        btn_crear_curriculum.setOnClickListener {
            var intent = Intent(this,CrearCV1Activity::class.java)
            startActivity(intent)
        }

        //btn_regresar.setOnClickListener {
        //    onBackPressed()
        //}
    }
}