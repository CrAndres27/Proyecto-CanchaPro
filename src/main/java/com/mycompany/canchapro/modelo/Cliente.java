package com.mycompany.canchapro.modelo;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import javax.swing.table.DefaultTableModel;

public class Cliente extends Persona {
    private LocalDate fecha_registro; 

    public Cliente(int id, String nombre, String telefono, String correo, LocalDate fecha_registro) {
        super(id, nombre, telefono, correo);
        this.fecha_registro = fecha_registro;
    }
    
    public Cliente(int id, String nombre) {
        super(0, nombre,"","");
        this.id = id;
        this.nombre = nombre;
    }

    public LocalDate getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(LocalDate fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    public static DefaultTableModel ModeloClientes(String coincidencia){
        DefaultTableModel cliente = new DefaultTableModel();
    
        cliente.addColumn("Id");
        cliente.addColumn("Nombre");
        cliente.addColumn("Telefono");
        cliente.addColumn("Correo");
        cliente.addColumn("Fecha Registro");
        
        String filtro ="";
        if(coincidencia !=null){
        filtro = coincidencia;
        }
    try{
        FileReader fr = new FileReader("datos/clientes.txt");
        BufferedReader br = new BufferedReader(fr);

        String linea;
        
        linea = br.readLine();
        while(linea != null){
            String[] datos = linea.split(",");
            if(datos.length>=5){
                String id = datos[0];
                String nombre = datos[1];
                
                if(filtro.isEmpty() || id.contains(filtro)|| nombre.contains(filtro)){
                    cliente.addRow(datos);
                }
            }
            linea = br.readLine();
        }
        br.close();
    }catch (IOException e){
       System.out.println("Error al cargar el archivo de canchas: " + e.getMessage());
    }
        return cliente;
    }
    
    public static String[] listaClientes(String coincidencia) {
    String[] listaTemp = new String[200];
    int contador = 0;

    try {
        FileReader fr = new FileReader("datos/clientes.txt");
        BufferedReader br = new BufferedReader(fr);
        String linea = br.readLine();

        while (linea != null) {
                String[] datos = linea.split(",");
                if (datos.length >= 2) {
                String id = datos[0];
                String nombre = datos[1];
                boolean coincide = (coincidencia == null || coincidencia.isEmpty()) || nombre.toLowerCase().contains(coincidencia.trim().toLowerCase());

                if (coincide) {
                    if (contador < listaTemp.length) {
                        listaTemp[contador] = id + " - " + nombre;
                        contador++;                        }
                    }
                }
            linea = br.readLine();
        }
        br.close();
    } catch (IOException e) {
        System.out.println("Error: " + e.getMessage());
    }
    String[] resultado = new String[contador];
    for (int i = 0; i < contador; i++) {
        resultado[i] = listaTemp[i];
    }
        return resultado;
    }
    
    public boolean crearCliente(){
        boolean exito = false;
        String Registronuevo = this.id + "," + this.nombre + "," + this.telefono + "," + this.correo + "," + this.fecha_registro;

    try {
        FileWriter fw = new FileWriter("datos/clientes.txt", true);
        PrintWriter pw = new PrintWriter(fw);
        pw.println(Registronuevo);

        pw.close();
        fw.close();
        exito = true;
    } catch (Exception e) {
        System.out.println("Error al guardar cliente: " + e.getMessage());
    }

    return exito;
    }
    
    public boolean editarCliente(){
        boolean exito = false;
        String Registroactu = this.id + "," + this.nombre + "," + this.telefono + "," + this.correo + "," + this.fecha_registro;

        try{
            FileReader fr = new FileReader("datos/clientes.txt");
            BufferedReader br = new BufferedReader(fr);
            
            String Infoactu = "";
            String linea = br.readLine();

            while(linea != null) {
                    String[] datos = linea.split(",");
                    if (datos.length >= 1 && datos[0].equals(String.valueOf(this.id))) {
                        Infoactu = Infoactu + Registroactu + "\n";
                        exito = true;
                    }else {
                        Infoactu = Infoactu + linea + "\n";
                    }
                
                linea = br.readLine();
            }
            br.close();

                FileWriter fw = new FileWriter("datos/clientes.txt");
                PrintWriter pw = new PrintWriter(fw);
                pw.print(Infoactu);
                pw.close();
            }catch (Exception e) {
            System.out.println("Error al editar cliente: " + e.getMessage());
            exito = false;
        }
            return exito;
        }
    
    public boolean eliminarCliente(){
        boolean exito = false;
        String nuevaInfo ="";
        
        try{
            FileReader fr = new FileReader("datos/clientes.txt");
            BufferedReader br = new BufferedReader(fr);
            String linea;
            
            linea = br.readLine();
            while(linea!=null){
                String[] datos = linea.split(",");
                if(datos.length>=1 && datos[0].equals(String.valueOf(this.id))){
                    exito = true;
                }else{
                    nuevaInfo = nuevaInfo + linea + "\n";
                }
                linea = br.readLine();
            }
            br.close();
            
            if(exito){
                FileWriter fw = new FileWriter("datos/clientes.txt");
                PrintWriter pw = new PrintWriter(fw);
                pw.print(nuevaInfo);
                pw.close();
            }
        }catch(Exception e){
            System.out.println("Error al elimianr cliente"+ e.getLocalizedMessage());
            exito = false;
        }
        return exito;
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
}


