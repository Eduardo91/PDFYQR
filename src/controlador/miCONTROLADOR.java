//CONTROLADOR
package controlador;
import vista.miBIENVENIDA;
import vista.miVISTA;

import struct.Alumno;
import struct.Conferencia;
import struct.Registro;

import datos.MySQLConexion;
import datos.comandos;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import java.util.Enumeration;
import javax.swing.DefaultComboBoxModel;
import mprlib.MprManager;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPCell;
import java.io.*;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.barcodelib.barcode.QRCode;
import java.awt.Desktop;
import java.io.File;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.Timer;

import java.awt.Desktop;
import java.io.File;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;

import java.applet.AudioClip;
import javafx.scene.paint.Stop;
import sun.applet.AppletAudioClip;
import sun.audio.AudioPlayer;
import com.sun.media.jfxmedia.events.PlayerStateEvent;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.Icon;
import javax.swing.table.DefaultTableModel;
import mprlib.MprCommands;
import sun.awt.RequestFocusController;
import vista.miSALIR;
/**
 *
 * @author Eduardo
 */
public class miCONTROLADOR implements ActionListener,Icon{
MySQLConexion MySQLConexion;
comandos datosComandos;
miBIENVENIDA vistaBienve;
miVISTA vistaMIvista;
miSALIR vistaSALIR;    
Timer timeWELCOME;
Timer timeLOGIN;
Timer timeVOZ1;
Timer timeVOZ2;
Timer timeVOZ3;
Timer timeVOZ4;
Timer timeEduardo;
Timer timeSALIR;
Timer timePDFcreado;
Timer timeUPDATER;
Image miUSUARIO;
Image imgQR;
QRCode fox;
File ruta_destino = null;
Document mipdf;
com.itextpdf.text.Image mifoto;
com.itextpdf.text.Image mifotoQR; 
AudioClip audioPASO1; 
AudioClip audioPASO2;
AudioClip rutaalma;
AudioClip pdfcreado;
DefaultTableModel mitabla;
    
ResultSet registro;
ResultSet registroConfe;
ResultSet registroREG;
    
Statement tabla;
Alumno alumno;
Conferencia confe;
Registro regis;
DefaultComboBoxModel puertos;
DefaultListModel frec;
MprManager lalo;

public miCONTROLADOR(comandos datosComandos,MySQLConexion MySQLConexion,miBIENVENIDA vistaBienve, miVISTA vistaMIvista ){
        this.datosComandos=datosComandos;
        this.MySQLConexion=MySQLConexion;
        this.vistaBienve = vistaBienve;
        this.vistaMIvista = vistaMIvista;
        }
/*----------------------------------------------------------------------------*/
public void MostrarTodo() throws SQLException{
/*----------------------------------------------------------------------------*/    
        registro = MySQLConexion.Selecionar();
        registro.isBeforeFirst();

        while(registro.next()){
            mitabla = (DefaultTableModel)vistaMIvista.jTable1.getModel();
            Object[] datos = {registro.getString(1),registro.getString(2),registro.getString(3),registro.getString(4),registro.getString(5)};
            mitabla.addRow(datos);
            vistaMIvista.jTable1.setModel(mitabla);
        }
    }    
/*----------------------------------------------------------------------------*/
public void Limpiar(){
/*----------------------------------------------------------------------------*/    
        mitabla=(DefaultTableModel)vistaMIvista.jTable1.getModel();
        int i = mitabla.getRowCount();
        for(int x=0;x<i;x++){
            mitabla.removeRow(0);
        }
    }
/*----------------------------------------------------------------------------*/
public void Refrescar() throws SQLException{
/*----------------------------------------------------------------------------*/    
        MySQLConexion.Desconectar();
        MySQLConexion.Conectar();
        Limpiar();
        MostrarTodo();
        registro = MySQLConexion.Selecionar();
        registro.first();
        mostrar();
    }
/*----------------------------------------------------------------------------*/
public void RefrescarRegistro() throws SQLException{
/*----------------------------------------------------------------------------*/    
        MySQLConexion.Desconectar();
        MySQLConexion.Conectar();
        limpiarREGISTROS();        
    }
/*----------------------------------------------------------------------------*/
public void RefrescarConfe() throws SQLException{
/*----------------------------------------------------------------------------*/    
        MySQLConexion.Desconectar();
        MySQLConexion.Conectar();
        //Limpiar();
        //MostrarTodo();
        registroConfe = MySQLConexion.SeleccionarConferencia();
        registroConfe.first();
        mostrarConferencias();
    }
/*----------------------------------------------------------------------------*/
public void limpiarREGISTROS(){
/*----------------------------------------------------------------------------*/    
vistaMIvista.jTextField3_ID_PARTPNTE_REG.setText(null);
vistaMIvista.jTextField6_HORA_REG.setText(null);
vistaMIvista.jTextField5_FECHA_REG.setText(null);
vistaMIvista.jTextField4_ID_CONFE_REG.setText(null);
vistaMIvista.jTextField3_ID_PARTPNTE_REG.requestFocus();

}
/*----------------------------------------------------------------------------*/
public void mostrar() throws SQLException{
/*----------------------------------------------------------------------------*/    
        vistaMIvista.jTextField4_ID.setText(registro.getString(1));
        vistaMIvista.jTextField3Nombre.setText(registro.getString(2));
        vistaMIvista.jTextField4Correo.setText(registro.getString(3));
        vistaMIvista.jTextField5Contrasena.setText(registro.getString(4));
        vistaMIvista.jTextField6Universidad.setText(registro.getString(5));
    }
/*----------------------------------------------------------------------------*/
public void mostrarConferencias() throws SQLException{
/*----------------------------------------------------------------------------*/    
        //registroConfe = MySQLConexion.SelecionarConferencia();
        vistaMIvista.jTextField3_ID_CONFERENCIA.setText(registroConfe.getString(1));
        vistaMIvista.jTextField4_TITULO_CONFE.setText(registroConfe.getString(2));
        vistaMIvista.jTextField5_CONFERENCISTA.setText(registroConfe.getString(3));
        }
/*----------------------------------------------------------------------------*/
public void mostrarRegistros() throws SQLException{
/*----------------------------------------------------------------------------*/    
        //registroREG = MySQLConexion.SelecionarRegistro();
        
        vistaMIvista.jTextField3_ID_PARTPNTE_REG.setText(registro.getString(2));
        vistaMIvista.jTextField4_ID_CONFE_REG.setText(registro.getString(3));
        vistaMIvista.jTextField5_FECHA_REG.setText(registro.getString(4));
        vistaMIvista.jTextField6_HORA_REG.setText(registro.getString(5));
    }
/*----------------------------------------------------------------------------*/
    public void tiempos (){
/*----------------------------------------------------------------------------*/        
    timeWELCOME = new Timer(5200, this);//5200
        timeWELCOME.start();
}    
/*----------------------------------------------------------------------------*/
public void tloading (){
/*----------------------------------------------------------------------------*/    
    timeLOGIN = new Timer(2000, this);
        timeLOGIN.start();    
}    
/*----------------------------------------------------------------------------*/
public void timeTalk1 (){
/*----------------------------------------------------------------------------*/    
    timeVOZ1 = new Timer(4000, this);
        timeVOZ1.start();    
}    
/*----------------------------------------------------------------------------*/
public void timeTalk2 (){
/*----------------------------------------------------------------------------*/    
    timeVOZ2 = new Timer(4500, this);
        timeVOZ2.start();    
}    
/*----------------------------------------------------------------------------*/
public void timeTalk3 (){
/*----------------------------------------------------------------------------*/    
    timeVOZ3 = new Timer(2000, this);
        timeVOZ3.start();    
}    
/*----------------------------------------------------------------------------*/
public void timeTalk4 (){
/*----------------------------------------------------------------------------*/    
    timeVOZ4 = new Timer(4512, this);
        timeVOZ4.start();    
}    
/*----------------------------------------------------------------------------*/
public void timeTalkEduardo (){
/*----------------------------------------------------------------------------*/    
    timeEduardo = new Timer(3150, this);
        timeEduardo.start();    
} 
/*----------------------------------------------------------------------------*/
public void timeTalkSALIR (){
/*----------------------------------------------------------------------------*/    
    timeSALIR = new Timer(2800, this);
        timeSALIR.start();    
}    
/*----------------------------------------------------------------------------*/
public void timePDF (){
/*----------------------------------------------------------------------------*/    
    timePDFcreado = new Timer(2300, this);
    timePDFcreado.start();    
}    
/*----------------------------------------------------------------------------*/
public void iniciaaudiodos(){
/*----------------------------------------------------------------------------*/    
    audioPASO2=java.applet.Applet.newAudioClip(getClass().getResource("/audios/aubparte2.wav"));
    audioPASO2.play();         
}
/*----------------------------------------------------------------------------*/
public void inicializarBienvenida(){
/*----------------------------------------------------------------------------*/    
    audioPASO1=java.applet.Applet.newAudioClip(getClass().getResource("/audios/aubparte1.wav"));
    audioPASO1.play(); 
    vistaBienve.setIconImage(new ImageIcon(getClass().getResource("/imagenes/icono.png")).getImage());
    vistaBienve.setTitle("Bienvenida");
    vistaBienve.setLocationRelativeTo(null);
    vistaBienve.PANELbienvenida.setOpaque(false);
    tiempos();
}                
/*----------------------------------------------------------------------------*/
public void inicializarMIvista() throws SQLException {
/*----------------------------------------------------------------------------*/
        vistaMIvista.setLocationRelativeTo(null);
        vistaMIvista.jTextField1.addActionListener(this);
        vistaMIvista.jTextField1.requestFocus();
        vistaMIvista.SALIR.addActionListener(this);
        vistaMIvista.BQR.addActionListener(this);
        vistaMIvista.BPDF.addActionListener(this);
        vistaMIvista.bOK.addActionListener(this);
        
        vistaMIvista.jButton1WRITE_TAG.addActionListener(this);
        
        vistaMIvista.jButton1MOVE_BACK.addActionListener(this);
        vistaMIvista.jButton1MOVE_NEXT.addActionListener(this);
        vistaMIvista.jButton1_UPDATE.addActionListener(this);
        
        vistaMIvista.jButton1_SELECT_FRECUENCIA.addActionListener(this);
        vistaMIvista.jButton2_ON.addActionListener(this);
        vistaMIvista.jButton3OFF.addActionListener(this);
        vistaMIvista.jButton1_INSERT.addActionListener(this);
        vistaMIvista.jbutton_NEW.addActionListener(this);///NUEVO ALUMNO
        vistaMIvista.jButton1_INSERT_CONFE.addActionListener(this);
        vistaMIvista.jButton_REGISTRAR_REG.addActionListener(this);
        vistaMIvista.jButton1_ACTIVAR_REG.addActionListener(this);
        vistaMIvista.jButton2_NEXT_CONFE.addActionListener(this);
        vistaMIvista.jButton1_BACK_CONFE.addActionListener(this);
        vistaMIvista.jButton1UPCONF.addActionListener(this);
        vistaMIvista.jButton1NUEVA_CONFER.addActionListener(this);
        vistaMIvista.jButton1PARAR_REGISTRO.addActionListener(this);
        
        vistaMIvista.jButton1WRITE_TAG.setContentAreaFilled(false);
        vistaMIvista.jButton1MOVE_BACK.setContentAreaFilled(false);
        vistaMIvista.jButton1_UPDATE.setContentAreaFilled(false);
        vistaMIvista.jButton1MOVE_NEXT.setContentAreaFilled(false);
        vistaMIvista.jButton1_SELECT_FRECUENCIA.setContentAreaFilled(false);
        vistaMIvista.jButton2_ON.setContentAreaFilled(false);
        vistaMIvista.jButton3OFF.setContentAreaFilled(false);
        vistaMIvista.jButton1_INSERT.setContentAreaFilled(false);
        vistaMIvista.jbutton_NEW.setContentAreaFilled(false);
        vistaMIvista.jButton1_INSERT_CONFE.setContentAreaFilled(false);
        vistaMIvista.jButton_REGISTRAR_REG.setContentAreaFilled(false);
        
        vistaMIvista.jButton2_NEXT_CONFE.setContentAreaFilled(false);
        vistaMIvista.jButton1_BACK_CONFE.setContentAreaFilled(false);
        vistaMIvista.jButton1UPCONF.setContentAreaFilled(false);
        vistaMIvista.jButton1NUEVA_CONFER.setContentAreaFilled(false);
        
        
        
        vistaMIvista.bOK.setContentAreaFilled(false);
        vistaMIvista.SALIR.setContentAreaFilled(false);
        vistaMIvista.BQR.setContentAreaFilled(false);
        vistaMIvista.BPDF.setContentAreaFilled(false);
        vistaMIvista.jComboBox1.setOpaque(false);
        
        vistaMIvista.jTextField4_ID.setOpaque(false);
        vistaMIvista.jTextField3Nombre.setOpaque(false);
        vistaMIvista.jTextField4Correo.setOpaque(false);
        vistaMIvista.jTextField5Contrasena.setOpaque(false);
        vistaMIvista.jTextField6Universidad.setOpaque(false);
        
        vistaMIvista.jTextField4_ID.setVisible(true);
        vistaMIvista.jTextField3Nombre.setVisible(true);
        vistaMIvista.jTextField4Correo.setVisible(true);
        vistaMIvista.jTextField5Contrasena.setVisible(true);
        vistaMIvista.jTextField6Universidad.setVisible(true);
        vistaMIvista.jTextField1.setOpaque(true);
          
        vistaMIvista.PANELIMAGEN.setVisible(false);
        //vistaMIvista.PANELQR.setVisible(false);
        vistaMIvista.jLabel4SUPER_ON.setVisible(false);
        
        vistaMIvista.setIconImage(new ImageIcon(getClass().getResource("/imagenes/icono.png")).getImage());
        vistaMIvista.jLabel5LOGIN.setVisible(false);
        vistaMIvista.jLabel3VOZ.setVisible(false);
    }


/*------------------------------------------------------------------------------
                    C R E A R  C O D I G O   Q R 
------------------------------------------------------------------------------*/
public void creaQR(String c) throws Exception{
        fox=new QRCode();
        fox.setData(c);
        fox.setDataMode(QRCode.MODE_BYTE);
        fox.setUOM(0);
        fox.setResolution(72);
        fox.setLeftMargin(0.000f);
        fox.setRightMargin(0.000f);
        fox.setTopMargin(0.000f);
        fox.setBottomMargin(0.000f);
        fox.setRotate(12);
        fox.setModuleSize(8);//ocho por que es byte
        String archivo="d:\\PDFYQR\\codigos\\"+vistaMIvista.jTextField1.getText()+".png";
        File a=new File(archivo);
        fox.renderBarcode(archivo);
        //Desktop.getDesktop().open(a);
    }
/*----------------------------------------------------------------------------*/
public void generarqr()throws Exception{
/*----------------------------------------------------------------------------*/    
        creaQR(vistaMIvista.jTextField2.getText());
}
/*----------------------------------------------------------------------------*/
public void mostrarQR(){
/*----------------------------------------------------------------------------*/    
        imgQR= Toolkit.getDefaultToolkit().getImage("d:\\PDFYQR\\codigos\\"+vistaMIvista.jTextField1.getText()+".png");
        vistaMIvista.jLabel4CODEQR.setIcon(new ImageIcon(imgQR.getScaledInstance(250, 230, 0)));
}
/*----------------------------------------------------------------------------*/
public void crear_tabla() throws DocumentException, FileNotFoundException, BadElementException, IOException{
/*----------------------------------------------------------------------------*/    
        Colocar_Destino(); 
        if (this.ruta_destino != null) {
            //Se crea el documento PDF
            mipdf = new Document();
            //Se escribe en el archivo PFD
            PdfWriter.getInstance(mipdf, new FileOutputStream(this.ruta_destino+".pdf"));            
            mipdf.open(); //Se abre el archivo            
      PdfPTable table1 = new PdfPTable(2);
      PdfPCell celda1;  
      PdfPCell celda2;
      table1.setWidthPercentage(50);
      table1.getDefaultCell().setBorder(0);
      table1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_MIDDLE);
      table1.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
      table1.getDefaultCell().setFixedHeight(140);
            
      for (int i=1;i<7;i++){
          
mifoto= com.itextpdf.text.Image.getInstance("d:\\PDFYQR\\usuarios\\"+vistaMIvista.jTextField1.getText()+".png");////APLICAR LO MISMO PARA QR
mifoto.scaleToFit(120,120);
mifoto.setAlignment(Chunk.ALIGN_CENTER);          
     table1.addCell(mifoto);
     
     
     
mifotoQR= com.itextpdf.text.Image.getInstance("d:\\PDFYQR\\codigos\\"+vistaMIvista.jTextField1.getText()+".png");////APLICAR LO MISMO PARA QR
mifotoQR.scaleToFit(120,120);
mifotoQR.setAlignment(Chunk.ALIGN_CENTER);               
table1.addCell(mifotoQR);///incrustar OBJETO QR
      }
      mipdf.add(table1);
      mipdf.close(); //se cierra el archivo
            //JOptionPane.showMessageDialog(null, "Documento PDF CREADO");
      timePDF();
      vistaMIvista.jLabel3VOZ.setVisible(true);
      pdfcreado=java.applet.Applet.newAudioClip(getClass().getResource("/audios/pdfcreado.wav"));
      pdfcreado.play(); 
        }
    }
