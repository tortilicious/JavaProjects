package model;

public class Account {

    private double income = 0.00;
    private double expense = 0.00;
    private double balance = 0.00;

    public Account() {
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income += income;
        updateBalance(); // Actualizar balance cada vez que se cambia el ingreso
    }


    public double getExpense() {
        return expense;
    }

    public void setExpense(double expense) {
        this.expense += expense;
        updateBalance(); // Actualizar balance cada vez que se cambia el gasto
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    private void updateBalance() {
        this.balance = income - expense;
    }
}
