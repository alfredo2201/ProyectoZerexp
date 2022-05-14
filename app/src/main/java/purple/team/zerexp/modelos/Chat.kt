package purple.team.zerexp.modelos

data class Chat(
    var id: String = "",
    var name: String = "",
    var users: List<String> = emptyList()
)