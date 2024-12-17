import java.util.*

class Perro (nombre:String, edad:Int, estado: String, fechaNacimiento: Date,
             val raza: String, var pulgas:Boolean) : Mascota(nombre,edad,estado,fechaNacimiento) {
    override fun muestra() {
        println("""
            Esta mascota es un perro:
                Nombre: $nombre
                Edad: $edad
                Fecha: $fechaNacimiento
                Raza: $raza            
        """.trimIndent())
    }

    override fun habla() {
        println("Guau guau!!")
    }
}