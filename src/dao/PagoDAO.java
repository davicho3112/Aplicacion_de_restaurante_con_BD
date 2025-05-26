package dao;

import modelo.Pago;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que gestiona las operaciones CRUD para la tabla Pago
 */
public class PagoDAO {
    private Connection conexion;

    /**
     * Constructor que recibe una conexión a la base de datos
     * @param conexion Conexión a la base de datos
     */
    public PagoDAO(Connection conexion) {
        this.conexion = conexion;
    }

    /**
     * Inserta un nuevo pago en la base de datos
     * @param pago Pago a insertar
     * @return true si la inserción fue exitosa, false en caso contrario
     */
    public boolean insertar(Pago pago) {
        if (conexion == null) {
            System.out.println("Error: No hay conexión a la base de datos");
            return false;
        }

        String sql = "INSERT INTO Pago (id, metodo_pago, total) VALUES (?, ?, ?)";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, pago.getId());
            statement.setString(2, pago.getMetodoPago());
            statement.setBigDecimal(3, pago.getTotal());

            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.out.println("Error al insertar pago: " + e.getMessage());
            return false;
        }
    }

    /**
     * Actualiza un pago existente en la base de datos
     * @param pago Pago con los datos actualizados
     * @return true si la actualización fue exitosa, false en caso contrario
     */
    public boolean actualizar(Pago pago) {
        if (conexion == null) {
            System.out.println("Error: No hay conexión a la base de datos");
            return false;
        }

        String sql = "UPDATE Pago SET metodo_pago = ?, total = ? WHERE id = ?";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, pago.getMetodoPago());
            statement.setBigDecimal(2, pago.getTotal());
            statement.setInt(3, pago.getId());

            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar pago: " + e.getMessage());
            return false;
        }
    }

    /**
     * Elimina un pago de la base de datos
     * @param id ID del pago a eliminar
     * @return true si la eliminación fue exitosa, false en caso contrario
     */
    public boolean eliminar(int id) {
        if (conexion == null) {
            System.out.println("Error: No hay conexión a la base de datos");
            return false;
        }

        String sql = "DELETE FROM Pago WHERE id = ?";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, id);

            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar pago: " + e.getMessage());
            return false;
        }
    }

    /**
     * Busca un pago por su ID
     * @param id ID del pago a buscar
     * @return Pago encontrado o null si no existe
     */
    public Pago buscarPorId(int id) {
        if (conexion == null) {
            System.out.println("Error: No hay conexión a la base de datos");
            return null;
        }

        String sql = "SELECT * FROM Pago WHERE id = ?";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Pago pago = new Pago();
                    pago.setId(resultSet.getInt("id"));
                    pago.setMetodoPago(resultSet.getString("metodo_pago"));
                    pago.setTotal(resultSet.getBigDecimal("total"));
                    return pago;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar pago: " + e.getMessage());
        }

        return null;
    }

    /**
     * Obtiene todos los pagos de la base de datos
     * @return Lista de pagos
     */
    public List<Pago> listarTodos() {
        List<Pago> pagos = new ArrayList<>();

        if (conexion == null) {
            System.out.println("Error: No hay conexión a la base de datos");
            return pagos;
        }

        String sql = "SELECT * FROM Pago";

        try (PreparedStatement statement = conexion.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Pago pago = new Pago();
                pago.setId(resultSet.getInt("id"));
                pago.setMetodoPago(resultSet.getString("metodo_pago"));
                pago.setTotal(resultSet.getBigDecimal("total"));
                pagos.add(pago);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar pagos: " + e.getMessage());
        }

        return pagos;
    }
}