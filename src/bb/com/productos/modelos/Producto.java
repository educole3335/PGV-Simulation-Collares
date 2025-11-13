package src.bb.com.productos.modelos;
/**
 * Clase que representa un producto generado por los productores.
 * Cada producto tiene un identificador único, tipo, productor y timestamp.
 */
public class Producto {
    private static int contador = 0;
    private final int id;
    private final String tipo;
    private final String productor;
    private final long tiempoProduccion;
    private final long timestamp;

    public Producto(String tipo, String productor, long tiempoProduccion) {
        this.id = ++contador;
        this.tipo = tipo;
        this.productor = productor;
        this.tiempoProduccion = tiempoProduccion;
        this.timestamp = System.currentTimeMillis();
    }

    public int getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public String getProductor() {
        return productor;
    }

    public long getTiempoProduccion() {
        return tiempoProduccion;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return String.format("Producto{id=%d, tipo='%s', productor='%s', tiempoMs=%d}",
                id, tipo, productor, tiempoProduccion);
    }
}
