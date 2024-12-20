import java.util.Date


abstract class Mascota (val nombre:String, var edad:Int, var estado:String, val fechaNacimiento:Date){

    abstract fun muestra()
    fun cumpleanos() {
        println("El cumple de esta mascota es ${fechaNacimiento.toString()}")
    }
    fun morir(){
        println("Lamentablemente $nombre ha abandonado este mundo")
    }
    abstract fun habla()
}