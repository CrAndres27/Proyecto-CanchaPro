
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

    public int getNum_pago() {
        return num_pago;
    }

    public void setNum_pago(int num_pago) {
        this.num_pago = num_pago;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public MetodoPago getMetodo() {
        return metodo;
    }

    public void setMetodo(MetodoPago metodo) {
        this.metodo = metodo;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    
    
    
}
