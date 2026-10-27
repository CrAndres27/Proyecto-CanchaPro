
package com.mycompany.canchapro.modelo;

public class DetalleServicio {
    private int cod_servicio;
    private int cantidad;
    private double subtotal;

    public DetalleServicio(int cod_servicio, int cantidad, double subtotal) {
        this.cod_servicio = cod_servicio;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }

    public int getCod_servicio() {
        return cod_servicio;
    }

    public void setCod_servicio(int cod_servicio) {
        this.cod_servicio = cod_servicio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
    
    
}
