package co.edu.uniquindio.poo.proyectofinal.modelo.entidades;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Sesion {
    public static Sesion INSTANCIA;

   private  Persona persona;
    private Sesion() {

    }

    public static Sesion getInstancia() {
        if(INSTANCIA== null){
            INSTANCIA= new Sesion();
        }
        return INSTANCIA;
    }

    public void cerrarSesion(){
        persona = null;
    }
}
