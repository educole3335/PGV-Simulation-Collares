# Consumidor (Hilo Consumidor)

## Descripción

La clase `Consumidor` representa un **hilo consumidor** en el patrón productor-consumidor. Simula el consumo/uso de collares que retira del almacén compartido.

## Ubicación

`src/bb/com/productos/hilos/Consumidor.java`

## Responsabilidades

- **Consumo simulado:** Retira productos del almacén compartido
- **Control de tiempo:** Simula tiempo de consumo/uso variable
- **Sincronización:** Interactúa con el `Almacén` de forma sincronizada
- **Finalización controlada:** Consume un número específico de items

## Atributos

- `String nombre`: Identificador del consumidor
- `Almacen almacen`: Referencia al almacén compartido
- `int cantidadProductos`: Número total de productos a consumir
- `long tiempoMaximoConsumo`: Tiempo máximo en ms para simular el consumo

## Flujo de Ejecución (run())

1. **Retirada:** Llamada a `almacen.retirar()` (puede bloquearse si almacén está vacío)
2. **Consumo simulado:** Simula uso del producto con `Thread.sleep(tiempoConsumo)`
3. **Logueo:** Registra el tipo de collar consumido
4. **Repetición:** Repite hasta consumir todos los productos solicitados

## Ejemplo de Uso

```java
Almacen almacen = new Almacen(5);
Consumidor consumidor = new Consumidor("Cliente-A", almacen, 10, 3000);
consumidor.start(); // Inicia el hilo
consumidor.join();  // Espera a que termine
```

## Constructor

```java
public Consumidor(String nombre, Almacen almacen, int cantidadProductos, long tiempoMaximoConsumo)
```

## Comportamiento en Concurrencia

- Si el almacén está **vacío**, el consumidor se bloquea en `retirar()`
- Continúa cuando el productor deposita productos y se notifica
- Genera logs informativos sobre su progreso
- Muestra el tipo de collar que está consumiendo

## Logs Generados

- `[CONSUMIENDO]`: Inicia el consumo de un producto
- `[CONSUMIDO]`: Finaliza el consumo de un producto
- `[ALMACEN VACIO]`: Espera productos cuando el almacén está vacío
- `[TERMINADO]`: Ha completado el consumo de todos sus items
