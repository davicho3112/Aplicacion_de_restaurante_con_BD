package dao;

import modelo.Platillo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que gestiona las operaciones CRUD para la tabla Platillo
 */
public class PlatilloDAO {
    private Connection conexion;

    /**
     * Constructor que recibe una conexión a la base de datos
     * @param conexion Conexión a la base de datos
     */
    public PlatilloDAO(Connection conexion) {
        this.conexion = conexion;
    }

    /**
     * Inserta un nuevo platillo en la base de datos
     * @param platillo Platillo a insertar
     * @return true si la inserción fue exitosa, false en caso contrario
     */
    public boolean insertar(Platillo platillo) {
        if (conexion == null) {
            System.out.println("Error: No hay conexión a la base de datos");
            return false;
        }

        String sql = "INSERT INTO Platillo (id, nombre, precio, acompañantes) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, platillo.getId());
            statement.setString(2, platillo.getNombre());
            statement.setBigDecimal(3, platillo.getPrecio());
            statement.setString(4, platillo.getAcompanantes());

            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.out.println("Error al insertar platillo: " + e.getMessage());
            return false;
        }
    }

    /**
     * Actualiza un platillo existente en la base de datos
     * @param platillo Platillo con los datos actualizados
     * @return true si la actualización fue exitosa, false en caso contrario
     */
    public boolean actualizar(Platillo platillo) {
        if (conexion == null) {
            System.out.println("Error: No hay conexión a la base de datos");
            return false;
        }

        String sql = "UPDATE Platillo SET nombre = ?, precio = ?, acompañantes = ? WHERE id = ?";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, platillo.getNombre());
            statement.setBigDecimal(2, platillo.getPrecio());
            statement.setString(3, platillo.getAcompanantes());
            statement.setInt(4, platillo.getId());

            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar platillo: " + e.getMessage());
            return false;
        }
    }

    /**
     * Elimina un platillo de la base de datos
     * @param id ID del platillo a eliminar
     * @return true si la eliminación fue exitosa, false en caso contrario
     */
    public boolean eliminar(int id) {
        if (conexion == null) {
            System.out.println("Error: No hay conexión a la base de datos");
            return false;
        }

        String sql = "DELETE FROM Platillo WHERE id = ?";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, id);

            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar platillo: " + e.getMessage());
            return false;
        }
    }

    /**
     * Busca un platillo por su ID
     * @param id ID del platillo a buscar
     * @return Platillo encontrado o null si no existe
     */
    public Platillo buscarPorId(int id) {
        if (conexion == null) {
            System.out.println("Error: No hay conexión a la base de datos");
            return null;
        }

        String sql = "SELECT * FROM Platillo WHERE id = ?";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Platillo platillo = new Platillo();
                    platillo.setId(resultSet.getInt("id"));
                    platillo.setNombre(resultSet.getString("nombre"));
                    platillo.setPrecio(resultSet.getBigDecimal("precio"));
                    platillo.setAcompanantes(resultSet.getString("acompañantes"));
                    return platillo;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar platillo: " + e.getMessage());
        }

        return null;
    }

    /**
     * Obtiene todos los platillos de la base de datos
     * @return Lista de platillos
     */
    public List<Platillo> listarTodos() {
        List<Platillo> platillos = new ArrayList<>();

        if (conexion == null) {
            System.out.println("Error: No hay conexión a la base de datos");
            return platillos;
        }

        String sql = "SELECT * FROM Platillo";

        try (PreparedStatement statement = conexion.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Platillo platillo = new Platillo();
                platillo.setId(resultSet.getInt("id"));
                platillo.setNombre(resultSet.getString("nombre"));
                platillo.setPrecio(resultSet.getBigDecimal("precio"));
                platillo.setAcompanantes(resultSet.getString("acompañantes"));
                platillos.add(platillo);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar platillos: " + e.getMessage());
        }

        return platillos;
    }

    /**
     * Busca platillos por nombre (búsqueda parcial)
     * @param nombre Nombre o parte del nombre a buscar
     * @return Lista de platillos que coinciden con la búsqueda
     */
    public List<Platillo> buscarPorNombre(String nombre) {
        List<Platillo> platillos = new ArrayList<>();

        if (conexion == null) {
            System.out.println("Error: No hay conexión a la base de datos");
            return platillos;
        }

        String sql = "SELECT * FROM Platillo WHERE nombre LIKE ?";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, "%" + nombre + "%");

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Platillo platillo = new Platillo();
                    platillo.setId(resultSet.getInt("id"));
                    platillo.setNombre(resultSet.getString("nombre"));
                    platillo.setPrecio(resultSet.getBigDecimal("precio"));
                    platillo.setAcompanantes(resultSet.getString("acompañantes"));
                    platillos.add(platillo);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar platillos por nombre: " + e.getMessage());
        }

        return platillos;
    }

    /**
     * Busca platillos por rango de precio
     * @param precioMinimo Precio mínimo
     * @param precioMaximo Precio máximo
     * @return Lista de platillos dentro del rango de precio
     */
    public List<Platillo> buscarPorRangoPrecio(BigDecimal precioMinimo, BigDecimal precioMaximo) {
        List<Platillo> platillos = new ArrayList<>();

        if (conexion == null) {
            System.out.println("Error: No hay conexión a la base de datos");
            return platillos;
        }

        String sql = "SELECT * FROM Platillo WHERE precio BETWEEN ? AND ?";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setBigDecimal(1, precioMinimo);
            statement.setBigDecimal(2, precioMaximo);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Platillo platillo = new Platillo();
                    platillo.setId(resultSet.getInt("id"));
                    platillo.setNombre(resultSet.getString("nombre"));
                    platillo.setPrecio(resultSet.getBigDecimal("precio"));
                    platillo.setAcompanantes(resultSet.getString("acompañantes"));
                    platillos.add(platillo);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar platillos por rango de precio: " + e.getMessage());
        }

        return platillos;
    }
}