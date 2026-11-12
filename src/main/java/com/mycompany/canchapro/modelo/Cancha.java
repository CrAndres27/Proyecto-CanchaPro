package com.mycompany.canchapro.modelo;
import java.time.LocalTime;
import com.mycompany.canchapro.modelo.misenums.TipoCancha;
import com.mycompany.canchapro.modelo.misenums.EstadoCancha;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.DayOfWeek;
import java.time.LocalDate;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Cancha {
    private String cod_cancha;
    private String nombre;
    private TipoCancha tipo;
    private int capacidad;        
    private double precio_base;
    private EstadoCancha estado_cancha;
    private LocalTime hora_apertura;
    private LocalTime hora_cierre;

    public Cancha(String cod_cancha, String nombre, TipoCancha tipo, int capacidad, double precio_base, EstadoCancha estado_cancha, LocalTime hora_apertura, LocalTime hora_cierre) {
        this.cod_cancha = cod_cancha;
        this.nombre = nombre;
        this.tipo = tipo;
        this.capacidad = capacidad;
        this.precio_base = precio_base;
        this.estado_cancha = estado_cancha;
        this.hora_apertura = hora_apertura;
        this.hora_cierre = hora_cierre;
    }
    
    public Cancha(String cod_cancha){
        this.cod_cancha = cod_cancha;
    }

    public String getCod_cancha() {
        return cod_cancha;
    }

    public void setCod_cancha(String cod_cancha) {
        this.cod_cancha = cod_cancha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoCancha getTipo() {
        return tipo;
    }

    public void setTipo(TipoCancha tipo) {
        this.tipo = tipo;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public double getPrecio_base() {
        return precio_base;
    }

    public void setPrecio_base(double precio_base) {
        this.precio_base = precio_base;
    }

    public EstadoCancha getEstado_cancha() {
        return estado_cancha;
    }

    public void setEstado_cancha(EstadoCancha estado_cancha) {
        this.estado_cancha = estado_cancha;
    }

    public LocalTime getHora_apertura() {
        return hora_apertura;
    }

    public void setHora_apertura(LocalTime hora_apertura) {
        this.hora_apertura = hora_apertura;
    }

    public LocalTime getHora_cierre() {
        return hora_cierre;
    }

    public void setHora_cierre(LocalTime hora_cierre) {
        this.hora_cierre = hora_cierre;
    }
    
    public static DefaultTableModel ModeloCancha(){
        DefaultTableModel cancha = new DefaultTableModel();
    
        cancha.addColumn("Código");
        cancha.addColumn("Nombre");
        cancha.addColumn("Tipo");
        cancha.addColumn("Capacidad");
        cancha.addColumn("Precio/Hr");
        cancha.addColumn("Estado");
        cancha.addColumn("Apertura");
        cancha.addColumn("Cierre");
    
    try{
        FileReader fr = new FileReader("datos/canchas.txt");
        BufferedReader br = new BufferedReader(fr);

        String linea;
        
        linea = br.readLine();
        while(linea != null){
            String[] datos = linea.split(",");
            if(datos.length>=8){
                cancha.addRow(datos);
            }
            linea = br.readLine();
        }
        br.close();
    }catch (IOException e){
       System.out.println("Error al cargar el archivo de canchas: " + e.getMessage());
    }
        return cancha;
    }
    
    public boolean cambiarEstado(EstadoCancha nuevoEstado){
        this.estado_cancha = nuevoEstado;
        boolean exito = false;
        String nuevaInfo ="";
        
        try{
            FileReader fr = new FileReader("datos/canchas.txt");
            BufferedReader br = new BufferedReader(fr);
            String linea;
        
        linea = br.readLine();
        while(linea != null) {
                String[] datos = linea.split(",");
            if(datos.length >= 8 && datos[0].equals(this.cod_cancha)) {
                datos[5] = this.estado_cancha.name();
                linea = String.join(",", datos); 
                exito = true;
            }
            nuevaInfo = nuevaInfo + linea+ "\n";
            linea = br.readLine();
        }
        br.close();

        if(exito){
           FileWriter fw = new FileWriter("datos/canchas.txt");
           PrintWriter pw = new PrintWriter(fw);
           pw.print(nuevaInfo);
           pw.close(); 
        }
    }catch (Exception e) {
        System.out.println("Error al actualizar el archivo de canchas: " + e.getMessage());
        exito = false;
     }
        return exito;
    }
    
    public double calcularPrecioCancha(String fecha, String horaInicio, int duracionHoras) {
        double tarifaPorHora = this.precio_base;

        try {
            LocalDate fechaActual = LocalDate.parse(fecha.trim());
            DayOfWeek dia = fechaActual.getDayOfWeek();

            if (dia == DayOfWeek.SATURDAY || dia == DayOfWeek.SUNDAY) {
                tarifaPorHora = 250.0; 
            } else {
                String[] Hora = horaInicio.split(":");
                int hora = Integer.parseInt(Hora[0].trim());

                if (hora >= 8 && hora < 17) {
                    tarifaPorHora = this.precio_base; 
                } else {
                    tarifaPorHora = 225.0; 
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " +e.getMessage());
        }
        return tarifaPorHora * duracionHoras;
    }
}
