public class Cliente {

    // ATRIBUTOS
    private String nombre;
    private String id;
    private String direccion;

    // CONSTRUCTOR
    public Cliente(String nombre, String id, String direccion) {
        this.nombre = nombre;
        this.id = id;
        this.direccion = direccion;
    }

    // ============= PONEMOS LOS GETTERS Y SETTERS ====================
    public String getNombre() {
        return nombre;
    }

    public String getId() {
        return id;
    }

    public String getDireccion() {
        return direccion;
    }
}
