
package com.mycompany.canchapro.modelo;
import com.mycompany.canchapro.modelo.misenums.MetodoPago;
import java.time.LocalDateTime;

public class Pago {
    private int num_pago;
    private LocalDateTime fechaHora;
    private double monto;
    private MetodoPago metodo;
    private String observacion;

    public Pago(int num_pago, LocalDateTime fechaHora, double monto, MetodoPago metodo, String observacion) {
        this.num_pago = num_pago;
        this.fechaHora = fechaHora;
        this.monto = monto;
        this.metodo = metodo;
        this.observacion = observacion;
    }
    
    
}
