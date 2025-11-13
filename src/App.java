import bb.com.productos.sincronizacion.Almacen;
import bb.com.productos.hilos.Productor;
import bb.com.productos.hilos.Consumidor;

/**
 * Aplicación principal del simulador de productor-consumidor.
 * Simula la producción y consumo de collares en un almacén compartido.
 * 
 * Escenario:
 * - 2 Productores (Ñesi y El Pichu) que producen 10 productos cada uno
 * - 3 Consumidores (Cesar, Mr. Gentleman(Adrian Te quiero), Noelia)
 * - Almacén con capacidad máxima de 5 productos
 */
public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("\n===== SIMULADOR DE PRODUCCION Y CONSUMO DE COLLARES =====\n");

        // Configuración del sistema
        int capacidadAlmacen = 5;
        long tiempoMaximoProduccion = 2000; // ms (máximo 2 segundos)
        long tiempoMaximoConsumo = 3000;    // ms (máximo 3 segundos)

        // Crear el almacén compartido
        Almacen almacen = new Almacen(capacidadAlmacen);

        System.out.println("Configuración inicial:");
        System.out.println("   - Capacidad del almacén: " + capacidadAlmacen + " productos");
        System.out.println("   - Tiempo maximo de producción: " + tiempoMaximoProduccion + "ms");
        System.out.println("   - Tiempo maximo de consumo: " + tiempoMaximoConsumo + "ms\n");

        // Crear productores
        Productor ñesi = new Productor("Ñesi", almacen, 10, tiempoMaximoProduccion);
        Productor elPichu = new Productor("El Pichu", almacen, 10, tiempoMaximoProduccion);

        // Crear consumidores
        Consumidor cesar = new Consumidor("Cesar", almacen, 5, tiempoMaximoConsumo);
        Consumidor mrGentleman = new Consumidor("Mr. Gentleman(Adrian Te quiero)", almacen, 10, tiempoMaximoConsumo);
        Consumidor noelia = new Consumidor("Noelia", almacen, 5, tiempoMaximoConsumo);

        System.out.println("Participantes:");
        System.out.println("   Productores:");
        System.out.println("     - Ñesi: 10 productos");
        System.out.println("     - El Pichu: 10 productos");
        System.out.println("   Consumidores:");
        System.out.println("     - Cesar: 5 productos");
        System.out.println("     - Mr. Gentleman(Adrian Te quiero): 10 productos");
        System.out.println("     - Noelia: 5 productos");
        System.out.println("\nIniciando simulación...\n");

        // Iniciar todos los hilos
        ñesi.start();
        elPichu.start();
        cesar.start();
        mrGentleman.start();
        noelia.start();

        // Esperar a que todos los hilos terminen
        ñesi.join();
        elPichu.join();
        cesar.join();
        mrGentleman.join();
        noelia.join();

        // Mostrar estadísticas finales
        almacen.mostrarEstadisticas();
        System.out.println("SIMULACION COMPLETADA EXITOSAMENTE");
        System.out.println("Los productos producidos (20) coinciden con los consumidos (20).\n");
    }
}

