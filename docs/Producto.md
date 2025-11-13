# Producto (Modelo de Dato)

## Descripción

La clase `Producto` representa un **collar** (producto) que es fabricado por productores y consumido por consumidores. Es el objeto que circula entre el almacén y los hilos.

## Ubicación

`src/bb/com/productos/modelos/Producto.java`

## Responsabilidades

- **Identidad:** Proporciona un ID único a cada collar producido
- **Trazabilidad:** Registra información sobre su origen y fabricación
- **Representación:** Oferece una representación clara en logs

## Atributos

- `int id`: Identificador único auto-incrementado
- `String tipo`: Tipo de collar (ej. "Collar Clásico", "Collar Moderno", etc.)
- `String productor`: Nombre del productor que fabricó este collar
- `long tiempoProduccion`: Tiempo (en ms) que tomó su fabricación
- `long timestamp`: Marca de tiempo cuando fue creado

## Atributo de Clase

- `static int contador`: Contador global para generar IDs únicos (se incrementa con cada instancia)

## Constructor

```java
public Producto(String tipo, String productor, long tiempoProduccion)
```

- Genera automáticamente un ID único
- Registra la marca de tiempo de creación

## Métodos Getter

- `getId()`: Retorna el ID único del producto
- `getTipo()`: Retorna el tipo de collar
- `getProductor()`: Retorna el nombre del productor
- `getTiempoProduccion()`: Retorna el tiempo de fabricación en ms
- `getTimestamp()`: Retorna la marca de tiempo de creación

## Método toString()

Proporciona una representación legible:

```
Producto{id=1, tipo='Collar Clásico', productor='ProdUnico', tiempoMs=523}
```

## Ejemplo de Uso

```java
Producto collar = new Producto("Collar Bohemio", "Productor-A", 1234);
System.out.println(collar.getId());         // 1
System.out.println(collar.getTipo());       // Collar Bohemio
System.out.println(collar.getProductor());  // Productor-A
System.out.println(collar);                 // Representación completa
```

## Importancia en el Patrón

- Cada `Producto` es **inmutable** (atributos final) garantizando thread-safety
- El contador estático permite rastrear cuántos productos se han creado
- La trazabilidad (productor + timestamp) facilita debugging y análisis
