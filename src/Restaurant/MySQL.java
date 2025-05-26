package Restaurant;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Clase que proporciona métodos estáticos para la conexión a la base de datos MySQL
 */
public class MySQL {
    // Constantes para la conexión a la base de datos
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/restaurante";
    private static final String USER = "root";
    private static final String PASSWORD = "admin123";
    
    /**
     * Obtiene una conexión a la base de datos
     * @return Conexión a la base de datos o null si ocurre un error
     */
    public static Connection obtenerConexion() {
        Connection conexion = null;

        try {
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexion exitosa");
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos");
            System.out.println(e.getMessage());
        }

        return conexion;
    }
    
    /**
     * Cierra una conexión a la base de datos
     * @param conexion Conexión a cerrar
     */
    public static void cerrarConexion(Connection conexion) {
        if (conexion != null) {
            try {
                conexion.close();
                System.out.println("Conexion cerrada");
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexion");
                System.out.println(e.getMessage());
            }
        }
    }
}