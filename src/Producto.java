public class Producto {

    // ATRIBUTOS
    private String nombre;
    private double precio;

    // CONSTRUCTOR
    public Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    // ============= PONEMOS LOS GETTERS Y SETTERS ====================
    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }
}
