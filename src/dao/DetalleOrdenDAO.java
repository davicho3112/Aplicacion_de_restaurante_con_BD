package dao;

import modelo.DetalleOrden;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que gestiona las operaciones CRUD para la tabla DetalleOrden
 */
public class DetalleOrdenDAO {
    private Connection conexion;

    /**
     * Constructor que recibe una conexión a la base de datos
     * @param conexion Conexión a la base de datos
     */
    public DetalleOrdenDAO(Connection conexion) {
        this.conexion = conexion;
    }

    /**
     * Inserta un nuevo detalle de orden en la base de datos
     * @param detalleOrden Detalle de orden a insertar
     * @return true si la inserción fue exitosa, false en caso contrario
     */
    public boolean insertar(DetalleOrden detalleOrden) {
        if (conexion == null) {
            System.out.println("Error: No hay conexión a la base de datos");
            return false;
        }

        String sql = "INSERT INTO DetalleOrden (id, cantidad, FK_id_orden, FK_id_platillo) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, detalleOrden.getId());
            statement.setInt(2, detalleOrden.getCantidad());
            statement.setInt(3, detalleOrden.getFkIdOrden());
            statement.setInt(4, detalleOrden.getFkIdPlatillo());

            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.out.println("Error al insertar detalle de orden: " + e.getMessage());
            return false;
        }
    }

    /**
     * Actualiza un detalle de orden existente en la base de datos
     * @param detalleOrden Detalle de orden con los datos actualizados
     * @return true si la actualización fue exitosa, false en caso contrario
     */
    public boolean actualizar(DetalleOrden detalleOrden) {
        if (conexion == null) {
            System.out.println("Error: No hay conexión a la base de datos");
            return false;
        }

        String sql = "UPDATE DetalleOrden SET cantidad = ?, FK_id_orden = ?, FK_id_platillo = ? WHERE id = ?";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, detalleOrden.getCantidad());
            statement.setInt(2, detalleOrden.getFkIdOrden());
            statement.setInt(3, detalleOrden.getFkIdPlatillo());
            statement.setInt(4, detalleOrden.getId());

            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar detalle de orden: " + e.getMessage());
            return false;
        }
    }

    /**
     * Elimina un detalle de orden de la base de datos
     * @param id ID del detalle de orden a eliminar
     * @return true si la eliminación fue exitosa, false en caso contrario
     */
    public boolean eliminar(int id) {
        if (conexion == null) {
            System.out.println("Error: No hay conexión a la base de datos");
            return false;
        }

        String sql = "DELETE FROM DetalleOrden WHERE id = ?";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, id);

            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar detalle de orden: " + e.getMessage());
            return false;
        }
    }

    /**
     * Busca un detalle de orden por su ID
     * @param id ID del detalle de orden a buscar
     * @return Detalle de orden encontrado o null si no existe
     */
    public DetalleOrden buscarPorId(int id) {
        if (conexion == null) {
            System.out.println("Error: No hay conexión a la base de datos");
            return null;
        }

        String sql = "SELECT * FROM DetalleOrden WHERE id = ?";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    DetalleOrden detalleOrden = new DetalleOrden();
                    detalleOrden.setId(resultSet.getInt("id"));
                    detalleOrden.setCantidad(resultSet.getInt("cantidad"));
                    detalleOrden.setFkIdOrden(resultSet.getInt("FK_id_orden"));
                    detalleOrden.setFkIdPlatillo(resultSet.getInt("FK_id_platillo"));
                    return detalleOrden;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar detalle de orden: " + e.getMessage());
        }

        return null;
    }

    /**
     * Obtiene todos los detalles de orden de la base de datos
     * @return Lista de detalles de orden
     */
    public List<DetalleOrden> listarTodos() {
        List<DetalleOrden> detallesOrden = new ArrayList<>();

        if (conexion == null) {
            System.out.println("Error: No hay conexión a la base de datos");
            return detallesOrden;
        }

        String sql = "SELECT * FROM DetalleOrden";

        try (PreparedStatement statement = conexion.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                DetalleOrden detalleOrden = new DetalleOrden();
                detalleOrden.setId(resultSet.getInt("id"));
                detalleOrden.setCantidad(resultSet.getInt("cantidad"));
                detalleOrden.setFkIdOrden(resultSet.getInt("FK_id_orden"));
                detalleOrden.setFkIdPlatillo(resultSet.getInt("FK_id_platillo"));
                detallesOrden.add(detalleOrden);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar detalles de orden: " + e.getMessage());
        }

        return detallesOrden;
    }

    /**
     * Busca detalles de orden por ID de orden
     * @param idOrden ID de la orden asociada
     * @return Lista de detalles de orden asociados a la orden
     */
    public List<DetalleOrden> buscarPorIdOrden(int idOrden) {
        List<DetalleOrden> detallesOrden = new ArrayList<>();

        if (conexion == null) {
            System.out.println("Error: No hay conexión a la base de datos");
            return detallesOrden;
        }

        String sql = "SELECT * FROM DetalleOrden WHERE FK_id_orden = ?";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, idOrden);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    DetalleOrden detalleOrden = new DetalleOrden();
                    detalleOrden.setId(resultSet.getInt("id"));
                    detalleOrden.setCantidad(resultSet.getInt("cantidad"));
                    detalleOrden.setFkIdOrden(resultSet.getInt("FK_id_orden"));
                    detalleOrden.setFkIdPlatillo(resultSet.getInt("FK_id_platillo"));
                    detallesOrden.add(detalleOrden);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar detalles de orden por ID de orden: " + e.getMessage());
        }

        return detallesOrden;
    }

    /**
     * Busca detalles de orden por ID de platillo
     * @param idPlatillo ID del platillo asociado
     * @return Lista de detalles de orden asociados al platillo
     */
    public List<DetalleOrden> buscarPorIdPlatillo(int idPlatillo) {
        List<DetalleOrden> detallesOrden = new ArrayList<>();

        if (conexion == null) {
            System.out.println("Error: No hay conexión a la base de datos");
            return detallesOrden;
        }

        String sql = "SELECT * FROM DetalleOrden WHERE FK_id_platillo = ?";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, idPlatillo);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    DetalleOrden detalleOrden = new DetalleOrden();
                    detalleOrden.setId(resultSet.getInt("id"));
                    detalleOrden.setCantidad(resultSet.getInt("cantidad"));
                    detalleOrden.setFkIdOrden(resultSet.getInt("FK_id_orden"));
                    detalleOrden.setFkIdPlatillo(resultSet.getInt("FK_id_platillo"));
                    detallesOrden.add(detalleOrden);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar detalles de orden por ID de platillo: " + e.getMessage());
        }

        return detallesOrden;
    }
}