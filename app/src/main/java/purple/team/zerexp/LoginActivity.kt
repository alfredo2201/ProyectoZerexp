package purple.team.zerexp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = Firebase.auth

        val btn_contra: TextView = findViewById(R.id.tv_olvidasteContra)


        btn_registrase.setOnClickListener {
            var intent = Intent(this,registrarActivity::class.java)
            startActivity(intent)
        }

        btn_omitir.setOnClickListener {
            var intent = Intent(this,FeedActivity::class.java)
            startActivity(intent)
        }

        btn_continuar.setOnClickListener {
            valida_ingreso()
        }

        btn_contra.setOnClickListener{
            val intent: Intent = Intent ( this, ContrasenaActivity::class.java)
            startActivity(intent)
        }

    }

    private fun valida_ingreso() {
        val et_nombre: EditText = findViewById(R.id.et_nombre_usuario)
        val et_contra: EditText = findViewById(R.id.et_contraseña)

        var nombre: String = et_nombre.text.toString()
        var contra: String = et_contra.text.toString()

        if (!nombre.isNullOrBlank() && !contra.isNullOrBlank()) {
            ingresaFirebase(nombre,contra)
        } else {
            Toast.makeText(this,"Ingresar datos", Toast.LENGTH_SHORT).show()
        }
    }

    private fun ingresaFirebase(email: String, password: String){

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser

                    val intent: Intent = Intent(this,FeedActivity::class.java)
                    startActivity(intent)

                } else {

                    Toast.makeText(baseContext, "Login Failed",
                        Toast.LENGTH_SHORT).show()
                }
            }

    }



}