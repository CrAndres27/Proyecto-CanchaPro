
package com.mycompany.canchapro.modelo;
import com.mycompany.canchapro.modelo.misenums.RolUsuario;

public class Usuario extends Persona {
    private String usuario;
    private String contraseña;
    private RolUsuario rol;

    public Usuario(int id, String nombre, String telefono, String correo, String usuario, String contraseña, RolUsuario rol) {
        super(id, nombre, telefono, correo);
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.rol = rol;
    }
    
    
}
