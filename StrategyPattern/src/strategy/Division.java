package strategy;

public class Division implements OperationStrategy {
    @Override
    public double calculate(double a, double b) {
        if (b == 0) throw new IllegalArgumentException("Divisor cannot be zero");
        return a / b;
    }
}

