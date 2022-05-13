package purple.team.zerexp

import java.io.Serializable

data class Perfil(var nombre:String, var apellidos:String
                  , var direccion:String, var ciudad:String,
                  var correo:String, var numeroTel:String):Serializable