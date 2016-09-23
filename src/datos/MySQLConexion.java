package datos;
import java.sql.*;

public class MySQLConexion {
        
    private Connection conexion;
    Statement tabla;
    ResultSet resultado;
    
    String ruta;
    String usuario;
    String contraseña;

    public MySQLConexion() {
        ruta="jdbc:mysql://localhost:3306/feriatec";
        usuario="root";
        contraseña ="";
    }
    
    public void Conectar() throws SQLException{
        try{    
            conexion=DriverManager.getConnection( ruta,usuario,contraseña);
            tabla = conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        }
        catch(Exception e){
            e.printStackTrace();//CON ESTE COMANDO IMPRIMES EL ERROR
            conexion = null;
        }
    }
///""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""    
    public ResultSet Selecionar() throws SQLException{
        resultado = tabla.executeQuery("select * from participante");
        return resultado;
    }
///""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""    
    public ResultSet SeleccionarConferencia() throws SQLException{
        resultado = tabla.executeQuery("select * from conferencia");
        return resultado;
    }
///""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""        
public ResultSet SelecionarRegistro() throws SQLException{
        resultado = tabla.executeQuery("select * from registro");
        return resultado;
    }
///""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""            
    public void Desconectar(){
        
        if(conexion == null)return;
        try{
           conexion.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        conexion = null;
    }

    public Connection getConexion() {
        return conexion;
    }
    
}
