package purple.team.zerexp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_crear_cv1.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.btn_continuar

class CrearCV1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_cv1)
        btn_continuar_cv1.setOnClickListener {
            var nombre_emp = txt_nombre_cv.text.toString()
            var apellidos_emp = txt_apellidos_cv.text.toString()
            var direccion_emp = txt_direccion_cv.text.toString()
            var ciudad_emp = txt_ciudad_cv.text.toString()
            var correo_emp = txt_correo_cv.text.toString()
            var numeroTel_emp = txt_numero_tel_cv.text.toString()
          /**  if (nombre_emp.isNullOrBlank()){
                Toast.makeText(this,"El campo de texto Nombre esta vacío. Vuelva a intentarlo.",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (apellidos_emp.isNullOrBlank()){
                Toast.makeText(this,"El campo de texto Apellidos esta vacío. Vuelva a intentarlo.",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (direccion_emp.isNullOrBlank()){
                Toast.makeText(this,"El campo de texto Dirección esta vacío. Vuelva a intentarlo.",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (ciudad_emp.isNullOrBlank()){
                Toast.makeText(this,"El campo de texto Ciudad esta vacío. Vuelva a intentarlo.",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (correo_emp.isNullOrBlank()){
                Toast.makeText(this,"El campo de texto Correo esta vacío. Vuelva a intentarlo.",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (numeroTel_emp.isNullOrBlank()){
                Toast.makeText(this,"El campo de texto Numero de Telefono esta vacío. Vuelva a intentarlo.",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
**/
            var intent = Intent(this, CrearCV2Activity::class.java)
            var perfil:Perfil = Perfil(nombre_emp,apellidos_emp,direccion_emp,ciudad_emp,correo_emp,numeroTel_emp)
            println("Perfil creado")
            println(perfil)
            intent.putExtra("perfil",perfil)
            startActivity(intent)
        }
        btn_regresar_cv1.setOnClickListener {
            onBackPressed()
        }
    }
}