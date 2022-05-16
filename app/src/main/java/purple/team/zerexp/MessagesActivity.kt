package purple.team.zerexp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_messages.*
import purple.team.zerexp.adaptadores.ChatAdapter
import purple.team.zerexp.modelos.Chat
import java.util.*
import kotlin.collections.ArrayList

class MessagesActivity : FragmentActivity() {

    private var adaptador: ChatAdapter? = null
    val db = Firebase.firestore
    val auth = Firebase.auth
    lateinit var email: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_messages)
        email = if (auth.currentUser?.email != null) auth.currentUser?.email!! else ""
        val userRef = db.collection("users").document(email)
        userRef.collection("chats").get().addOnSuccessListener { chatsDB ->
            val chats = chatsDB.toObjects(Chat::class.java) as ArrayList<Chat>
            adaptador = ChatAdapter(this, chats)
            lv_chats.adapter = adaptador
        }
        userRef.collection("chats").addSnapshotListener { chatsDB, error ->
            if (error == null) {
                chatsDB?.let {
                    val chats = chatsDB.toObjects(Chat::class.java) as ArrayList<Chat>
                    adaptador = ChatAdapter(this, chats)
                    lv_chats.adapter = adaptador
                }
            }
        }
    }

}