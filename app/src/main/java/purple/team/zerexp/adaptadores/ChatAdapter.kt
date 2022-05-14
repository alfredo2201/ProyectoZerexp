package purple.team.zerexp.adaptadores

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.message_view.view.*
import purple.team.zerexp.R
import purple.team.zerexp.modelos.Chat

class ChatAdapter: BaseAdapter {

    var chats = ArrayList<Chat>()
    var context: Context? = null

    constructor(context: Context, chats: ArrayList<Chat>) {
        this.context = context
        this.chats = chats
    }

    override fun getCount(): Int {
        return this.chats.size
    }

    override fun getItem(p0: Int): Any {
        return chats[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var chat = chats[p0]
        var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var view = inflator.inflate(R.layout.message_view, null)
        view.tv_username.setText(chat.name)
        view.tv_last_message.setText("Holiwis uwu")
        view.tv_last_message_time.setText("13:07")
        return view
    }


}