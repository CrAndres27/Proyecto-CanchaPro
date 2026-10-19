
package com.mycompany.canchapro.modelo;
import com.mycompany.canchapro.modelo.misenums.CategoriaProducto;
import com.mycompany.canchapro.modelo.misenums.EstadoProducto;

public class Producto {
    private int cod_producto;
    private String nombre;
    private CategoriaProducto categoria;
    private double precio;
    private int existencia;
    private int stock_minimo;
    private EstadoProducto estado;

    public Producto(int cod_producto, String nombre, CategoriaProducto categoria, double precio, int existencia, int stock_minimo, EstadoProducto estado) {
        this.cod_producto = cod_producto;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.existencia = existencia;
        this.stock_minimo = stock_minimo;
        this.estado = estado;
    }
            
    
}
