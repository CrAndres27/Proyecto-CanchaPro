
package com.mycompany.canchapro.modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ConfiguracionTienda {
    
    public static boolean leerEstadoTienda() {
        boolean estado = false;
        try{
            FileReader fr = new FileReader("datos/tienda.txt");
            BufferedReader br = new BufferedReader(fr);
            String linea;
            
            linea = br.readLine();
            if(linea != null) {
                String[] partes = linea.split("=");
                if(partes.length == 2) {
                    String valor = partes[1].trim();
                    if(valor.equals("true")) {
                        estado = true;
                    }else{
                        estado = false;
                    }
                }
                linea = br.readLine();
            }
            br.close();
        }catch(IOException e) {
            System.out.println("Error al leer tienda.txt: " + e.getMessage());
        }
        return estado;
    }

    public static void guardarEstadoTienda(boolean estado) {
        try{
            FileWriter fw = new FileWriter("datos/tienda.txt", false);
            PrintWriter pw = new PrintWriter(fw);

            if(estado == true) {
                pw.println("modulo_tienda=true");
            }else{
                pw.println("modulo_tienda=false");
            }
            pw.close();
        }catch (IOException e) {
            System.out.println("Error al guardar tienda.txt: " + e.getMessage());
        }
    }
}
