package purple.team.zerexp

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_mensajes.*
import kotlinx.android.synthetic.main.fragment_mensajes.*
import purple.team.zerexp.adaptadores.MessageAdapter
import purple.team.zerexp.databinding.ActivityMensajesBinding
import purple.team.zerexp.modelos.Message

class Mensajes : FragmentActivity() {

    private lateinit var binding: ActivityMensajesBinding

    val auth = Firebase.auth
    val db = Firebase.firestore
    lateinit var chatId: String
    lateinit var email: String
    private var adaptador: MessageAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMensajesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_mensajes)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        // setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        /*
        email = if (auth.currentUser?.email != null) auth.currentUser?.email!! else ""

        if (intent.extras != null) {
            chatId = intent.getStringExtra("chatId").toString()
        }
        Log.d("[ CHAT ID ]", chatId)
        // val userRef = db.collection("users").document(email)
        val chatRef = db.collection("chats").document(chatId)

        chatRef.collection("messages").get().addOnSuccessListener { messagesDB ->
            val messages = messagesDB.toObjects(Message::class.java) as ArrayList<Message>
            adaptador = MessageAdapter(this, messages, email)
            lv_chats.adapter = adaptador
        }
        chatRef.collection("messages").addSnapshotListener { messagesDB, error ->
            if (error == null) {
                messagesDB?.let {
                    val messages = messagesDB.toObjects(Message::class.java) as ArrayList<Message>
                    adaptador = MessageAdapter(this, messages, email)
                    lv_chats.adapter = adaptador
                }
            }
        }

        nav_host_fragment_activity_mensajes.btn_enviar_priv.setOnClickListener {
            enviarMensaje()
        }
        */
    }

    fun enviarMensaje() {
        var email: String = if (auth.currentUser?.email != null) auth.currentUser?.email!! else ""
        val message = Message(
            message = nav_host_fragment_activity_mensajes.et_message_priv.text.toString(),
            from = email
        )
        db.collection("chats").document(chatId).collection("messages").document().set(message)
        nav_host_fragment_activity_mensajes.et_message_priv.setText("")
    }
}