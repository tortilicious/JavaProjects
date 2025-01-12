package strategy;

public class Subtraction implements OperationStrategy {
    @Override
    public double calculate(double a, double b) {
        return a - b;
    }
}
