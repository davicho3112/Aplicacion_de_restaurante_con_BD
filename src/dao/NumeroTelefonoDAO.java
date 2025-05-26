package dao;

import modelo.NumeroTelefono;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que gestiona las operaciones CRUD para la tabla Numero_Telefono
 */
public class NumeroTelefonoDAO {
    private Connection conexion;

    /**
     * Constructor que recibe una conexión a la base de datos
     * @param conexion Conexión a la base de datos
     */
    public NumeroTelefonoDAO(Connection conexion) {
        this.conexion = conexion;
    }

    /**
     * Inserta un nuevo número de teléfono en la base de datos
     * @param numeroTelefono Número de teléfono a insertar
     * @return true si la inserción fue exitosa, false en caso contrario
     */
    public boolean insertar(NumeroTelefono numeroTelefono) {
        if (conexion == null) {
            System.out.println("Error: No hay conexión a la base de datos");
            return false;
        }

        String sql = "INSERT INTO Numero_Telefono (id, numero, prefijo, tipo_uso) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, numeroTelefono.getId());
            statement.setLong(2, numeroTelefono.getNumero());
            statement.setString(3, numeroTelefono.getPrefijo());
            statement.setString(4, numeroTelefono.getTipoUso());

            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.out.println("Error al insertar número de teléfono: " + e.getMessage());
            return false;
        }
    }

    /**
     * Actualiza un número de teléfono existente en la base de datos
     * @param numeroTelefono Número de teléfono con los datos actualizados
     * @return true si la actualización fue exitosa, false en caso contrario
     */
    public boolean actualizar(NumeroTelefono numeroTelefono) {
        if (conexion == null) {
            System.out.println("Error: No hay conexión a la base de datos");
            return false;
        }

        String sql = "UPDATE Numero_Telefono SET numero = ?, prefijo = ?, tipo_uso = ? WHERE id = ?";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setLong(1, numeroTelefono.getNumero());
            statement.setString(2, numeroTelefono.getPrefijo());
            statement.setString(3, numeroTelefono.getTipoUso());
            statement.setInt(4, numeroTelefono.getId());

            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar número de teléfono: " + e.getMessage());
            return false;
        }
    }

    /**
     * Elimina un número de teléfono de la base de datos
     * @param id ID del número de teléfono a eliminar
     * @return true si la eliminación fue exitosa, false en caso contrario
     */
    public boolean eliminar(int id) {
        if (conexion == null) {
            System.out.println("Error: No hay conexión a la base de datos");
            return false;
        }

        String sql = "DELETE FROM Numero_Telefono WHERE id = ?";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, id);

            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar número de teléfono: " + e.getMessage());
            return false;
        }
    }

    /**
     * Busca un número de teléfono por su ID
     * @param id ID del número de teléfono a buscar
     * @return Número de teléfono encontrado o null si no existe
     */
    public NumeroTelefono buscarPorId(int id) {
        if (conexion == null) {
            System.out.println("Error: No hay conexión a la base de datos");
            return null;
        }

        String sql = "SELECT * FROM Numero_Telefono WHERE id = ?";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    NumeroTelefono numeroTelefono = new NumeroTelefono();
                    numeroTelefono.setId(resultSet.getInt("id"));
                    numeroTelefono.setNumero(resultSet.getLong("numero"));
                    numeroTelefono.setPrefijo(resultSet.getString("prefijo"));
                    numeroTelefono.setTipoUso(resultSet.getString("tipo_uso"));
                    return numeroTelefono;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar número de teléfono: " + e.getMessage());
        }

        return null;
    }

    /**
     * Obtiene todos los números de teléfono de la base de datos
     * @return Lista de números de teléfono
     */
    public List<NumeroTelefono> listarTodos() {
        List<NumeroTelefono> numerosTelefono = new ArrayList<>();

        if (conexion == null) {
            System.out.println("Error: No hay conexión a la base de datos");
            return numerosTelefono;
        }

        String sql = "SELECT * FROM Numero_Telefono";

        try (PreparedStatement statement = conexion.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                NumeroTelefono numeroTelefono = new NumeroTelefono();
                numeroTelefono.setId(resultSet.getInt("id"));
                numeroTelefono.setNumero(resultSet.getLong("numero"));
                numeroTelefono.setPrefijo(resultSet.getString("prefijo"));
                numeroTelefono.setTipoUso(resultSet.getString("tipo_uso"));
                numerosTelefono.add(numeroTelefono);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar números de teléfono: " + e.getMessage());
        }

        return numerosTelefono;
    }
}