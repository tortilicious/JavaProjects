package strategy;

public class Addition implements OperationStrategy {

    @Override
    public double calculate(double a, double b) {
        return a + b;
    }
}
