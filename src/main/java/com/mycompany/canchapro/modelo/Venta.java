
package com.mycompany.canchapro.modelo;
import com.mycompany.canchapro.modelo.misenums.MetodoPago;
import java.time.LocalDateTime;

public class Venta {
    private int cod_venta;
    private LocalDateTime fecha_hora; 
    private double total;
    private MetodoPago metodo;
    private DetalleVenta [] detalle = new DetalleVenta[100];
    private int cont_detalle;

    public Venta(int cod_venta, LocalDateTime fecha_hora, double total, MetodoPago metodo, int cont_detalle) {
        this.cod_venta = cod_venta;
        this.fecha_hora = fecha_hora;
        this.total = total;
        this.metodo = metodo;
        this.cont_detalle = cont_detalle;
    }

    public int getCod_venta() {
        return cod_venta;
    }

    public void setCod_venta(int cod_venta) {
        this.cod_venta = cod_venta;
    }

    public LocalDateTime getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(LocalDateTime fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public MetodoPago getMetodo() {
        return metodo;
    }

    public void setMetodo(MetodoPago metodo) {
        this.metodo = metodo;
    }

    public DetalleVenta[] getDetalle() {
        return detalle;
    }

    public void setDetalle(DetalleVenta[] detalle) {
        this.detalle = detalle;
    }

    public int getCont_detalle() {
        return cont_detalle;
    }

    public void setCont_detalle(int cont_detalle) {
        this.cont_detalle = cont_detalle;
    }
    
    
    
}
