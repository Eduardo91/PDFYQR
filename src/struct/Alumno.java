package struct;
/**
 *
 * @author Eduardo
 */
public class Alumno {

private String id;    
private String Nombre;
private String Correo;
private String Contrasena;
private String Universidad;
private int perfil;

    public Alumno() {
    }

    public Alumno(String id,String Nombre, String Correo, String Contrasena, String Universidad,int perfil) {
        this.id=id;
        this.Nombre = Nombre;
        this.Correo = Correo;
        this.Contrasena = Contrasena;
        this.Universidad = Universidad;
        this.perfil=perfil;
    }
////############################################################################

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    public String getContrasena() {
        return Contrasena;
    }

    public void setContrasena(String Contrasena) {
        this.Contrasena = Contrasena;
    }

    public String getUniversidad() {
        return Universidad;
    }

    public void setUniversidad(String Universidad) {
        this.Universidad = Universidad;
    }

    public int getPerfil() {
        return perfil;
    }

    public void setPerfil(int perfil) {
        this.perfil = perfil;
    }

    
    
       
    
    
}
