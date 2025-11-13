package bb.com.productos.hilos;

import java.util.Random;
import bb.com.productos.modelos.Producto;
import bb.com.productos.sincronizacion.Almacen;

/**
 * Clase que simula a un consumidor (hilo).
 * Retira productos del almacén y simula su consumo.
 */
public class Consumidor extends Thread {
    private final String nombre;
    private final Almacen almacen;
    private final int cantidadProductos;
    private final long tiempoMaximoConsumo;

    public Consumidor(String nombre, Almacen almacen, int cantidadProductos, long tiempoMaximoConsumo) {
        super("Consumidor-" + nombre);
        this.nombre = nombre;
        this.almacen = almacen;
        this.cantidadProductos = cantidadProductos;
        this.tiempoMaximoConsumo = tiempoMaximoConsumo;
    }

    @Override
    public void run() {
        Random random = new Random();

        for (int i = 1; i <= cantidadProductos; i++) {
            try {
                // Retirar producto del almacén
                Producto producto = almacen.retirar(nombre);

                // Simular tiempo de consumo (aleatorio)
                long tiempoConsumo = random.nextLong(tiempoMaximoConsumo) + 1;

                System.out.println("[CONSUMIENDO] [" + nombre + "] Producto " + i + "/" + cantidadProductos +
                        " (tardara " + tiempoConsumo + "ms): " + producto.getTipo());
                Thread.sleep(tiempoConsumo);

                System.out.println("[CONSUMIDO] [" + nombre + "] Finalizo consumo del producto #" + producto.getId());

            } catch (InterruptedException e) {
                System.err.println("[ERROR] [" + nombre + "] Fue interrumpido: " + e.getMessage());
                Thread.currentThread().interrupt();
                break;
            }
        }

        System.out.println("[TERMINADO] [" + nombre + "] Ha terminado de consumir todos sus productos.");
    }
}
