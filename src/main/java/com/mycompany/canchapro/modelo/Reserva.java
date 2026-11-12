
package com.mycompany.canchapro.modelo;
import com.mycompany.canchapro.modelo.Cliente;
import com.mycompany.canchapro.modelo.Cancha;
import com.mycompany.canchapro.modelo.misenums.EstadoReserva;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;

public class Reserva {
    private int cod_reserva;
    private Cliente cliente;
    private Cancha cancha;
    private LocalDate fecha;
    private LocalTime hora_inicio;
    private LocalTime hora_fin;
    private double total;
    private double saldo_pendiente;
    private EstadoReserva estado_reserva;
    private Pago[] pagos = new Pago[100];
    private int cont_pago = 0;
    private DetalleServicio [] servicio = new DetalleServicio[100];
    private int cont_servicio = 0;
    private LocalDateTime fecha_cancelacion;
    private String motivo_cancelacion;
    private String operador_reserva;

    public Reserva(int cod_reserva, Cliente cliente, Cancha cancha, LocalDate fecha, LocalTime hora_inicio, LocalTime hora_fin, double total, double saldo_pendiente, EstadoReserva estado_reserva, LocalDateTime fecha_cancelacion, String motivo_cancelacion, String operador_reserva) {
        this.cod_reserva = cod_reserva;
        this.cliente = cliente;
        this.cancha = cancha;
        this.fecha = fecha;
        this.hora_inicio = hora_inicio;
        this.hora_fin = hora_fin;
        this.total = total;
        this.saldo_pendiente = saldo_pendiente;
        this.estado_reserva = estado_reserva;
        this.fecha_cancelacion = fecha_cancelacion;
        this.motivo_cancelacion = motivo_cancelacion;
        this.operador_reserva = operador_reserva;
    }
    
    public Reserva(int cod_reserva) {
        this.cod_reserva = cod_reserva;
    }

    public int getCod_reserva() {
        return cod_reserva;
    }

