package dao;

import modelo.Reserva;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que gestiona las operaciones CRUD para la tabla Reserva
 */
public class ReservaDAO {
    private Connection conexion;

    /**
     * Constructor que recibe una conexión a la base de datos
     * @param conexion Conexión a la base de datos
     */
    public ReservaDAO(Connection conexion) {
        this.conexion = conexion;
    }

    /**
     * Inserta una nueva reserva en la base de datos
     * @param reserva Reserva a insertar
     * @return true si la inserción fue exitosa, false en caso contrario
     */
    public boolean insertar(Reserva reserva) {
        if (conexion == null) {
            System.out.println("Error: No hay conexión a la base de datos");
            return false;
        }

        String sql = "INSERT INTO Reserva (id, num_asientos, num_mesas, fecha_reserva, num_acompañantes) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, reserva.getId());
            statement.setInt(2, reserva.getNumAsientos());
            statement.setInt(3, reserva.getNumMesas());
            statement.setTimestamp(4, reserva.getFechaReserva());
            statement.setInt(5, reserva.getNumAcompanantes());

            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.out.println("Error al insertar reserva: " + e.getMessage());
            return false;
        }
    }

    /**
     * Actualiza una reserva existente en la base de datos
     * @param reserva Reserva con los datos actualizados
     * @return true si la actualización fue exitosa, false en caso contrario
     */
    public boolean actualizar(Reserva reserva) {
        if (conexion == null) {
            System.out.println("Error: No hay conexión a la base de datos");
            return false;
        }

        String sql = "UPDATE Reserva SET num_asientos = ?, num_mesas = ?, fecha_reserva = ?, num_acompañantes = ? WHERE id = ?";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, reserva.getNumAsientos());
            statement.setInt(2, reserva.getNumMesas());
            statement.setTimestamp(3, reserva.getFechaReserva());
            statement.setInt(4, reserva.getNumAcompanantes());
            statement.setInt(5, reserva.getId());

            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar reserva: " + e.getMessage());
            return false;
        }
    }

    /**
     * Elimina una reserva de la base de datos
     * @param id ID de la reserva a eliminar
     * @return true si la eliminación fue exitosa, false en caso contrario
     */
    public boolean eliminar(int id) {
        if (conexion == null) {
            System.out.println("Error: No hay conexión a la base de datos");
            return false;
        }

        String sql = "DELETE FROM Reserva WHERE id = ?";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, id);

            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar reserva: " + e.getMessage());
            return false;
        }
    }

    /**
     * Busca una reserva por su ID
     * @param id ID de la reserva a buscar
     * @return Reserva encontrada o null si no existe
     */
    public Reserva buscarPorId(int id) {
        if (conexion == null) {
            System.out.println("Error: No hay conexión a la base de datos");
            return null;
        }

        String sql = "SELECT * FROM Reserva WHERE id = ?";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Reserva reserva = new Reserva();
                    reserva.setId(resultSet.getInt("id"));
                    reserva.setNumAsientos(resultSet.getInt("num_asientos"));
                    reserva.setNumMesas(resultSet.getInt("num_mesas"));
                    reserva.setFechaReserva(resultSet.getTimestamp("fecha_reserva"));
                    reserva.setNumAcompanantes(resultSet.getInt("num_acompañantes"));
                    return reserva;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar reserva: " + e.getMessage());
        }

        return null;
    }

    /**
     * Obtiene todas las reservas de la base de datos
     * @return Lista de reservas
     */
    public List<Reserva> listarTodos() {
        List<Reserva> reservas = new ArrayList<>();

        if (conexion == null) {
            System.out.println("Error: No hay conexión a la base de datos");
            return reservas;
        }

        String sql = "SELECT * FROM Reserva";

        try (PreparedStatement statement = conexion.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Reserva reserva = new Reserva();
                reserva.setId(resultSet.getInt("id"));
                reserva.setNumAsientos(resultSet.getInt("num_asientos"));
                reserva.setNumMesas(resultSet.getInt("num_mesas"));
                reserva.setFechaReserva(resultSet.getTimestamp("fecha_reserva"));
                reserva.setNumAcompanantes(resultSet.getInt("num_acompañantes"));
                reservas.add(reserva);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar reservas: " + e.getMessage());
        }

        return reservas;
    }
}