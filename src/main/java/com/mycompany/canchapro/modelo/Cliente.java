package com.mycompany.canchapro.modelo;
import java.time.LocalDate;

public class Cliente extends Persona {
    private LocalDate fecha_registro; 

    public Cliente(int id, String nombre, String telefono, String correo, LocalDate fecha_registro) {
        super(id, nombre, telefono, correo);
        this.fecha_registro = fecha_registro;
    }
    

}


