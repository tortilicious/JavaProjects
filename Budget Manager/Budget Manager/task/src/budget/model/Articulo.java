package budget.model;

import java.util.List;

public class Articulo {

    private String nombre;
    private Double precio;

    public Articulo(String title, Double cost) {
        this.nombre = title;
        this.precio = cost;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return String.format("%s $%.2f", nombre, precio);
    }


}
