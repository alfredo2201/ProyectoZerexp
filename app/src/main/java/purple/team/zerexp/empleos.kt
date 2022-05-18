package purple.team.zerexp

import android.app.AlertDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_empleos.*

class empleos : AppCompatActivity() {

    val db = Firebase.firestore
    var empleos = ArrayList<Empleo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empleos)
        lv_empleos.divider = null
        cargarDatos()
        val btn_agregar: ImageButton = findViewById(R.id.btn_add)
        val dialog = AlertDialog.Builder(this).create()
        val inflater = layoutInflater
        
        dialog.setView(inflater.inflate(R.layout.publicar_empleo, null))
        dialog.setButton(AlertDialog.BUTTON_POSITIVE, "SEND") { dialogs, which ->

            var nombre = dialog.findViewById(R.id.nombre) as TextView
            var name = nombre.getText().toString()

            var empresa = dialog.findViewById(R.id.empresa) as TextView
            var company = empresa.getText().toString()

            var ubicacion = dialog.findViewById(R.id.ubicacion) as TextView
            var location = ubicacion.getText().toString()

            var salario = dialog.findViewById(R.id.salario) as TextView
            var pay = salario.getText().toString()



            if (!name.isNullOrBlank() && !company.isNullOrBlank() && !location.isNullOrBlank() && !pay.isNullOrBlank() ){
                val job = hashMapOf(
                    "Nombre" to name,
                    "Empresa" to company,
                    "Ubicacion" to location,
                    "Salario" to pay,
                    "Vacante Status" to true
                )


                db.collection("empleos").document(company).set(job)
                    .addOnCompleteListener {
                        Toast.makeText(baseContext, "Job Created", Toast.LENGTH_SHORT).show()
                    }.addOnFailureListener {
                        Toast.makeText(baseContext, "Job not Created", Toast.LENGTH_SHORT).show()
                    }

                dialog.dismiss()

                empleos.add(Empleo(name,company,location,pay,true))

                empleos = orderDatos()
                cargarDatos()
                nombre.setText("")
                empresa.setText("")
                ubicacion.setText("")
                salario.setText("")
            }else{
                Toast. makeText(this,"Ingresar datos", Toast.LENGTH_SHORT).show()
            }

        }
        dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "CANCEL") { dialogs, which ->
            dialog.dismiss()
            var nombre = dialog.findViewById(R.id.nombre) as TextView
            var empresa = dialog.findViewById(R.id.empresa) as TextView
            var ubicacion = dialog.findViewById(R.id.ubicacion) as TextView
            var salario = dialog.findViewById(R.id.salario) as TextView

            nombre.setText("")
            empresa.setText("")
            ubicacion.setText("")
            salario.setText("")
        }

        btn_agregar.setOnClickListener {
            dialog.create()
            dialog.show()
        }
        btn_regresar_empleos.setOnClickListener {
            onBackPressed()
            finish()
        }



    }

    fun orderDatos():ArrayList<Empleo>{

        var reEmpleo =ArrayList<Empleo>()

        for (i in (empleos.size-1) downTo 0){
            reEmpleo.add(empleos.get(i))
        }

        return reEmpleo

    }

    fun cargarDatos(): ArrayList<Empleo>{
        var adaptador: AdaptadorEmpleos? = null
        db.collection("empleos")
            .get()
            .addOnSuccessListener { result ->
                println("[  RESULT  ]"+result.documents)
                empleos = result.toObjects(Empleo::class.java) as ArrayList<Empleo>
                adaptador = AdaptadorEmpleos ( this, empleos)
                lv_empleos.adapter = adaptador
                println("[   EMPLEOS  ] "+ empleos)
            }

        return empleos

    }
}



private class AdaptadorEmpleos : BaseAdapter {
    var empleos = ArrayList<Empleo>()
    var contexto: Context? = null

    constructor(contexto: Context, empleos: ArrayList<Empleo>) {
        this.empleos = empleos
        this.contexto = contexto
    }

    override fun getCount(): Int {
        return empleos.size
    }

    override fun getItem(p0: Int): Any {
        return empleos[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var prod = empleos[p0]
        var inflador = LayoutInflater.from(contexto)
        var vista = inflador.inflate(R.layout.empleo_view, null)

        var nombre = vista.findViewById(R.id.nom_empleo) as TextView
        var empresa = vista.findViewById(R.id.emp_empleo) as TextView
        var ubicacion = vista.findViewById(R.id.ubica_empleo) as TextView
        var salario = vista.findViewById(R.id.salar_empleo) as TextView

        nombre.setText(prod.name)
        empresa.setText(prod.Empresa)
        ubicacion.setText(prod.Ubicacion)
        salario.setText("$${prod.Salario}")

        return vista

    }
}