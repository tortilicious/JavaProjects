package strategy;

public class Multiplication implements OperationStrategy {
    @Override
    public double calculate(double a, double b) {
        return a * b;
    }
}
