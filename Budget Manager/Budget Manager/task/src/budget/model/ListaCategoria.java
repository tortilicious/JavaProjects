package budget.model;

import java.util.List;

public class ListaCategoria {

    private String categoria;
    private List<Articulo> articulos;
    private double coste;

    public ListaCategoria(String categoria, List<Articulo> articulos) {
        this.categoria = categoria;
        this.articulos = articulos;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public List<Articulo> getArticulos() {
        return articulos;
    }

    public void setArticulos(List<Articulo> articulos) {
        this.articulos = articulos;
    }

    public double getCoste() {
        return articulos.stream()
                .mapToDouble(Articulo::getPrecio)
                .sum();
    }

    public void setCoste(double coste) {
        this.coste = coste;
    }
}
