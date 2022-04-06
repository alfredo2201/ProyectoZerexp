package purple.team.zerexp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_registrar.*

class registrarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar)

        btn_regis_empresa.setOnClickListener {
            var intent = Intent(this,RegEmpresaActivity::class.java)
            startActivity(intent)
        }

        btn_regis_usuario.setOnClickListener {
            var intent = Intent(this,RegUsuarioActivity::class.java)
            startActivity(intent)
        }

        btn_regresar.setOnClickListener {
            onBackPressed()
        }
    }
}