# Productor (Hilo Productor)

## Descripción

La clase `Productor` representa un **hilo productor** en el patrón productor-consumidor. Simula la producción de collares que deposita en un almacén compartido.

## Ubicación

`src/bb/com/productos/hilos/Productor.java`

## Responsabilidades

- **Producción simulada:** Genera produtos aleatorios de diferentes tipos
- **Control de tiempo:** Simula tiempo de fabricación variable
- **Sincronización:** Interactúa con el `Almacén` de forma sincronizada
- **Finalización controlada:** Produce un número específico de items

## Atributos

- `String nombre`: Identificador del productor
- `Almacen almacen`: Referencia al almacén compartido
- `int cantidadProductos`: Número total de productos a producir
- `long tiempoMaximoProduccion`: Tiempo máximo en ms para simular la producción
- `String[] TIPOS_PRODUCTOS`: Array con 10 tipos diferentes de collares

## Tipos de Collares Disponibles

1. Collar Clásico
2. Collar Moderno
3. Collar Vintage
4. Collar Deportivo
5. Collar Elegante
6. Collar Casual
7. Collar Bohemio
8. Collar Minimalista
9. Collar Floral
10. Collar Artesanal

## Flujo de Ejecución (run())

1. **Producción:** Simula fabricación con `Thread.sleep(tiempoProduccion)`
2. **Selección tipo:** Elige aleatoriamente un tipo de collar
3. **Creación:** Instancia un nuevo `Producto`
4. **Depósito:** Llamada a `almacen.depositar()` (puede bloquearse si almacén está lleno)
5. **Repetición:** Repite hasta fabricar todos los productos solicitados

## Ejemplo de Uso

```java
Almacen almacen = new Almacen(5);
Productor productor = new Productor("Fabricante-A", almacen, 10, 2000);
productor.start(); // Inicia el hilo
productor.join();  // Espera a que termine
```

## Constructor

```java
public Productor(String nombre, Almacen almacen, int cantidadProductos, long tiempoMaximoProduccion)
```

## Comportamiento en Concurrencia

- Si el almacén está **lleno**, el productor se bloquea en `depositar()`
- Continúa cuando el consumidor retira productos y se notifica
- Genera logs informativos sobre su progreso
