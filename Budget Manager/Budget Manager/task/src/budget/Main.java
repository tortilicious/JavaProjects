package budget;

import budget.model.Articulo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        List<Articulo> articulos = new ArrayList<Articulo>(); // Donde almacenamos los inputs

        while (scanner.hasNext()) {

            // Procesamos cada linea de input
            String[] parts = scanner.nextLine().split(" \\$");

            String nombre = parts[0];
            Double precio = Double.parseDouble(parts[1]);

            // Añadimos el objeto a nuestro Array
            articulos.add(new Articulo(nombre, precio));
        }

        // Mostramos la lista de articulos
        Articulo.mostrarTodosArticulos(articulos);
        System.out.println();
        Articulo.mostrarCosteTotal(articulos);
    }


}
