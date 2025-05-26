package dao;

import modelo.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que gestiona las operaciones CRUD para la tabla Cliente
 */
public class ClienteDAO {
    private Connection conexion;

    /**
     * Constructor que recibe una conexión a la base de datos
     * @param conexion Conexión a la base de datos
     */
    public ClienteDAO(Connection conexion) {
        this.conexion = conexion;
    }

    /**
     * Inserta un nuevo cliente en la base de datos
     * @param cliente Cliente a insertar
     * @return true si la inserción fue exitosa, false en caso contrario
     */
    public boolean insertar(Cliente cliente) {
        if (conexion == null) {
            System.out.println("Error: No hay conexión a la base de datos");
            return false;
        }

        String sql = "INSERT INTO Cliente (dni, nombre, fecha_nacimiento) VALUES (?, ?, ?)";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, cliente.getDni());
            statement.setString(2, cliente.getNombre());
            statement.setDate(3, cliente.getFechaNacimiento());

            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.out.println("Error al insertar cliente: " + e.getMessage());
            return false;
        }
    }

    /**
     * Actualiza un cliente existente en la base de datos
     * @param cliente Cliente con los datos actualizados
     * @return true si la actualización fue exitosa, false en caso contrario
     */
    public boolean actualizar(Cliente cliente) {
        if (conexion == null) {
            System.out.println("Error: No hay conexión a la base de datos");
            return false;
        }

        String sql = "UPDATE Cliente SET nombre = ?, fecha_nacimiento = ? WHERE dni = ?";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, cliente.getNombre());
            statement.setDate(2, cliente.getFechaNacimiento());
            statement.setInt(3, cliente.getDni());

            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar cliente: " + e.getMessage());
            return false;
        }
    }

    /**
     * Elimina un cliente de la base de datos
     * @param dni DNI del cliente a eliminar
     * @return true si la eliminación fue exitosa, false en caso contrario
     */
    public boolean eliminar(int dni) {
        if (conexion == null) {
            System.out.println("Error: No hay conexión a la base de datos");
            return false;
        }

        String sql = "DELETE FROM Cliente WHERE dni = ?";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, dni);

            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar cliente: " + e.getMessage());
            return false;
        }
    }

    /**
     * Busca un cliente por su DNI
     * @param dni DNI del cliente a buscar
     * @return Cliente encontrado o null si no existe
     */
    public Cliente buscarPorDni(int dni) {
        if (conexion == null) {
            System.out.println("Error: No hay conexión a la base de datos");
            return null;
        }

        String sql = "SELECT * FROM Cliente WHERE dni = ?";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, dni);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Cliente cliente = new Cliente();
                    cliente.setDni(resultSet.getInt("dni"));
                    cliente.setNombre(resultSet.getString("nombre"));
                    cliente.setFechaNacimiento(resultSet.getDate("fecha_nacimiento"));
                    return cliente;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar cliente: " + e.getMessage());
        }

        return null;
    }

    /**
     * Obtiene todos los clientes de la base de datos
     * @return Lista de clientes
     */
    public List<Cliente> listarTodos() {
        List<Cliente> clientes = new ArrayList<>();

        if (conexion == null) {
            System.out.println("Error: No hay conexión a la base de datos");
            return clientes;
        }

        String sql = "SELECT * FROM Cliente";

        try (PreparedStatement statement = conexion.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Cliente cliente = new Cliente();
                cliente.setDni(resultSet.getInt("dni"));
                cliente.setNombre(resultSet.getString("nombre"));
                cliente.setFechaNacimiento(resultSet.getDate("fecha_nacimiento"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar clientes: " + e.getMessage());
        }

        return clientes;
    }
}
