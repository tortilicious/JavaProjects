import java.util.*

class Loro (nombre:String, edad:Int, estado:String, fechaNacimiento: Date,
            pico:String, vuela:Boolean,
            val origen: String, var habla:Boolean): Aves(nombre,edad,estado,fechaNacimiento, pico, vuela) {
    override fun volar() {
        if (this.vuela) {
            println("El loro vuela")
        } else {
            println("El loro no sabe volar")
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
        if (this.habla) {
            println("El loro habla")
        } else {
            println("El loro no sabe hablar")
        }
    }

}