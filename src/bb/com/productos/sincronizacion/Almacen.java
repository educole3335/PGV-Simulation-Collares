package bb.com.productos.sincronizacion;

import java.util.LinkedList;
import java.util.Queue;
import bb.com.productos.modelos.Producto;

/**
 * Clase que representa el almacén compartido entre productores y consumidores.
 * Implementa el patrón productor-consumidor con sincronización.
 * - Los productores depositan productos
 * - Los consumidores retiran productos
 * - Tiene capacidad máxima limitada
 */
public class Almacen {
    private final Queue<Producto> productos;
    private final int capacidadMaxima;
    private int productosProducidos = 0;
    private int productosConsumidos = 0;

    public Almacen(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
        this.productos = new LinkedList<>();
    }

    /**
     * Deposita un producto en el almacén.
     * Si está lleno, el productor espera.
     */
    public synchronized void depositar(Producto producto) throws InterruptedException {
        // Esperar si el almacén está lleno
        while (productos.size() >= capacidadMaxima) {
            System.out.println("[ALMACEN LLENO] [" + Thread.currentThread().getName() + "] Esperando espacio...");
            wait();
        }

        // Agregar el producto
        productos.add(producto);
        productosProducidos++;
        System.out.println("[DEPOSITADO] [PRODUCTOR: " + producto.getProductor() + "] " + producto +
                " | Almacen: " + productos.size() + "/" + capacidadMaxima);

        // Notificar a los consumidores que hay un producto disponible
        notifyAll();
    }

    /**
     * Retira un producto del almacén.
     * Si está vacío, el consumidor espera.
     */
    public synchronized Producto retirar(String consumidor) throws InterruptedException {
        // Esperar si el almacén está vacío
        while (productos.isEmpty()) {
            System.out.println("[ALMACEN VACIO] [CONSUMIDOR: " + consumidor + "] Esperando productos...");
            wait();
        }

        // Retirar el producto
        Producto producto = productos.poll();
        productosConsumidos++;
        System.out.println("[RETIRADO] [CONSUMIDOR: " + consumidor + "] " + producto +
                " | Almacen: " + productos.size() + "/" + capacidadMaxima);

        // Notificar a los productores que hay espacio disponible
        notifyAll();

        return producto;
    }

    /**
     * Obtiene el número actual de productos en el almacén.
     */
    public synchronized int obtenerCantidad() {
        return productos.size();
    }

    /**
     * Obtiene la capacidad máxima del almacén.
     */
    public int obtenerCapacidad() {
        return capacidadMaxima;
    }

    /**
     * Obtiene estadísticas del almacén.
     */
    public synchronized void mostrarEstadisticas() {
        System.out.println("\n=== ESTADISTICAS DEL ALMACEN ===");
        System.out.println("Productos producidos: " + productosProducidos);
        System.out.println("Productos consumidos: " + productosConsumidos);
        System.out.println("Productos en almacen: " + productos.size());
        System.out.println("Capacidad maxima: " + capacidadMaxima);
        System.out.println("=====================================\n");
    }
}
