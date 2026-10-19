
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
    
}
