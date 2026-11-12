
package com.mycompany.canchapro.modelo;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


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
    
    public void guardardetalleVenta(int numVenta) {
    try{
        FileWriter fr = new FileWriter("datos/detalleventa.txt", true);
        PrintWriter pw = new PrintWriter("fr");
        
        String nuevoprecio = String.format("%.2f", this.precio).replace(",", ".");
        String nuevosubtotal = String.format("%.2f", this.subtotal).replace(",", ".");

        pw.println(this.cod_detalle + "," + numVenta + "," + this.producto.getCod_producto() + "," + this.cantidad + "," + nuevoprecio + "," + nuevosubtotal);
    }catch (IOException e) {
        System.out.println("Error al guardar el detalle: " + e.getMessage());
        }
    }
}
