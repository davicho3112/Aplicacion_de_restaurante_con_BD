package conexion;

import Restaurant.MySQL;
import dao.ClienteDAO;
import dao.NumeroTelefonoDAO;
import dao.ReservaDAO;
import dao.ReservaClienteDAO;
import dao.OrdenDAO;
import dao.PagoDAO;
import dao.FacturaDAO;
import dao.PlatilloDAO;
import dao.DetalleOrdenDAO;
import dao.CategoriaDAO;
import dao.PlatilloCategoriaDAO;
import modelo.Cliente;
import modelo.NumeroTelefono;
import modelo.Reserva;
import modelo.ReservaCliente;
import modelo.Orden;
import modelo.Pago;
import modelo.Factura;
import modelo.Platillo;
import modelo.DetalleOrden;
import modelo.Categoria;
import modelo.PlatilloCategoria;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.List;

/**
 * Clase que gestiona la conexión a la base de datos y proporciona métodos para operaciones CRUD
 */
public class Conexion {

    /**
     * Obtiene una conexión a la base de datos
     * @return Conexión a la base de datos
     */
    public Connection obtenerConexion() {
        return MySQL.obtenerConexion();
    }

    /**
     * Cierra una conexión a la base de datos
     * @param conexion Conexión a cerrar
     */
    public void cerrarConexion(Connection conexion) {
        MySQL.cerrarConexion(conexion);
    }

    /**
     * Inserta un nuevo cliente en la base de datos
     * @param cliente Cliente a insertar
     * @return true si la inserción fue exitosa, false en caso contrario
     */
    public boolean insertarCliente(Cliente cliente) {
        Connection conexion = obtenerConexion();
        ClienteDAO clienteDAO = new ClienteDAO(conexion);
        boolean resultado = clienteDAO.insertar(cliente);
        cerrarConexion(conexion);
        return resultado;
    }

    /**
     * Actualiza un cliente existente en la base de datos
     * @param cliente Cliente con los datos actualizados
     * @return true si la actualización fue exitosa, false en caso contrario
     */
    public boolean actualizarCliente(Cliente cliente) {
        Connection conexion = obtenerConexion();
        ClienteDAO clienteDAO = new ClienteDAO(conexion);
        boolean resultado = clienteDAO.actualizar(cliente);
        cerrarConexion(conexion);
        return resultado;
    }

    /**
     * Elimina un cliente de la base de datos
     * @param dni DNI del cliente a eliminar
     * @return true si la eliminación fue exitosa, false en caso contrario
     */
    public boolean eliminarCliente(int dni) {
        Connection conexion = obtenerConexion();
        ClienteDAO clienteDAO = new ClienteDAO(conexion);
        boolean resultado = clienteDAO.eliminar(dni);
        cerrarConexion(conexion);
        return resultado;
    }

    /**
     * Busca un cliente por su DNI
     * @param dni DNI del cliente a buscar
     * @return Cliente encontrado o null si no existe
     */
    public Cliente buscarClientePorDni(int dni) {
        Connection conexion = obtenerConexion();
        ClienteDAO clienteDAO = new ClienteDAO(conexion);
        Cliente cliente = clienteDAO.buscarPorDni(dni);
        cerrarConexion(conexion);
        return cliente;
    }

    /**
     * Obtiene todos los clientes de la base de datos
     * @return Lista de clientes
     */
    public List<Cliente> listarTodosLosClientes() {
        Connection conexion = obtenerConexion();
        ClienteDAO clienteDAO = new ClienteDAO(conexion);
        List<Cliente> clientes = clienteDAO.listarTodos();
        cerrarConexion(conexion);
        return clientes;
    }

    /**
     * Inserta un nuevo número de teléfono en la base de datos
     * @param numeroTelefono Número de teléfono a insertar
     * @return true si la inserción fue exitosa, false en caso contrario
     */
    public boolean insertarNumeroTelefono(NumeroTelefono numeroTelefono) {
        Connection conexion = obtenerConexion();
        NumeroTelefonoDAO numeroTelefonoDAO = new NumeroTelefonoDAO(conexion);
        boolean resultado = numeroTelefonoDAO.insertar(numeroTelefono);
        cerrarConexion(conexion);
        return resultado;
    }

