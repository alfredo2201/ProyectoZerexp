package purple.team.zerexp.adaptadores

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.android.synthetic.main.perfil_view.view.*
import purple.team.zerexp.R

class MiniProfileAdapter: BaseAdapter {

    lateinit var lista: List<QueryDocumentSnapshot>
    var context: Context? = null

    constructor(context: Context, lista: List<QueryDocumentSnapshot>) {
        this.context = context
        this.lista = lista
    }

    override fun getCount(): Int {
        return lista.size
    }

    override fun getItem(p0: Int): Any {
        return lista.toList()[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var user = lista[p0]
        var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var view = inflator.inflate(R.layout.perfil_view, null)
        view.txt_nombre_perfil.setText(user.get("Nombre de Usuario").toString())
        view.txt_giro_perfil.setText(user.get("Profesion").toString())
        return view
    }
}