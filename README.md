# Aplicación de Restaurante con Base de Datos

Esta aplicación permite gestionar clientes, números de teléfono, reservas y órdenes de un restaurante mediante una interfaz de consola, utilizando una base de datos MySQL.

## Estructura del Proyecto

El proyecto está organizado en los siguientes paquetes:

- `conexion`: Contiene la clase `Conexion` que gestiona la conexión a la base de datos.
- `dao`: Contiene las clases `ClienteDAO`, `NumeroTelefonoDAO`, `ReservaDAO`, `ReservaClienteDAO` y `OrdenDAO` que implementan las operaciones CRUD para las tablas Cliente, Numero_Telefono, Reserva, Reserva_Cliente y Orden respectivamente.
- `modelo`: Contiene las clases `Cliente`, `NumeroTelefono`, `Reserva`, `ReservaCliente` y `Orden` que representan las entidades en la base de datos.
- `vista`: Contiene las clases `ClienteConsola`, `NumeroTelefonoConsola`, `ReservaConsola`, `ReservaClienteConsola` y `OrdenConsola` que proporcionan interfaces de consola para interactuar con las tablas correspondientes.
- `Restaurant`: Contiene la clase `MySQL` que proporciona métodos para conectarse a la base de datos.

## Configuración de la Base de Datos

1. Asegúrate de tener MySQL instalado y en ejecución.
2. Crea una base de datos llamada `restaurante`.
3. Ejecuta el script SQL ubicado en `sql/create_tables.sql` para crear todas las tablas necesarias.

```sql
CREATE TABLE Cliente(
    dni INT PRIMARY KEY,
    nombre VARCHAR(30),
    fecha_nacimiento DATE
);

CREATE TABLE Numero_Telefono (
    id INT PRIMARY KEY,
    numero BIGINT,
    prefijo VARCHAR(40),
    tipo_uso VARCHAR(40)
);

CREATE TABLE Reserva (
    id INT PRIMARY KEY,
    num_asientos INT,
    num_mesas INT,
    fecha_reserva DATETIME,
    num_acompañantes INT
);

CREATE TABLE Reserva_Cliente (
    id INT PRIMARY KEY,
    FK_dni_cliente INT,
    FK_reserva INT,
    FOREIGN KEY (FK_dni_cliente) REFERENCES Cliente(dni),
    FOREIGN KEY (FK_reserva) REFERENCES Reserva(id)
);

CREATE TABLE Orden (
    id INT PRIMARY KEY,
    fecha_orden DATETIME,
    FK_dni_cliente INT,
    FOREIGN KEY (FK_dni_cliente) REFERENCES Cliente(dni)
);
```

4. Verifica la configuración de conexión en la clase `MySQL.java`:
   - URL: `jdbc:mysql://localhost:3306/restaurante`
   - Usuario: `root`
   - Contraseña: `admin123`

   Si necesitas cambiar estos valores, modifica las constantes en la clase `MySQL.java`.

## Ejecución de la Aplicación

1. Compila el proyecto.
2. Ejecuta la clase `Main`.
3. Selecciona la opción deseada en el menú principal para gestionar clientes o números de teléfono.
4. Sigue las instrucciones en la consola para realizar las operaciones deseadas.

## Funcionalidades

La aplicación permite realizar las siguientes operaciones:

### Gestión de Clientes

1. Insertar un nuevo cliente
2. Actualizar un cliente existente
3. Eliminar un cliente
4. Buscar un cliente por DNI
5. Listar todos los clientes

### Gestión de Números de Teléfono

1. Insertar un nuevo número de teléfono
2. Actualizar un número de teléfono existente
3. Eliminar un número de teléfono
4. Buscar un número de teléfono por ID
5. Listar todos los números de teléfono

### Gestión de Reservas

1. Crear una nueva reserva
2. Actualizar una reserva existente
3. Eliminar una reserva
4. Buscar una reserva por ID
5. Listar todas las reservas

### Gestión de Relaciones Cliente-Reserva

1. Asignar un cliente a una reserva
2. Actualizar una relación cliente-reserva
3. Eliminar una relación cliente-reserva
4. Buscar una relación por ID
5. Listar todas las relaciones
6. Listar relaciones por cliente
7. Listar relaciones por reserva

### Gestión de Órdenes

1. Crear una nueva orden
2. Actualizar una orden existente
3. Eliminar una orden
4. Buscar una orden por ID
5. Listar todas las órdenes
6. Listar órdenes por cliente

## Ejemplo de Uso

### Gestión de Clientes

1. En el menú principal, selecciona la opción 1 para gestionar clientes.
2. Selecciona la opción 1 para insertar un nuevo cliente.
3. Ingresa el DNI, nombre y fecha de nacimiento del cliente.
4. El cliente será insertado en la base de datos.
5. Puedes verificar que el cliente ha sido insertado seleccionando la opción 5 para listar todos los clientes.

### Gestión de Números de Teléfono

1. En el menú principal, selecciona la opción 2 para gestionar números de teléfono.
2. Selecciona la opción 1 para insertar un nuevo número de teléfono.
3. Ingresa el ID, número, prefijo y tipo de uso del número de teléfono.
4. El número de teléfono será insertado en la base de datos.
5. Puedes verificar que el número de teléfono ha sido insertado seleccionando la opción 5 para listar todos los números de teléfono.

### Gestión de Reservas

1. En el menú principal, selecciona la opción 3 para gestionar reservas.
2. Selecciona la opción 1 para crear una nueva reserva.
3. Ingresa el ID, número de asientos, número de mesas, fecha y hora de la reserva, y número de acompañantes.
4. La reserva será insertada en la base de datos.
5. Puedes verificar que la reserva ha sido insertada seleccionando la opción 5 para listar todas las reservas.

### Gestión de Relaciones Cliente-Reserva

1. En el menú principal, selecciona la opción 4 para gestionar relaciones cliente-reserva.
2. Selecciona la opción 1 para asignar un cliente a una reserva.
3. Ingresa el ID de la relación, el DNI del cliente y el ID de la reserva.
4. La relación será insertada en la base de datos.
5. Puedes verificar que la relación ha sido insertada seleccionando la opción 5 para listar todas las relaciones.
6. También puedes listar las relaciones por cliente (opción 6) o por reserva (opción 7).

### Gestión de Órdenes

1. En el menú principal, selecciona la opción 5 para gestionar órdenes.
2. Selecciona la opción 1 para crear una nueva orden.
3. Ingresa el ID, el DNI del cliente y la fecha y hora de la orden.
4. La orden será insertada en la base de datos.
5. Puedes verificar que la orden ha sido insertada seleccionando la opción 5 para listar todas las órdenes.
6. También puedes listar las órdenes por cliente (opción 6).

## Requisitos

- Java 8 o superior
- MySQL 5.7 o superior
- Conector JDBC para MySQL