    /**
     * Actualiza un número de teléfono existente en la base de datos
     * @param numeroTelefono Número de teléfono con los datos actualizados
     * @return true si la actualización fue exitosa, false en caso contrario
     */
    public boolean actualizarNumeroTelefono(NumeroTelefono numeroTelefono) {
        Connection conexion = obtenerConexion();
        NumeroTelefonoDAO numeroTelefonoDAO = new NumeroTelefonoDAO(conexion);
        boolean resultado = numeroTelefonoDAO.actualizar(numeroTelefono);
        cerrarConexion(conexion);
        return resultado;
    }

    /**
     * Elimina un número de teléfono de la base de datos
     * @param id ID del número de teléfono a eliminar
     * @return true si la eliminación fue exitosa, false en caso contrario
     */
    public boolean eliminarNumeroTelefono(int id) {
        Connection conexion = obtenerConexion();
        NumeroTelefonoDAO numeroTelefonoDAO = new NumeroTelefonoDAO(conexion);
        boolean resultado = numeroTelefonoDAO.eliminar(id);
        cerrarConexion(conexion);
        return resultado;
    }

    /**
     * Busca un número de teléfono por su ID
     * @param id ID del número de teléfono a buscar
     * @return Número de teléfono encontrado o null si no existe
     */
    public NumeroTelefono buscarNumeroTelefonoPorId(int id) {
        Connection conexion = obtenerConexion();
        NumeroTelefonoDAO numeroTelefonoDAO = new NumeroTelefonoDAO(conexion);
        NumeroTelefono numeroTelefono = numeroTelefonoDAO.buscarPorId(id);
        cerrarConexion(conexion);
        return numeroTelefono;
    }

    /**
     * Obtiene todos los números de teléfono de la base de datos
     * @return Lista de números de teléfono
     */
    public List<NumeroTelefono> listarTodosLosNumerosTelefono() {
        Connection conexion = obtenerConexion();
        NumeroTelefonoDAO numeroTelefonoDAO = new NumeroTelefonoDAO(conexion);
        List<NumeroTelefono> numerosTelefono = numeroTelefonoDAO.listarTodos();
        cerrarConexion(conexion);
        return numerosTelefono;
    }

    /**
     * Inserta una nueva reserva en la base de datos
     * @param reserva Reserva a insertar
     * @return true si la inserción fue exitosa, false en caso contrario
     */
    public boolean insertarReserva(Reserva reserva) {
        Connection conexion = obtenerConexion();
        ReservaDAO reservaDAO = new ReservaDAO(conexion);
        boolean resultado = reservaDAO.insertar(reserva);
        cerrarConexion(conexion);
        return resultado;
    }

    /**
     * Actualiza una reserva existente en la base de datos
     * @param reserva Reserva con los datos actualizados
     * @return true si la actualización fue exitosa, false en caso contrario
     */
    public boolean actualizarReserva(Reserva reserva) {
        Connection conexion = obtenerConexion();
        ReservaDAO reservaDAO = new ReservaDAO(conexion);
        boolean resultado = reservaDAO.actualizar(reserva);
        cerrarConexion(conexion);
        return resultado;
    }

    /**
     * Elimina una reserva de la base de datos
     * @param id ID de la reserva a eliminar
     * @return true si la eliminación fue exitosa, false en caso contrario
     */
    public boolean eliminarReserva(int id) {
        Connection conexion = obtenerConexion();
        ReservaDAO reservaDAO = new ReservaDAO(conexion);
        boolean resultado = reservaDAO.eliminar(id);
        cerrarConexion(conexion);
        return resultado;
    }

    /**
     * Busca una reserva por su ID
     * @param id ID de la reserva a buscar
     * @return Reserva encontrada o null si no existe
     */
    public Reserva buscarReservaPorId(int id) {
        Connection conexion = obtenerConexion();
        ReservaDAO reservaDAO = new ReservaDAO(conexion);
        Reserva reserva = reservaDAO.buscarPorId(id);
        cerrarConexion(conexion);
        return reserva;
    }

    /**
     * Obtiene todas las reservas de la base de datos
     * @return Lista de reservas
     */
    public List<Reserva> listarTodasLasReservas() {
        Connection conexion = obtenerConexion();
        ReservaDAO reservaDAO = new ReservaDAO(conexion);
        List<Reserva> reservas = reservaDAO.listarTodos();
        cerrarConexion(conexion);
        return reservas;
    }

