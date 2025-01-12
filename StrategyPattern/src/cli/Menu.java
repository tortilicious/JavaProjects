package cli;

import model.Calculator;
import strategy.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    private Scanner scanner;
    private Calculator calculator;


    public Menu() {
        scanner = new Scanner(System.in);
        calculator = new Calculator();
    }

    public void run() {
        int selection;
        double a;
        double b;

        do {
            try {
                System.out.println("Introduce the first number: ");
                double a = scanner.nextDouble();
                System.out.println("Introduce the second number: ");
                double b = scanner.nextDouble();

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Introduce a number please.");
                continue;
            }

            System.out.println("""
                Choose an operation:
                1. Add
                2. Subtract
                3. Multiply
                4. Divide
                5. Modulo
                6. Exit
                """);

            try {
                selection = scanner.nextInt();
                switch (selection) {
                    case 1 -> {
                        calculator.setStrategy(new Addition());
                        calculator.calculate(a, b);
                    }
                    case 2 -> calculator.setStrategy(new Subtraction());
                    case 3 -> calculator.setStrategy(new Multiplication());
                    case 4 -> calculator.setStrategy(new Division());
                    case 5 -> calculator.setStrategy(new Modulo());
                    case 6 -> {
                        System.out.println("Finalizing program.");
                        return;
                    }
                    default -> System.out.println("Invalid selection. Try again.");

                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Introduce a number please.");
            }

        } while (true);

    }


}
