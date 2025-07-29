package DAO;

import Modelo.Evento; // Asegúrate de que tu clase Evento esté en el paquete Modelo
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date; // Para convertir LocalDate a java.sql.Date
import java.sql.Time; // Para convertir LocalTime a java.sql.Time
import java.sql.Statement; // Importación necesaria para Statement
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Clase DAO (Data Access Object) para la entidad Evento.
 * Maneja las operaciones CRUD (Crear, Leer, Actualizar, Eliminar) con la base de datos.
 */
public class EventosDAO {

    /**
     * Guarda un nuevo evento en la base de datos.
     * Si el evento se guarda exitosamente, su ID generado por la base de datos
     * será asignado de vuelta al objeto Evento.
     * @param evento El objeto Evento a guardar.
     * @return true si el evento fue guardado exitosamente, false en caso contrario.
     */
    public boolean guardarEvento(Evento evento) {
        // SQL modificado: Eliminada la columna 'ubicacion'
        String sql = "INSERT INTO eventos (nombre_evento, fecha, hora, descripcion) VALUES (?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = ConexionBD.conectar();
            if (conn != null) {
                pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                pstmt.setString(1, evento.getNombreEvento());
                pstmt.setDate(2, Date.valueOf(evento.getFecha()));
                pstmt.setTime(3, Time.valueOf(evento.getHora()));
                pstmt.setString(4, evento.getDescripcion());
                // pstmt.setString(5, evento.getUbicacion()); // ¡Eliminado!
                int filasAfectadas = pstmt.executeUpdate();

                if (filasAfectadas > 0) {
                    rs = pstmt.getGeneratedKeys();
                    if (rs.next()) {
                        evento.setId(rs.getInt(1));
                    }
                    return true;
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar evento: " + e.getMessage(), "Error de BD", JOptionPane.ERROR_MESSAGE);
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
        return false;
    }

    /**
     * Actualiza un evento existente en la base de datos.
     * @param evento El objeto Evento con los datos actualizados (debe tener un ID válido).
     * @return true si el evento fue actualizado exitosamente, false en caso contrario.
     */
    public boolean actualizarEvento(Evento evento) {
        // SQL modificado: Eliminada la columna 'ubicacion'
        String sql = "UPDATE eventos SET nombre_evento = ?, fecha = ?, hora = ?, descripcion = ? WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = ConexionBD.conectar();
            if (conn != null) {
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, evento.getNombreEvento());
                pstmt.setDate(2, Date.valueOf(evento.getFecha()));
                pstmt.setTime(3, Time.valueOf(evento.getHora()));
                pstmt.setString(4, evento.getDescripcion());
                // pstmt.setString(5, evento.getUbicacion()); // ¡Eliminado!
                pstmt.setInt(5, evento.getId()); // El índice del ID cambia de 6 a 5
                int filasAfectadas = pstmt.executeUpdate();
                return filasAfectadas > 0;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar evento: " + e.getMessage(), "Error de BD", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                ConexionBD.cerrar(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * Elimina un evento de la base de datos por su ID.
     * @param id El ID del evento a eliminar.
     * @return true si el evento fue eliminado exitosamente, false en caso contrario.
     */
    public boolean eliminarEvento(int id) {
        String sql = "DELETE FROM eventos WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = ConexionBD.conectar();
            if (conn != null) {
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, id);
                int filasAfectadas = pstmt.executeUpdate();
                return filasAfectadas > 0;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar evento: " + e.getMessage(), "Error de BD", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                ConexionBD.cerrar(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * Obtiene todos los eventos de la base de datos.
     * @return Una lista de objetos Evento.
     */
    public List<Evento> obtenerTodosEventos() {
        List<Evento> eventos = new ArrayList<>();
        // SQL modificado: Eliminada la columna 'ubicacion'
        String sql = "SELECT id, nombre_evento, fecha, hora, descripcion FROM eventos ORDER BY fecha, hora";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ConexionBD.conectar();
            if (conn != null) {
                pstmt = conn.prepareStatement(sql);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    Evento evento = new Evento();
                    evento.setId(rs.getInt("id"));
                    evento.setNombreEvento(rs.getString("nombre_evento"));
                    evento.setFecha(rs.getDate("fecha").toLocalDate());
                    evento.setHora(rs.getTime("hora").toLocalTime());
                    evento.setDescripcion(rs.getString("descripcion"));
                    // evento.setUbicacion(rs.getString("ubicacion")); // ¡Eliminado!
                    eventos.add(evento);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener eventos: " + e.getMessage(), "Error de BD", JOptionPane.ERROR_MESSAGE);
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
        return eventos;
    }

    /**
     * Obtiene un evento de la base de datos por su ID.
     * @param id El ID del evento a obtener.
     * @return El objeto Evento si se encuentra, o null si no existe.
     */
    public Evento obtenerEventoPorId(int id) {
        Evento evento = null;
        // SQL modificado: Eliminada la columna 'ubicacion'
        String sql = "SELECT id, nombre_evento, fecha, hora, descripcion FROM eventos WHERE id = ?";
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
                    evento = new Evento();
                    evento.setId(rs.getInt("id"));
                    evento.setNombreEvento(rs.getString("nombre_evento"));
                    evento.setFecha(rs.getDate("fecha").toLocalDate());
                    evento.setHora(rs.getTime("hora").toLocalTime());
                    evento.setDescripcion(rs.getString("descripcion"));
                    // evento.setUbicacion(rs.getString("ubicacion")); // ¡Eliminado!
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener evento por ID: " + e.getMessage(), "Error de BD", JOptionPane.ERROR_MESSAGE);
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
        return evento;
    }
}
