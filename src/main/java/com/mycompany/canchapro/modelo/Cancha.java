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
    
    
}