/*----------------------------------------------------------------------------*/
public void Colocar_Destino(){
/*----------------------------------------------------------------------------*/    
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo PDF","pdf","PDF");
        JFileChooser fileChoser = new JFileChooser();
        fileChoser.setFileFilter(filter);
        int result = fileChoser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            this.ruta_destino = fileChoser.getSelectedFile().getAbsoluteFile();
        }
    }
/*----------------------------------------------------------------------------*/
public void mostrarUSUARIO() throws SQLException{
/*----------------------------------------------------------------------------*/    

    vistaMIvista.PANELIMAGEN.setVisible(true);
vistaMIvista.jLabel4IMAGENUSUARIO.setVisible(true);
miUSUARIO=Toolkit.getDefaultToolkit().getImage("d:\\PDFYQR\\usuarios\\"+vistaMIvista.jTextField1.getText()+ ".png");
vistaMIvista.jLabel4IMAGENUSUARIO.setIcon(new ImageIcon(miUSUARIO.getScaledInstance(170, 180, 0)));                          

}
/*----------------------------------------------------------------------------*/
public void Timer(){
/*----------------------------------------------------------------------------*/    
    timeUPDATER = new Timer(4000, this);
        timeUPDATER.start();    
}
/*----------------------------------------------------------------------------*/
private class verificacionPuertos extends Thread{//HILO
/*----------------------------------------------------------------------------*/    
public void listapuertos() {
        vistaMIvista.jComboBox1.setModel(new DefaultComboBoxModel());///CONBOBOX
        puertos=(DefaultComboBoxModel)vistaMIvista.jComboBox1.getModel();
        Enumeration lp=CommPortIdentifier.getPortIdentifiers();
        CommPortIdentifier idpuerto=null;
        boolean encontrado=false;
        while(lp.hasMoreElements()&& !encontrado)
        {
            idpuerto=(CommPortIdentifier)lp.nextElement();
        puertos.addElement(idpuerto.getName());
        }
        
    }
/*----------------------------------------------------------------------------*/
    public void frecuencia(){
/*----------------------------------------------------------------------------*/    
        vistaMIvista.jList1.setModel(new DefaultListModel());
        frec=(DefaultListModel)vistaMIvista.jList1.getModel();
        for(int f=1200;f<15000;f+=1200){
            frec.addElement(f);
        }
    }
/*----------------------------------------------------------------------------*/
    public void inicio(){
/*----------------------------------------------------------------------------*/        
        
        String p=puertos.getSelectedItem().toString();
        System.out.println(p);
        int f=Integer.parseInt(frec.getElementAt(vistaMIvista.jList1.getSelectedIndex()).toString());
        try {
            vistaMIvista.jLabel4_MENSAJEPUERTO.setText(comunicaPuerto1(p, f));
        } catch (Exception e) {
        
        }
    }
/*----------------------------------------------------------------------------*/
    public String comunicaPuerto1(String puerto, int frecuencia){
/*----------------------------------------------------------------------------*/    
    String resp;
    lalo =new MprManager(puerto,frecuencia);
    if(lalo.connect()){
    resp="la conexion con el puerto:"+puerto+"a una frecuencia de:"+frecuencia+"Exitosa";
    }else{
        resp="la conexion con el puerto:"+puerto+"a una frecuencia de:"+frecuencia+"fallo";
        lalo.close();
    }
  return resp;
}

}
/*----------------------------------------------------------------------------*/
    private class escrituraTAGS extends Thread{//HILO
/*----------------------------------------------------------------------------*/    
    public void escribirentag() throws Exception{///////////GRABA SOLO GRABA GRABA GRABA 
    String puerto="COM4";////RECOGE EL PUERTO
    
    MprManager foxManager=new MprManager(puerto, 9600);
    String id=vistaMIvista.jTextField4_ID.getText();
    
    byte[] bytes=id.getBytes();
    foxManager.setByteSeparator("-");
    
    if(!foxManager.connect())
        throw new Exception("No se pudo iniciar la comunicacion con el puerto");///              aqui un audio
    
    while (!foxManager.c1g2_WriteID(bytes, 0,bytes.length))//c1g2_WriteID
        audioPASO1=java.applet.Applet.newAudioClip(getClass().getResource("/audios/rfidEscribiendo.wav"));
        audioPASO1.play(); 
        //System.out.println("Intentando escribir");///                                            aqui un audio
        audioPASO2=java.applet.Applet.newAudioClip(getClass().getResource("/audios/rfidGrabacionexitosa.wav"));
        audioPASO2.play(); 
        //System.out.println("La grabacion fue todo un exito");///                                 aqui un audio
        System.exit(0);
}    
}
/*----------------------------------------------------------------------------*/
private class fechahora extends Thread{ //HILO 
/*----------------------------------------------------------------------------*/    
   
