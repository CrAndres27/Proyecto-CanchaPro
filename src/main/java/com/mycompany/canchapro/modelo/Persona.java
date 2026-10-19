
package com.mycompany.canchapro.modelo;

public abstract class Persona {
    protected int id;
    protected String nombre;
    protected String telefono;
    protected String correo;

    public Persona(int id, String nombre, String telefono, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
    }
    
    
}
