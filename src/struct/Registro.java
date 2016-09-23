package struct;



/**
 *
 * @author Eduardo
 */
public class Registro {

private String idUsuarioReg;
private int id_conferenciaReg;
private String fecha;
private String hora;

    public Registro() {
    }

    public Registro(String idUsuarioReg, int id_conferenciaReg, String fecha, String hora) {
        this.idUsuarioReg = idUsuarioReg;
        this.id_conferenciaReg = id_conferenciaReg;
        this.fecha = fecha;
        this.hora = hora;
    }

    public String getIdUsuarioReg() {
        return idUsuarioReg;
    }

    public void setIdUsuarioReg(String idUsuarioReg) {
        this.idUsuarioReg = idUsuarioReg;
    }

    public int getId_conferenciaReg() {
        return id_conferenciaReg;
    }

    public void setId_conferenciaReg(int id_conferenciaReg) {
        this.id_conferenciaReg = id_conferenciaReg;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    

    
    
    
    
}
