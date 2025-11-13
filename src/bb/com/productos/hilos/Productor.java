package bb.com.productos.hilos;

import bb.com.productos.modelos.Producto;
import bb.com.productos.sincronizacion.Almacen;
import java.util.Random;

/**
 * Clase que simula a un productor (hilo).
 * Genera productos de diferentes tipos y los deposita en el almacén.
 */
public class Productor extends Thread {
    private final String nombre;
    private final Almacen almacen;
    private final int cantidadProductos;
    private final long tiempoMaximoProduccion;
    private static final String[] TIPOS_PRODUCTOS = {
        "Collar Clasico",
        "Collar Moderno",
        "Collar Vintage",
        "Collar Deportivo",
        "Collar Elegante",
        "Collar Casual",
        "Collar Bohemio",
        "Collar Minimalista",
        "Collar Floral",
        "Collar Artesanal"
    };

    public Productor(String nombre, Almacen almacen, int cantidadProductos, long tiempoMaximoProduccion) {
        super("Productor-" + nombre);
        this.nombre = nombre;
        this.almacen = almacen;
        this.cantidadProductos = cantidadProductos;
        this.tiempoMaximoProduccion = tiempoMaximoProduccion;
    }

    @Override
    public void run() {
        Random random = new Random();

        for (int i = 1; i <= cantidadProductos; i++) {
            try {
                // Simular tiempo de producción (aleatorio)
                long tiempoProduccion = random.nextLong(tiempoMaximoProduccion) + 1;

                System.out.println("[PRODUCIENDO] [" + nombre + "] Producto " + i + "/" + cantidadProductos +
                        " (sera listo en " + tiempoProduccion + "ms)...");
                Thread.sleep(tiempoProduccion);

                // Elegir tipo de producto aleatorio
                String tipoProducto = TIPOS_PRODUCTOS[random.nextInt(TIPOS_PRODUCTOS.length)];

                // Crear y depositar el producto
                Producto producto = new Producto(tipoProducto, nombre, tiempoProduccion);
                almacen.depositar(producto);

            } catch (InterruptedException e) {
                System.err.println("[ERROR] [" + nombre + "] Fue interrumpido: " + e.getMessage());
                Thread.currentThread().interrupt();
                break;
            }
        }

        System.out.println("[TERMINADO] [" + nombre + "] Ha terminado de producir todos sus productos.");
    }
}