    /**
     * Inserta una nueva relación entre cliente y reserva en la base de datos
     * @param reservaCliente Relación a insertar
     * @return true si la inserción fue exitosa, false en caso contrario
     */
    public boolean insertarReservaCliente(ReservaCliente reservaCliente) {
        Connection conexion = obtenerConexion();
        ReservaClienteDAO reservaClienteDAO = new ReservaClienteDAO(conexion);
        boolean resultado = reservaClienteDAO.insertar(reservaCliente);
        cerrarConexion(conexion);
        return resultado;
    }

    /**
     * Actualiza una relación existente entre cliente y reserva en la base de datos
     * @param reservaCliente Relación con los datos actualizados
     * @return true si la actualización fue exitosa, false en caso contrario
     */
    public boolean actualizarReservaCliente(ReservaCliente reservaCliente) {
        Connection conexion = obtenerConexion();
        ReservaClienteDAO reservaClienteDAO = new ReservaClienteDAO(conexion);
        boolean resultado = reservaClienteDAO.actualizar(reservaCliente);
        cerrarConexion(conexion);
        return resultado;
    }

    /**
     * Elimina una relación entre cliente y reserva de la base de datos
     * @param id ID de la relación a eliminar
     * @return true si la eliminación fue exitosa, false en caso contrario
     */
    public boolean eliminarReservaCliente(int id) {
        Connection conexion = obtenerConexion();
        ReservaClienteDAO reservaClienteDAO = new ReservaClienteDAO(conexion);
        boolean resultado = reservaClienteDAO.eliminar(id);
        cerrarConexion(conexion);
        return resultado;
    }

    /**
     * Busca una relación entre cliente y reserva por su ID
     * @param id ID de la relación a buscar
     * @return Relación encontrada o null si no existe
     */
    public ReservaCliente buscarReservaClientePorId(int id) {
        Connection conexion = obtenerConexion();
        ReservaClienteDAO reservaClienteDAO = new ReservaClienteDAO(conexion);
        ReservaCliente reservaCliente = reservaClienteDAO.buscarPorId(id);
        cerrarConexion(conexion);
        return reservaCliente;
    }

    /**
     * Obtiene todas las relaciones entre clientes y reservas de la base de datos
     * @return Lista de relaciones
     */
    public List<ReservaCliente> listarTodasLasReservasClientes() {
        Connection conexion = obtenerConexion();
        ReservaClienteDAO reservaClienteDAO = new ReservaClienteDAO(conexion);
        List<ReservaCliente> reservasClientes = reservaClienteDAO.listarTodos();
        cerrarConexion(conexion);
        return reservasClientes;
    }

    /**
     * Busca todas las relaciones para un cliente específico
     * @param dniCliente DNI del cliente
     * @return Lista de relaciones del cliente
     */
    public List<ReservaCliente> buscarReservaClientePorCliente(int dniCliente) {
        Connection conexion = obtenerConexion();
        ReservaClienteDAO reservaClienteDAO = new ReservaClienteDAO(conexion);
        List<ReservaCliente> reservasClientes = reservaClienteDAO.buscarPorCliente(dniCliente);
        cerrarConexion(conexion);
        return reservasClientes;
    }

    /**
     * Busca todas las relaciones para una reserva específica
     * @param idReserva ID de la reserva
     * @return Lista de relaciones de la reserva
     */
    public List<ReservaCliente> buscarReservaClientePorReserva(int idReserva) {
        Connection conexion = obtenerConexion();
        ReservaClienteDAO reservaClienteDAO = new ReservaClienteDAO(conexion);
        List<ReservaCliente> reservasClientes = reservaClienteDAO.buscarPorReserva(idReserva);
        cerrarConexion(conexion);
        return reservasClientes;
    }

    /**
     * Inserta una nueva orden en la base de datos
     * @param orden Orden a insertar
     * @return true si la inserción fue exitosa, false en caso contrario
     */
    public boolean insertarOrden(Orden orden) {
        Connection conexion = obtenerConexion();
        OrdenDAO ordenDAO = new OrdenDAO(conexion);
        boolean resultado = ordenDAO.insertar(orden);
        cerrarConexion(conexion);
        return resultado;
    }

