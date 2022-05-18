package purple.team.zerexp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_mensages.*
import purple.team.zerexp.adaptadores.ChatAdapter
import purple.team.zerexp.modelos.Chat

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MensagesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MensagesFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var adaptador: ChatAdapter? = null
    val db = Firebase.firestore
    val auth = Firebase.auth
    lateinit var email: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val vista = inflater.inflate(R.layout.fragment_mensages, container, false)

        email = if (auth.currentUser?.email != null) auth.currentUser?.email!! else ""
        if (email.isNotEmpty() && email.isNotBlank()) {
            val userRef = db.collection("users").document(email)

            userRef.collection("chats").get().addOnSuccessListener { chatsDB ->
                val chats = chatsDB.toObjects(Chat::class.java) as ArrayList<Chat>
                adaptador = ChatAdapter(vista.context, chats)
                lv_chats.adapter = adaptador
            }
            userRef.collection("chats").addSnapshotListener { chatsDB, error ->
                if (error == null) {
                    chatsDB?.let {
                        val chats = chatsDB.toObjects(Chat::class.java) as ArrayList<Chat>
                        adaptador = ChatAdapter(vista.context, chats)
                        lv_chats.adapter = adaptador
                    }
                }
            }
        }
        return vista
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MensagesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MensagesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}