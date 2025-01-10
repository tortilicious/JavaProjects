package budget.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Usuario implements Serializable {

    private List<Articulo> historialCompras;
    private double balanceCuenta;

    public Usuario(List<Articulo> historialCompras, double balanceCuenta) {
        this.historialCompras = historialCompras;
        this.balanceCuenta = balanceCuenta;
    }

    public Usuario() {
        this.historialCompras = new ArrayList<Articulo>();
        this.balanceCuenta = 0;
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
