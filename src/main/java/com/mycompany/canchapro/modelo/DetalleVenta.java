
package com.mycompany.canchapro.modelo;


public class DetalleVenta {
    private int cod_detalle;
    private Producto producto;
    private int cantidad;
    private double precio;
    private double subtotal;

    public DetalleVenta(int cod_detalle, Producto producto ,int cantidad, double precio, double subtotal) {
        this.cod_detalle = cod_detalle;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precio = precio;
        this.subtotal = subtotal;
    }

    public int getCod_detalle() {
        return cod_detalle;
    }

    public void setCod_detalle(int cod_detalle) {
        this.cod_detalle = cod_detalle;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        calcularSubtotal();
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
    
    public double calcularSubtotal(){
        this.subtotal = this.cantidad * this.precio;
        return this.subtotal;
    }
    
}
