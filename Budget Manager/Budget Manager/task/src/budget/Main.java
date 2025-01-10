package budget;

import budget.cli.Menu;
import budget.model.Usuario;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Menu menu = new Menu();
        Usuario usuario = new Usuario();
        int option = -1;

        do {
            menu.mostrarMenu();
            try {
                option = Integer.parseInt(sc.nextLine());
                switch (option) {
                    case 1 -> {
                        menu.agregarIngreso(usuario);
                    }
                    case 2 -> {
                        menu.agregarCompra(usuario);
                    }
                    case 3 -> {
                        menu.mostrarTodosArticulos(usuario.getHistorialCompras());
                    }
                    case 4 -> {
                        menu.mostrarBalance(usuario);
                    }
                    case 0 -> {
                        menu.salir();
                    }
                    default -> {
                        System.out.println("Invalid option");
                    }
                }

            } catch (NumberFormatException e) {
                System.out.println("Enter a valid numeric option");
            }

        } while (option != 0);
        sc.close();
    }
}
