package Modelo;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author sebas
 */
public class Evento {
    private int id;
    private String nombreEvento; 
    private LocalDate fecha;
    private LocalTime hora;
    private String descripcion;

    public Evento() {
    }

    public Evento(int id, String nombreEvento, LocalDate fecha, LocalTime hora, String descripcion) {
        this.id = id;
        this.nombreEvento = nombreEvento;
        this.fecha = fecha;
        this.hora = hora;
        this.descripcion = descripcion;    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
