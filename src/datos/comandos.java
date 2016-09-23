

package datos;
import struct.Alumno;
import struct.Conferencia;
import struct.Registro;
/**
 *
 * @author CC_Laboratorio
 */
public class comandos {
//€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€     
    public static String insertarAlumno(Alumno a){
        
        String query = "INSERT INTO participante VALUES ('"+a.getId()+"','"+a.getNombre()+"','"+a.getCorreo()+"','"+a.getContrasena()+ "','"+a.getUniversidad()+"','"+1+"');";
        return query;        
    }
//==============================================================================    
   public static String eliminarAlumno(Alumno a){
       
       String query = "Delete From participante where idUsuario='"+a.getId()+"';";
       return query;
   }
//==============================================================================   
   public static String actualizarAlumno(Alumno a){
       String query = "update participante set Nombre='"+
               a.getNombre()+"',Correo='"+
               a.getCorreo()+"',Contrasena='"+
               a.getContrasena()+"',Universidad='"+
               a.getUniversidad()+"'where idUsuario ='"+
               a.getId()+"';";
       
       return query;
   }
//==============================================================================
//€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€   
public static String insertarConferencia(Conferencia c){   
    
String query = "INSERT INTO conferencia VALUES ('"+c.getId_conferencia()+"','"+c.getTitulo()+"','"+c.getConferencista()+"');";    
    return query;
}
//==============================================================================
public static String actualizarConferencia(Conferencia a){
       String query = "update conferencia set id_conferencia='"+
               a.getId_conferencia()+"',titulo='"+
               a.getTitulo()+"',conferencista='"+
               a.getConferencista()+"',where id_conferencia='"+
               a.getId_conferencia()+"';";      
       
       return query;
   }
//€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€      
 public static String insertarRegistro(Registro i){   
    String query = "INSERT INTO registro VALUES (null,'"+i.getIdUsuarioReg()+"','"+i.getId_conferenciaReg()+"','"+i.getFecha()+"','"+i.getHora()+"');";    
    return query;
}  
   
   
}
