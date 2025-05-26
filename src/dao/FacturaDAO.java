package dao;

import modelo.Factura;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que gestiona las operaciones CRUD para la tabla Factura
 */
public class FacturaDAO {
    private Connection conexion;

    /**
     * Constructor que recibe una conexión a la base de datos
     * @param conexion Conexión a la base de datos
     */
    public FacturaDAO(Connection conexion) {
        this.conexion = conexion;
    }

    /**
     * Inserta una nueva factura en la base de datos
     * @param factura Factura a insertar
     * @return true si la inserción fue exitosa, false en caso contrario
     */
    public boolean insertar(Factura factura) {
        if (conexion == null) {
            System.out.println("Error: No hay conexión a la base de datos");
            return false;
        }

        String sql = "INSERT INTO Factura (id, FK_id_pago, FK_id_reserva) VALUES (?, ?, ?)";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, factura.getId());
            statement.setInt(2, factura.getFkIdPago());
            statement.setInt(3, factura.getFkIdReserva());

            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.out.println("Error al insertar factura: " + e.getMessage());
            return false;
        }
    }

    /**
     * Actualiza una factura existente en la base de datos
     * @param factura Factura con los datos actualizados
     * @return true si la actualización fue exitosa, false en caso contrario
     */
    public boolean actualizar(Factura factura) {
        if (conexion == null) {
            System.out.println("Error: No hay conexión a la base de datos");
            return false;
        }

        String sql = "UPDATE Factura SET FK_id_pago = ?, FK_id_reserva = ? WHERE id = ?";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, factura.getFkIdPago());
            statement.setInt(2, factura.getFkIdReserva());
            statement.setInt(3, factura.getId());

            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar factura: " + e.getMessage());
            return false;
        }
    }

    /**
     * Elimina una factura de la base de datos
     * @param id ID de la factura a eliminar
     * @return true si la eliminación fue exitosa, false en caso contrario
     */
    public boolean eliminar(int id) {
        if (conexion == null) {
            System.out.println("Error: No hay conexión a la base de datos");
            return false;
        }

        String sql = "DELETE FROM Factura WHERE id = ?";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, id);

            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar factura: " + e.getMessage());
            return false;
        }
    }

    /**
     * Busca una factura por su ID
     * @param id ID de la factura a buscar
     * @return Factura encontrada o null si no existe
     */
    public Factura buscarPorId(int id) {
        if (conexion == null) {
            System.out.println("Error: No hay conexión a la base de datos");
            return null;
        }

        String sql = "SELECT * FROM Factura WHERE id = ?";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Factura factura = new Factura();
                    factura.setId(resultSet.getInt("id"));
                    factura.setFkIdPago(resultSet.getInt("FK_id_pago"));
                    factura.setFkIdReserva(resultSet.getInt("FK_id_reserva"));
                    return factura;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar factura: " + e.getMessage());
        }

        return null;
    }

    /**
     * Obtiene todas las facturas de la base de datos
     * @return Lista de facturas
     */
    public List<Factura> listarTodos() {
        List<Factura> facturas = new ArrayList<>();

        if (conexion == null) {
            System.out.println("Error: No hay conexión a la base de datos");
            return facturas;
        }

        String sql = "SELECT * FROM Factura";

        try (PreparedStatement statement = conexion.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Factura factura = new Factura();
                factura.setId(resultSet.getInt("id"));
                factura.setFkIdPago(resultSet.getInt("FK_id_pago"));
                factura.setFkIdReserva(resultSet.getInt("FK_id_reserva"));
                facturas.add(factura);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar facturas: " + e.getMessage());
        }

        return facturas;
    }

    /**
     * Busca facturas por ID de pago
     * @param idPago ID del pago asociado
     * @return Lista de facturas asociadas al pago
     */
    public List<Factura> buscarPorIdPago(int idPago) {
        List<Factura> facturas = new ArrayList<>();

        if (conexion == null) {
            System.out.println("Error: No hay conexión a la base de datos");
            return facturas;
        }

        String sql = "SELECT * FROM Factura WHERE FK_id_pago = ?";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, idPago);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Factura factura = new Factura();
                    factura.setId(resultSet.getInt("id"));
                    factura.setFkIdPago(resultSet.getInt("FK_id_pago"));
                    factura.setFkIdReserva(resultSet.getInt("FK_id_reserva"));
                    facturas.add(factura);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar facturas por ID de pago: " + e.getMessage());
        }

        return facturas;
    }

    /**
     * Busca facturas por ID de reserva
     * @param idReserva ID de la reserva asociada
     * @return Lista de facturas asociadas a la reserva
     */
    public List<Factura> buscarPorIdReserva(int idReserva) {
        List<Factura> facturas = new ArrayList<>();

        if (conexion == null) {
            System.out.println("Error: No hay conexión a la base de datos");
            return facturas;
        }

        String sql = "SELECT * FROM Factura WHERE FK_id_reserva = ?";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, idReserva);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Factura factura = new Factura();
                    factura.setId(resultSet.getInt("id"));
                    factura.setFkIdPago(resultSet.getInt("FK_id_pago"));
                    factura.setFkIdReserva(resultSet.getInt("FK_id_reserva"));
                    facturas.add(factura);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar facturas por ID de reserva: " + e.getMessage());
        }

        return facturas;
    }
}