import java.util.*

abstract class Aves(nombre:String, edad:Int, estado:String, fechaNacimiento: Date,
           val pico:String, val vuela:Boolean) : Mascota(nombre,edad,estado,fechaNacimiento) {
    abstract fun volar()
}