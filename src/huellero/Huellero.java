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
      probarAsignarConfiguracion();
    }
    
  /**
   *
   */
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
