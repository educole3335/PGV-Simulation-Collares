# PGV-Simulation-Collares

## Descripción General
Este proyecto simula un sistema productor-consumidor aplicado a una **empresa de fabricación de collares**. El simulador demuestra cómo múltiples productores y consumidores interactúan de forma sincronizada a través de un almacén compartido con capacidad limitada.

---

## 🏭 Empresa: Joyería y Accesorios "Collares Artesanales"

### Tipo de Empresa
Pequeña empresa manufacturera especializada en la **fabricación artesanal de collares** con énfasis en diseño personalizado y calidad.

### Productos
Fabricamos **collares** en diversas categorías estéticas:
- Collar Clásico
- Collar Moderno
- Collar Vintage
- Collar Deportivo
- Collar Elegante
- Collar Casual
- Collar Bohemio
- Collar Minimalista
- Collar Floral
- Collar Artesanal

---

## Roles del Sistema

### Productor
**¿Quién?** Obreros/artesanos de la fábrica
- **Responsabilidad:** Fabricar collares de forma continua
- **Acciones:**
  1. Produce collares de tipos aleatorios
  2. Simula tiempo de fabricación (entre 1ms y el máximo configurado)
  3. Deposita cada collar en el almacén compartido
  4. Si el almacén está lleno, espera hasta haber espacio

- **Características:**
  - Puede haber múltiples productores trabajando simultáneamente
  - Cada productor genera una cantidad predefinida de collares
  - Son independientes pero comparten el mismo almacén

### Consumidor
**¿Quién?** Vendedores/distribuidores o proceso de calidad
- **Responsabilidad:** Retirar collares del almacén y procesarlos
- **Acciones:**
  1. Retira un collar del almacén
  2. Simula tiempo de procesamiento (almacenamiento, calidad, envío)
  3. Si el almacén está vacío, espera hasta haya productos disponibles

- **Características:**
  - Puede haber múltiples consumidores retirando simultáneamente
  - Cada consumidor procesa una cantidad predefinida de collares
  - Velocidad de consumo puede variar según el experimento

### Almacén (Recurso Compartido)
**¿Qué?** Espacio de almacenamiento con capacidad limitada
- **Responsabilidad:** Gestionar el buffer entre productores y consumidores
- **Características:**
  - Capacidad máxima configurable (ej. 5 collares simultáneamente)
  - Implementa sincronización con `synchronized`, `wait()` y `notifyAll()`
  - Evita condiciones de carrera
  - Registra estadísticas: productos producidos, consumidos y actuales

- **Comportamiento:**
  - **Almacén lleno:** Productores esperan sin poder depositar
  - **Almacén vacío:** Consumidores esperan sin poder retirar

---

##  Interacciones

```
Productor 1 ─┐
Productor 2 ─┼─→ [Almacén Compartido] ←─┬─ Consumidor 1
Productor 3 ─┘    (Capacidad: 5)         └─ Consumidor 2
```

**Sincronización:** Cuando hay espacio en el almacén y productos listos, los productores depositan; cuando hay productos en el almacén, los consumidores retiran. El `Almacén` usa locks e se notificaciones mutuas.

---

## Estructura del Proyecto

```
src/
├── App.java                                  # Aplicación principal
├── AppExperimentoUno.java                    # Experimento 1: Básico
├── AppExperimentoDos.java                    # Experimento 2: Producción rápida
├── AppExperimentoTres.java                   # Experimento 3: Prioridades
└── bb/com/productos/
    ├── hilos/
    │   ├── Productor.java                   # Hilo productor
    │   └── Consumidor.java                  # Hilo consumidor
    ├── modelos/
    │   └── Producto.java                    # Modelo: collar
    └── sincronizacion/
        └── Almacen.java                     # Recurso compartido sincronizado

docs/
├── Almacen.md                                # Documentación del almacén
├── Productor.md                              # Documentación del productor
├── Consumidor.md                             # Documentación del consumidor
└── Producto.md                               # Documentación del modelo
```

---

##  Experimentos

Este proyecto incluye tres experimentos que demuestran diferentes aspectos de la concurrencia:

### Experimento-Uno
- **Rama:** `experimento-uno`
- **Objetivo:** Caso base con un productor y un consumidor
- **Parámetros:** Tiempos iguales de producción y consumo
- **Resultado esperado:** Ejecución equilibrada, pocos bloqueos
![alt text](image.png)

### Experimento-Dos
- **Rama:** `experimento-dos`
- **Objetivo:** Producción más rápida que consumo
- **Parámetros:** Tiempo producción << Tiempo consumo
- **Resultado esperado:** Almacén se llena rápidamente, productor se bloquea
![alt text](image-1.png)
### Experimento-Tres
- **Rama:** `experimento-tres`
- **Objetivo:** Impacto de prioridades de hilos
- **Parámetros:** Productor con `Thread.MAX_PRIORITY`, consumidor con `Thread.NORM_PRIORITY`
- **Resultado esperado:** Productor ejecuta con mayor frecuencia
![alt text](image-3.png)
---
### Ejecutar Aplicación Principal
```bash
java -cp bin App
```

### Ejecutar Experimentos
```bash
java -cp bin AppExperimentoUno
java -cp bin AppExperimentoDos
java -cp bin AppExperimentoTres
```