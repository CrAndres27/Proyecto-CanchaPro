
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
    
}
