package budget.cli;

import budget.model.Articulo;
import budget.model.Usuario;

import java.util.List;
import java.util.Scanner;

public class Menu {

    Scanner scanner;

    public Menu() {
        this.scanner = new Scanner(System.in);
    }

    public void mostrarTodosArticulos(List<Articulo> articulos) {
        if (articulos.isEmpty()) {
            System.out.println("The purchase list is empty");
        } else {
            for (Articulo articulo : articulos) {
                System.out.println(articulo.toString());
            }
        }
        System.out.println();
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

    public void agregarCompra(Usuario usuario) {
        System.out.println("Enter purchase name:");
        String nombre = scanner.nextLine();
        System.out.println("Enter its price:");

        try {
            double precio = Double.parseDouble(scanner.nextLine());

            usuario.getHistorialCompras().add(new Articulo(nombre, precio));
            usuario.setBalanceCuenta(usuario.getBalanceCuenta() - precio);
            System.out.println("Purchase was added!");
            System.out.println();

        } catch (NumberFormatException e) {
            System.out.println("Add a valid ammount.");
        }


    }

    public void mostrarMenu() {
        System.out.println("""
                Choose your action:
                1) Add income
                2) Add purchase
                3) Show list of purchases
                4) Balance
                0) Exit
                """);
    }

    public void mostrarBalance(Usuario usuario) {
        System.out.printf("Balance: $%.2f%n", usuario.getBalanceCuenta());
        System.out.println();
    }

    public void salir() {
        System.out.println("Bye!");
    }
}
