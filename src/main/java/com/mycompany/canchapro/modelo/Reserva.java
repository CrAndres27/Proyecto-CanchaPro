
package com.mycompany.canchapro.modelo;
import com.mycompany.canchapro.modelo.Cliente;
import com.mycompany.canchapro.modelo.Cancha;
import com.mycompany.canchapro.modelo.misenums.EstadoReserva;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

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

    
    
    
    
}
