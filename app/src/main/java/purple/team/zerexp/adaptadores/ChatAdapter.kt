package purple.team.zerexp.adaptadores

import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.message_view.view.*
import purple.team.zerexp.Mensajes
import purple.team.zerexp.R
import purple.team.zerexp.modelos.Chat
import purple.team.zerexp.modelos.Message
import java.time.ZoneId

class ChatAdapter: BaseAdapter {

    var chats = ArrayList<Chat>()
    var context: Context? = null
    val db = Firebase.firestore

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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var chat = chats[p0]
        var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var view = inflator.inflate(R.layout.message_view, null)
        val chatRef = db.collection("chats").document(chat.id.trim())
        view.tv_username.setText(chat.name)
        chatRef.collection("messages").orderBy("dob", Query.Direction.ASCENDING)
            .get().addOnSuccessListener { messagesDB ->
                if (messagesDB.size() > 0) {
                    val messages = messagesDB.toObjects(Message::class.java) as ArrayList<Message>
                    view.tv_last_message.setText(messages.last().message)
                    val time = messages.last().dob.toInstant().atZone(ZoneId.systemDefault()).toLocalTime()
                    view.tv_last_message_time.setText("${time.hour}:${time.minute}")
                }
            }
        view.ll_chat_info_layout.setOnClickListener {
            var intent = Intent(context, Mensajes::class.java)
            intent.putExtra("chatId", chat.id)
            ContextCompat.startActivity(context!!, intent, null)
        }
        return view
    }
}