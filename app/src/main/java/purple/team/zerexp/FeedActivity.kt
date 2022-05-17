package purple.team.zerexp

import android.content.Intent
import android.icu.lang.UCharacter.IndicPositionalCategory.RIGHT
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.Gravity.RIGHT
import androidx.core.view.GravityCompat
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.ItemTouchHelper.RIGHT
import kotlinx.android.synthetic.main.activity_feed.*
import kotlinx.android.synthetic.main.fragment_feed.*

class FeedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)

        btn_regresar_feed.setOnClickListener {
            onBackPressed()
        }

        img_barra.setOnClickListener {
           drawerLayout.openDrawer(GravityCompat.START)
        }


        var navView = nav_view
        var navController = Navigation.findNavController(this,R.id.navHostFragment)
        NavigationUI.setupWithNavController(navView,navController)

    }
}