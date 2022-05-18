package purple.team.zerexp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_crear_cv3.*
import purple.team.zerexp.modelos.Educacion
import purple.team.zerexp.modelos.ExperienciaLaboral
import purple.team.zerexp.modelos.Perfil

class CrearCV3Activity : AppCompatActivity() {
    var perfil: Perfil? = null
    var educacion: Educacion? = null
    var exp: ExperienciaLaboral?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_cv3)
        var bundle = intent.extras
        if (bundle != null){
            perfil = bundle.getSerializable("perfil") as? Perfil
            educacion = bundle.getSerializable("educacion") as? Educacion
        }

        btn_continuar_cv3.setOnClickListener {
            if (cb_sin_exp.isChecked){
                var intent = Intent(this, CrearCV4Activity::class.java)
                intent.putExtra("perfil",perfil)
                intent.putExtra("educacion",educacion)
                exp = ExperienciaLaboral("null","null","null","null","null","null")
                intent.putExtra("experiencia",exp)
                startActivity(intent)
                return@setOnClickListener
            }
            var titulo = txt_tituloEmpleo_cv.text.toString()
            var empresa = txt_empresa_cv.text.toString()
            var ciudad = txt_ciudad_empleo_cv.text.toString()
            var periodo = txt_periodo_tiempo_cv.text.toString()
            var mesInicio = txt_mes_inicio_cv3.text.toString()
            var anioInicio = txt_anio_inicio_cv3.text.toString()
            var mesFin = txt_mes_fin_cv3.text.toString()
            var anioFin = txt_anio_fin_cv3.text.toString()

            if (titulo.isNullOrBlank()){
                Toast.makeText(this,"El campo de texto Titulo del empleo esta vacío. Vuelva a intentarlo.",
                    Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (empresa.isNullOrBlank()){
                Toast.makeText(this,"El campo de texto Empresa esta vacío. Vuelva a intentarlo.",
                    Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (periodo.isNullOrBlank()){
                Toast.makeText(this,"El campo de texto Peridodo esta vacío. Vuelva a intentarlo.",
                    Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (ciudad.isNullOrBlank()){
                Toast.makeText(this,"El campo de texto Ciudad esta vacío. Vuelva a intentarlo.",
                    Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (mesInicio.isNullOrBlank()){
                Toast.makeText(this,"El campo de texto Mes de inicio esta vacío. Vuelva a intentarlo.",
                    Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (anioInicio.isNullOrBlank()){
                Toast.makeText(this,"El campo de texto Año de Inicio esta vacío. Vuelva a intentarlo.",
                    Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (mesFin.isNullOrBlank()){
                Toast.makeText(this,"El campo de texto Mes de fin esta vacío. Vuelva a intentarlo.",
                    Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (anioFin.isNullOrBlank()){
                Toast.makeText(this,"El campo de texto Año de fin esta vacío. Vuelva a intentarlo.",
                    Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            var fechaInicio = mesInicio +"/"+anioInicio
            var fechaFin = mesFin +"/"+anioFin
            exp = ExperienciaLaboral(titulo,empresa,ciudad,periodo, fechaInicio,fechaFin)
            var intent = Intent(this, CrearCV4Activity::class.java)
            intent.putExtra("perfil",perfil)
            intent.putExtra("educacion",educacion)
            intent.putExtra("experiencia",exp)
            startActivity(intent)
        }
        btn_regresar_cv3.setOnClickListener {
            onBackPressed()
        }
    }
}