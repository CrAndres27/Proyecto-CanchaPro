
package com.mycompany.canchapro.modelo;
import com.mycompany.canchapro.modelo.Cliente;
import com.mycompany.canchapro.modelo.Cancha;
import com.mycompany.canchapro.modelo.misenums.EstadoReserva;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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

   public boolean guardarReserva(){
   try{
       FileWriter fr = new FileWriter("datos/reservas.txt", true);
       PrintWriter pw = new PrintWriter(fr);
       
       String linea = getCod_reserva() + "," + getCliente().getNombre()+ "," + getCancha().getNombre() + "," + getFecha() + "," + getHora_inicio() + "," + getHora_fin() + "," + getTotal() + "," + getSaldo_pendiente() + "," + getEstado_reserva();
       
       pw.println(linea);
       pw.close();
       
       return true;
   }catch (IOException e){
   JOptionPane.showMessageDialog(null, "Error al guardar la Reserva");
   return false;
    }
   }
   
   public static Reserva buscarReserva(String nombre){
       try{
           FileReader fr = new FileReader("datos/reservas.txt");
           BufferedReader br = new BufferedReader(fr);
           String linea;
           
           linea = br.readLine();
           while(linea !=null){
               if(!linea.equals("")){
                   String[] datos = linea.split(",");
                   if(datos.length>=9){
                       String cliente = datos[1];
                       String estado = datos[8];
                       
                    if(cliente.equalsIgnoreCase(nombre) && (estado.equalsIgnoreCase("PENDIENTE"))){
                        int cod = Integer.parseInt(datos[0]);
                        String cancha = datos[2];
                        LocalDate fecha = LocalDate.parse(datos[3].trim());
                        LocalTime horaInicio = LocalTime.parse(datos[4].trim());
                        LocalTime horaFin = LocalTime.parse(datos[5].trim());
                        double total = Double.parseDouble(datos[6]);
                        double saldoPen = Double.parseDouble(datos[7]);
                        
                        return new Reserva(
                                cod, new Cliente(cliente), new Cancha(cancha), fecha, horaInicio, horaFin, total,
                                saldoPen,EstadoReserva.PENDIENTE,null,null,"Operador 1"
                        );
                    }
                   }
                   linea = br.readLine();
               }
           }
       }catch(IOException e){
       }
       return null;
   }
   
   public static boolean actualizarEstado(int cod, EstadoReserva nuevoEst, double nuevoSald){
   String[] nuevaLinea = new String[100];
   int cont = 0;
   boolean actu = false;
   
   try{
       FileReader fr = new FileReader("datos/reservas.txt");
       BufferedReader br = new BufferedReader(fr);
       String linea;
       
       linea = br.readLine();
       while(linea != null){
           if(!linea.equals("")){
           String[] datos = linea.split(",");
           if(datos.length>=9){
               int codigo = Integer.parseInt(datos[0].trim());
           if(codigo == cod){
               datos[7] = String.valueOf(nuevoSald);
               datos[8] = nuevoEst.name();
               linea = String.join(",",datos); 
               actu = true;
           }
           }
           nuevaLinea[cont] = linea;
           cont++;
           }
           linea = br.readLine();
       }
       br.close();
   }catch(IOException e){
      return false;
   }
   
   if(actu){
       try{
           FileWriter fw = new FileWriter("datos/reservas.txt", false);
           PrintWriter pw = new PrintWriter(fw);
           
           for(int i = 0; i<cont; i++){
               pw.println(nuevaLinea[i]);
           }
           pw.close();
           return true;
       }catch(IOException e){
           return false;
       }
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

        String canchaExistente = datos[2].trim();
        LocalDate fechaExistente = LocalDate.parse(datos[3].trim());
        LocalTime inicioExistente = LocalTime.parse(datos[4].trim());
        LocalTime finExistente = LocalTime.parse(datos[5].trim());
        String estadoExistente = datos[8].trim();

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
    
}
