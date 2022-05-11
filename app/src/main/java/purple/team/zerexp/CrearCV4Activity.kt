package purple.team.zerexp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_crear_cv3.*
import kotlinx.android.synthetic.main.activity_crear_cv4.*

class CrearCV4Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_cv4)
        btn_regresar_cv4.setOnClickListener {
            onBackPressed()
        }
    }
}