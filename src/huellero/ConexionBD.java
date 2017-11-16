/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huellero;

/**
 * @librerias importadas
 * @author: Napster2011
 */
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ConexionBD {

  /**
   * @atributos @author: Napster2011
   * @access: public public String puerto="8080"; public String
   * nomservidor="localhost"; public String db="huellas"; public String
   * user="root"; public String pass=""; Connection conn=null;
   */
//  public String puerto = "8080";
  /*Parametros app interna
    public String nomservidor = "10.25.30.60";
  public String db = "dbsalud_as";
  public String user = "apperp";
  public String pass = "sql123";*/
    /*Parametros app externa*/
    public String nomservidor = "52.67.236.75";
  public String db = "dbsalud_as";
  public String user = "apperp";
  public String pass = "sql123";
  Connection conn = null;

  /**
   * @function: conectar
   * @author: Napster2011
   * @description: esta funcion se encarga de conectar la base de datos con el
   * servidor
   * @access: public
   * @return
   */
  public Connection conectar() {
    asignarConfiguracion();
    try {
      String ruta = "jdbc:mysql://";
      String servidor = nomservidor + "/";
      Class.forName("com.mysql.jdbc.Driver");
      conn = DriverManager.getConnection(ruta + servidor + db, user, pass);
      if (conn != null) {
        System.out.println("Conección a base de datos listo…");
      } else {
        throw new SQLException();
      }
    } catch (SQLException e) {
      JOptionPane.showMessageDialog(null, e.getMessage());
    } catch (ClassNotFoundException e) {
      JOptionPane.showMessageDialog(null, "Se produjo el siguiente error: " + e.getMessage());
    } catch (NullPointerException e) {
      JOptionPane.showMessageDialog(null, "Se produjo el siguiente error: " + e.getMessage());
    } finally {
      return conn;
    }
  }

  /**
   * @function: desconectar
   * @author: Napster2011
   * @description: esta funcion se encarga de realizar la desconexion de la base
   * de datos con el servidor
   * @access: public
   * @return
   */
  public void desconectar() {
    conn = null;
    System.out.println("Desconexion a base de datos listo…");
  }

  protected void asignarConfiguracion() {
    Map configuracion = null;
    try {
      //***}
      configuracion = Huellero_old.readSystemConfig(Variables.ubicacionArchivoConfiguracion);
    } catch (FileNotFoundException ex) {
      String mens = "Archivo no encontrado para asignar la configuracion en ConexionBD, se estableceran parametros por defecto";
//          EnviarTexto(mens);
      System.out.println(mens);
      return;
    } catch (IOException ex) {
      Logger.getLogger(CapturarHuella.class.getName()).log(Level.SEVERE, null, ex);
      ex.printStackTrace();
    }
    if (configuracion == null) {
      System.out.println("configuracion null");
      return;
    } else if (configuracion.get("server") == null) {
      System.out.println("No encontre empresa en el mapa de configuración");
      return;
    } else if (configuracion.get("database") == null) {
      System.out.println("No encontre empresa en el mapa de configuración");
      return;
    } else if (configuracion.get("user") == null) {
      System.out.println("No encontre empresa en el mapa de configuración");
      return;
    } else if (configuracion.get("password") == null) {
      System.out.println("No encontre empresa en el mapa de configuración");
      return;
    }
    //***
    nomservidor = (String) configuracion.get("server");
    db = (String) configuracion.get("database");
    user = (String) configuracion.get("user");
    pass = (String) configuracion.get("password");
    System.out.println("Asigno conexion con server " + nomservidor + " db " + db + " user " + user + " pass " + pass);
  }
}
