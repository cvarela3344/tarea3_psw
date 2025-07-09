# Test Driven Development Proof

Este documento deja evidencia del proceso **TDD** utilizado para desarrollar la **Tarea 3** del curso, correspondiente a la implementación de un sistema de fidelidad gamificada.

Para cada uno de los siguientes módulos:

- Gestión de clientes  
- Gestión de compras  
- Niveles de fidelidad  
- Menú CLI  

En este documento se muestra el ciclo Red, Green, Refactor, además de los resultados obtenidos en cobertura de pruebas y diagramas UML de apoyo.

---

## Gestión de Clientes

### 1. Agregar cliente

**Atributos requeridos:**

- `id` (String)  
- `nombre` (String)  
- `correo` (String)

**Valores iniciales por defecto:**

- `puntos = 0`  
- `nivel = Bronce`

**Validación:**  
El correo debe contener el carácter `@`. En caso contrario, se lanza una excepción.

**Red**  
![](fotos/gestion_clientes/agregar_usuario/red.png)

**Green**  
![](fotos/gestion_clientes/agregar_usuario/green.png)

**Refactor**  
No es necesario.

---

### 2. Listar clientes

Devuelve todos los clientes almacenados en memoria. Se espera que estén en una colección interna del repositorio o sistema.

**Red**  
![](fotos/gestion_clientes/listar_clientes/red.png)

**Green**  
![](fotos/gestion_clientes/listar_clientes/green.png)

**Refactor**  
Futuro refactor podría mover la lógica de almacenamiento a un repositorio de cliente.

---

### 3. Actualizar cliente

Permite modificar el nombre y/o correo de un cliente existente por ID.

**Validación:** El correo debe ser válido nuevamente.

**Red**  
![](fotos/gestion_clientes/actualizar_cliente/red.png)

**Green**  
![](fotos/gestion_clientes/actualizar_cliente/green.png)

**Refactor**  
Nada.

---

### 4. Eliminar cliente

Elimina un cliente por ID. Si el cliente no existe, puede lanzar una excepción o retornar un valor que lo indique.

**Red**  
![](fotos/gestion_clientes/eliminar_cliente/red.png)

**Green**  
![](fotos/gestion_clientes/eliminar_cliente/green.png)

**Refactor**  
Nada.

---

### 5. Obtener cliente por ID (implícito)

Aunque no se menciona directamente, es necesario para actualizar, eliminar o registrar compras.

**Red**  
![](fotos/gestion_clientes/buscar_clienteid/red.png)

**Green**  
![](fotos/gestion_clientes/buscar_clienteid/green.png)

**Refactor**  
Nada.

---

**Refactor general:**  
Se realiza un refactor para mover la lógica de almacenamiento a `ClienteRepository`.

![](fotos/gestion_clientes/refactor.png)

---

## Gestión de Compras

### 1. Registrar una compra con puntos base

**Atributos:**

- `idCompra`: identificador único de la compra  
- `idCliente`: ID del cliente asociado  
- `monto`: monto total de la compra  
- `fecha`: fecha de la compra  

**Regla de puntos:**  
Cada $100 genera 1 punto (redondeo hacia abajo).

**Red**  
![](fotos/gestion_compras/registrar_compra/red.png)

**Green**  
![](fotos/gestion_compras/registrar_compra/green.png)

**Refactor**  
Se planifica mover la lógica de almacenamiento a `CompraRepository`.

---

### 2. Aplicar multiplicador por nivel

| Nivel   | Multiplicador |
| ------- | ------------- |
| Bronce  | ×1            |
| Plata   | ×1.2          |
| Oro     | ×1.5          |
| Platino | ×2            |

**Red**  
![](fotos/gestion_compras/aplicar_multo/red.png)

**Green**  
![](fotos/gestion_compras/aplicar_multo/green.png)

**Refactor**  
Se implementa el repositorio de compras.  
![](fotos/gestion_compras/aplicar_multo/refac.png) 

---

### 3. Bono por compras múltiples en el mismo día

Si un cliente realiza **3 compras el mismo día**, recibe un bono adicional de **+10 puntos**.  
Esta bonificación se reinicia cada día.

**Red**  
![](fotos/gestion_compras/bono3compras/red.png)

**Green**  
![](fotos/gestion_compras/bono3compras/green.png)

**Refactor**  
Nada.

---

### 4. Actualización del nivel de fidelidad

| Nuevo nivel | Puntos acumulados |
| ----------- | ----------------- |
| Bronce      | 0 – 499           |
| Plata       | 500 – 1499        |
| Oro         | 1500 – 2999       |
| Platino     | 3000+             |

Este comportamiento fue implementado junto con el multiplicador.

---

### 5. CRUD de compras

- Agregar compra  
- Listar compras  
- Buscar compra por ID  
- Eliminar compra (opcional, pero mencionado como "CRUD")

**Red**  
![](fotos/gestion_compras/crud/red.png)

**Green**  
![](fotos/gestion_compras/crud/green.png)

**Refactor**  
Nada.

---

## Menú CLI

La interacción se implementó mediante un menú de texto simple por consola.

---

## SonarQube Coverage

![](fotos/coverage.png)

![](fotos/uncovered.png)

He medido cobertura estructural del código (líneas, instrucciones y ramas) mediante JaCoCo para instrumentación, y SonarQube para análisis avanzado y depuración de cobertura irrelevante. Esto permitió validar rigurosamente la aplicación del ciclo TDD y mejorar la calidad del producto entregado.

Al inicio, usando únicamente **JaCoCo**, se obtuvo un **~46%** de cobertura.  
Al incorporar **SonarQube**, la cobertura subió a **82%**.

Esto se puede deber a que SonarQube excluye automáticamente clases como `Main`, `Menu`, y posiblemente líneas no ejecutables, lo que mejora la cobertura efectiva.

> El 82% de cobertura se considera bueno, aunque no se probaron explícitamente algunos `setters` ni todos los cambios de nivel posibles.


---

## Diagramas UML

### Diagrama de Clases (Java)
![](fotos/diagramaclases.png)

### Diagrama de Secuencia (Registrar Compra)
![](fotos/diagramasecuencia.png)
