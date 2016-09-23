package struct;

/**
 *
 * @author Eduardo
 */
public class Conferencia {
    
private int id_conferencia;
private String titulo;
private String conferencista;

    public Conferencia() {
    }

    public Conferencia(int id_conferencia, String titulo, String conferencista) {
        this.id_conferencia = id_conferencia;
        this.titulo = titulo;
        this.conferencista = conferencista;
    }

    public int getId_conferencia() {
        return id_conferencia;
    }

    public void setId_conferencia(int id_conferencia) {
        this.id_conferencia = id_conferencia;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getConferencista() {
        return conferencista;
    }

    public void setConferencista(String conferencista) {
        this.conferencista = conferencista;
    }


    


    
}
