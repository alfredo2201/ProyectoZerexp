package purple.team.zerexp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_mensajes.*
import kotlinx.android.synthetic.main.fragment_mensajes.view.*
import purple.team.zerexp.R
import purple.team.zerexp.adaptadores.MessageAdapter
import purple.team.zerexp.databinding.FragmentEmpresasBinding
import purple.team.zerexp.modelos.Message

class HomeFragment : Fragment() {

    private var _binding: FragmentEmpresasBinding? = null
    val auth = Firebase.auth
    val db = Firebase.firestore
    lateinit var chatId: String
    lateinit var email: String
    private var adaptador: MessageAdapter? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentEmpresasBinding.inflate(inflater, container, false)

        val root = inflater.inflate(R.layout.fragment_mensajes, container, false)

        val textView: TextView = binding.txtMensajeEmpresas
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        email = if (auth.currentUser?.email != null) auth.currentUser?.email!! else ""

        if (activity?.intent?.extras != null) {
            chatId = activity?.intent?.getStringExtra("chatId").toString()
        }
        println("[ EMAIL ] = ${email}")
        Log.d("[ CHAT ID ]", chatId)
        // val userRef = db.collection("users").document(email)
        val chatRef = db.collection("chats").document(chatId.trim())
        println("[ DOCUMENTO ] ${chatRef.path}")
        chatRef.collection("messages").orderBy("dob", Query.Direction.ASCENDING)
            .get()
            .addOnSuccessListener { messagesDB ->
            val messages = messagesDB.toObjects(Message::class.java) as ArrayList<Message>
            println("[ MENSAJES ] ${messages}")
            adaptador = MessageAdapter(this.requireContext(), messages, email)
            root.lv_mensajes_priv.adapter = adaptador
        }
        chatRef.collection("messages").orderBy("dob", Query.Direction.ASCENDING)
            .addSnapshotListener { messagesDB, error ->
            if (error == null) {
                messagesDB?.let {
                    val messages = messagesDB.toObjects(Message::class.java) as ArrayList<Message>
                    adaptador = MessageAdapter(this.requireContext(), messages, email)
                    root.lv_mensajes_priv.adapter = adaptador
                }
            }
        }

        root.btn_enviar_priv.setOnClickListener {
            enviarMensaje(root)
        }

        return root
    }

    private fun enviarMensaje(root: View) {
        val email: String = if (auth.currentUser?.email != null) auth.currentUser?.email!! else ""
        if (email.isNotEmpty() && email.isNotBlank()) {
            println("[ EMAIL ] = ${email}")
            val message = Message(
                message = root.et_message_priv.text.toString(),
                from = email
            )
            db.collection("chats").document(chatId.trim()).collection("messages").document().set(message)
            root.et_message_priv.setText("")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}