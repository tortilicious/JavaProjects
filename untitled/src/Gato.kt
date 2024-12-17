import java.util.*


class Gato (nombre:String, edad:Int, estado:String, fechaNacimiento: Date,
            val color:String, var peloLargo:Boolean) : Mascota(nombre,edad,estado,fechaNacimiento) {
    override fun muestra() {
        println("""
            Esta mascota es un gato:
                Nombre: $nombre
                Edad: $edad
                Fecha: $fechaNacimiento      
                Color: $color
        """.trimIndent())
    }

    override fun habla() {
        println("Miau miau!!")
    }
}