import java.util.ArrayList;
import java.util.List;

public class Pedido {

    // ATRIBUTOS
    private Cliente cliente;
    private List<Producto> productos;

    // CONSTRUCTOR
    public Pedido(Cliente cliente) {
        this.cliente = cliente;
        this.productos = new ArrayList<>();
    }

    // AÑADIMOS LOS PRODUCTOS AL PEDIDO
    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    // ============= PONEMOS LOS GETTERS Y SETTERS ====================
    public Cliente getCliente() {
        return cliente;
    }

    public List<Producto> getProductos() {
        return productos;
    }
    // ================== AQUÍ TERMINAN LOS GETTERS Y SETTERS ======================

    // BUCLE QUE CALCULA EL TOTAL DEL PEDIDO A REALIZAR
    public double calcularTotal() {
        double total = 0;
        for (Producto producto : productos) {
            total += producto.getPrecio();
        }
        return total;
    }
}
