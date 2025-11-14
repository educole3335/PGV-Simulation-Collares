import bb.com.productos.sincronizacion.Almacen;
import bb.com.productos.hilos.Productor;
import bb.com.productos.hilos.Consumidor;

public class AppExperimentoDos {
    public static void main(String[] args) throws Exception {
        System.out.println("\n===== EXPERIMENTO DOS: PRODUCCION MAS RAPIDA QUE CONSUMO =====\n");

        int capacidadAlmacen = 5;
        long tiempoMaximoProduccion = 200; // ms (producto rapido)
        long tiempoMaximoConsumo = 1000;   // ms (consumo mas lento)

        Almacen almacen = new Almacen(capacidadAlmacen);

        System.out.println("Configuracion:");
        System.out.println(" - Capacidad: " + capacidadAlmacen);
        System.out.println(" - Tiempo produccion max: " + tiempoMaximoProduccion + "ms");
        System.out.println(" - Tiempo consumo max: " + tiempoMaximoConsumo + "ms\n");

        Productor productorUnico = new Productor("ProdUnico", almacen, 5, tiempoMaximoProduccion);
        Consumidor consumidorUnico = new Consumidor("ConsUnico", almacen, 5, tiempoMaximoConsumo);

        productorUnico.start();
        consumidorUnico.start();

        productorUnico.join();
        consumidorUnico.join();

        almacen.mostrarEstadisticas();
        System.out.println("FIN EXPERIMENTO DOS\n");
    }
}
