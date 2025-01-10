package budget.model;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

    private List<Articulo> historialCompras;
    private double balanceCuenta;

    public Usuario(List<Articulo> historialCompras, double balanceCuenta) {
        this.historialCompras = historialCompras;
        this.balanceCuenta = balanceCuenta;
    }

    public Usuario() {
        this.historialCompras = new ArrayList<Articulo>();
    }

    public List<Articulo> getHistorialCompras() {
        return historialCompras;
    }

    public void setHistorialCompras(List<Articulo> historialCompras) {
        this.historialCompras = historialCompras;
    }

    public double getBalanceCuenta() {
        return balanceCuenta;
    }

    public void setBalanceCuenta(double balanceCuenta) {
        this.balanceCuenta = balanceCuenta;
    }


}
