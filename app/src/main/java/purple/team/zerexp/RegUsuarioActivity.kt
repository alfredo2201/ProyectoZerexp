package purple.team.zerexp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_reg_empresa.*
import kotlinx.android.synthetic.main.activity_reg_empresa.btn_regresar
import kotlinx.android.synthetic.main.activity_registrar.*

class RegUsuarioActivity : AppCompatActivity() {

    val db = Firebase.firestore
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg_usuario)

        btn_continuar.setOnClickListener {
//            var intent = Intent(this,FeedActivity::class.java)
//            startActivity(intent)
              registrar()
        }
        btn_regresar.setOnClickListener {
            onBackPressed()
        }
    }


    fun registrar(){


    }
}