package dao;

import modelo.ReservaCliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que gestiona las operaciones CRUD para la tabla Reserva_Cliente
 */
public class ReservaClienteDAO {
    private Connection conexion;

    /**
     * Constructor que recibe una conexión a la base de datos
     * @param conexion Conexión a la base de datos
     */
    public ReservaClienteDAO(Connection conexion) {
        this.conexion = conexion;
    }

    /**
     * Inserta una nueva relación entre cliente y reserva en la base de datos
     * @param reservaCliente Relación a insertar
     * @return true si la inserción fue exitosa, false en caso contrario
     */
    public boolean insertar(ReservaCliente reservaCliente) {
        if (conexion == null) {
            System.out.println("Error: No hay conexión a la base de datos");
            return false;
        }

        String sql = "INSERT INTO Reserva_Cliente (id, FK_dni_cliente, FK_reserva) VALUES (?, ?, ?)";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, reservaCliente.getId());
            statement.setInt(2, reservaCliente.getFkDniCliente());
            statement.setInt(3, reservaCliente.getFkReserva());

            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.out.println("Error al insertar relación cliente-reserva: " + e.getMessage());
            return false;
        }
    }

    /**
     * Actualiza una relación existente entre cliente y reserva en la base de datos
     * @param reservaCliente Relación con los datos actualizados
     * @return true si la actualización fue exitosa, false en caso contrario
     */
    public boolean actualizar(ReservaCliente reservaCliente) {
        if (conexion == null) {
            System.out.println("Error: No hay conexión a la base de datos");
            return false;
        }

        String sql = "UPDATE Reserva_Cliente SET FK_dni_cliente = ?, FK_reserva = ? WHERE id = ?";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, reservaCliente.getFkDniCliente());
            statement.setInt(2, reservaCliente.getFkReserva());
            statement.setInt(3, reservaCliente.getId());

            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar relación cliente-reserva: " + e.getMessage());
            return false;
        }
    }

    /**
     * Elimina una relación entre cliente y reserva de la base de datos
     * @param id ID de la relación a eliminar
     * @return true si la eliminación fue exitosa, false en caso contrario
     */
    public boolean eliminar(int id) {
        if (conexion == null) {
            System.out.println("Error: No hay conexión a la base de datos");
            return false;
        }

        String sql = "DELETE FROM Reserva_Cliente WHERE id = ?";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, id);

            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar relación cliente-reserva: " + e.getMessage());
            return false;
        }
    }

    /**
     * Busca una relación entre cliente y reserva por su ID
     * @param id ID de la relación a buscar
     * @return Relación encontrada o null si no existe
     */
    public ReservaCliente buscarPorId(int id) {
        if (conexion == null) {
            System.out.println("Error: No hay conexión a la base de datos");
            return null;
        }

        String sql = "SELECT * FROM Reserva_Cliente WHERE id = ?";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    ReservaCliente reservaCliente = new ReservaCliente();
                    reservaCliente.setId(resultSet.getInt("id"));
                    reservaCliente.setFkDniCliente(resultSet.getInt("FK_dni_cliente"));
                    reservaCliente.setFkReserva(resultSet.getInt("FK_reserva"));
                    return reservaCliente;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar relación cliente-reserva: " + e.getMessage());
        }

        return null;
    }

    /**
     * Obtiene todas las relaciones entre clientes y reservas de la base de datos
     * @return Lista de relaciones
     */
    public List<ReservaCliente> listarTodos() {
        List<ReservaCliente> reservasClientes = new ArrayList<>();

        if (conexion == null) {
            System.out.println("Error: No hay conexión a la base de datos");
            return reservasClientes;
        }

        String sql = "SELECT * FROM Reserva_Cliente";

        try (PreparedStatement statement = conexion.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                ReservaCliente reservaCliente = new ReservaCliente();
                reservaCliente.setId(resultSet.getInt("id"));
                reservaCliente.setFkDniCliente(resultSet.getInt("FK_dni_cliente"));
                reservaCliente.setFkReserva(resultSet.getInt("FK_reserva"));
                reservasClientes.add(reservaCliente);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar relaciones cliente-reserva: " + e.getMessage());
        }

        return reservasClientes;
    }

    /**
     * Busca todas las relaciones para un cliente específico
     * @param dniCliente DNI del cliente
     * @return Lista de relaciones del cliente
     */
    public List<ReservaCliente> buscarPorCliente(int dniCliente) {
        List<ReservaCliente> reservasClientes = new ArrayList<>();

        if (conexion == null) {
            System.out.println("Error: No hay conexión a la base de datos");
            return reservasClientes;
        }

        String sql = "SELECT * FROM Reserva_Cliente WHERE FK_dni_cliente = ?";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, dniCliente);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    ReservaCliente reservaCliente = new ReservaCliente();
                    reservaCliente.setId(resultSet.getInt("id"));
                    reservaCliente.setFkDniCliente(resultSet.getInt("FK_dni_cliente"));
                    reservaCliente.setFkReserva(resultSet.getInt("FK_reserva"));
                    reservasClientes.add(reservaCliente);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar relaciones por cliente: " + e.getMessage());
        }

        return reservasClientes;
    }

    /**
     * Busca todas las relaciones para una reserva específica
     * @param idReserva ID de la reserva
     * @return Lista de relaciones de la reserva
     */
    public List<ReservaCliente> buscarPorReserva(int idReserva) {
        List<ReservaCliente> reservasClientes = new ArrayList<>();

        if (conexion == null) {
            System.out.println("Error: No hay conexión a la base de datos");
            return reservasClientes;
        }

        String sql = "SELECT * FROM Reserva_Cliente WHERE FK_reserva = ?";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, idReserva);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    ReservaCliente reservaCliente = new ReservaCliente();
                    reservaCliente.setId(resultSet.getInt("id"));
                    reservaCliente.setFkDniCliente(resultSet.getInt("FK_dni_cliente"));
                    reservaCliente.setFkReserva(resultSet.getInt("FK_reserva"));
                    reservasClientes.add(reservaCliente);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar relaciones por reserva: " + e.getMessage());
        }

        return reservasClientes;
    }
}