    /**
     * Actualiza una orden existente en la base de datos
     * @param orden Orden con los datos actualizados
     * @return true si la actualización fue exitosa, false en caso contrario
     */
    public boolean actualizarOrden(Orden orden) {
        Connection conexion = obtenerConexion();
        OrdenDAO ordenDAO = new OrdenDAO(conexion);
        boolean resultado = ordenDAO.actualizar(orden);
        cerrarConexion(conexion);
        return resultado;
    }

    /**
     * Elimina una orden de la base de datos
     * @param id ID de la orden a eliminar
     * @return true si la eliminación fue exitosa, false en caso contrario
     */
    public boolean eliminarOrden(int id) {
        Connection conexion = obtenerConexion();
        OrdenDAO ordenDAO = new OrdenDAO(conexion);
        boolean resultado = ordenDAO.eliminar(id);
        cerrarConexion(conexion);
        return resultado;
    }

    /**
     * Busca una orden por su ID
     * @param id ID de la orden a buscar
     * @return Orden encontrada o null si no existe
     */
    public Orden buscarOrdenPorId(int id) {
        Connection conexion = obtenerConexion();
        OrdenDAO ordenDAO = new OrdenDAO(conexion);
        Orden orden = ordenDAO.buscarPorId(id);
        cerrarConexion(conexion);
        return orden;
    }

    /**
     * Obtiene todas las órdenes de la base de datos
     * @return Lista de órdenes
     */
    public List<Orden> listarTodasLasOrdenes() {
        Connection conexion = obtenerConexion();
        OrdenDAO ordenDAO = new OrdenDAO(conexion);
        List<Orden> ordenes = ordenDAO.listarTodos();
        cerrarConexion(conexion);
        return ordenes;
    }

    /**
     * Busca todas las órdenes de un cliente específico
     * @param dniCliente DNI del cliente
     * @return Lista de órdenes del cliente
     */
    public List<Orden> buscarOrdenesPorCliente(int dniCliente) {
        Connection conexion = obtenerConexion();
        OrdenDAO ordenDAO = new OrdenDAO(conexion);
        List<Orden> ordenes = ordenDAO.buscarPorCliente(dniCliente);
        cerrarConexion(conexion);
        return ordenes;
    }

    // Métodos para la tabla Pago

    /**
     * Inserta un nuevo pago en la base de datos
     * @param pago Pago a insertar
     * @return true si la inserción fue exitosa, false en caso contrario
     */
    public boolean insertarPago(Pago pago) {
        Connection conexion = obtenerConexion();
        PagoDAO pagoDAO = new PagoDAO(conexion);
        boolean resultado = pagoDAO.insertar(pago);
        cerrarConexion(conexion);
        return resultado;
    }

    /**
     * Actualiza un pago existente en la base de datos
     * @param pago Pago con los datos actualizados
     * @return true si la actualización fue exitosa, false en caso contrario
     */
    public boolean actualizarPago(Pago pago) {
        Connection conexion = obtenerConexion();
        PagoDAO pagoDAO = new PagoDAO(conexion);
        boolean resultado = pagoDAO.actualizar(pago);
        cerrarConexion(conexion);
        return resultado;
    }

    /**
     * Elimina un pago de la base de datos
     * @param id ID del pago a eliminar
     * @return true si la eliminación fue exitosa, false en caso contrario
     */
    public boolean eliminarPago(int id) {
        Connection conexion = obtenerConexion();
        PagoDAO pagoDAO = new PagoDAO(conexion);
        boolean resultado = pagoDAO.eliminar(id);
        cerrarConexion(conexion);
        return resultado;
    }

    /**
     * Busca un pago por su ID
     * @param id ID del pago a buscar
     * @return Pago encontrado o null si no existe
     */
    public Pago buscarPagoPorId(int id) {
        Connection conexion = obtenerConexion();
        PagoDAO pagoDAO = new PagoDAO(conexion);
        Pago pago = pagoDAO.buscarPorId(id);
        cerrarConexion(conexion);
        return pago;
    }

    /**
     * Obtiene todos los pagos de la base de datos
     * @return Lista de pagos
     */
    public List<Pago> listarTodosLosPagos() {
        Connection conexion = obtenerConexion();
        PagoDAO pagoDAO = new PagoDAO(conexion);
        List<Pago> pagos = pagoDAO.listarTodos();
        cerrarConexion(conexion);
        return pagos;
    }

    // Métodos para la tabla Factura

