package purple.team.zerexp.modelos

import java.io.Serializable

data class Perfil(var nombre:String, var apellidos:String
                  , var direccion:String, var ciudad:String,
                  var correo:String, var numeroTel:String):Serializable