package com.mycompany.canchapro.modelo;
import java.time.LocalDate;

public class Cliente {
    private int cod_cliente;
    private String nombre;
    private String telefono;
    private String correo;
    private LocalDate fecha_registro; 

    public Cliente(int cod_cliente, String nombre, String telefono, String correo, LocalDate fecha_registro) {
        this.cod_cliente = cod_cliente;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.fecha_registro = fecha_registro;
    }
    
    
}