    public void setCod_reserva(int cod_reserva) {
        this.cod_reserva = cod_reserva;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cancha getCancha() {
        return cancha;
    }

    public void setCancha(Cancha cancha) {
        this.cancha = cancha;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(LocalTime hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public LocalTime getHora_fin() {
        return hora_fin;
    }

    public void setHora_fin(LocalTime hora_fin) {
        this.hora_fin = hora_fin;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getSaldo_pendiente() {
        return saldo_pendiente;
    }

    public void setSaldo_pendiente(double saldo_pendiente) {
        this.saldo_pendiente = saldo_pendiente;
    }

    public EstadoReserva getEstado_reserva() {
        return estado_reserva;
    }

    public void setEstado_reserva(EstadoReserva estado_reserva) {
        this.estado_reserva = estado_reserva;
    }

    public Pago[] getPagos() {
        return pagos;
    }

    public void setPagos(Pago[] pagos) {
        this.pagos = pagos;
    }

    public int getCont_pago() {
        return cont_pago;
    }

    public void setCont_pago(int cont_pago) {
        this.cont_pago = cont_pago;
    }

    public DetalleServicio[] getServicio() {
        return servicio;
    }

    public void setServicio(DetalleServicio[] servicio) {
        this.servicio = servicio;
    }

    public int getCont_servicio() {
        return cont_servicio;
    }

    public void setCont_servicio(int cont_servicio) {
        this.cont_servicio = cont_servicio;
    }

    public LocalDateTime getFecha_cancelacion() {
        return fecha_cancelacion;
    }

    public void setFecha_cancelacion(LocalDateTime fecha_cancelacion) {
        this.fecha_cancelacion = fecha_cancelacion;
    }

    public String getMotivo_cancelacion() {
        return motivo_cancelacion;
    }

    public void setMotivo_cancelacion(String motivo_cancelacion) {
        this.motivo_cancelacion = motivo_cancelacion;
    }

    public String getOperador_reserva() {
        return operador_reserva;
    }

    public void setOperador_reserva(String operador_reserva) {
        this.operador_reserva = operador_reserva;
    }
    
    public double getAdelanto(){
        return this.total - this.saldo_pendiente;
    }

   public boolean guardarReserva(){
   try{
       FileWriter fr = new FileWriter("datos/reservas.txt", true);
       PrintWriter pw = new PrintWriter(fr);
       
       String linea = getCod_reserva() + "," + getCliente().getId() + "," +getCliente().getNombre()+ "," + getCancha().getNombre() + "," + getFecha() 
               + "," + getHora_inicio() + "," + getHora_fin() + "," + getTotal() + "," + getSaldo_pendiente() + "," + getEstado_reserva();
       
       pw.println(linea);
       pw.close();
       
       return true;
   }catch (Exception e){
    System.out.println("Error al guardar la Reserva");
   return false;
    }
   }
   
   public static Reserva buscarReserva(String nombre, String nomCancha){
       try{
           FileReader fr = new FileReader("datos/reservas.txt");
           BufferedReader br = new BufferedReader(fr);
           String linea;
           
           linea = br.readLine();
           while(linea !=null){
                   String[] datos = linea.split(",");
                   if(datos.length>=10){
                       String cliente = datos[2].trim();
                       String cancha = datos[3].trim();
                       String fecha = datos[4].trim();
                       String estado = datos[9].trim();
                       
                    if(cliente.equalsIgnoreCase(nombre) && cancha.equalsIgnoreCase(nomCancha) && estado.equals("PENDIENTE")){
                        int cod = Integer.parseInt(datos[0]);
                        int idCliente = Integer.parseInt(datos[1]);
                        LocalDate fechaReserv = LocalDate.parse(fecha);
                        LocalTime horaInicio = LocalTime.parse(datos[5].trim());
                        LocalTime horaFin = LocalTime.parse(datos[6].trim());
                        double total = Double.parseDouble(datos[7]);
                        double saldoPen = Double.parseDouble(datos[8]);
                        
                        br.close();
                        
                        Cancha canchita = new Cancha("", cancha,null,0,0.0,null,null,null);
                        return new Reserva(
                                cod, new Cliente(idCliente, cliente), canchita, fechaReserv, horaInicio, horaFin, total,
                                saldoPen,EstadoReserva.PENDIENTE,null,null,"Operador 1"
                        );
                    }
                   }
                   linea = br.readLine();
           }
           br.close();
       }catch(IOException e){
           System.out.println("Error al buscar reserva: "+e.getMessage());
       }
       return null;
   }
   
   public boolean actualizarEstado(EstadoReserva nuevoEst, double nuevoSald){
   this.estado_reserva = nuevoEst;
   this.saldo_pendiente = nuevoSald;
   boolean actu = false;
   
   try{
       int totalLineas = 0;
       FileReader fr1 = new FileReader("datos/reservas.txt");
       BufferedReader br1 = new BufferedReader(fr1);
       String lineaTemp;
       
       lineaTemp = br1.readLine();
       while(lineaTemp !=null){
           totalLineas++;
           lineaTemp = br1.readLine();
       }
       br1.close();
       
       String[]nuevaLinea = new String[totalLineas];
       int cont = 0;
       
       FileReader fr2 = new FileReader("datos/reservas.txt");
       BufferedReader br2 = new BufferedReader(fr2);
       String linea;
       
       linea = br2.readLine();
       while(linea !=null){
           String[] datos = linea.split(",");
           if(datos.length>=10){
               int codigo = Integer.parseInt(datos[0]);
               if(codigo == this.cod_reserva){
                   datos[8] = String.valueOf(this.saldo_pendiente);
                   datos[9] = this.estado_reserva.name();
                   linea = String.join(",", datos);
                   actu = true;
               }  
           }
           nuevaLinea[cont] = linea;
           cont++;
           linea = br2.readLine();
        }
       br2.close();
       
       if(actu){
           FileWriter fw = new FileWriter("datos/reservas.txt",false);
           PrintWriter pw = new PrintWriter(fw);
           for(int i=0; i< nuevaLinea.length; i++){
               pw.println(nuevaLinea[i]);
           }
           pw.close();
           return true;
       }
       }catch(IOException e){
           return false;
       }
    return false;       
   }
   
   public static boolean HorarioOcupado(String cancha, LocalDate fecha, LocalTime horaInicio, LocalTime horaFin) {
    try {
        FileReader fr = new FileReader("datos/reservas.txt");
        BufferedReader br = new BufferedReader(fr);
        String linea;

        linea = br.readLine();
        while (linea != null) {
            if (!linea.equals("")) {
        String[] datos = linea.split(",");

        String canchaExistente = datos[3].trim();
        LocalDate fechaExistente = LocalDate.parse(datos[4].trim());
        LocalTime inicioExistente = LocalTime.parse(datos[5].trim());
        LocalTime finExistente = LocalTime.parse(datos[6].trim());
        String estadoExistente = datos[9].trim();

        if (!estadoExistente.equalsIgnoreCase("CANCELADA")) {
           if (canchaExistente.equalsIgnoreCase(cancha) && fechaExistente.equals(fecha)) {
              if (horaInicio.isBefore(finExistente) && horaFin.isAfter(inicioExistente)) {
                    br.close();
                    return true;
              }
        }
     }
    }
    linea = br.readLine();
    }
    br.close();
        } catch (IOException e) {
    }
    return false; 
    }  
    
    public static int generarId() {
    int cod = 1;
    try {
        FileReader fr = new FileReader("datos/reservas.txt");
        BufferedReader br = new BufferedReader(fr);
        String linea = br.readLine();

        while (linea != null) {
                String[] datos = linea.split(",");
                cod = Integer.parseInt(datos[0].trim()) + 1;
                linea = br.readLine();
        }
        br.close();
    }catch (IOException e) {
        cod = 1;
        }
    return cod;
    }   

    public static double TotalReserva(Cancha cancha, LocalDate fecha, LocalTime horainicio, LocalTime horafin, int balones, int chalecos, boolean arbitro) {
        int duracion = horafin.getHour() - horainicio.getHour();

        if (duracion <= 0) {
            duracion = 1;
        }
        
        double precioCancha = cancha.calcularPrecioCancha(fecha.toString(), horainicio.toString(), duracion);
        double totalServicios = DetalleServicio.calcularServicios(horainicio, horafin, balones, chalecos, arbitro);

        return precioCancha + totalServicios;
        }
    }
