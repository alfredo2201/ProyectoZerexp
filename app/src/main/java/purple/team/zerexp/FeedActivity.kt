package purple.team.zerexp

import android.content.Intent
import android.icu.lang.UCharacter.IndicPositionalCategory.RIGHT
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.Gravity.RIGHT
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.ItemTouchHelper.RIGHT
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_feed.*
import kotlinx.android.synthetic.main.fragment_feed.*

class FeedActivity : AppCompatActivity() {
    private var auth = Firebase.auth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)

        btn_regresar_feed.setOnClickListener {
            auth.signOut()
            onBackPressed()
            finish()
        }

        img_barra.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }


        var navView = nav_view
        var navController = Navigation.findNavController(this, R.id.navHostFragment)
        NavigationUI.setupWithNavController(navView, navController)

    }

}