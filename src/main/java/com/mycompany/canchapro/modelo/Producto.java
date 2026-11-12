
package com.mycompany.canchapro.modelo;
import com.mycompany.canchapro.modelo.misenums.CategoriaProducto;
import com.mycompany.canchapro.modelo.misenums.EstadoProducto;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.table.DefaultTableModel;

public class Producto {
    private int cod_producto;
    private String nombre;
    private CategoriaProducto categoria;
    private double precio;
    private int existencia;
    private int stock_minimo;
    private EstadoProducto estado;

    public Producto(int cod_producto, String nombre, CategoriaProducto categoria, double precio, int existencia, int stock_minimo, EstadoProducto estado) {
        this.cod_producto = cod_producto;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.existencia = existencia;
        this.stock_minimo = stock_minimo;
        this.estado = estado;
    }
    
    public Producto(int cod_producto, int existencia){
        this.cod_producto = cod_producto;
        this.existencia = existencia;
    }

    public int getCod_producto() {
        return cod_producto;
    }

    public void setCod_producto(int cod_producto) {
        this.cod_producto = cod_producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public CategoriaProducto getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaProducto categoria) {
        this.categoria = categoria;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

    public int getStock_minimo() {
        return stock_minimo;
    }

    public void setStock_minimo(int stock_minimo) {
        this.stock_minimo = stock_minimo;
    }

    public EstadoProducto getEstado() {
        return estado;
    }

    public void setEstado(EstadoProducto estado) {
        this.estado = estado;
    }
    
    public static boolean modificarStock(int codProducto, int cantidad, boolean aumento){
        try {
            FileReader fr = new FileReader("datos/productos.txt");
            BufferedReader br = new BufferedReader(fr);

            String[] lineasTemp = new String[200]; 
            int contador = 0;
            String linea;
            boolean productoEncontrado = false;

            linea = br.readLine();
            while(linea  != null) {
                if(!linea.trim().isEmpty()) {
                    String[] datos = linea.split(",");

                    if(datos.length >= 5) {
                        int id = Integer.parseInt(datos[0].trim());

                    if(id == codProducto) {
                            int stockActual = Integer.parseInt(datos[4].trim());
                            int nuevoStock;

                        if (aumento) {
                                nuevoStock = stockActual + cantidad;
                        }else {
                                nuevoStock = stockActual - cantidad;
                            if(nuevoStock < 0) {
                               br.close();
                               fr.close();
                               return false; 
                                }
                            }
                            datos[4] = String.valueOf(nuevoStock);
                            linea = String.join(",", datos);
                            productoEncontrado = true;
                        }
                    }
                    lineasTemp[contador] = linea;
                    contador++;
                }
                linea = br.readLine();
            }
            br.close();

            if (productoEncontrado) {
                PrintWriter pw = new PrintWriter(new FileWriter("datos/productos.txt", false));
                for (int i = 0; i < contador; i++) {
                    pw.println(lineasTemp[i]);
                }
                pw.close();
                return true;
            }
        }catch (Exception e) {
            System.out.println("Error al actualizar el stock: " + e.getMessage());
        }
        return false;
    }
    
    public boolean descontarStock(int cantidad){
       if(cantidad <=0 || this.existencia < cantidad){
           return false;
       }
       this.existencia = this.existencia - cantidad;
        return modificarStock(this.cod_producto, cantidad, false);
    }
    
    public boolean agregarStock(int cantidad){
        if(cantidad <=0){
            return false;
        }
        this.existencia = this.existencia + cantidad;
        return modificarStock(this.cod_producto, cantidad, true);
    }
    
    public static boolean actualizarProducto(String cod, String nom, String cat, String precio, String stock, String stockMin, String estado){
    try {
        String[] lineas = leerArchivo("datos/productos.txt");
        boolean encontrado = false;

        for(int i = 0; i < lineas.length; i++) {
            if (lineas[i] != null && !lineas[i].trim().isEmpty()) {
                String[] datos = lineas[i].split(",");
                
                if(datos.length >= 7 && datos[0].trim().equals(cod.trim())) {
                    lineas[i] = cod.trim() + "," + nom.trim() + "," + cat.trim() + "," + precio.trim() + "," + stock.trim() + "," + stockMin.trim() + "," + estado.trim();
                    encontrado = true;
                    break;
                }
            }
        }
        if(encontrado) {
            guardarArchivo("datos/productos.txt", lineas);
            return true;
        }
    }catch(Exception e) {
        System.out.println("Error al actualizar producto: " + e.getMessage());
    }

    return false;
    }
            
    public static String[] buscarProductoNombre(String nombre){
    try {
        FileReader fr = new FileReader("datos/productos.txt");
        BufferedReader br = new BufferedReader(fr);
        String linea;
        
        linea = br.readLine();
        while (linea != null) {
                String[] datos = linea.split(",");
                if (datos.length >= 2  && datos[1].trim().equalsIgnoreCase(nombre)) {
                    br.close();
                    return datos;
                }
                linea = br.readLine();
            }
            br.close();
        }catch (Exception e) {
        System.out.println("Error al buscar producto: " + e.getMessage());
        }
        return null;
    }
    
    public static String[] buscarProductoCodigo(String codigo) {
    try {
        FileReader fr = new FileReader("datos/productos.txt");
        BufferedReader br = new BufferedReader(fr);
        String linea;
        
        linea = br.readLine();
        while (linea != null) {
                String[] datos = linea.split(",");
                if (datos.length >= 7) {
                    if (datos[0].trim().equals(codigo.trim())) {
                        br.close();
                        return datos; 
                    }
                }
            linea = br.readLine();
            }
        br.close();
    }catch(Exception e) {
        System.out.println("Error al buscar producto: " + e.getMessage());
    }
    return null; 
    }
    
    public static double calcularTotal(DefaultTableModel modelo){
        double totalVenta = 0.0;
        for (int i = 0; i < modelo.getRowCount(); i++) {
            Object Subtotal = modelo.getValueAt(i, 4);
            if (Subtotal != null) {
            String textoSubtotal = Subtotal.toString().replace("Q", "").trim();
            if (!textoSubtotal.isEmpty()) {
                totalVenta += Double.parseDouble(textoSubtotal);
                }
            }
        }
        return totalVenta;
    }
    
    public static String[] leerArchivo(String rutaArchivo) {
    int totalLineas = 0;
    try{
        FileReader fr1 = new FileReader(rutaArchivo);
        BufferedReader br1 = new BufferedReader(fr1);
        
        while (br1.readLine() != null) {
            totalLineas++;
        }
    } catch (Exception e) {
        return new String[0];
    }

    String[] lineas = new String[totalLineas];
    try{
        FileReader fr2 = new FileReader(rutaArchivo);
        BufferedReader br2 = new BufferedReader(fr2);
        String linea;
        int i = 0;
        
        linea = br2.readLine();
        while (linea  != null) {
            lineas[i] = linea;
            i++;
            linea = br2.readLine();
        }
    }catch (Exception e) {
        System.out.println("Error al leer archivo: " + rutaArchivo + e.getMessage());
    }
        return lineas;
    }

    public static void guardarArchivo(String rutaArchivo, String[] lineas) {
    try (PrintWriter pw = new PrintWriter(new FileWriter(rutaArchivo, false))) {
        for (int i=0; i<lineas.length; i++) {
            String linea = lineas[i];
            
            if (linea != null && !linea.trim().isEmpty()) {
                pw.println(linea);
            }
        }
     }catch (Exception e) {
        System.out.println("Error al guardar archivo: " + e.getMessage());
        }
    }
    
   public static int generarId(String rutaArchivo) {
    int Mayorcod = 1;
    try {
        FileReader fr = new FileReader(rutaArchivo);
        BufferedReader br = new BufferedReader(fr);
        String linea = br.readLine();

        while (linea != null) {
                String[] datos = linea.split(",");
                if(datos.length>=1){
                int codActual = Integer.parseInt(datos[0]);
                
                if(codActual > Mayorcod){
                    Mayorcod = codActual;
                    }
                }
                linea = br.readLine();
        }
        br.close();
    }catch (Exception e) {
        }
    return Mayorcod + 1;
    }
   
    public static String[] listaProductos(String coincidencia) {
        String[] listaTemp = new String[200];
        int contador = 0;

    try {
        FileReader fr = new FileReader("datos/productos.txt");
        BufferedReader br = new BufferedReader(fr);
        String linea = br.readLine();

        while (linea != null) {
            if(!linea.trim().isEmpty()) {
                String[] datos = linea.split(",");
            if(datos.length >= 4) {
                    String id = datos[0];
                    String nombre = datos[1].trim();
                    String precio = datos[3].trim().replace("Q", "").replace(",", ".");

                boolean coincide = (coincidencia == null || coincidencia.trim().isEmpty()) || nombre.toLowerCase().contains(coincidencia.trim().toLowerCase());

                    if(coincide) {
                        if(contador < listaTemp.length) {
                            listaTemp[contador] = id + " - " + nombre + " - Q" + precio;
                            contador++;
                        }
                    }
                }
            }
            linea = br.readLine();
        }
        br.close();
    }catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
    String[] resultado = new String[contador];
    for (int i = 0; i < contador; i++) {
        resultado[i] = listaTemp[i];
        }
    return resultado;
    }
}
