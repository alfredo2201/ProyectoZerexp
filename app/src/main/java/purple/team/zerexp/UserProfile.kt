package purple.team.zerexp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_user_profile.*
import purple.team.zerexp.modelos.Chat
import java.util.*
import kotlin.collections.ArrayList

class UserProfile : AppCompatActivity() {

    val db = Firebase.firestore
    val auth = Firebase.auth
    lateinit var email: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)
        email = if (auth.currentUser?.email != null) auth.currentUser?.email!! else ""
        btn_enviar.setOnClickListener {

        }
    }

    private fun createChat() {
        val chatId = UUID.randomUUID().toString()
        val otherUser = tv_username_profile.text.toString()
        val users = listOf(email, otherUser)

        var chats: ArrayList<Chat>
        db.collection("chats").whereArrayContains("users", listOf(email, otherUser)).get().addOnSuccessListener { chats ->
            Log.d("[ IT DOES EXISTS ]", "Al parecer si existen los chats con ambos users.")
        }

        if (false) {
            val chat = Chat(
                id = chatId,
                name = "Chat con $otherUser",
                users = users
            )

            db.collection("chats").document(chatId).set(chat)
            db.collection("users").document(email).collection("chats").document(chatId).set(chat)
            db.collection("users").document(otherUser).collection("chats").document(chatId).set(chat)

            val intent = Intent(this, Mensajes::class.java)
            intent.putExtra("chatId", chatId)
            intent.putExtra("user", email)
            startActivity(intent)
        }
    }
}