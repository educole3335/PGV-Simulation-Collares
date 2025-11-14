import bb.com.productos.sincronizacion.Almacen;
import bb.com.productos.hilos.Productor;
import bb.com.productos.hilos.Consumidor;

public class AppExperimentoTres {
    public static void main(String[] args) throws Exception {
        System.out.println("\n===== EXPERIMENTO TRES: PRIORIDADES ALTAS PARA PRODUCTORES =====\n");

        int capacidadAlmacen = 5;
        long tiempoMaximoProduccion = 1000; // ms
        long tiempoMaximoConsumo = 1000;    // ms

        Almacen almacen = new Almacen(capacidadAlmacen);

        System.out.println("Configuracion:");
        System.out.println(" - Capacidad: " + capacidadAlmacen);
        System.out.println(" - Tiempo produccion max: " + tiempoMaximoProduccion + "ms");
        System.out.println(" - Tiempo consumo max: " + tiempoMaximoConsumo + "ms\n");

        Productor productorUnico = new Productor("ProdUnico", almacen, 5, tiempoMaximoProduccion);
        Consumidor consumidorUnico = new Consumidor("ConsUnico", almacen, 5, tiempoMaximoConsumo);

        // Subir prioridad del productor
        productorUnico.setPriority(Thread.MAX_PRIORITY);
        consumidorUnico.setPriority(Thread.NORM_PRIORITY);

        productorUnico.start();
        consumidorUnico.start();

        productorUnico.join();
        consumidorUnico.join();

        almacen.mostrarEstadisticas();
        System.out.println("FIN EXPERIMENTO TRES\n");
    }
}
