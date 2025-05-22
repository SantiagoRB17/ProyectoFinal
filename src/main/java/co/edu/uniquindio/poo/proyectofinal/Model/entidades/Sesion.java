package co.edu.uniquindio.poo.proyectofinal.Model.entidades;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Sesion {
    private static Sesion instancia;
    private Persona persona;

    private Sesion() {
    }

    public static Sesion getInstancia() {
        if (instancia == null) {
            instancia = new Sesion();
        }
        return instancia;
    }

    public void cerrarSesion() {
        persona = null;
    }
}
