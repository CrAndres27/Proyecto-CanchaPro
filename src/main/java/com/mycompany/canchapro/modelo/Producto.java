
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

    public int getCod_producto() {
        return cod_producto;
    }

    public void setCod_producto(int cod_producto) {
        this.cod_producto = cod_producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public CategoriaProducto getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaProducto categoria) {
        this.categoria = categoria;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

    public int getStock_minimo() {
        return stock_minimo;
    }

    public void setStock_minimo(int stock_minimo) {
        this.stock_minimo = stock_minimo;
    }

    public EstadoProducto getEstado() {
        return estado;
    }

    public void setEstado(EstadoProducto estado) {
        this.estado = estado;
    }
    
    
            
    
}
