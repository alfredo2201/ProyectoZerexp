package purple.team.zerexp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_crear_cv3.*
import kotlinx.android.synthetic.main.activity_crear_cv4.*
import kotlinx.android.synthetic.main.nueva_habilidad.view.*

class CrearCV4Activity : AppCompatActivity() {
    var perfil:Perfil? = null
    var educacion:Educacion? = null
    var experienciaLaboral:ExperienciaLaboral? = null
    var habilidades = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_cv4)
        habilidades = arrayListOf()
        var bundle = intent.extras
        if (bundle != null){
            perfil = bundle.getSerializable("perfil") as Perfil
            educacion = bundle.getSerializable("educacion") as Educacion
            experienciaLaboral = bundle.getSerializable("experiencia") as ExperienciaLaboral
        }



        btn_agregar_mas.setOnClickListener {
            list_habilidades.adapter = null
            var habilidadNueva = txt_habilidad.text.toString()
            if (habilidadNueva.isNullOrBlank()){
                Toast.makeText(this,"El campo de texto para agregar una habilidad esta vacío. Vuelva a intentarlo.",
                    Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            habilidades.add(habilidadNueva)
            for(hab in habilidades){
                println(hab)
            }
            var adaptador:HabilidadAdapter = HabilidadAdapter(habilidades,this)
            list_habilidades.adapter = adaptador
        }

        btn_regresar_cv4.setOnClickListener {
            onBackPressed()
        }
    }
    private class HabilidadAdapter:BaseAdapter{
        var habilidades = ArrayList<String>()
        var contexto:Context? = null
        constructor(habilidades:ArrayList<String>,contexto:Context?){
            this.contexto = contexto
            this.habilidades = habilidades
        }

        override fun getCount(): Int {
            return habilidades.size
        }

        override fun getItem(p0: Int): Any {
            return habilidades[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            var hab = habilidades[p0]
            var inflador = LayoutInflater.from(contexto)
            var vista = inflador.inflate(R.layout.nueva_habilidad,null)

            vista.txt_habilidad_nueva.text = hab
            //Toast.makeText(contexto,texto.text,Toast.LENGTH_SHORT).show()
            return vista
        }


    }
}