package purple.team.zerexp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_crear_cv1.*
import kotlinx.android.synthetic.main.activity_crear_cv3.*

class CrearCV3Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_cv3)
        btn_continuar_cv3.setOnClickListener {
            var intent = Intent(this, CrearCV4Activity::class.java)
            startActivity(intent)
        }
        btn_regresar_cv3.setOnClickListener {
            onBackPressed()
        }
    }
}