    public void run(){
    while(true){
        try{
    Date fechayhora = new Date();
    String h = "hh:mm:ss";
    String f="YYYY-MM-dd";//dd/MM/YYYY
    SimpleDateFormat y= new SimpleDateFormat(f);
    SimpleDateFormat x= new SimpleDateFormat(h);
    
    
    vistaMIvista.jTextField5_FECHA_REG.setText(y.format(fechayhora));
    vistaMIvista.jTextField6_HORA_REG.setText(x.format(fechayhora));
    
        
            Thread.sleep(1000);
        } catch (Exception e) {
        }
    }    
    }
}
/*----------------------------------------------------------------------------*/
private class obtenerIDconferencia extends Thread{
/*----------------------------------------------------------------------------*/    
   public void run(){
    
    while(true){
        try {
            vistaMIvista.jTextField4_ID_CONFE_REG.setText(vistaMIvista.jTextField3_ID_CONFERENCIA.getText());
            Thread.sleep(1000); 
        } catch (Exception e) {
        }
    }  
    
}    
    
    
    
}
///$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
///ACTION PERFORMED ACTION PERFORMED ACTION PERFORMEDACTION PERFORMEDACTION PERFORMEDACTION PERFORMEDACTION PERFORMEDACTION PERFORMED
///$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
private class lecturaTAGS1 extends Thread{
    
MprManager foxManager;
String DatoByte=null;
String DatosAscci =null;    

public lecturaTAGS1(MprManager mpr){
    foxManager=mpr;
}    
public void run(){
    while(true){
        try {
           DatoByte=foxManager.readOneTagID(MprCommands.TagProtocol.EPC_C1G2, 1);///Se va guardar la cadena aqui
           DatosAscci=ConvertirByte(DatoByte);
           DatosAscci=DatosAscci.trim();
           System.out.println("Dato en manera de byte:"+DatoByte);
           System.out.println("Dato en manera de Ascci:"+DatosAscci);
           ///vistaMIvista.jTextField3_ID_PARTPNTE_REG.setText(DatosAscci);
           Thread.sleep(1000);
        } catch (Exception e) {
        }
    }
}    
public String ConvertirByte(String datoEnByte){
        String Salida="";
        String []datos=datoEnByte.split("-");
        try {
        for(String fox:datos){
            Salida+=(char)Integer.parseInt(fox.trim(),16);
        }    
        } catch (Exception e) {
        
        
        }
        return Salida;
    }
}   
//~~~~~~~~€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€
private class lecturaTAGSmain extends Thread{
    public void run(){
        try {
    String puerto="COM4";
    
    MprManager foxManager=new MprManager(puerto,9600);
    foxManager.setByteSeparator("-");
    if(!foxManager.connect())throw new Exception("camarita no hay comunicacion con del puerto");
    
    lecturaTAGS1 lect=new lecturaTAGS1(foxManager);
    java.util.Timer tiempo=new java.util.Timer();
Thread.sleep(1000);    
//tiempo.schedule(lect, 0,1000);
        } catch (Exception e) {
            
        }
    }
}


















