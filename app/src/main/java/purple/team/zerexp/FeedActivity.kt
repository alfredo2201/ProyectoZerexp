package purple.team.zerexp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import androidx.core.view.GravityCompat
import kotlinx.android.synthetic.main.activity_feed.*

class FeedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)

        btn_crear_curriculum.setOnClickListener {
            var intent = Intent(this,CrearCV1Activity::class.java)
            startActivity(intent)
        }

        btn_regresar_feed.setOnClickListener {
            onBackPressed()
        }

        btn_publicar_empleo.setOnClickListener {
            val intent = Intent(this, MessagesActivity::class.java)
            startActivity(intent)
        }
        img_barra.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.END)
        }
    }
}