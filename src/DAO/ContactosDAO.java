package DAO; // Asegúrate de que tu paquete se llama 'DAO'

import Modelo.Contacto; // Asegúrate de que tu clase Contacto esté en el paquete Modelo
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement; // Importación necesaria para Statement
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Clase DAO (Data Access Object) para la entidad Contacto.
 * Maneja las operaciones CRUD (Crear, Leer, Actualizar, Eliminar) con la base de datos.
 */
public class ContactosDAO { // Renombrado de ContactoDAO a ContactosDAO

    /**
     * Guarda un nuevo contacto en la base de datos.
     * Si el contacto se guarda exitosamente, su ID generado por la base de datos
     * será asignado de vuelta al objeto Contacto.
     * @param contacto El objeto Contacto a guardar.
     * @return true si el contacto fue guardado exitosamente, false en caso contrario.
     */
    public boolean guardarContacto(Contacto contacto) {
        // SQL modificado: Eliminada la columna 'direccion'
        String sql = "INSERT INTO contactos (nombres, apellidos, email, telefono) VALUES (?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null; // Para obtener el ID generado

        try {
            conn = ConexionBD.conectar(); // Usando la clase ConexionBD
            if (conn != null) {
                // PreparedStatement.RETURN_GENERATED_KEYS es necesario para obtener el ID
                pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                pstmt.setString(1, contacto.getNombres());
                pstmt.setString(2, contacto.getApellidos());
                pstmt.setString(3, contacto.getEmail());
                pstmt.setString(4, contacto.getTelefono());
                int filasAfectadas = pstmt.executeUpdate();

                if (filasAfectadas > 0) {
                    rs = pstmt.getGeneratedKeys();
                    if (rs.next()) {
                        contacto.setId(rs.getInt(1)); // Asigna el ID generado al objeto Contacto
                    }
                    return true;
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar contacto: " + e.getMessage(), "Error de BD", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close(); // Cerrar ResultSet
                if (pstmt != null) pstmt.close();
                ConexionBD.cerrar(conn); // Cerrar la conexión usando ConexionBD
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * Actualiza un contacto existente en la base de datos.
     * @param contacto El objeto Contacto con los datos actualizados (debe tener un ID válido).
     * @return true si el contacto fue actualizado exitosamente, false en caso contrario.
     */
    public boolean actualizarContacto(Contacto contacto) {
        // SQL modificado: Eliminada la columna 'direccion'
        String sql = "UPDATE contactos SET nombres = ?, apellidos = ?, email = ?, telefono = ? WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = ConexionBD.conectar(); // Usando la clase ConexionBD
            if (conn != null) {
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, contacto.getNombres());
                pstmt.setString(2, contacto.getApellidos());
                pstmt.setString(3, contacto.getEmail());
                pstmt.setString(4, contacto.getTelefono());
                pstmt.setInt(5, contacto.getId()); // El índice del ID cambia de 6 a 5
                int filasAfectadas = pstmt.executeUpdate();
                return filasAfectadas > 0;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar contacto: " + e.getMessage(), "Error de BD", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                ConexionBD.cerrar(conn); // Cerrar la conexión usando ConexionBD
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * Elimina un contacto de la base de datos por su ID.
     * @param id El ID del contacto a eliminar.
     * @return true si el contacto fue eliminado exitosamente, false en caso contrario.
     */
    public boolean eliminarContacto(int id) {
        String sql = "DELETE FROM contactos WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = ConexionBD.conectar(); // Usando la clase ConexionBD
            if (conn != null) {
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, id);
                int filasAfectadas = pstmt.executeUpdate();
                return filasAfectadas > 0;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar contacto: " + e.getMessage(), "Error de BD", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                ConexionBD.cerrar(conn); // Cerrar la conexión usando ConexionBD
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * Obtiene todos los contactos de la base de datos.
     * @return Una lista de objetos Contacto.
     */
    public List<Contacto> obtenerTodosContactos() {
        List<Contacto> contactos = new ArrayList<>();
        // SQL modificado: Eliminada la columna 'direccion'
        String sql = "SELECT id, nombres, apellidos, email, telefono FROM contactos ORDER BY nombres, apellidos";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ConexionBD.conectar(); // Usando la clase ConexionBD
            if (conn != null) {
                pstmt = conn.prepareStatement(sql);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    Contacto contacto = new Contacto();
                    contacto.setId(rs.getInt("id"));
                    contacto.setNombres(rs.getString("nombres"));
                    contacto.setApellidos(rs.getString("apellidos"));
                    contacto.setEmail(rs.getString("email"));
                    contacto.setTelefono(rs.getString("telefono"));
                    contactos.add(contacto);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener contactos: " + e.getMessage(), "Error de BD", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                ConexionBD.cerrar(conn); // Cerrar la conexión usando ConexionBD
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return contactos;
    }

    /**
     * Busca contactos en la base de datos por nombre, apellido, email o teléfono.
     * @param textoBusqueda El texto a buscar.
     * @return Una lista de objetos Contacto que coinciden con la búsqueda.
     */
    public List<Contacto> buscarContactos(String textoBusqueda) {
        List<Contacto> contactos = new ArrayList<>();
        // SQL modificado: Eliminada la columna 'direccion' de la búsqueda
        String sql = "SELECT id, nombres, apellidos, email, telefono FROM contactos WHERE " +
                     "nombres ILIKE ? OR apellidos ILIKE ? OR email ILIKE ? OR telefono ILIKE ? " +
                     "ORDER BY nombres, apellidos";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ConexionBD.conectar();
            if (conn != null) {
                pstmt = conn.prepareStatement(sql);
                String param = "%" + textoBusqueda + "%";
                pstmt.setString(1, param);
                pstmt.setString(2, param);
                pstmt.setString(3, param);
                pstmt.setString(4, param);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    Contacto contacto = new Contacto();
                    contacto.setId(rs.getInt("id"));
                    contacto.setNombres(rs.getString("nombres"));
                    contacto.setApellidos(rs.getString("apellidos"));
                    contacto.setEmail(rs.getString("email"));
                    contacto.setTelefono(rs.getString("telefono"));
                    // contacto.setDireccion(rs.getString("direccion")); // ¡Eliminado!
                    contactos.add(contacto);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar contactos: " + e.getMessage(), "Error de BD", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                ConexionBD.cerrar(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return contactos;
    }

    /**
     * Obtiene un contacto de la base de datos por su ID.
     * @param id El ID del contacto a obtener.
     * @return El objeto Contacto si se encuentra, o null si no existe.
     */
    public Contacto obtenerContactoPorId(int id) {
        Contacto contacto = null;
        // SQL modificado: Eliminada la columna 'direccion'
        String sql = "SELECT id, nombres, apellidos, email, telefono FROM contactos WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ConexionBD.conectar();
            if (conn != null) {
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, id);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    contacto = new Contacto();
                    contacto.setId(rs.getInt("id"));
                    contacto.setNombres(rs.getString("nombres"));
                    contacto.setApellidos(rs.getString("apellidos"));
                    contacto.setEmail(rs.getString("email"));
                    contacto.setTelefono(rs.getString("telefono"));
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener contacto por ID: " + e.getMessage(), "Error de BD", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                ConexionBD.cerrar(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return contacto;
    }
}
