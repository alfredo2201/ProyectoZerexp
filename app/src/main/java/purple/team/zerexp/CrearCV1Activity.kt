package purple.team.zerexp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_crear_cv1.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.btn_continuar

class CrearCV1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_cv1)
        btn_continuar_cv1.setOnClickListener {
            var intent = Intent(this, CrearCV2Activity::class.java)
            startActivity(intent)
        }
        btn_regresar_cv1.setOnClickListener {
            onBackPressed()
        }
    }
}