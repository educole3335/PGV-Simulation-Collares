import bb.com.productos.sincronizacion.Almacen;
import bb.com.productos.hilos.Productor;
import bb.com.productos.hilos.Consumidor;

public class AppExperimentoUno {
    public static void main(String[] args) throws Exception {
        System.out.println("\n===== SIMULADOR DE PRODUCCION Y CONSUMO DE COLLARES =====\n");

        // Configuración del sistema (experimento: un productor y un consumidor)
        int capacidadAlmacen = 5;
        long tiempoMaximoProduccion = 1000; // ms
        long tiempoMaximoConsumo = 1000; // ms

        // Crear el almacén compartido
        Almacen almacen = new Almacen(capacidadAlmacen);

        System.out.println("Configuración inicial:");
        System.out.println("   - Capacidad del almacén: " + capacidadAlmacen + " productos");
        System.out.println("   - Tiempo maximo de producción: " + tiempoMaximoProduccion + "ms");
        System.out.println("   - Tiempo maximo de consumo: " + tiempoMaximoConsumo + "ms\n");

        // Crear un único productor y un único consumidor (experimento-uno)
        Productor productorUnico = new Productor("ProdUnico", almacen, 5, tiempoMaximoProduccion);
        Consumidor consumidorUnico = new Consumidor("ConsUnico", almacen, 5, tiempoMaximoConsumo);

        System.out.println("Participantes:");
        System.out.println("   Productores:");
        System.out.println("     - ProdUnico: 5 productos");
        System.out.println("   Consumidores:");
        System.out.println("     - ConsUnico: 5 productos");
        System.out.println("\nIniciando simulación...\n");

        // Iniciar todos los hilos
        productorUnico.start();
        consumidorUnico.start();

        // Esperar a que todos los hilos terminen
        productorUnico.join();
        consumidorUnico.join();

        // Mostrar estadísticas finales
        almacen.mostrarEstadisticas();
        System.out.println("SIMULACION COMPLETADA EXITOSAMENTE");
        System.out.println("Los productos producidos coinciden con los consumidos.\n");
    }
}
