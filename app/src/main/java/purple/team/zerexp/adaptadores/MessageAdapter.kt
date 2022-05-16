package purple.team.zerexp.adaptadores

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.priv_message_view.view.*
import purple.team.zerexp.R
import purple.team.zerexp.modelos.Message

class MessageAdapter: BaseAdapter {

    var messages = ArrayList<Message>()
    var context: Context? = null
    var user: String

    constructor(context: Context, messages: ArrayList<Message>, user: String) {
        this.context = context
        this.messages = messages
        this.user = user
    }

    override fun getCount(): Int {
        return messages.size
    }

    override fun getItem(p0: Int): Any {
        return messages[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var message = messages[p0]
        var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var view = inflator.inflate(R.layout.priv_message_view, null)
        if (user == message.from) {
            view.ll_my_message_layout.visibility = View.VISIBLE
            view.ll_other_message_layout.visibility = View.GONE
            view.tv_mi_priv_message.text = message.message
        } else {
            view.ll_other_message_layout.visibility = View.VISIBLE
            view.ll_my_message_layout.visibility = View.GONE
            view.tv_de_priv_message.text = message.message
        }
        return view
    }
}