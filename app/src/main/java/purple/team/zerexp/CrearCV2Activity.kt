package purple.team.zerexp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_crear_cv2.*
import purple.team.zerexp.modelos.Educacion
import purple.team.zerexp.modelos.Perfil

class CrearCV2Activity : AppCompatActivity() {
    var result: Perfil? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_cv2)
        var bundle = intent.extras
        if (bundle != null){
            result = bundle.getSerializable("perfil") as? Perfil
            println(result)
        }

        btn_continuar_cv2.setOnClickListener {
            var grado = txt_grado_cv.text.toString()
            var area = txt_area_estudio_cv.text.toString()
            var institucion = txt_institucion_cv.text.toString()
            var ciudad = txt_ciudad_inst_cv.text.toString()
            var mesInicio = txt_mes_inicio_cv.text.toString()
            var anioInicio = txt_anio_inicio_cv.text.toString()
            var mesFin = txt_mes_fin_cv.text.toString()
            var anioFin = txt_anio_fin_cv.text.toString()

            if (grado.isNullOrBlank()){
                Toast.makeText(this,"El campo de texto Grado Académico esta vacío. Vuelva a intentarlo.",
                    Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (area.isNullOrBlank()){
                Toast.makeText(this,"El campo de texto Área de estudio esta vacío. Vuelva a intentarlo.",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (institucion.isNullOrBlank()){
                Toast.makeText(this,"El campo de texto Institución educativa esta vacío. Vuelva a intentarlo.",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (ciudad.isNullOrBlank()){
                Toast.makeText(this,"El campo de texto Ciudad esta vacío. Vuelva a intentarlo.",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (mesInicio.isNullOrBlank()){
                Toast.makeText(this,"El campo de texto Mes de inicio esta vacío. Vuelva a intentarlo.",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (anioInicio.isNullOrBlank()){
                Toast.makeText(this,"El campo de texto Año de Inicio esta vacío. Vuelva a intentarlo.",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (mesFin.isNullOrBlank()){
                Toast.makeText(this,"El campo de texto Mes de fin esta vacío. Vuelva a intentarlo.",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (anioFin.isNullOrBlank()){
                Toast.makeText(this,"El campo de texto Año de fin esta vacío. Vuelva a intentarlo.",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            var fechaInicio = mesInicio +"/"+anioInicio
            var fechaFin = mesFin +"/"+anioFin
            var educacion = Educacion(grado,area,institucion,ciudad,fechaInicio,fechaFin)
            var intent = Intent(this, CrearCV3Activity::class.java)
            intent.putExtra("perfil",result)
            intent.putExtra("educacion",educacion)
            startActivity(intent)
        }
        btn_regresar_cv2.setOnClickListener {
            onBackPressed()
        }
    }
}