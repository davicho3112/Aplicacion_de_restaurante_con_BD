package dao;

import modelo.PlatilloCategoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que gestiona las operaciones CRUD para la tabla Platillo_Categoria
 */
public class PlatilloCategoriaDAO {
    private Connection conexion;

    /**
     * Constructor que recibe una conexión a la base de datos
     * @param conexion Conexión a la base de datos
     */
    public PlatilloCategoriaDAO(Connection conexion) {
        this.conexion = conexion;
    }

    /**
     * Inserta una nueva relación entre platillo y categoría en la base de datos
     * @param platilloCategoria Relación a insertar
     * @return true si la inserción fue exitosa, false en caso contrario
     */
    public boolean insertar(PlatilloCategoria platilloCategoria) {
        if (conexion == null) {
            System.out.println("Error: No hay conexión a la base de datos");
            return false;
        }

        String sql = "INSERT INTO Platillo_Categoria (id, FK_platillo, FK_categoria) VALUES (?, ?, ?)";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, platilloCategoria.getId());
            statement.setInt(2, platilloCategoria.getFkPlatillo());
            statement.setInt(3, platilloCategoria.getFkCategoria());

            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.out.println("Error al insertar relación platillo-categoría: " + e.getMessage());
            return false;
        }
    }

    /**
     * Actualiza una relación existente entre platillo y categoría en la base de datos
     * @param platilloCategoria Relación con los datos actualizados
     * @return true si la actualización fue exitosa, false en caso contrario
     */
    public boolean actualizar(PlatilloCategoria platilloCategoria) {
        if (conexion == null) {
            System.out.println("Error: No hay conexión a la base de datos");
            return false;
        }

        String sql = "UPDATE Platillo_Categoria SET FK_platillo = ?, FK_categoria = ? WHERE id = ?";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, platilloCategoria.getFkPlatillo());
            statement.setInt(2, platilloCategoria.getFkCategoria());
            statement.setInt(3, platilloCategoria.getId());

            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar relación platillo-categoría: " + e.getMessage());
            return false;
        }
    }

    /**
     * Elimina una relación entre platillo y categoría de la base de datos
     * @param id ID de la relación a eliminar
     * @return true si la eliminación fue exitosa, false en caso contrario
     */
    public boolean eliminar(int id) {
        if (conexion == null) {
            System.out.println("Error: No hay conexión a la base de datos");
            return false;
        }

        String sql = "DELETE FROM Platillo_Categoria WHERE id = ?";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, id);

            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar relación platillo-categoría: " + e.getMessage());
            return false;
        }
    }

    /**
     * Busca una relación entre platillo y categoría por su ID
     * @param id ID de la relación a buscar
     * @return Relación encontrada o null si no existe
     */
    public PlatilloCategoria buscarPorId(int id) {
        if (conexion == null) {
            System.out.println("Error: No hay conexión a la base de datos");
            return null;
        }

        String sql = "SELECT * FROM Platillo_Categoria WHERE id = ?";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    PlatilloCategoria platilloCategoria = new PlatilloCategoria();
                    platilloCategoria.setId(resultSet.getInt("id"));
                    platilloCategoria.setFkPlatillo(resultSet.getInt("FK_platillo"));
                    platilloCategoria.setFkCategoria(resultSet.getInt("FK_categoria"));
                    return platilloCategoria;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar relación platillo-categoría: " + e.getMessage());
        }

        return null;
    }

    /**
     * Obtiene todas las relaciones entre platillos y categorías de la base de datos
     * @return Lista de relaciones
     */
    public List<PlatilloCategoria> listarTodos() {
        List<PlatilloCategoria> platilloCategorias = new ArrayList<>();

        if (conexion == null) {
            System.out.println("Error: No hay conexión a la base de datos");
            return platilloCategorias;
        }

        String sql = "SELECT * FROM Platillo_Categoria";

        try (PreparedStatement statement = conexion.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                PlatilloCategoria platilloCategoria = new PlatilloCategoria();
                platilloCategoria.setId(resultSet.getInt("id"));
                platilloCategoria.setFkPlatillo(resultSet.getInt("FK_platillo"));
                platilloCategoria.setFkCategoria(resultSet.getInt("FK_categoria"));
                platilloCategorias.add(platilloCategoria);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar relaciones platillo-categoría: " + e.getMessage());
        }

        return platilloCategorias;
    }

    /**
     * Busca relaciones por ID de platillo
     * @param idPlatillo ID del platillo asociado
     * @return Lista de relaciones asociadas al platillo
     */
    public List<PlatilloCategoria> buscarPorIdPlatillo(int idPlatillo) {
        List<PlatilloCategoria> platilloCategorias = new ArrayList<>();

        if (conexion == null) {
            System.out.println("Error: No hay conexión a la base de datos");
            return platilloCategorias;
        }

        String sql = "SELECT * FROM Platillo_Categoria WHERE FK_platillo = ?";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, idPlatillo);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    PlatilloCategoria platilloCategoria = new PlatilloCategoria();
                    platilloCategoria.setId(resultSet.getInt("id"));
                    platilloCategoria.setFkPlatillo(resultSet.getInt("FK_platillo"));
                    platilloCategoria.setFkCategoria(resultSet.getInt("FK_categoria"));
                    platilloCategorias.add(platilloCategoria);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar relaciones por ID de platillo: " + e.getMessage());
        }

        return platilloCategorias;
    }

    /**
     * Busca relaciones por ID de categoría
     * @param idCategoria ID de la categoría asociada
     * @return Lista de relaciones asociadas a la categoría
     */
    public List<PlatilloCategoria> buscarPorIdCategoria(int idCategoria) {
        List<PlatilloCategoria> platilloCategorias = new ArrayList<>();

        if (conexion == null) {
            System.out.println("Error: No hay conexión a la base de datos");
            return platilloCategorias;
        }

        String sql = "SELECT * FROM Platillo_Categoria WHERE FK_categoria = ?";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, idCategoria);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    PlatilloCategoria platilloCategoria = new PlatilloCategoria();
                    platilloCategoria.setId(resultSet.getInt("id"));
                    platilloCategoria.setFkPlatillo(resultSet.getInt("FK_platillo"));
                    platilloCategoria.setFkCategoria(resultSet.getInt("FK_categoria"));
                    platilloCategorias.add(platilloCategoria);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar relaciones por ID de categoría: " + e.getMessage());
        }

        return platilloCategorias;
    }
}