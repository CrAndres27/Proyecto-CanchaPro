
package com.mycompany.canchapro.modelo;
import com.mycompany.canchapro.modelo.misenums.RolUsuario;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.table.DefaultTableModel;

public class Usuario extends Persona {
    private String usuario;
    private String contraseña;
    private RolUsuario rol;

    public Usuario(int id, String nombre, String telefono, String correo, String usuario, String contraseña, RolUsuario rol) {
        super(id, nombre, telefono, correo);
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.rol = rol;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public RolUsuario getRol() {
        return rol;
    }

    public void setRol(RolUsuario rol) {
        this.rol = rol;
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
    
    public static DefaultTableModel ModeloUsuario(String coincidencia){
        DefaultTableModel usuario = new DefaultTableModel();
    
        usuario.addColumn("Id");
        usuario.addColumn("Nombre");
        usuario.addColumn("Telefono");
        usuario.addColumn("Correo");
        usuario.addColumn("Usuario");
        usuario.addColumn("Contraseña");
        usuario.addColumn("Rol");
        
        String filtro ="";
        if(coincidencia !=null){
        filtro = coincidencia;
        }
    
    try{
        FileReader fr = new FileReader("datos/usuarios.txt");
        BufferedReader br = new BufferedReader(fr);

        String linea;
        
        linea = br.readLine();
        while(linea != null){
            String[] datos = linea.split(",");
            if(datos.length>=5){
               String id = datos[0];
               String nombre = datos[1];
               
               if(filtro.isEmpty() || id.contains(filtro)|| nombre.contains(filtro)){
                    usuario.addRow(datos);
               }
            }
            linea = br.readLine();
        }
        br.close();
    }catch (IOException e){
       System.out.println("Error al cargar el archivo de canchas: " + e.getMessage());
    }
        return usuario;
    }
    
    public boolean crerUsuario(){
        boolean exito = false;
        String Registronuevo = this.id + "," + this.nombre + "," + this.telefono + "," + this.correo + "," + this.usuario + "," + this.contraseña + "," + this.rol;

        try {
            FileWriter fw = new FileWriter("datos/usuarios.txt", true);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(Registronuevo);
        
            pw.close();
            exito = true;
        }catch (Exception e) {
            System.out.println("Error al guardar usuario: " + e.getMessage());
        }
        return exito;
    }
    
    public boolean editarUsuario(){
    boolean exito = false;
        String Registroactu = this.id + "," + this.nombre + "," + this.telefono + "," + this.correo + "," + this.usuario + "," + this.contraseña +"," + this.rol;

        try{
            FileReader fr = new FileReader("datos/usuarios.txt");
            BufferedReader br = new BufferedReader(fr);
            
            String Infoactu = "";
            String linea = br.readLine();

            while(linea != null) {
                    String[] datos = linea.split(",");
                    if(datos.length >= 1 && datos[0].equals(String.valueOf(this.id))) {
                        Infoactu = Infoactu + Registroactu + "\n";
                        exito = true;
                    }else {
                        Infoactu = Infoactu + linea + "\n";
                    }
                
                linea = br.readLine();
            }
            br.close();

                FileWriter fw = new FileWriter("datos/usuarios.txt");
                PrintWriter pw = new PrintWriter(fw);
                pw.print(Infoactu);
                pw.close();
            }catch (Exception e) {
            System.out.println("Error al editar cliente: " + e.getMessage());
            exito = false;
        }
            return exito;
        }
    
    public boolean eliminarUsuario(){
        boolean exito = false;
        String nuevaInfo ="";
        
        try{
            FileReader fr = new FileReader("datos/usuarios.txt");
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
                FileWriter fw = new FileWriter("datos/usuarios.txt");
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
    
    public static boolean validarUsuario(String usuario, String contra){
        try{
            FileReader fr = new FileReader("datos/usuarios.txt");
            BufferedReader br = new BufferedReader(fr);
            String linea;
            
            linea = br.readLine();
            while(linea !=null){
                String[] datos = linea.split(",");
                if(datos.length>=6){
                    if(usuario.equals(datos[4]) && contra.equals(datos[5])){
                        return true;
                    }
                }
                linea = br.readLine();
            }
            br.close();
        }catch(IOException e){
            System.out.println("Error al abrir el archivo de usuarios: "+e.getMessage());
        }
        return false;
    }
    
}
