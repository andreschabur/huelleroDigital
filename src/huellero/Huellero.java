/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huellero;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cirsisjmo
 */
public class Huellero {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      //probarAsignarConfiguracion();
        probarConsultaSelect();
    }
    
  /**
   *
   */
  protected static void probarConsultaSelect(){
      ConexionBD con = new ConexionBD();
      Integer empresaID = 10;
      String cedula = "16221934";
      String sql="SELECT ter.*  \n" +
                "FROM dbsalud_as.con_tercero ter \n"+
                "WHERE 1=1 "+
                "AND ter.empresa_id ="+empresaID+" \n"+
                "AND ter.documento = '"+cedula+"'";
      System.out.println("la consulta es "+sql);
        try 
        {
            //Establece los valores para la sentencia SQL
            Connection c = con.conectar(); //establece la conexion con la BD
            
            PreparedStatement verificaCedulaUsuario = c.prepareStatement(sql);
            ResultSet rs = verificaCedulaUsuario.executeQuery();
//            System.out.println("existe "+rs.first()+" documento "+cedula);
//            if(rs.first()){
            //System.out.println("rs "+rs.getRow());
            Boolean encontrado = false;            
            if (rs.next()) {
                encontrado = true;
                System.out.println("algo encontre ********************************");    
                System.out.println("ts 1"+rs.getString(1));
                System.out.println("result: "+rs.getString(2));
                System.out.println("result: cedula "+rs.getString(3));
                System.out.println("result: "+rs.getString(4));
                System.out.println("result: emp "+rs.getString(5));
                System.out.println("result: "+rs.getString(6));
                System.out.println("result: "+rs.getString(7));
                System.out.println("result: "+rs.getString(8));
                System.out.println("result: "+rs.getString(9));
                System.out.println("result: "+rs.getString(10));
                System.out.println("result: "+rs.getString(11));
                System.out.println("result: "+rs.getString(12));                
                System.out.println("result: "+rs.getString(13));
                System.out.println("result: "+rs.getString(14));
                System.out.println("result: huella "+rs.getString(15));
                System.out.println("result: 16 "+rs.getString(16));
                System.out.println("result: 17 "+rs.getString(17));
                System.out.println("result: 18 "+rs.getString(18));
                System.out.println("result: 19 "+rs.getString(19));
                System.out.println("result: 20 "+rs.getString(20));
                System.out.println("result:  "+rs.getString(21));
                System.out.println("result:  "+rs.getString(22));
                System.out.println("result:  "+rs.getString(23));
                System.out.println("result:  "+rs.getString(24));
                System.out.println("result:  "+rs.getString(25));
                System.out.println("result:  "+rs.getString(26));
                System.out.println("result:  "+rs.getString(27));
                System.out.println("result:  "+rs.getString(28));
                System.out.println("result:  "+rs.getString(29));
//                System.out.println("ts "+rs.toString());
                ///la empresa cual seria? ah
                
                
            }
            if(!encontrado){
                System.out.println("no encontre nada");    
            }
        }catch(Exception e){
                e.printStackTrace();
                System.out.println("no encontre nada");    
            }
  }
  protected static void probarAsignarConfiguracion(){
      //***
      try {
        System.out.println("Correr desde la carpeta en la que se ubica el jar con java -jar Huellero.jar, el archivoNuevo.txt debe estar dentro de lib");
        // TODO code application logic here
//        muestraContenido("C:\\archivo.txt");
        Map muestraContenido = readSystemConfig("lib/config.cfg");
        String empresa = (String) muestraContenido.get("empresa");
        String clave = (String) muestraContenido.get("clave");
        System.out.println("clave "+clave+" empresa "+empresa);
        
      } catch (FileNotFoundException ex) {
        System.out.println("Archivo no encontrado para asignar la configuracion, se estableceran parametros por defecto");
      } catch (IOException ex) {
        Logger.getLogger(Huellero.class.getName()).log(Level.SEVERE, null, ex);
        ex.printStackTrace();
      }
    }
    /**
     * Lee archivo con estructura llave:valor
     * debe estar separado por ":" la llave del valor
     * se crea para esperar una llave empresa,
     * 
     * @param archivo
     * @return
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public static Map readSystemConfig(String archivo) throws FileNotFoundException, IOException {
        String cadena;
        FileReader f = new FileReader(archivo);
        BufferedReader b = new BufferedReader(f);
//        ArrayList lista = new ArrayList();
        Map mapa = new HashMap();
        while((cadena = b.readLine())!=null) {
//            System.out.println(cadena);
          String[] split = cadena.split(":");
            mapa.put(split[0], split[1]);
//            lista.add(mapa);
        }
        b.close();
//        return lista;
        return mapa;
  }
    
}
