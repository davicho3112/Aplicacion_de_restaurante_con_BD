-- Script para crear las tablas Cliente, Numero_Telefono, Reserva, Reserva_Cliente, Orden, Pago, Factura, Platillo, DetalleOrden, Categoria y Platillo_Categoria

-- Crear la tabla Cliente
CREATE TABLE Cliente(
    dni INT PRIMARY KEY,
    nombre VARCHAR(30),
    fecha_nacimiento DATE
);

-- Comentarios sobre la tabla Cliente:
-- dni: Número de identificación del cliente (clave primaria)
-- nombre: Nombre del cliente (máximo 30 caracteres)
-- fecha_nacimiento: Fecha de nacimiento del cliente

-- Crear la tabla Numero_Telefono
CREATE TABLE Numero_Telefono (
    id INT PRIMARY KEY,
    numero BIGINT,
    prefijo VARCHAR(40),
    tipo_uso VARCHAR(40)
);

-- Comentarios sobre la tabla Numero_Telefono:
-- id: Identificador único del número de teléfono (clave primaria)
-- numero: Número de teléfono (tipo BIGINT para números grandes)
-- prefijo: Prefijo del número de teléfono (máximo 40 caracteres)
-- tipo_uso: Tipo de uso del número de teléfono (máximo 40 caracteres)

-- Crear la tabla Reserva
CREATE TABLE Reserva (
    id INT PRIMARY KEY,
    num_asientos INT,
    num_mesas INT,
    fecha_reserva DATETIME,
    num_acompañantes INT
);

-- Comentarios sobre la tabla Reserva:
-- id: Identificador único de la reserva (clave primaria)
-- num_asientos: Número de asientos reservados
-- num_mesas: Número de mesas reservadas
-- fecha_reserva: Fecha y hora de la reserva
-- num_acompañantes: Número de acompañantes

-- Crear la tabla Reserva_Cliente
CREATE TABLE Reserva_Cliente (
    id INT PRIMARY KEY,
    FK_dni_cliente INT,
    FK_reserva INT,
    FOREIGN KEY (FK_dni_cliente) REFERENCES Cliente(dni),
    FOREIGN KEY (FK_reserva) REFERENCES Reserva(id)
);

-- Comentarios sobre la tabla Reserva_Cliente:
-- id: Identificador único de la relación (clave primaria)
-- FK_dni_cliente: Clave foránea que referencia al cliente
-- FK_reserva: Clave foránea que referencia a la reserva

-- Crear la tabla Orden
CREATE TABLE Orden (
    id INT PRIMARY KEY,
    fecha_orden DATETIME,
    FK_dni_cliente INT,
    FOREIGN KEY (FK_dni_cliente) REFERENCES Cliente(dni)
);

-- Comentarios sobre la tabla Orden:
-- id: Identificador único de la orden (clave primaria)
-- fecha_orden: Fecha y hora de la orden
-- FK_dni_cliente: Clave foránea que referencia al cliente

-- Crear la tabla Pago
CREATE TABLE Pago (
    id INT PRIMARY KEY,
    metodo_pago VARCHAR(30),
    total DECIMAL(10,2)
);

-- Comentarios sobre la tabla Pago:
-- id: Identificador único del pago (clave primaria)
-- metodo_pago: Método de pago utilizado (máximo 30 caracteres)
-- total: Monto total del pago (con 2 decimales)

-- Crear la tabla Factura
CREATE TABLE Factura (
    id INT PRIMARY KEY,
    FK_id_pago INT,
    FK_id_reserva INT,
    FOREIGN KEY (FK_id_pago) REFERENCES Pago(id),
    FOREIGN KEY (FK_id_reserva) REFERENCES Reserva(id)
);

-- Comentarios sobre la tabla Factura:
-- id: Identificador único de la factura (clave primaria)
-- FK_id_pago: Clave foránea que referencia al pago
-- FK_id_reserva: Clave foránea que referencia a la reserva

-- Crear la tabla Platillo
CREATE TABLE Platillo (
    id INT PRIMARY KEY,
    nombre VARCHAR(50),
    precio DECIMAL(10,2),
    acompañantes VARCHAR(50)
);

-- Comentarios sobre la tabla Platillo:
-- id: Identificador único del platillo (clave primaria)
-- nombre: Nombre del platillo (máximo 50 caracteres)
-- precio: Precio del platillo (con 2 decimales)
-- acompañantes: Acompañantes del platillo (máximo 50 caracteres)

-- Crear la tabla DetalleOrden
CREATE TABLE DetalleOrden (
    id INT PRIMARY KEY,
    cantidad INT,
    FK_id_orden INT,
    FK_id_platillo INT,
    FOREIGN KEY (FK_id_orden) REFERENCES Orden(id),
    FOREIGN KEY (FK_id_platillo) REFERENCES Platillo(id)
);

-- Comentarios sobre la tabla DetalleOrden:
-- id: Identificador único del detalle de orden (clave primaria)
-- cantidad: Cantidad de platillos ordenados
-- FK_id_orden: Clave foránea que referencia a la orden
-- FK_id_platillo: Clave foránea que referencia al platillo

-- Crear la tabla Categoria
CREATE TABLE Categoria (
    id INT PRIMARY KEY,
    nombre VARCHAR(30),
    descripcion VARCHAR(30)
);

-- Comentarios sobre la tabla Categoria:
-- id: Identificador único de la categoría (clave primaria)
-- nombre: Nombre de la categoría (máximo 30 caracteres)
-- descripcion: Descripción de la categoría (máximo 30 caracteres)

-- Crear la tabla Platillo_Categoria
CREATE TABLE Platillo_Categoria (
    id INT PRIMARY KEY,
    FK_platillo INT,
    FK_categoria INT,
    FOREIGN KEY (FK_platillo) REFERENCES Platillo(id),
    FOREIGN KEY (FK_categoria) REFERENCES Categoria(id)
);

-- Comentarios sobre la tabla Platillo_Categoria:
-- id: Identificador único de la relación (clave primaria)
-- FK_platillo: Clave foránea que referencia al platillo
-- FK_categoria: Clave foránea que referencia a la categoría
