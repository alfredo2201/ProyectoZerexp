package purple.team.zerexp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        btn_registrase.setOnClickListener {
            var intent = Intent(this,registrarActivity::class.java)
            startActivity(intent)
        }

        btn_omitir.setOnClickListener {
            var intent = Intent(this,FeedActivity::class.java)
            startActivity(intent)
        }

        btn_continuar.setOnClickListener {
            var intent = Intent(this,FeedActivity::class.java)
            startActivity(intent)
        }

    }



}