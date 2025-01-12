import model.Calculator;
import strategy.Addition;
import strategy.Division;
import strategy.Multiplication;
import strategy.Subtraction;

public class Main {
    public static void main(String[] args) {


        Calculator calculator = new Calculator();

        double a = 7;
        double b = 2;

        // Caso en el que queramos realizar una suma
        calculator.setStrategy(new Addition());
        System.out.println("7 + 2 = " + calculator.calculate(a, b));

        // Caso en el que queremos realizar una resta
        calculator.setStrategy(new Subtraction());
        System.out.println("7 - 2 = " +calculator.calculate(a, b));

        // Caso en el que queremos realizar una multiplicación
        calculator.setStrategy(new Multiplication());
        System.out.println("7 * 2 = " + calculator.calculate(a, b));

        // Caso en el que queremos realizar una division
        calculator.setStrategy(new Division());
        System.out.println("7 / 2 = " + calculator.calculate(a, b));
    }

}