    /**
     * Inserta una nueva factura en la base de datos
     * @param factura Factura a insertar
     * @return true si la inserción fue exitosa, false en caso contrario
     */
    public boolean insertarFactura(Factura factura) {
        Connection conexion = obtenerConexion();
        FacturaDAO facturaDAO = new FacturaDAO(conexion);
        boolean resultado = facturaDAO.insertar(factura);
        cerrarConexion(conexion);
        return resultado;
    }

    /**
     * Actualiza una factura existente en la base de datos
     * @param factura Factura con los datos actualizados
     * @return true si la actualización fue exitosa, false en caso contrario
     */
    public boolean actualizarFactura(Factura factura) {
        Connection conexion = obtenerConexion();
        FacturaDAO facturaDAO = new FacturaDAO(conexion);
        boolean resultado = facturaDAO.actualizar(factura);
        cerrarConexion(conexion);
        return resultado;
    }

    /**
     * Elimina una factura de la base de datos
     * @param id ID de la factura a eliminar
     * @return true si la eliminación fue exitosa, false en caso contrario
     */
    public boolean eliminarFactura(int id) {
        Connection conexion = obtenerConexion();
        FacturaDAO facturaDAO = new FacturaDAO(conexion);
        boolean resultado = facturaDAO.eliminar(id);
        cerrarConexion(conexion);
        return resultado;
    }

    /**
     * Busca una factura por su ID
     * @param id ID de la factura a buscar
     * @return Factura encontrada o null si no existe
     */
    public Factura buscarFacturaPorId(int id) {
        Connection conexion = obtenerConexion();
        FacturaDAO facturaDAO = new FacturaDAO(conexion);
        Factura factura = facturaDAO.buscarPorId(id);
        cerrarConexion(conexion);
        return factura;
    }

    /**
     * Obtiene todas las facturas de la base de datos
     * @return Lista de facturas
     */
    public List<Factura> listarTodasLasFacturas() {
        Connection conexion = obtenerConexion();
        FacturaDAO facturaDAO = new FacturaDAO(conexion);
        List<Factura> facturas = facturaDAO.listarTodos();
        cerrarConexion(conexion);
        return facturas;
    }

    /**
     * Busca facturas por ID de pago
     * @param idPago ID del pago asociado
     * @return Lista de facturas asociadas al pago
     */
    public List<Factura> buscarFacturasPorIdPago(int idPago) {
        Connection conexion = obtenerConexion();
        FacturaDAO facturaDAO = new FacturaDAO(conexion);
        List<Factura> facturas = facturaDAO.buscarPorIdPago(idPago);
        cerrarConexion(conexion);
        return facturas;
    }

    /**
     * Busca facturas por ID de reserva
     * @param idReserva ID de la reserva asociada
     * @return Lista de facturas asociadas a la reserva
     */
    public List<Factura> buscarFacturasPorIdReserva(int idReserva) {
        Connection conexion = obtenerConexion();
        FacturaDAO facturaDAO = new FacturaDAO(conexion);
        List<Factura> facturas = facturaDAO.buscarPorIdReserva(idReserva);
        cerrarConexion(conexion);
        return facturas;
    }

    // Métodos para la tabla Platillo

    /**
     * Inserta un nuevo platillo en la base de datos
     * @param platillo Platillo a insertar
     * @return true si la inserción fue exitosa, false en caso contrario
     */
    public boolean insertarPlatillo(Platillo platillo) {
        Connection conexion = obtenerConexion();
        PlatilloDAO platilloDAO = new PlatilloDAO(conexion);
        boolean resultado = platilloDAO.insertar(platillo);
        cerrarConexion(conexion);
        return resultado;
    }

    /**
     * Actualiza un platillo existente en la base de datos
     * @param platillo Platillo con los datos actualizados
     * @return true si la actualización fue exitosa, false en caso contrario
     */
    public boolean actualizarPlatillo(Platillo platillo) {
        Connection conexion = obtenerConexion();
        PlatilloDAO platilloDAO = new PlatilloDAO(conexion);
        boolean resultado = platilloDAO.actualizar(platillo);
        cerrarConexion(conexion);
        return resultado;
    }

    /**
     * Elimina un platillo de la base de datos
     * @param id ID del platillo a eliminar
     * @return true si la eliminación fue exitosa, false en caso contrario
     */
    public boolean eliminarPlatillo(int id) {
        Connection conexion = obtenerConexion();
        PlatilloDAO platilloDAO = new PlatilloDAO(conexion);
        boolean resultado = platilloDAO.eliminar(id);
        cerrarConexion(conexion);
        return resultado;
    }