@Override
    public void actionPerformed(ActionEvent e) {
        try {
/*----------------------------------------------------------------------------*/
            if(e.getSource()==timeWELCOME){
                vistaBienve.dispose();
                vistaMIvista.setVisible(true);
            }
/*----------------------------------------------------------------------------*/
            if(e.getActionCommand().equals("PararR")){
                vistaMIvista.jLabel4SUPER_ON.setVisible(false);
                vistaMIvista.jLabel4_SUPEROFF.setVisible(true);
                obtenerIDconferencia obtidc=new obtenerIDconferencia();
                obtidc.wait(10000);
                fechahora fechhora=new fechahora();
                fechhora.wait(10000);
            }
/*----------------------------------------------------------------------------*/
            if(e.getActionCommand().equals("NC")){
/*----------------------------------------------------------------------------*/    
                vistaMIvista.jTextField3_ID_CONFERENCIA.setText(null);
                vistaMIvista.jTextField4_TITULO_CONFE.setText(null);
                vistaMIvista.jTextField5_CONFERENCISTA.setText(null);
                vistaMIvista.jTextField3_ID_CONFERENCIA.requestFocus();
}            
/*----------------------------------------------------------------------------*/
            if(e.getActionCommand().equals("BCO")){
/*----------------------------------------------------------------------------*/    
                mostrarConferencias(); 
                registroConfe.previous();
                if(registroConfe.isBeforeFirst()){
                    registroConfe.first();
                }    
    
            }            
/*----------------------------------------------------------------------------*/
            if(e.getActionCommand().equals("NCO")){
/*----------------------------------------------------------------------------*/                
                mostrarConferencias();
                registroConfe.next();
                if(registroConfe.isAfterLast()){
                    registroConfe.last();
                }
               
            }            
/*----------------------------------------------------------------------------*/
            if(e.getActionCommand().equals("ActivarReg")){
/*----------------------------------------------------------------------------*/    
                vistaMIvista.jLabel4_SUPEROFF.setVisible(false);
                vistaMIvista.jLabel4SUPER_ON.setVisible(true);

                System.out.println("Hola id confe");
                obtenerIDconferencia obtidc=new obtenerIDconferencia();
                obtidc.start();
                fechahora fechhora=new fechahora();
                fechhora.start();
            }            
/*----------------------------------------------------------------------------*/
            if(e.getActionCommand().equals("NEW")){
/*----------------------------------------------------------------------------*/    
                vistaMIvista.jTextField4_ID.setText(null);
                vistaMIvista.jTextField3Nombre.setText(null);
                vistaMIvista.jTextField4Correo.setText(null);
                vistaMIvista.jTextField5Contrasena.setText(null);
                vistaMIvista.jTextField6Universidad.setText(null);
                vistaMIvista.jTextField4_ID.requestFocus();
}                        
/*----------------------------------------------------------------------------*/
            if(e.getActionCommand().equals("INSERT")){
/*----------------------------------------------------------------------------*/    
               Statement tablan = null;
               ResultSet regitron = null;
               
               String id=vistaMIvista.jTextField4_ID.getText();
               String nom=vistaMIvista.jTextField3Nombre.getText();
               String email=vistaMIvista.jTextField4Correo.getText();
               String contra = vistaMIvista.jTextField5Contrasena.getText();
               String uni= vistaMIvista.jTextField6Universidad.getText();
              
               alumno = new Alumno(id, nom, email, contra, uni,1);
              
               tablan=MySQLConexion.getConexion().createStatement();
               tablan.executeUpdate(datosComandos.insertarAlumno(alumno));
               JOptionPane.showMessageDialog(null,"Registro añadido");
               Refrescar();  
            }            
/*----------------------------------------------------------------------------*/
            if(e.getActionCommand().equals("INSERTC")){
/*----------------------------------------------------------------------------*/    
                System.out.println("Hola insertc");
                Statement tablan = null;
                ResultSet regitron = null;
                int idconf=Integer.parseInt(vistaMIvista.jTextField3_ID_CONFERENCIA.getText());
                String titconf=vistaMIvista.jTextField4_TITULO_CONFE.getText();
                String confer=vistaMIvista.jTextField5_CONFERENCISTA.getText();
                confe = new Conferencia(idconf, titconf, confer);
                tablan=MySQLConexion.getConexion().createStatement();
                tablan.executeUpdate(datosComandos.insertarConferencia(confe));
                JOptionPane.showMessageDialog(null,"Registro añadido");
                Refrescar();  
            }            
/*----------------------------------------------------------------------------*/
            if(e.getActionCommand().equals("REGISTRAR")){
/*----------------------------------------------------------------------------*/    
                System.out.println("Hola insertar mio");
                Statement tablan = null;
                ResultSet regitron = null;
               
                String idParti=vistaMIvista.jTextField3_ID_PARTPNTE_REG.getText();
                int idConfe=Integer.parseInt(vistaMIvista.jTextField3_ID_CONFERENCIA.getText());
                String fech=vistaMIvista.jTextField5_FECHA_REG.getText();
                String hor=vistaMIvista.jTextField6_HORA_REG.getText();

                regis = new Registro(idParti, idConfe, fech, hor);

                tablan=MySQLConexion.getConexion().createStatement();
                tablan.executeUpdate(datosComandos.insertarRegistro(regis));

                JOptionPane.showMessageDialog(null,"Registro añadido");
                RefrescarRegistro();
              // MySQLConexion.Desconectar();
        //MySQLConexion.Conectar();
//        //Limpiar();
//        //MostrarTodo();
        //registroREG = MySQLConexion.SelecionarRegistro();
//        //registro.first();
//mostrar();
            }            
/*----------------------------------------------------------------------------*/
            if(e.getActionCommand().equals("WRITE")){
/*----------------------------------------------------------------------------*/    
                System.out.println("hola write");
                escrituraTAGS escTAG=new escrituraTAGS();
                escTAG.escribirentag();
            }            
/*----------------------------------------------------------------------------*/
            if(e.getActionCommand().equals("ON")){
/*----------------------------------------------------------------------------*/    
                verificacionPuertos vp=new verificacionPuertos();
                vp.listapuertos();
                vp.frecuencia();
                //vistaMIvista.jTextField3MIS_PUERTOS.setText(vistaMIvista.jComboBox1.getSelectedItem();
            }
/*----------------------------------------------------------------------------*/
            if(e.getActionCommand().equals("OFF")){
/*----------------------------------------------------------------------------*/    
                verificacionPuertos vp=new verificacionPuertos();
            }
/*----------------------------------------------------------------------------*/
            if(e.getActionCommand().equals("INICIO")){
                verificacionPuertos vp=new verificacionPuertos();
                vp.inicio();
            }
/*----------------------------------------------------------------------------*/
            if(e.getActionCommand().equals("BACK")){
/*----------------------------------------------------------------------------*/    
               registro.previous();
                if(registro.isBeforeFirst()){
                registro.first();
                }
                 mostrar();
                 vistaMIvista.jTextField2.setText(vistaMIvista.jTextField4Correo.getText());
                 
                 vistaMIvista.jLabel4CODEQR.setVisible(false);
        
                vistaMIvista.jTextField4_ID.setVisible(true);         
                vistaMIvista.jTextField3Nombre.setVisible(true);
                vistaMIvista.jTextField4Correo.setVisible(true);
                vistaMIvista.jTextField5Contrasena.setVisible(true);
                vistaMIvista.jTextField6Universidad.setVisible(true);
           }
/*----------------------------------------------------------------------------*/
            if(e.getActionCommand().equals("NEXT")){
/*----------------------------------------------------------------------------*/    
               registro.next();
                if(registro.isAfterLast()){
                    registro.last();
                }
                mostrar();
                vistaMIvista.jTextField2.setText(vistaMIvista.jTextField4Correo.getText());
                
                vistaMIvista.jLabel4CODEQR.setVisible(false);
                
                vistaMIvista.jTextField4_ID.setVisible(true);                 
                vistaMIvista.jTextField3Nombre.setVisible(true);
                vistaMIvista.jTextField4Correo.setVisible(true);
                vistaMIvista.jTextField5Contrasena.setVisible(true);
                vistaMIvista.jTextField6Universidad.setVisible(true);
           }
/*----------------------------------------------------------------------------*/
if(e.getActionCommand().equals("UPDATE")){         
/*----------------------------------------------------------------------------*/    
               Statement tablan = null;
               ResultSet regitron = null;
               
               String id=vistaMIvista.jTextField4_ID.getText();
               String nom=vistaMIvista.jTextField3Nombre.getText();
               String email=vistaMIvista.jTextField4Correo.getText();
               String contra = vistaMIvista.jTextField5Contrasena.getText();
               String uni= vistaMIvista.jTextField6Universidad.getText();
              
               
               alumno = new Alumno(id, nom, email, contra, uni,1);
              
               tablan=MySQLConexion.getConexion().createStatement();
               tablan.executeUpdate(datosComandos.actualizarAlumno(alumno));
               Refrescar();  
               //System.out.println("La actualizaciòn es todo un exito");
                audioPASO1=java.applet.Applet.newAudioClip(getClass().getResource("/audios/rfidBDACT.wav"));
                audioPASO1.play();         
           }
/*----------------------------------------------------------------------------*/
if(e.getActionCommand().equals("UPCONFE")){         
    
    
//    Statement tablan = null;
//               ResultSet regitron = null;
//
//               int idconf=Integer.parseInt(vistaMIvista.jTextField3_ID_CONFERENCIA.getText());
//               String titconf=vistaMIvista.jTextField4_TITULO_CONFE.getText();
//               String confer=vistaMIvista.jTextField5_CONFERENCISTA.getText();                       
//confe = new Conferencia(idconf, titconf, confer);
//
// tablan=MySQLConexion.getConexion().createStatement();
//               tablan.executeUpdate(datosComandos.actualizarConferencia(confe));
//         RefrescarConfe();
//               JOptionPane.showMessageDialog(null,"Actualizado");
    RefrescarConfe();
           audioPASO1=java.applet.Applet.newAudioClip(getClass().getResource("/audios/rfidBDACT.wav"));
    audioPASO1.play();             
}
///#############################################################################____________________OK
if(e.getActionCommand().equals("OK")) {
if(vistaMIvista.jTextField1.getText().equals("14002080")){

    mostrarUSUARIO();

timeTalkEduardo();
vistaMIvista.jLabel3VOZ.setVisible(true);
audioPASO1=java.applet.Applet.newAudioClip(getClass().getResource("/audios/"+vistaMIvista.jTextField1.getText()+".wav"));
audioPASO1.play(); 
vistaMIvista.jTextField2.requestFocus();

Refrescar();
RefrescarConfe();
vistaMIvista.jTextField2.setText(vistaMIvista.jTextField4Correo.getText());
Refrescar();  

}


}
///#############################################################################____________
//if(e.getActionCommand().equals("OK")) {
// mostrarUSUARIO();   
//}
/*----------------------------------------------------------------------------*/
if (e.getActionCommand().equals("Salir")) {
/*----------------------------------------------------------------------------*/    
timeTalkSALIR();
vistaMIvista.jLabel3VOZ.setVisible(true);
    audioPASO1=java.applet.Applet.newAudioClip(getClass().getResource("/audios/salirsistema.wav"));
    audioPASO1.play();         
    
    if (JOptionPane.showConfirmDialog(new JFrame(),
        "¿Desea salir del sistema?","",
            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
             System.exit(0);
            vistaMIvista.jTextField1.requestFocus();
    
            }                        
/*----------------------------------------------------------------------------*/
if (e.getActionCommand().equals("Generar")) {
/*----------------------------------------------------------------------------*/    
    if(vistaMIvista.jTextField1.getText().equals("")&&(vistaMIvista.jTextField1.getText().equals("")))
    {
        timeTalk1();///3 a 4 segundos
        vistaMIvista.jLabel3VOZ.setVisible(true);
        audioPASO1=java.applet.Applet.newAudioClip(getClass().getResource("/audios/iniciesesion.wav"));
        audioPASO1.play(); 
        vistaMIvista.jTextField2.requestFocus();
    }
    else if (vistaMIvista.jTextField2.getText().equals(""))
    {
        timeTalk2();///3 a 4 segundos
        vistaMIvista.jLabel3VOZ.setVisible(true);
        audioPASO1=java.applet.Applet.newAudioClip(getClass().getResource("/audios/ingresedato.wav"));
        audioPASO1.play(); 
        vistaMIvista.jTextField2.requestFocus();    
    }
    else
    {
        vistaMIvista.jTextField4_ID.setVisible(false);
        vistaMIvista.jTextField3Nombre.setVisible(false);
        vistaMIvista.jTextField4Correo.setVisible(false);
        vistaMIvista.jTextField5Contrasena.setVisible(false);
        vistaMIvista.jTextField6Universidad.setVisible(false);
        timeTalk3();
        vistaMIvista.jLabel3VOZ.setVisible(true);
        tloading();
        vistaMIvista.jLabel4CODEQR.setVisible(true);    
        //vistaMIvista.PANELQR.setVisible(true);
        generarqr();
        mostrarQR();
        vistaMIvista.jLabel5LOGIN.setVisible(true);
        vistaMIvista.jTextField1.requestFocus();
        audioPASO1=java.applet.Applet.newAudioClip(getClass().getResource("/audios/generaqr.wav"));
        audioPASO1.play(); 
        vistaMIvista.jTextField2.requestFocus();
    }

}            
/*----------------------------------------------------------------------------*/
        if (e.getActionCommand().equals("pdf")){
/*----------------------------------------------------------------------------*/    
    try {
        if(vistaMIvista.jTextField1.getText().equals("")&&(vistaMIvista.jTextField1.getText().equals("")))
        {
            timeTalk1();///3 a 4 segundos
            vistaMIvista.jLabel3VOZ.setVisible(true);
            audioPASO1=java.applet.Applet.newAudioClip(getClass().getResource("/audios/iniciesesion.wav"));
            audioPASO1.play(); 
            vistaMIvista.jTextField2.requestFocus();
        }
        else if (vistaMIvista.jTextField2.getText().equals(""))
        {
            timeTalk2();///3 a 4 segundos
            vistaMIvista.jLabel3VOZ.setVisible(true);
            audioPASO1=java.applet.Applet.newAudioClip(getClass().getResource("/audios/ingresedato.wav"));
            audioPASO1.play(); 
            vistaMIvista.jTextField2.requestFocus();    
        }
        else
        {        
            timeTalk4();
            vistaMIvista.jLabel3VOZ.setVisible(true);        
            rutaalma=java.applet.Applet.newAudioClip(getClass().getResource("/audios/rutaalma.wav"));
            rutaalma.play(); 
            vistaMIvista.jTextField2.requestFocus();
            crear_tabla();
            vistaMIvista.jTextField1.requestFocus();
        }
        } catch (Exception q) {}
    }
///#############################################################################____________

        } catch (Exception w) {
            
            if(e.getSource()==timeLOGIN){               
                
               vistaMIvista.jLabel5LOGIN.setVisible(false);
                    timeLOGIN.stop();            
            }
            if(e.getSource()==timeVOZ1){               
                
               vistaMIvista.jLabel3VOZ.setVisible(false);
                    timeVOZ1.stop();            
            }
            if(e.getSource()==timeVOZ2){               
                
               vistaMIvista.jLabel3VOZ.setVisible(false);
                    timeVOZ2.stop();            
            }
            if(e.getSource()==timeVOZ3){               
                
               vistaMIvista.jLabel3VOZ.setVisible(false);
                    timeVOZ3.stop();            
            }
            if(e.getSource()==timeVOZ4){               
                
               vistaMIvista.jLabel3VOZ.setVisible(false);
                    timeVOZ4.stop();            
            }
            if(e.getSource()==timeEduardo){               
                
               vistaMIvista.jLabel3VOZ.setVisible(false);
                    timeEduardo.stop();            
            }
            if(e.getSource()==timeSALIR){               
                
               vistaMIvista.jLabel3VOZ.setVisible(false);
                    timeSALIR.stop();            
            }
            if(e.getSource()==timePDFcreado){               
                
               vistaMIvista.jLabel3VOZ.setVisible(false);
                    timePDFcreado.stop();            
            }
///mostrar usuario
        
        
        
    }
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
    Image msjDialogo=new ImageIcon(getClass().getResource("/imagenes/icono.png")).getImage();
    g.drawImage(msjDialogo, x, y, c);
    }

    @Override
    public int getIconWidth() {
        System.out.println("");
    return 0;
    }

    @Override
    public int getIconHeight() {
    return 0;

    }
    
    
}
