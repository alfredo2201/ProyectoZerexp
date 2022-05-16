package purple.team.zerexp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
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
        siteDrawerMenuButton.setOnClickListener { v ->
            openCloseNavigationDrawer(v)
        }
    }

    fun openCloseNavigationDrawer(view: View) {
        Log.d("[ CHECK ]", "ENTERS IN THE FUNCTION")
        if (dwl_side_bar.isDrawerOpen(GravityCompat.END)) {
            dwl_side_bar.closeDrawer(GravityCompat.END)
        } else {
            dwl_side_bar.openDrawer(GravityCompat.END)
        }
    }
}