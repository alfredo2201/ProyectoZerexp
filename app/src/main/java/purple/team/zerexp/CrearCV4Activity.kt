package purple.team.zerexp

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.PackageManager.*
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.pdf.PdfDocument
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.text.TextPaint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.activity_crear_cv3.*
import kotlinx.android.synthetic.main.activity_crear_cv4.*
import kotlinx.android.synthetic.main.nueva_habilidad.view.*
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class CrearCV4Activity : AppCompatActivity() {

    var tituloText = "Curriculum vitae"
    var descripcionText = ""
    var perfil:Perfil? = null
    var educacion:Educacion? = null
    var experienciaLaboral:ExperienciaLaboral? = null
    var habilidades = ArrayList<String>()
    var storage = Firebase.storage("gs://zerexp-67034.appspot.com")
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
        if (checkPermission()){
            Toast.makeText(this,"Permiso Aceptado",Toast.LENGTH_SHORT)
        }else{
            requestPermissions()
        }

        btn_agregar_mas.setOnClickListener {
            var habilidadNueva = txt_habilidad.text.toString()
            if (habilidadNueva.isNullOrBlank()){
                Toast.makeText(this,"El campo de texto para agregar una habilidad esta vacío. Vuelva a intentarlo.",
                    Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            habilidades.add(habilidadNueva)
            txt_habilidad.text.clear()
            var adaptador:HabilidadAdapter = HabilidadAdapter(habilidades,this)
            list_habilidades.adapter = adaptador
        }

        btn_regresar_cv4.setOnClickListener {
            onBackPressed()
        }
        btn_continuar_cv4.setOnClickListener(View.OnClickListener {
            if (experienciaLaboral!!.titulo.isNullOrBlank() ||experienciaLaboral!!.titulo == " " ) {
                descripcionText = " --Perfil-- \nNombre completo: " + (perfil?.nombre
                    ?: "None") + " " + (perfil?.apellidos ?: "") + "\n" +
                        "Dirección: " + (perfil?.direccion ?: "") + "\n" +
                        "Ciudad: " + (perfil?.ciudad ?: "") + "\n" +
                        "Correo electrónico: " + (perfil?.correo ?: "") + "\n" +
                        "Número de teléfono: " + (perfil?.numeroTel ?: "") + "\n" +
                        " --Educación-- \n" +
                        "Grado académico: " + (educacion?.grado ?: "") + "\n" +
                        "Área de estudio: " + (educacion?.area ?: "") + "\n" +
                        "Institución educativa: " + (educacion?.institucion ?: "") + "\n" +
                        "Ciudad: " + (educacion?.ciudad ?: "") + "\n" +
                        "Del: " + (educacion?.fechaInicio ?: "") + "\n" +
                        "Al: " + (educacion?.fechaFin ?: "") + "\n" +
                        " --Experiencia Laboral-- \n" +
                        "Titulo del empleo: " + (experienciaLaboral?.titulo ?: "") + "\n" +
                        "Empresa: " + (experienciaLaboral?.empresa ?: "") + "\n" +
                        "Ciudad: " + (experienciaLaboral?.ciudad ?: "") + "\n" +
                        "Periodo de tiempo: " + (experienciaLaboral?.periodo ?: "") + "\n" +
                        "Del: " + (experienciaLaboral?.fechaInicio ?: "") + "\n" +
                        "Al: " + (experienciaLaboral?.fechaFin ?: "") + "\n" +
                        "Habilidades: \n"
                for (item in habilidades){
                    descripcionText += item+"\n"
                }
            }else{
                descripcionText = " --Perfil--  \nNombre completo: " + (perfil?.nombre
                    ?: "None") + " " + (perfil?.apellidos ?: "") + "\n" +
                        "Dirección: " + (perfil?.direccion ?: "") + "\n" +
                        "Ciudad: " + (perfil?.ciudad ?: "") + "\n" +
                        "Correo electrónico: " + (perfil?.correo ?: "") + "\n" +
                        "Número de teléfono: " + (perfil?.numeroTel ?: "") + "\n" +
                        "-- Educación --\n" +
                        "Grado académico: " + (educacion?.grado ?: "") + "\n" +
                        "Área de estudio: " + (educacion?.area ?: "") + "\n" +
                        "Institución educativa: " + (educacion?.institucion ?: "") + "\n" +
                        "Ciudad: " + (educacion?.ciudad ?: "") + "\n" +
                        "Del: " + (educacion?.fechaInicio ?: "") + "\n" +
                        "Al: " + (educacion?.fechaFin ?: "") + "\n" +
                        "-- Experiencia Laboral --\n" +
                        "Ninguna\n"+
                        "Habilidades: \n"
                for (item in habilidades){
                    descripcionText += item+"\n"
                }
            }
            generarPdf()
            var intent = Intent(this,FeedActivity::class.java)
            startActivity(intent)
            finish()
        })
    }

    private fun subirCurriculum(){
        val storageRef = storage.reference
        var file = Uri.fromFile(File(Environment.getExternalStorageDirectory(),"Curriculum.pdf"))
        println("----")
        println(file)
        println("----")
        val curriculumRef = storageRef.child("Curriculum.pdf")
        var uploadTask = curriculumRef.putFile(file)

        val urlTask = uploadTask.continueWithTask { task ->
            if (!task.isSuccessful) {
                task.exception?.let {
                    throw it
                }
            }
            curriculumRef.downloadUrl
        }.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val downloadUri = task.result
            } else {
                // Handle failures
                // ...
            }
        }

    }

    private fun generarPdf(){
        var pdfDocument = PdfDocument()
        var paint = Paint()
        var titulo = TextPaint()
        var descripcion = TextPaint()

        var paginaInfo = PdfDocument.PageInfo.Builder(816,1054,1).create()
        var pagina1 = pdfDocument.startPage(paginaInfo)

        var canvas = pagina1.canvas

        var bitmap = BitmapFactory.decodeResource(resources,R.drawable.logo_blanco)
        var bitmapEscala = Bitmap.createScaledBitmap(bitmap,80,80,false)
        canvas.drawBitmap(bitmapEscala,368f,20f,paint)

        titulo.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.BOLD))
        titulo.textSize = 20f
        canvas.drawText(tituloText,10f,150f,titulo)

        descripcion.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL))
        descripcion.textSize = 14f

        var arrDescripcion = descripcionText.split("\n")
        var y = 200f
        for (item in arrDescripcion){
            canvas.drawText(item,10f,y,descripcion)
            y+=15
        }
        pdfDocument.finishPage(pagina1)

        val file = File(Environment.getExternalStorageDirectory(),"Curriculum.pdf")
        try {
            if(!file.exists()){ // Si no existe, crea el archivo.
                file.createNewFile();
            }
            pdfDocument.writeTo(FileOutputStream(file))
            println(file.path)
            Toast.makeText(this,"Se creo el curriculum",Toast.LENGTH_SHORT).show()
        }catch (e : Exception){
            e.printStackTrace()
        }
        pdfDocument.close()
        subirCurriculum()
    }


    private fun checkPermission():Boolean{
        val permission1 = ContextCompat.checkSelfPermission(applicationContext,WRITE_EXTERNAL_STORAGE)
        val permission2 = ContextCompat.checkSelfPermission(applicationContext,READ_EXTERNAL_STORAGE)
        return permission1 == PackageManager.PERMISSION_GRANTED && permission2 == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermissions(){
        ActivityCompat.requestPermissions(
            this,
            arrayOf(WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE),
            200
        )
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 200){
            if (grantResults.size > 0){
                val writeStorage = grantResults[0] == PackageManager.PERMISSION_GRANTED
                val readStorage = grantResults[1] == PackageManager.PERMISSION_GRANTED

                if (writeStorage && readStorage){
                    Toast.makeText(this,"Permisos concedidos",Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this,"Permisos rechazados",Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
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

