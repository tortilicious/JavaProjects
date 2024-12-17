import java.util.*

class Canario (nombre:String, edad:Int, estado:String, fechaNacimiento: Date,
            pico:String, vuela:Boolean,
            val color:String, val canta:Boolean): Aves(nombre,edad,estado,fechaNacimiento, pico, vuela) {




    override fun volar() {
        if (this.vuela) {
            println("El canario vuela")
        } else {
            println("El canario no sabe volar")
        }
    }

    override fun muestra() {
        println("""
            Esta mascota es un loro:
                Nombre: $nombre
                Edad: $edad
                Estado: $estado
                Fecha: $fechaNacimiento      
                Pico: $pico
                Vuela: ${volar()}
                Habla: ${habla()}
                
        """.trimIndent())
    }

    override fun habla() {
        TODO("Not yet implemented")
    }

    fun canta() {
        if (this.canta) {
            println("El canario canta")
        } else {
            println("El canario no cantar")
        }
    }

}