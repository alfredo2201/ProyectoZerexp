package purple.team.zerexp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_contrasena.*
import kotlinx.android.synthetic.main.activity_reg_empresa.*
import kotlinx.android.synthetic.main.activity_reg_empresa.btn_regresar

class ContrasenaActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contrasena)

        auth = Firebase.auth

        val btn_restablecer: Button = findViewById(R.id.btn_restablecer)
        btn_regresar.setOnClickListener {
            onBackPressed()
        }
        btn_restablecer.setOnClickListener {
            val et_correo: EditText = findViewById(R.id.et_contra_Restablecer)

            var correo: String = et_correo.text.toString()

            if (!correo.isNullOrBlank()) {

                auth.sendPasswordResetEmail(correo)
                    .addOnCompleteListener {  task ->
                        if (task.isSuccessful){
                            Toast.makeText(this,"Se envio un correo a $correo", Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(this,"Error al enviar correo", Toast.LENGTH_SHORT).show()
                        }

                    }
            } else {
                Toast.makeText(this, "Ingresar correo", Toast.LENGTH_SHORT).show()
            }
        }
    }
}