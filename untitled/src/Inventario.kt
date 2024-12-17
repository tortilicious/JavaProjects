class Inventario (){

    private var animales: MutableList<Mascota> = mutableListOf<Mascota>()

    fun addAnimal(animal: Mascota){
        this.animales.add(animal)
    }

    fun deleteAnimal(animal: Mascota){
        this.animales.remove(animal)
    }

    fun mostrarAnimales(){
        if (this.animales.isEmpty()){
            println("La lista de animales esta vacia")
        } else {
            this.animales.forEach{
                animal ->
                println("${animal.nombre}, ${animal::class.simpleName}")
            }
        }
    }


    fun vaciarAnimales(){
        this.animales.clear()
    }


}