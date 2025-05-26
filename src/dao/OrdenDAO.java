package dao;

import modelo.Orden;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que gestiona las operaciones CRUD para la tabla Orden
 */
public class OrdenDAO {
    private Connection conexion;

    /**
     * Constructor que recibe una conexión a la base de datos
     * @param conexion Conexión a la base de datos
     */
    public OrdenDAO(Connection conexion) {
        this.conexion = conexion;
    }

    /**
     * Inserta una nueva orden en la base de datos
     * @param orden Orden a insertar
     * @return true si la inserción fue exitosa, false en caso contrario
     */
    public boolean insertar(Orden orden) {
        if (conexion == null) {
            System.out.println("Error: No hay conexión a la base de datos");
            return false;
        }

        String sql = "INSERT INTO Orden (id, fecha_orden, FK_dni_cliente) VALUES (?, ?, ?)";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, orden.getId());
            statement.setTimestamp(2, orden.getFechaOrden());
            statement.setInt(3, orden.getFkDniCliente());

            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.out.println("Error al insertar orden: " + e.getMessage());
            return false;
        }
    }

    /**
     * Actualiza una orden existente en la base de datos
     * @param orden Orden con los datos actualizados
     * @return true si la actualización fue exitosa, false en caso contrario
     */
    public boolean actualizar(Orden orden) {
        if (conexion == null) {
            System.out.println("Error: No hay conexión a la base de datos");
            return false;
        }

        String sql = "UPDATE Orden SET fecha_orden = ?, FK_dni_cliente = ? WHERE id = ?";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setTimestamp(1, orden.getFechaOrden());
            statement.setInt(2, orden.getFkDniCliente());
            statement.setInt(3, orden.getId());

            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar orden: " + e.getMessage());
            return false;
        }
    }

    /**
     * Elimina una orden de la base de datos
     * @param id ID de la orden a eliminar
     * @return true si la eliminación fue exitosa, false en caso contrario
     */
    public boolean eliminar(int id) {
        if (conexion == null) {
            System.out.println("Error: No hay conexión a la base de datos");
            return false;
        }

        String sql = "DELETE FROM Orden WHERE id = ?";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, id);

            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar orden: " + e.getMessage());
            return false;
        }
    }

    /**
     * Busca una orden por su ID
     * @param id ID de la orden a buscar
     * @return Orden encontrada o null si no existe
     */
    public Orden buscarPorId(int id) {
        if (conexion == null) {
            System.out.println("Error: No hay conexión a la base de datos");
            return null;
        }

        String sql = "SELECT * FROM Orden WHERE id = ?";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Orden orden = new Orden();
                    orden.setId(resultSet.getInt("id"));
                    orden.setFechaOrden(resultSet.getTimestamp("fecha_orden"));
                    orden.setFkDniCliente(resultSet.getInt("FK_dni_cliente"));
                    return orden;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar orden: " + e.getMessage());
        }

        return null;
    }

    /**
     * Obtiene todas las órdenes de la base de datos
     * @return Lista de órdenes
     */
    public List<Orden> listarTodos() {
        List<Orden> ordenes = new ArrayList<>();

        if (conexion == null) {
            System.out.println("Error: No hay conexión a la base de datos");
            return ordenes;
        }

        String sql = "SELECT * FROM Orden";

        try (PreparedStatement statement = conexion.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Orden orden = new Orden();
                orden.setId(resultSet.getInt("id"));
                orden.setFechaOrden(resultSet.getTimestamp("fecha_orden"));
                orden.setFkDniCliente(resultSet.getInt("FK_dni_cliente"));
                ordenes.add(orden);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar órdenes: " + e.getMessage());
        }

        return ordenes;
    }

    /**
     * Busca todas las órdenes de un cliente específico
     * @param dniCliente DNI del cliente
     * @return Lista de órdenes del cliente
     */
    public List<Orden> buscarPorCliente(int dniCliente) {
        List<Orden> ordenes = new ArrayList<>();

        if (conexion == null) {
            System.out.println("Error: No hay conexión a la base de datos");
            return ordenes;
        }

        String sql = "SELECT * FROM Orden WHERE FK_dni_cliente = ?";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, dniCliente);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Orden orden = new Orden();
                    orden.setId(resultSet.getInt("id"));
                    orden.setFechaOrden(resultSet.getTimestamp("fecha_orden"));
                    orden.setFkDniCliente(resultSet.getInt("FK_dni_cliente"));
                    ordenes.add(orden);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar órdenes por cliente: " + e.getMessage());
        }

        return ordenes;
    }
}