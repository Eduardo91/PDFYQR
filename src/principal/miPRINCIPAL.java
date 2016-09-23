///PRINCIPAL
package principal;



import vista.miBIENVENIDA;
import vista.miVISTA;
import datos.MySQLConexion;
import datos.comandos;
import controlador.miCONTROLADOR;

import java.sql.*;

/**
 *
 * @author Eduardo
 */
public class miPRINCIPAL {
    public static void main (String []args) throws Exception{
MySQLConexion conexion = new MySQLConexion();             
vista.miBIENVENIDA vistaPRINCIPAL=new vista.miBIENVENIDA();
vista.miVISTA vistaSECUNDARIA=new vista.miVISTA();
vista.miSALIR vistaSALIDA=new vista.miSALIR();
comandos coman = new comandos();

miCONTROLADOR mc=new miCONTROLADOR(coman, conexion, vistaPRINCIPAL, vistaSECUNDARIA);


mc.inicializarBienvenida();
mc.iniciaaudiodos();
vistaPRINCIPAL.setVisible(true);

//mc.inicializarSALIDA();
//vistaSALIDA.setVisible(false);

mc.inicializarMIvista();
vistaSECUNDARIA.setVisible(false);

        
    }
    
    
}
