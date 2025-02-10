Microservicios de Gestión de Productos, Ventas y Carritos

Este proyecto es una aplicación basada en microservicios que permite gestionar productos, ventas y carritos de compras de 
una supuesta tienda de Electrodomesticos. Está construido con Spring Boot, Spring Cloud Gateway, Eureka para el descubrimiento 
de servicios, y Docker para la contenerización.

Estructura del Proyecto

El sistema está compuesto por los siguientes microservicios:

- API Gateway: Sirve como punto de entrada único para todas las solicitudes y enruta las peticiones a los microservicios correspondientes.

- Eureka Server: Servicio de descubrimiento que permite a los microservicios registrarse y descubrirse entre sí.

- Product Service: Gestiona la información de los productos (crear, leer, actualizar, eliminar).

- Cart Service: Gestiona los carritos de compras (agregar productos, eliminar productos, calcular totales).

- Sale Service: Gestiona las ventas y pedidos realizados por los usuarios.

Tecnologías Utilizadas

- Spring Boot: Framework para construir aplicaciones Java.

- Spring Cloud Gateway: Para el enrutamiento y gestión de solicitudes.

- Eureka: Para el descubrimiento de servicios.

- Docker: Para la contenerización de los microservicios.

- Maven: Para la gestión de dependencias y construcción del proyecto.

Requisitos Previos

Antes de ejecutar la aplicación, asegúrate de tener instalado lo siguiente:

- Java 17 o superior.

- Docker y Docker Compose.

- Maven (opcional, si deseas construir el proyecto manualmente).

Configuración y Ejecución
1. Clonar el Repositorio
Primero, clona el repositorio en tu máquina local:

git clone https://github.com/matiasgiache/ElectroMicroS.git

2. Construir los Microservicios
Cada microservicio tiene su propio Dockerfile. Para construir las imágenes Docker, ejecuta los siguientes comandos en cada carpeta del microservicio:

cd eureka-server
docker build -t eureka-sv .

cd ../product-service
docker build -t product-service .

cd ../cart-service
docker build -t cart-service .

cd ../order-service
docker build -t sale-service .

cd ../api-gateway
docker build -t api-gateway-electro .

3. Ejecutar con Docker Compose
En la raíz del proyecto, ejecuta el siguiente comando para levantar todos los servicios:

docker-compose up

Esto levantará los siguientes contenedores:

Eureka Server: Disponible en http://localhost:8761.

API Gateway: Disponible en http://localhost:433.

Product Service: Registrado en Eureka como product-service.

Cart Service: Registrado en Eureka como cart-service.

Order Service: Registrado en Eureka como sale-service.

4. Acceder a los Servicios

Una vez que todos los servicios estén en funcionamiento, puedes acceder a ellos a través del API Gateway:

Productos: http://localhost:443/product/

Carritos: http://localhost:443/cart/

Ventas: http://localhost:8443080/sale/

También puedes ver los servicios registrados en el panel de Eureka: http://localhost:8761.

Endpoints de los Microservicios

- Product Service

GET /product/get: Obtener todos los productos.

GET /product/{id}: Obtener un producto por su ID.

GET /product/get/name/{nombre de producto}: Obtener un producto por su nombre.

POST /product/save: Crear un nuevo producto.

PUT /product/edit/{id}: Actualizar un producto existente.

DELETE /product/delete/{id}: Eliminar un producto.

- Cart Service

GET /cart/get: Obtener todos los carritos.

GET /cart/getFull/{cart_id}: Obtener el carrito y su lista de productos con detalles segun ID.

GET /cart/get/{cart_id}: Obtener el carrito simplificado segun ID.

GET /cart/get/products/{cart_id}: Obtener la lista de productos de un carrito.

POST /cart/create: Crear un nuevo carrito.

PUT /cart/put/{cart_id}/{nombre del producto}: Agregar producto al carrito.

PUT /cart/remove/{cart_id}/{nombre del producto}: Eliminar producto del carrito.

DELETE /cart/delete/{cart_id}: Eliminar carrito}.

- Order Service

GET /sale/getAlee: Obtener todas las ventas.

GET /sale/get/{id}: Obtener una venta por su ID.

POST /sale/create: Crear una nueva venta.

DELETE /sale/delete/{sale_id}: Eliminar venta.

Ejemplos de Uso

Crear un Producto

curl -X POST http://localhost:443/product/create \
  -H "Content-Type: application/json" \
  -d '{
        "name": "Laptop",
        "price": 1200.00,
        "code": "LT-452",
        "brand": "Lenovo"
      }'
Agregar un Producto al Carrito

curl -X POST http://localhost:443/cart/put/1/Laptop \
  -H "Content-Type: application/json" \
  
Realizar una Venta

curl -X POST http://localhost:8080/sale/create \
  -H "Content-Type: application/json" \
  -d '{
        "cart_id": 1
      }'




Licencia
Este proyecto está bajo la licencia MIT.

Notas Adicionales
Asegúrate de que los puertos utilizados no estén en conflicto con otras aplicaciones en tu máquina.

Si deseas agregar más funcionalidades, como autenticación con Spring Security, puedes expandir este proyecto.
