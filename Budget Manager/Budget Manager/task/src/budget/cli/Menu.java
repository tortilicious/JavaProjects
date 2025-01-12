package budget.cli;

import budget.model.Articulo;
import budget.model.ListaCategoria;
import budget.model.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private final Scanner scanner;


    public Menu() {
        this.scanner = new Scanner(System.in);
    }



    public void agregarCompra(Usuario usuario) {

        do {
            mostrarMenuCategorias();
            String categoria;
            int opcion = Integer.parseInt(scanner.nextLine());
            switch (opcion) {
                case 1 -> categoria = "Food";
                case 2 -> categoria = "Clothes";
                case 3 -> categoria = "Entertainment";
                case 4 -> categoria = "Other";
                case 5 -> {
                    return;
                }
                default -> {
                    System.out.println("Invalid option");
                    continue;
                }
            }

            System.out.println("Enter purchase name:");
            String nombre = scanner.nextLine();
            System.out.println("Enter its price:");

            try {
                double precio = Double.parseDouble(scanner.nextLine());

                usuario.getHistorialCompras().add(new Articulo(nombre, precio, categoria));
                usuario.setBalanceCuenta(usuario.getBalanceCuenta() - precio);
                System.out.println("Purchase was added!");
                System.out.println();

            } catch (NumberFormatException e) {
                System.out.println("Add a valid ammount.");
            }
        } while (true);
    }

    public void agregarIngreso(Usuario usuario) {
        System.out.println("Enter income:");
        try {
            double ingreso = Double.parseDouble(scanner.nextLine());
            if (ingreso <= 0) {
                System.out.println("Please add a positive ammount of money.");
            } else {
                usuario.setBalanceCuenta(usuario.getBalanceCuenta() + ingreso);
                System.out.println("Income was added!");
                System.out.println();
            }

        } catch (NumberFormatException e) {
            System.out.println("Add a valid ammount.");
        }
    }

    public double calcularCosteLista(List<Articulo> articulos) {
        return articulos.stream()
                .mapToDouble(Articulo::getPrecio)
                .sum();
    }

    public static List<Articulo> filtrarPorCategoria(List<Articulo> articulos, String categoria) {
        return articulos.stream()
                .filter(articulo -> articulo.getCategoria().equals(categoria))
                .toList();
    }

    public void guardarDatos(Usuario usuario) {

        File fichero = new File("purchases.txt");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichero))) {
            for (Articulo articulo : usuario.getHistorialCompras()) {
                oos.writeObject(articulo);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Purchases were saved!");
        System.out.println();
    }

    public void leerDatos(Usuario usuario) {
        File fichero = new File("purchases.txt");
        List<Articulo> articulos = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichero))) {
            while (true) {
                try {
                    Articulo articulo = (Articulo) ois.readObject();
                    articulos.add(articulo);

                } catch (EOFException | ClassNotFoundException e) {
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        usuario.setHistorialCompras(articulos);
        usuario.setBalanceCuenta(1000);
        System.out.println("Purchases were loaded!");
        System.out.println();
    }

    public void mostrarArticulosPorCategoria(List<Articulo> articulos, String categoria) {

        List<Articulo> listaCategoria = filtrarPorCategoria(articulos, categoria);

        if (listaCategoria.isEmpty()) {
            System.out.println("The purchase list is empty!");
            System.out.println();
            return;
        }

        double coste = calcularCosteLista(listaCategoria);
        listaCategoria.forEach(articulo -> System.out.printf("%s $%.2f\n", articulo.getNombre(), articulo.getPrecio()));
        System.out.printf("Total sum: $%.2f", coste);
        System.out.println();

    }

    public void mostrarArticulos(Usuario usuario) {

        do {
            mostrarMenuArticulos();
            try {
                int opcion = Integer.parseInt(scanner.nextLine());
                String categoria;
                switch (opcion) {
                    case 1 -> {
                        categoria = "Food";
                        mostrarArticulosPorCategoria(usuario.getHistorialCompras(), categoria);
                    }
                    case 2 -> {
                        categoria = "Clothes";
                        mostrarArticulosPorCategoria(usuario.getHistorialCompras(), categoria);
                    }
                    case 3 -> {
                        categoria = "Entertainment";
                        mostrarArticulosPorCategoria(usuario.getHistorialCompras(), categoria);
                    }
                    case 4 -> {
                        categoria = "Other";
                        mostrarArticulosPorCategoria(usuario.getHistorialCompras(), categoria);
                    }
                    case 5 -> mostrarTodosArticulos(usuario.getHistorialCompras());

                    case 6 -> {
                        return;
                    }
                    default -> System.out.println("Invalid option");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input");
            }
            System.out.println();
        } while (true);
    }

    public void mostrarBalance(Usuario usuario) {
        double gastos = 0;
        for (Articulo articulo : usuario.getHistorialCompras()) {
            gastos += articulo.getPrecio();
        }

        System.out.printf("Balance: $%.2f%n", usuario.getBalanceCuenta() - gastos);
        System.out.println();
    }

    public void mostrarMenu() {
        System.out.println("""
                Choose your action:
                1) Add income
                2) Add purchase
                3) Show list of purchases
                4) Balance
                5) Save
                6) Load
                7) Analyze (Sort)
                0) Exit
                """);
    }

    public void mostrarMenuArticulos() {
        System.out.println("""
                1) Food
                2) Clothes
                3) Entertainment
                4) Other
                5) All
                6) Back
                """);
    }

    public void mostrarMenuCategorias() {
        System.out.println("""
                1) Food
                2) Clothes
                3) Entertainment
                4) Other
                5) Back
                """);
    }

    public void mostrarMenuOrdenar() {
        System.out.println("""
                How do you want to sort?
                1) Sort all purchases
                2) Sort by type
                3) Sort certain type
                4) Back
                """);

    }

    public void mostrarTodosArticulos(List<Articulo> articulos) {

        if (articulos.isEmpty()) {
            System.out.println("The purchase list is empty");
            System.out.println();
            return;
        }
        double total = calcularCosteLista(articulos);
        articulos.forEach(articulo -> System.out.printf("%s $%.2f\n", articulo.getNombre(), articulo.getPrecio()));
        System.out.printf("Total sum: $%.2f", total);
        System.out.println();
    }

    public void ordenar(Usuario usuario) {
        do {
            mostrarMenuOrdenar();
            int opcion = Integer.parseInt(scanner.nextLine());
            switch (opcion) {
                case 1 -> ordenarCompras(usuario);
                case 2 -> ordenarPorCategorias(usuario.getHistorialCompras());
                case 3 -> ordenarCategoria(usuario.getHistorialCompras());
                case 4 -> {
                    return;
                }
                default -> System.out.println("Invalid option");
            }

        } while (true);
    }

    public void ordenarCompras(Usuario usuario) {

        if (usuario.getHistorialCompras().isEmpty()) {
            System.out.println("The purchase list is empty!");
            System.out.println();
            return;
        }

        double coste = calcularCosteLista(usuario.getHistorialCompras());

        System.out.println("All:");
        usuario.getHistorialCompras().stream()
                .sorted(Comparator.comparing(Articulo::getPrecio))
                .forEach(Articulo::toString);
        System.out.printf("Total: $%.2f", coste);
    }

    public void ordenarPorCategorias(List<Articulo> articulos) {
        if (articulos.isEmpty()) {
            System.out.println("The purchase list is empty!");
            System.out.println();
            return;
        }

        ListaCategoria listaComida = new ListaCategoria("Food", filtrarPorCategoria(articulos, "Food"));
        ListaCategoria listaEntretenimiento = new ListaCategoria("Entertainment", filtrarPorCategoria(articulos, "Entertainment"));
        ListaCategoria listaRopa = new ListaCategoria("Clothes", filtrarPorCategoria(articulos, "Clothes"));
        ListaCategoria listaOtros = new ListaCategoria("Other", filtrarPorCategoria(articulos, "Other"));

        List<ListaCategoria> listaCategoriasSinOrdenar = new ArrayList<>();
        listaCategoriasSinOrdenar.add(listaComida);
        listaCategoriasSinOrdenar.add(listaEntretenimiento);
        listaCategoriasSinOrdenar.add(listaRopa);
        listaCategoriasSinOrdenar.add(listaOtros);

        // Ordenamos la lista por coste de forma descendente

        listaCategoriasSinOrdenar
                .sort(Comparator.comparingDouble(ListaCategoria::getCoste)
                        .reversed());

        // Calculamos el coste

        // Salida del metodo por consola

        System.out.println("Types:");
        listaCategoriasSinOrdenar.forEach(listaCategoria -> {
            System.out.printf("%s - $%.2f\n", listaCategoria.getCategoria(), listaCategoria.getCoste());
        });
        System.out.printf("Total sum: $%.2f\n", calcularCosteLista(articulos));


    }

    public void ordenarCategoria(List<Articulo> articulos) {
        if (articulos.isEmpty()) {
            System.out.println("The purchase list is empty!");
            System.out.println();
            return;
        }

        do {
            mostrarMenuCategorias();
            String categoria;
            int opcion = Integer.parseInt(scanner.nextLine());
            switch (opcion) {
                case 1 -> categoria = "Food";
                case 2 -> categoria = "Clothes";
                case 3 -> categoria = "Entertainment";
                case 4 -> categoria = "Other";
                case 5 -> {
                    return;
                }
                default -> {
                    System.out.println("Invalid option");
                    continue;
                }
            }
            ListaCategoria listaCategoria = new ListaCategoria(categoria, filtrarPorCategoria(articulos, categoria));

            // Ordenamos los articulos
            listaCategoria.getArticulos().sort(Comparator.comparing(Articulo::getPrecio).reversed());

            // Salida a consola usando stream
            System.out.printf("%s:\n", listaCategoria.getCategoria());

            // Imprimir cada articulo usando stream
            listaCategoria.getArticulos().forEach(articulo ->
                    System.out.printf("%s $%.2f\n", articulo.getNombre(), articulo.getPrecio()));

            // Imprimir el total de la categoría
            System.out.printf("Total sum: $%.2f\n", listaCategoria.getCoste());

        } while (true);
    }

    public void salir() {
        System.out.println("Bye!");
    }
}
