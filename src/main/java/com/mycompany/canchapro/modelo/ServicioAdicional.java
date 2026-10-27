
package com.mycompany.canchapro.modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;

public class ServicioAdicional {
    private String nombre;
    private double precio;

    public ServicioAdicional(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
   
    public static double PrecioServicio(String nombreBuscado){
        double Precio = 0.0;
        
        try{
            FileReader fr = new FileReader("datos/servicioadicional.txt");
            BufferedReader br = new BufferedReader(fr);
            
            String linea;
            linea = br.readLine();
            while(linea != null){
                String[] datos = linea.split(",");
                if(datos.length>=2){
                   String nomServicio = datos[0];
                   double preServicio = Double.parseDouble(datos[1]);
                   
                   if(nomServicio.equals(nombreBuscado)){
                       Precio = preServicio;
                       break;
                   }
                }
                linea = br.readLine();
            }
            br.close();
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, "Error al leer el archivo");
        }
        return Precio;
    }
    
}
