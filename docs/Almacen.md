# Almacén (Recurso Compartido)

## Descripción

La clase `Almacen` representa el **recurso compartido** en el patrón productor-consumidor. Es un almacén de collares con capacidad máxima limitada que gestiona la sincronización entre productores y consumidores.

## Ubicación

`src/bb/com/productos/sincronizacion/Almacen.java`

## Responsabilidades

- **Almacenamiento:** Mantiene una cola FIFO (`LinkedList`) de productos
- **Sincronización:** Implementa métodos `synchronized` para evitar condiciones de carrera
- **Control de capacidad:**
  - Productores esperan si el almacén está lleno
  - Consumidores esperan si el almacén está vacío
- **Notificación:** Usa `wait()` y `notifyAll()` para coordinar hilos

## Métodos Principales

### `depositar(Producto producto)`

- Los **productores** llaman a este método para agregar productos
- Si el almacén está lleno, el productor se pone en espera
- Notifica a consumidores cuando hay nuevo producto disponible

### `retirar(String consumidor)`

- Los **consumidores** llaman a este método para obtener productos
- Si el almacén está vacío, el consumidor se pone en espera
- Notifica a productores cuando hay espacio disponible
- Retorna el producto retirado

### `mostrarEstadisticas()`

- Muestra el total de productos producidos, consumidos y el estado actual del almacén

## Ejemplo de Uso

```java
Almacen almacen = new Almacen(5); // Capacidad máxima: 5 productos
Producto p = new Producto("Collar Clásico", "Productor-A", 500);
almacen.depositar(p);
Producto retirado = almacen.retirar("Consumidor-A");
```

## Parámetros

- **capacidadMaxima** (int): Número máximo de productos que puede contener simultáneamente

## Atributos

- `Queue<Producto> productos`: Cola de productos
- `int capacidadMaxima`: Límite de capacidad
- `int productosProducidos`: Contador total de productos depositados
- `int productosConsumidos`: Contador total de productos retirados