    /**
     * Busca un platillo por su ID
     * @param id ID del platillo a buscar
     * @return Platillo encontrado o null si no existe
     */
    public Platillo buscarPlatilloPorId(int id) {
        Connection conexion = obtenerConexion();
        PlatilloDAO platilloDAO = new PlatilloDAO(conexion);
        Platillo platillo = platilloDAO.buscarPorId(id);
        cerrarConexion(conexion);
        return platillo;
    }

    /**
     * Obtiene todos los platillos de la base de datos
     * @return Lista de platillos
     */
    public List<Platillo> listarTodosLosPlatillos() {
        Connection conexion = obtenerConexion();
        PlatilloDAO platilloDAO = new PlatilloDAO(conexion);
        List<Platillo> platillos = platilloDAO.listarTodos();
        cerrarConexion(conexion);
        return platillos;
    }

    /**
     * Busca platillos por nombre
     * @param nombre Nombre o parte del nombre del platillo
     * @return Lista de platillos que coinciden con el nombre
     */
    public List<Platillo> buscarPlatillosPorNombre(String nombre) {
        Connection conexion = obtenerConexion();
        PlatilloDAO platilloDAO = new PlatilloDAO(conexion);
        List<Platillo> platillos = platilloDAO.buscarPorNombre(nombre);
        cerrarConexion(conexion);
        return platillos;
    }

    /**
     * Busca platillos por rango de precio
     * @param precioMinimo Precio mínimo
     * @param precioMaximo Precio máximo
     * @return Lista de platillos dentro del rango de precio
     */
    public List<Platillo> buscarPlatillosPorRangoPrecio(BigDecimal precioMinimo, BigDecimal precioMaximo) {
        Connection conexion = obtenerConexion();
        PlatilloDAO platilloDAO = new PlatilloDAO(conexion);
        List<Platillo> platillos = platilloDAO.buscarPorRangoPrecio(precioMinimo, precioMaximo);
        cerrarConexion(conexion);
        return platillos;
    }

    // Métodos para la tabla DetalleOrden

    /**
     * Inserta un nuevo detalle de orden en la base de datos
     * @param detalleOrden Detalle de orden a insertar
     * @return true si la inserción fue exitosa, false en caso contrario
     */
    public boolean insertarDetalleOrden(DetalleOrden detalleOrden) {
        Connection conexion = obtenerConexion();
        DetalleOrdenDAO detalleOrdenDAO = new DetalleOrdenDAO(conexion);
        boolean resultado = detalleOrdenDAO.insertar(detalleOrden);
        cerrarConexion(conexion);
        return resultado;
    }

    /**
     * Actualiza un detalle de orden existente en la base de datos
     * @param detalleOrden Detalle de orden con los datos actualizados
     * @return true si la actualización fue exitosa, false en caso contrario
     */
    public boolean actualizarDetalleOrden(DetalleOrden detalleOrden) {
        Connection conexion = obtenerConexion();
        DetalleOrdenDAO detalleOrdenDAO = new DetalleOrdenDAO(conexion);
        boolean resultado = detalleOrdenDAO.actualizar(detalleOrden);
        cerrarConexion(conexion);
        return resultado;
    }

    /**
     * Elimina un detalle de orden de la base de datos
     * @param id ID del detalle de orden a eliminar
     * @return true si la eliminación fue exitosa, false en caso contrario
     */
    public boolean eliminarDetalleOrden(int id) {
        Connection conexion = obtenerConexion();
        DetalleOrdenDAO detalleOrdenDAO = new DetalleOrdenDAO(conexion);
        boolean resultado = detalleOrdenDAO.eliminar(id);
        cerrarConexion(conexion);
        return resultado;
    }

    /**
     * Busca un detalle de orden por su ID
     * @param id ID del detalle de orden a buscar
     * @return Detalle de orden encontrado o null si no existe
     */
    public DetalleOrden buscarDetalleOrdenPorId(int id) {
        Connection conexion = obtenerConexion();
        DetalleOrdenDAO detalleOrdenDAO = new DetalleOrdenDAO(conexion);
        DetalleOrden detalleOrden = detalleOrdenDAO.buscarPorId(id);
        cerrarConexion(conexion);
        return detalleOrden;
    }

