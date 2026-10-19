
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


    
    
    
}
