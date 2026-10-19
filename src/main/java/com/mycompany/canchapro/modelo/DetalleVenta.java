
package com.mycompany.canchapro.modelo;


public class DetalleVenta {
    private int cod_detalle;
    private int cantidad;
    private double precio;
    private double subtotal;

    public DetalleVenta(int cod_detalle, int cantidad, double precio, double subtotal) {
        this.cod_detalle = cod_detalle;
        this.cantidad = cantidad;
        this.precio = precio;
        this.subtotal = subtotal;
    }
    
    
}
