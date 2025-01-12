package model;

import strategy.OperationStrategy;

public class Calculator {
    private OperationStrategy strategy;

    public void setStrategy(OperationStrategy strategy) {
        this.strategy = strategy;
    }

    public double calculate (double a, double b) {
        return strategy.calculate(a, b);
    }
}
