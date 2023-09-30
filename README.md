![Bimbonet](https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTdvaS5WJdBE5yYh2DUqWBQom_9B-U0WBrbtcihjYTXQeG_RF9dNmVu3WJFCEJomMmXkA&usqp=CAU)

# Prueba Técnica - BimboNet API


Esta API simula el funcionamiento de las máquinas expendedoras de productos para BimboNet.

## 📐 Arquitectura y Patrones de Diseño

- La aplicación sigue una arquitectura en capas: Modelo, Repositorio, Servicio y Controlador.
- Se han utilizado patrones de diseño como Singleton, Repository Pattern, Service Pattern, DTO y MVC.

## 🛠 Tecnologías y Librerías

<img src="https://miro.medium.com/v2/resize:fit:1000/1*XtjiQD35ja0DcA9H-JuM-g.png" alt="Bimbonet" width="200"/>

- **Spring Boot**: Versión `2.7.16`
- **Java**: JDK `1.8`
- **SpringFox**: Para la integración de Swagger, versión `3.0.0`.
- **H2 Database**: Base de datos en memoria utilizada para el desarrollo.
- **Spring Boot Starters**: Web, Validation, Security, Test, Data JPA.
- **Docker Spotify Maven Plugin**: Versión `1.4.13` para la creación y publicación de imágenes Docker.

## 🚀 Proceso de Despliegue

1. **Configuración de la Base de Datos**:
   
   La aplicación utiliza una base de datos H2 en memoria para el desarrollo. No se necesita configuración adicional para ejecutar la aplicación con esta base de datos.

2. **Construcción y Ejecución**:

   ```bash
   # Navega al directorio del proyecto
   cd path_to_project

   # Construye el proyecto con Maven
   mvn clean install

   # Ejecuta la aplicación
   java -jar target/bimbonet-prueba-tecnica-gil-1.0-SNAPSHOT.jar
   ```
  
Una vez ejecutada, la API estará disponible en `http://localhost:8080/api/bimbo`.

1. **Despliegue con Docker:**

	Puedes construir y desplegar la aplicación utilizando Docker:
   ```bash
	# Construir la imagen Docker (requiere Docker instalado)
	mvn package -Pcompilar-docker

	# Ejecuta la aplicación
	docker run -p 8080:8080 gcr.io/bimbonet-proyecto/bimbonet-prueba-tecnica-gil:1.0-SNAPSHOT

   ```
 
 Tras ejecutar estos comandos, la aplicación estará disponible nuevamente en `http://localhost:8080/api/bimbo`.
 
 ## 📋 Verificación de Requerimientos

Se ha cumplido con todos los requerimientos solicitados en la prueba técnica. A continuación, se presenta una revisión detallada:

1. **La información mínima de una máquina expendedora...**
   - Número de máquina: ✅ (En la entidad `Maquina` como `numeroMaquina`).
   - Lote: ✅ (En la entidad `Maquina`).
   - SKU del producto: ✅ (A través de la relación en `PosicionProducto`).
   - Posición del producto: ✅ (En la entidad `PosicionProducto` como `posicion`).
   - Unidades disponibles en la posición: ✅ (En la entidad `PosicionProducto` como `unidadesDisponibles`).

2. **Los datos indispensables del producto...**
   - Nombre del producto: ✅ (En la entidad `Producto`).
   - SKU: ✅ (En la entidad `Producto`).
   - Precio de venta: ✅ (En la entidad `Producto`).
   - Código de barras: ✅ (En la entidad `Producto`).

3. **Objetivos deseables**:
   - Guardar nuevos productos en el catálogo: ✅ (Endpoint `POST /api/bimbo/producto`).
   - Agregar nuevas máquinas expendedoras: ✅ (Endpoint `POST /api/bimbo/maquina`).
   - Consultar máquinas expendedoras por lote o por identificador: 
     - Por lote: ✅ (Endpoint `GET /api/bimbo/maquinasPorLote/{lote}`).
     - Por identificador: ✅ (Endpoint `GET /api/bimbo/maquina/{id}`).
   - Conocer el valor monetario que almacena una máquina expendedora: ✅ (Endpoint `GET /api/bimbo/maquina/{id}/valor`).
   - Identificar cuál o cuáles son las máquinas que almacenan el mayor valor monetario: ✅ (Endpoint `GET /api/bimbo/maquinasPorValor`).

Además, se agregó una funcionalidad adicional para actualizar el stock de un producto en una máquina con el endpoint `PUT /api/bimbo/maquina/{maquinaId}/producto/{productoId}/actualizarStock`.

# Documentación

Para facilitar la integración y comprensión del funcionamiento de la API, se ha incluido una documentación detallada utilizando Swagger.

Puedes acceder a la interfaz gráfica de la documentación de Swagger en:

- http://localhost:8080/swagger-ui/index.html#/

Adicionalmente, la definición completa de la API en formato OpenAPI 3.0 se encuentra en:

- http://localhost:8080/v3/api-docs

Esta documentación proporciona detalles sobre cada endpoint, los modelos de datos utilizados y ejemplos de respuestas y peticiones.


Comprometido con la calidad y el cumplimiento de los requerimientos, se ha hecho un esfuerzo para asegurarme de que se cumplan todas las expectativas del proyecto.