    /**
     * Obtiene todos los detalles de orden de la base de datos
     * @return Lista de detalles de orden
     */
    public List<DetalleOrden> listarTodosLosDetallesOrden() {
        Connection conexion = obtenerConexion();
        DetalleOrdenDAO detalleOrdenDAO = new DetalleOrdenDAO(conexion);
        List<DetalleOrden> detallesOrden = detalleOrdenDAO.listarTodos();
        cerrarConexion(conexion);
        return detallesOrden;
    }

    /**
     * Busca detalles de orden por ID de orden
     * @param idOrden ID de la orden
     * @return Lista de detalles de orden asociados a la orden
     */
    public List<DetalleOrden> buscarDetallesOrdenPorIdOrden(int idOrden) {
        Connection conexion = obtenerConexion();
        DetalleOrdenDAO detalleOrdenDAO = new DetalleOrdenDAO(conexion);
        List<DetalleOrden> detallesOrden = detalleOrdenDAO.buscarPorIdOrden(idOrden);
        cerrarConexion(conexion);
        return detallesOrden;
    }

    /**
     * Busca detalles de orden por ID de platillo
     * @param idPlatillo ID del platillo
     * @return Lista de detalles de orden que incluyen el platillo
     */
    public List<DetalleOrden> buscarDetallesOrdenPorIdPlatillo(int idPlatillo) {
        Connection conexion = obtenerConexion();
        DetalleOrdenDAO detalleOrdenDAO = new DetalleOrdenDAO(conexion);
        List<DetalleOrden> detallesOrden = detalleOrdenDAO.buscarPorIdPlatillo(idPlatillo);
        cerrarConexion(conexion);
        return detallesOrden;
    }

    // Métodos para la tabla Categoria

    /**
     * Inserta una nueva categoría en la base de datos
     * @param categoria Categoría a insertar
     * @return true si la inserción fue exitosa, false en caso contrario
     */
    public boolean insertarCategoria(Categoria categoria) {
        Connection conexion = obtenerConexion();
        CategoriaDAO categoriaDAO = new CategoriaDAO(conexion);
        boolean resultado = categoriaDAO.insertar(categoria);
        cerrarConexion(conexion);
        return resultado;
    }

    /**
     * Actualiza una categoría existente en la base de datos
     * @param categoria Categoría con los datos actualizados
     * @return true si la actualización fue exitosa, false en caso contrario
     */
    public boolean actualizarCategoria(Categoria categoria) {
        Connection conexion = obtenerConexion();
        CategoriaDAO categoriaDAO = new CategoriaDAO(conexion);
        boolean resultado = categoriaDAO.actualizar(categoria);
        cerrarConexion(conexion);
        return resultado;
    }

    /**
     * Elimina una categoría de la base de datos
     * @param id ID de la categoría a eliminar
     * @return true si la eliminación fue exitosa, false en caso contrario
     */
    public boolean eliminarCategoria(int id) {
        Connection conexion = obtenerConexion();
        CategoriaDAO categoriaDAO = new CategoriaDAO(conexion);
        boolean resultado = categoriaDAO.eliminar(id);
        cerrarConexion(conexion);
        return resultado;
    }

    /**
     * Busca una categoría por su ID
     * @param id ID de la categoría a buscar
     * @return Categoría encontrada o null si no existe
     */
    public Categoria buscarCategoriaPorId(int id) {
        Connection conexion = obtenerConexion();
        CategoriaDAO categoriaDAO = new CategoriaDAO(conexion);
        Categoria categoria = categoriaDAO.buscarPorId(id);
        cerrarConexion(conexion);
        return categoria;
    }

    /**
     * Obtiene todas las categorías de la base de datos
     * @return Lista de categorías
     */
    public List<Categoria> listarTodasLasCategorias() {
        Connection conexion = obtenerConexion();
        CategoriaDAO categoriaDAO = new CategoriaDAO(conexion);
        List<Categoria> categorias = categoriaDAO.listarTodos();
        cerrarConexion(conexion);
        return categorias;
    }

    /**
     * Busca categorías por nombre
     * @param nombre Nombre o parte del nombre de la categoría
     * @return Lista de categorías que coinciden con el nombre
     */
    public List<Categoria> buscarCategoriasPorNombre(String nombre) {
        Connection conexion = obtenerConexion();
        CategoriaDAO categoriaDAO = new CategoriaDAO(conexion);
        List<Categoria> categorias = categoriaDAO.buscarPorNombre(nombre);
        cerrarConexion(conexion);
        return categorias;
    }

