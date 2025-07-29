package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * Clase para gestionar la conexión a la base de datos PostgreSQL.
 */
public class ConexionBD {

    // URL de conexión a la base de datos PostgreSQL.
    private static final String URL = "jdbc:postgresql://localhost:5432/agenda_bd";
    private static final String USUARIO = "postgres"; //  usuario de PostgreSQL
    private static final String CONTRASENA = "12345"; //  contraseña de PostgreSQL

    /**
     * Establece una conexión con la base de datos.
     * @return Un objeto Connection si la conexión es exitosa, o null en caso de error.
     */
    public static Connection conectar() {
        Connection conexion = null;
        try {
            // Cargar el driver de PostgreSQL (esto no es estrictamente necesario en JDBC 4.0+,
            // pero es una buena práctica para asegurar la disponibilidad del driver)
            Class.forName("org.postgresql.Driver");

            // Establecer la conexión
            conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
            // JOptionPane.showMessageDialog(null, "Conexión exitosa a la base de datos.", "Conexión", JOptionPane.INFORMATION_MESSAGE);
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error: Driver de PostgreSQL no encontrado. " + e.getMessage(), "Error de Conexión", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos.\nVerifica la URL, usuario y contraseña.\n" + e.getMessage(), "Error de Conexión", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return conexion;
    }

    /**
     * Cierra una conexión a la base de datos.
     * @param conexion El objeto Connection a cerrar.
     */
    public static void cerrar(Connection conexion) {
        if (conexion != null) {
            try {
                conexion.close();
                // System.out.println("Conexión cerrada."); // Para depuración, puedes descomentar
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al cerrar la conexión a la base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }

    /**
     * Método main para probar la conexión a la base de datos.
     * Puedes ejecutar este método para verificar si la conexión funciona de forma independiente.
     */
    public static void main(String[] args) {
        Connection testConnection = conectar();
        if (testConnection != null) {
            JOptionPane.showMessageDialog(null, "Prueba de conexión exitosa.", "Prueba de Conexión", JOptionPane.INFORMATION_MESSAGE);
            cerrar(testConnection);
        } else {
            JOptionPane.showMessageDialog(null, "Fallo en la prueba de conexión.", "Prueba de Conexión", JOptionPane.ERROR_MESSAGE);
        }
    }
}
