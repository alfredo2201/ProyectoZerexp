package purple.team.zerexp.adaptadores

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.message_view.view.*
import purple.team.zerexp.Mensajes
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
        view.tv_last_message.setText(chat.id)
        view.tv_last_message_time.setText("13:07")
        view.ll_chat_info_layout.setOnClickListener {
            var intent = Intent(context, Mensajes::class.java)
            intent.putExtra("chatId", chat.id)
            ContextCompat.startActivity(context!!, intent, null)
        }
        return view
    }
}