
package com.mycompany.canchapro.modelo;

import java.time.LocalTime;

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
    
    public double calcularSubtotal(double precioUnitario) {
        this.subtotal = this.cantidad * precioUnitario;
        return this.subtotal;
    }

    public static double calcularServicios(LocalTime horainicio, LocalTime horafin, int balones, int chalecos, boolean arbitro) {
        int duracion = duracion = horafin.getHour() - horainicio.getHour();
        if(duracion <= 0) {
            duracion = 1;
        }

        double subBalones = balones * ServicioAdicional.PrecioServicio("BALON");
        double subChalecos = chalecos * ServicioAdicional.PrecioServicio("CHALECOS");
        double subArbitro = 0.0;

        if(arbitro) {
        subArbitro = duracion * ServicioAdicional.PrecioServicio("ARBITRO");
        }

        return subBalones + subChalecos + subArbitro;
    }
}