    // Métodos para la tabla Platillo_Categoria

    /**
     * Inserta una nueva relación entre platillo y categoría en la base de datos
     * @param platilloCategoria Relación a insertar
     * @return true si la inserción fue exitosa, false en caso contrario
     */
    public boolean insertarPlatilloCategoria(PlatilloCategoria platilloCategoria) {
        Connection conexion = obtenerConexion();
        PlatilloCategoriaDAO platilloCategoriaDAO = new PlatilloCategoriaDAO(conexion);
        boolean resultado = platilloCategoriaDAO.insertar(platilloCategoria);
        cerrarConexion(conexion);
        return resultado;
    }

    /**
     * Actualiza una relación existente entre platillo y categoría en la base de datos
     * @param platilloCategoria Relación con los datos actualizados
     * @return true si la actualización fue exitosa, false en caso contrario
     */
    public boolean actualizarPlatilloCategoria(PlatilloCategoria platilloCategoria) {
        Connection conexion = obtenerConexion();
        PlatilloCategoriaDAO platilloCategoriaDAO = new PlatilloCategoriaDAO(conexion);
        boolean resultado = platilloCategoriaDAO.actualizar(platilloCategoria);
        cerrarConexion(conexion);
        return resultado;
    }

    /**
     * Elimina una relación entre platillo y categoría de la base de datos
     * @param id ID de la relación a eliminar
     * @return true si la eliminación fue exitosa, false en caso contrario
     */
    public boolean eliminarPlatilloCategoria(int id) {
        Connection conexion = obtenerConexion();
        PlatilloCategoriaDAO platilloCategoriaDAO = new PlatilloCategoriaDAO(conexion);
        boolean resultado = platilloCategoriaDAO.eliminar(id);
        cerrarConexion(conexion);
        return resultado;
    }

    /**
     * Busca una relación entre platillo y categoría por su ID
     * @param id ID de la relación a buscar
     * @return Relación encontrada o null si no existe
     */
    public PlatilloCategoria buscarPlatilloCategoriaPorId(int id) {
        Connection conexion = obtenerConexion();
        PlatilloCategoriaDAO platilloCategoriaDAO = new PlatilloCategoriaDAO(conexion);
        PlatilloCategoria platilloCategoria = platilloCategoriaDAO.buscarPorId(id);
        cerrarConexion(conexion);
        return platilloCategoria;
    }

    /**
     * Obtiene todas las relaciones entre platillos y categorías de la base de datos
     * @return Lista de relaciones
     */
    public List<PlatilloCategoria> listarTodosLosPlatilloCategorias() {
        Connection conexion = obtenerConexion();
        PlatilloCategoriaDAO platilloCategoriaDAO = new PlatilloCategoriaDAO(conexion);
        List<PlatilloCategoria> platilloCategorias = platilloCategoriaDAO.listarTodos();
        cerrarConexion(conexion);
        return platilloCategorias;
    }

    /**
     * Busca relaciones por ID de platillo
     * @param idPlatillo ID del platillo
     * @return Lista de relaciones asociadas al platillo
     */
    public List<PlatilloCategoria> buscarPlatilloCategoriasPorIdPlatillo(int idPlatillo) {
        Connection conexion = obtenerConexion();
        PlatilloCategoriaDAO platilloCategoriaDAO = new PlatilloCategoriaDAO(conexion);
        List<PlatilloCategoria> platilloCategorias = platilloCategoriaDAO.buscarPorIdPlatillo(idPlatillo);
        cerrarConexion(conexion);
        return platilloCategorias;
    }

    /**
     * Busca relaciones por ID de categoría
     * @param idCategoria ID de la categoría
     * @return Lista de relaciones asociadas a la categoría
     */
    public List<PlatilloCategoria> buscarPlatilloCategoriasPorIdCategoria(int idCategoria) {
        Connection conexion = obtenerConexion();
        PlatilloCategoriaDAO platilloCategoriaDAO = new PlatilloCategoriaDAO(conexion);
        List<PlatilloCategoria> platilloCategorias = platilloCategoriaDAO.buscarPorIdCategoria(idCategoria);
        cerrarConexion(conexion);
        return platilloCategorias;
    }
}
