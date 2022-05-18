package purple.team.zerexp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_reg_empresa.*
import kotlinx.android.synthetic.main.activity_reg_empresa.btn_regresar
import kotlinx.android.synthetic.main.activity_registrar.*

class RegEmpresaActivity : AppCompatActivity() {

    val db = Firebase.firestore
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg_empresa)

        auth = Firebase.auth

        btn_continuar.setOnClickListener {
            valida_registro()
        }

        btn_regresar.setOnClickListener {
            onBackPressed()
        }

    }


    fun valida_registro(){
        val nomEmpresa = this.findViewById(R.id.et_reg_nombre_usuario) as EditText
        var nombre = nomEmpresa.getText().toString()

        val corrEmpresa = this.findViewById(R.id.et_reg_email) as EditText
        var email = corrEmpresa.getText().toString()


        val contra1 = this.findViewById(R.id.et_reg_password) as EditText
        var pass1 = contra1.getText().toString()

        val contra2 = this.findViewById(R.id.et_reg_repeat_password) as EditText
        var pass2 = contra2.getText().toString()

        val ubiEmpresa = this.findViewById(R.id.et_reg_location) as EditText
        var location = ubiEmpresa.getText().toString()


        val giroEmpresa = this.findViewById(R.id.et_reg_profession) as EditText
        var giro = giroEmpresa.getText().toString()



        if(!nombre.isNullOrBlank() && !email.isNullOrBlank() && !pass1.isNullOrBlank() &&
            !pass2.isNullOrBlank() && !location.isNullOrBlank() && !giro.isNullOrBlank()) {

            if (pass1 == pass2) {

                registrarFirebase(email,pass1)
                guardarEmpresa(nombre,email,location,giro)
                var intent = Intent(this,FeedActivity::class.java)
                startActivity(intent)


            } else {
                Toast.makeText(this, "Las contraseña no coinciden", Toast.LENGTH_SHORT).show()
            }

        }else{
            Toast. makeText(this,"Ingresar datos", Toast.LENGTH_SHORT).show()
        }

    }


    private fun guardarEmpresa(nombre: String, email: String,location: String, giro: String){
        val empresa = hashMapOf(
            "Nombre de Empresa" to nombre,
            "Correo Electronico" to email,
            "Ubicacion" to location,
            "Giro" to giro,
        )

        db.collection("empresas").document(email).set(empresa)
            .addOnCompleteListener {
                Toast.makeText(baseContext, "User Created", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(baseContext, "User not Created", Toast.LENGTH_SHORT).show()
            }
    }

    private fun registrarFirebase(email: String, password: String){

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    Toast.makeText(baseContext, "Authentication Passed.",
                        Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }

    }
}