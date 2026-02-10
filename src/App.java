import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Elisabet Soria Zaitseva
 */
public class App {

    private static final double TASA_IVA = 0.21;
    private static final double DESCUENTO_GRAN_VOLUMEN = 0.05;
    private static final double MAX_DESCUENTO = 3000;

    public static void main(String[] args) {

        System.out.println("INICIANDO SISTEMA DE PEDIDOS v2.0 (Refactorizado)...");

        // PEDIDO 1 DEL CLIENTE 1
        Cliente cliente1 = new Cliente("TechSolutions SL", "B12345678", "Calle Industria 55, Madrid");

        Pedido pedido1 = new Pedido(cliente1);
        pedido1.agregarProducto(new Producto("Servidor Dell PowerEdge", 2500.00));
        pedido1.agregarProducto(new Producto("Licencia Windows Server", 800.00));
        pedido1.agregarProducto(new Producto("Cableado Estructurado", 250.50));

        procesarPedido(pedido1);

        System.out.println("\n---------------------------------------------\n");

        // PEDIDO 2 DEL CLIENTE 2
        Cliente cliente2 = new Cliente("Libreria Moderna", "A98765432", "Av. Diagonal 200, Barcelona");

        Pedido pedido2 = new Pedido(cliente2);
        pedido2.agregarProducto(new Producto("Pack Libros Escolares", 1200.00));
        pedido2.agregarProducto(new Producto("Estantería Metálica", 300.00));

        procesarPedido(pedido2);
    }

    // ================= MÉTODOS EXTRAÍDOS DE MANERA REFACTORIZADA ===================
    /**
     * PROCESARPEDIDO() SE ENCARGA DE MOSTRAR POR PANTALLA Y CALCULAR, CON AYUDA DE
     * LOS OTROS MÉTODOS,
     * LA SUMA TOTAL JUNTO CON EL IVA
     * 
     * @param pedido
     */
    private static void procesarPedido(Pedido pedido) {

        double total = 0;
        int contador = 1;

        Cliente cliente = pedido.getCliente();

        // MUESTRA DATOS DEL CLIENTE
        System.out.println(String.format("Procesando pedido para: %s", cliente.getNombre()));
        System.out.println(String.format("ID Cliente: %s", cliente.getId()));

        // RECORRE TODOS LOS PRODUCTOS Y HACE LA SUMA TOTAL
        for (Producto producto : pedido.getProductos()) {
            System.out.println(String.format(
                    "Item %d: %s - %.2f EUR",
                    contador++, producto.getNombre(), producto.getPrecio()));
            total += producto.getPrecio();
        }

        // COMPRUEBA SI APLICA EL DESCUENTO
        if (aplicaDescuento(total)) {
            System.out.println("Aplica descuento por gran volumen (5%)");
            total = total * (1 - DESCUENTO_GRAN_VOLUMEN);
        }

        // CALCULA EL TOTAQL CON EL IVA LA INCLUIDO
        double totalConIVA = totalConIVA(total);

        // MUESTRA EL TOTAL DEL PEDIDO POR PANTALLA DE CADA CLIENTE
        System.out.println(String.format("Total Neto: %.2f EUR", total));
        System.out.println(String.format("Total con IVA (%.0f%%): %.2f EUR", TASA_IVA * 100, totalConIVA));

        // GUARDA EN UN ARCHIVO TXT LA INFORMACIÓN DE CADA PEDIDO REALIZADO DE CADA
        // CLIENTE
        guardarFacturaEnArchivo(cliente, totalConIVA);
    }

    /**
     * BOOLEAN QUE APLICA EL DESCUENTO
     * 
     * @param total
     * @return
     */
    private static boolean aplicaDescuento(double total) {
        return total > MAX_DESCUENTO;
    }

    /**
     * CALCULA EL TOTAL DEL PEDIDO + EL IVA
     * 
     * @param total
     * @return
     */
    private static double totalConIVA(double total) {
        return total + (total * TASA_IVA);
    }

    /**
     * SE ENCARGA DE GUARDAR LOS DATOS EN FICHEROS TXT, CADA PEDIDO SE GUARDA EN UN
     * FICHERO TXT
     * 
     * @param cliente
     * @param totalConIVA
     */
    private static void guardarFacturaEnArchivo(Cliente cliente, double totalConIVA) {
        String nombreArchivo = String.format("pedido_%s.txt", cliente.getId());

        try (FileWriter writer = new FileWriter(nombreArchivo)) {
            writer.write("FACTURA\n");
            writer.write(String.format("Cliente: %s\n", cliente.getNombre()));
            writer.write(String.format("Direccion: %s\n", cliente.getDireccion()));
            writer.write(String.format("Total a pagar: %.2f EUR\n", totalConIVA));
            System.out.println("Archivo guardado correctamente.");

            // MANEJO DE EXCEPCIONES
        } catch (IOException e) {
            System.out.println("ERROR**: No se ha conseguido guardar la factura correctamente.");
            e.printStackTrace();
        }
    }
}