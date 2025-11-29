import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // URL de conexión a la base de datos SQLite
    public static final String URL = "jdbc:sqlite:./db/SistemaDeRentaDeSillasYMesas_DB.db";

    // Variable para guardar la conexión
    public static Connection connection = null;

    // Obtener la conexion con la base de datos
    public static Connection getConnection() {
        if (connection == null) {
            try {
                if (connection == null || connection.isClosed()) {
                    connection = DriverManager.getConnection(URL);
                    System.out.println("Conexion exitosa");
                }
            } catch (SQLException e) {
                System.out.println("Error al conectar la base de datos");
                e.printStackTrace();
            }
        }
        return connection;
    }

    // cerrar la conexion con la base de datos
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexion cerrada");
                connection = null;
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexion");
                e.printStackTrace();
            }
        }
    }

    // verificar si la conexion esta activa
    public static boolean isConnected() {
        try {
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }
}
