package com.mycompany.canchapro.modelo;
import java.time.LocalTime;
import com.mycompany.canchapro.modelo.misenums.TipoCancha;
import com.mycompany.canchapro.modelo.misenums.EstadoCancha;

public class Cancha {
    private String cod_cancha;
    private String nombre;
    private TipoCancha tipo;
    private int capacidad;        
    private double precio_base;
    private EstadoCancha estado_cancha;
    private LocalTime hora_apertura;
    private LocalTime hora_cierre;

    public Cancha(String cod_cancha, String nombre, TipoCancha tipo, int capacidad, double precio_base, EstadoCancha estado_cancha, LocalTime hora_apertura, LocalTime hora_cierre) {
        this.cod_cancha = cod_cancha;
        this.nombre = nombre;
        this.tipo = tipo;
        this.capacidad = capacidad;
        this.precio_base = precio_base;
        this.estado_cancha = estado_cancha;
        this.hora_apertura = hora_apertura;
        this.hora_cierre = hora_cierre;
    }
    
    public Cancha(String nombre){
        this.nombre = nombre;
    }

    public String getCod_cancha() {
        return cod_cancha;
    }

    public void setCod_cancha(String cod_cancha) {
        this.cod_cancha = cod_cancha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoCancha getTipo() {
        return tipo;
    }

    public void setTipo(TipoCancha tipo) {
        this.tipo = tipo;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public double getPrecio_base() {
        return precio_base;
    }

    public void setPrecio_base(double precio_base) {
        this.precio_base = precio_base;
    }

    public EstadoCancha getEstado_cancha() {
        return estado_cancha;
    }

    public void setEstado_cancha(EstadoCancha estado_cancha) {
        this.estado_cancha = estado_cancha;
    }

    public LocalTime getHora_apertura() {
        return hora_apertura;
    }

    public void setHora_apertura(LocalTime hora_apertura) {
        this.hora_apertura = hora_apertura;
    }

    public LocalTime getHora_cierre() {
        return hora_cierre;
    }

    public void setHora_cierre(LocalTime hora_cierre) {
        this.hora_cierre = hora_cierre;
    }
    
    
    
}
