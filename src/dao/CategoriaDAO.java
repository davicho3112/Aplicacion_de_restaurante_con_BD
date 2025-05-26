package dao;

import modelo.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que gestiona las operaciones CRUD para la tabla Categoria
 */
public class CategoriaDAO {
    private Connection conexion;

    /**
     * Constructor que recibe una conexión a la base de datos
     * @param conexion Conexión a la base de datos
     */
    public CategoriaDAO(Connection conexion) {
        this.conexion = conexion;
    }

    /**
     * Inserta una nueva categoría en la base de datos
     * @param categoria Categoría a insertar
     * @return true si la inserción fue exitosa, false en caso contrario
     */
    public boolean insertar(Categoria categoria) {
        if (conexion == null) {
            System.out.println("Error: No hay conexión a la base de datos");
            return false;
        }

        String sql = "INSERT INTO Categoria (id, nombre, descripcion) VALUES (?, ?, ?)";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, categoria.getId());
            statement.setString(2, categoria.getNombre());
            statement.setString(3, categoria.getDescripcion());

            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.out.println("Error al insertar categoría: " + e.getMessage());
            return false;
        }
    }

    /**
     * Actualiza una categoría existente en la base de datos
     * @param categoria Categoría con los datos actualizados
     * @return true si la actualización fue exitosa, false en caso contrario
     */
    public boolean actualizar(Categoria categoria) {
        if (conexion == null) {
            System.out.println("Error: No hay conexión a la base de datos");
            return false;
        }

        String sql = "UPDATE Categoria SET nombre = ?, descripcion = ? WHERE id = ?";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, categoria.getNombre());
            statement.setString(2, categoria.getDescripcion());
            statement.setInt(3, categoria.getId());

            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar categoría: " + e.getMessage());
            return false;
        }
    }

    /**
     * Elimina una categoría de la base de datos
     * @param id ID de la categoría a eliminar
     * @return true si la eliminación fue exitosa, false en caso contrario
     */
    public boolean eliminar(int id) {
        if (conexion == null) {
            System.out.println("Error: No hay conexión a la base de datos");
            return false;
        }

        String sql = "DELETE FROM Categoria WHERE id = ?";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, id);

            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar categoría: " + e.getMessage());
            return false;
        }
    }

    /**
     * Busca una categoría por su ID
     * @param id ID de la categoría a buscar
     * @return Categoría encontrada o null si no existe
     */
    public Categoria buscarPorId(int id) {
        if (conexion == null) {
            System.out.println("Error: No hay conexión a la base de datos");
            return null;
        }

        String sql = "SELECT * FROM Categoria WHERE id = ?";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Categoria categoria = new Categoria();
                    categoria.setId(resultSet.getInt("id"));
                    categoria.setNombre(resultSet.getString("nombre"));
                    categoria.setDescripcion(resultSet.getString("descripcion"));
                    return categoria;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar categoría: " + e.getMessage());
        }

        return null;
    }

    /**
     * Obtiene todas las categorías de la base de datos
     * @return Lista de categorías
     */
    public List<Categoria> listarTodos() {
        List<Categoria> categorias = new ArrayList<>();

        if (conexion == null) {
            System.out.println("Error: No hay conexión a la base de datos");
            return categorias;
        }

        String sql = "SELECT * FROM Categoria";

        try (PreparedStatement statement = conexion.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Categoria categoria = new Categoria();
                categoria.setId(resultSet.getInt("id"));
                categoria.setNombre(resultSet.getString("nombre"));
                categoria.setDescripcion(resultSet.getString("descripcion"));
                categorias.add(categoria);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar categorías: " + e.getMessage());
        }

        return categorias;
    }

    /**
     * Busca categorías por nombre (búsqueda parcial)
     * @param nombre Nombre o parte del nombre a buscar
     * @return Lista de categorías que coinciden con la búsqueda
     */
    public List<Categoria> buscarPorNombre(String nombre) {
        List<Categoria> categorias = new ArrayList<>();

        if (conexion == null) {
            System.out.println("Error: No hay conexión a la base de datos");
            return categorias;
        }

        String sql = "SELECT * FROM Categoria WHERE nombre LIKE ?";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, "%" + nombre + "%");

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Categoria categoria = new Categoria();
                    categoria.setId(resultSet.getInt("id"));
                    categoria.setNombre(resultSet.getString("nombre"));
                    categoria.setDescripcion(resultSet.getString("descripcion"));
                    categorias.add(categoria);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar categorías por nombre: " + e.getMessage());
        }

        return categorias;
    }
}