package purple.team.zerexp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toolbar
import androidx.appcompat.app.ActionBar
import androidx.core.view.GravityCompat
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_side_bar_menu.*

class SideBarMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_side_bar_menu)

        toolbar("Menu Lateral")

    }

    fun toolbar(title: String) {
        setSupportActionBar(tb_side_bar)

        var ab: ActionBar? = supportActionBar

        if (ab != null) {
            ab.setHomeAsUpIndicator(R.drawable.ic_action_name_gray)
            ab.setDisplayHomeAsUpEnabled(true)
            ab.title = title
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            android.R.id.home -> {
                dwl_side_bar.openDrawer(GravityCompat.START)
            }
        }

        return super.onOptionsItemSelected(item)
    }

}