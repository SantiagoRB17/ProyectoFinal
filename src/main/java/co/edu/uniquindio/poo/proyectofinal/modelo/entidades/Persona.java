package co.edu.uniquindio.poo.proyectofinal.modelo.entidades;

import co.edu.uniquindio.poo.proyectofinal.modelo.enums.Rol;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Persona {
    private String nombre;
    private String apellidos;
    private String cedula;
    private String email;
    private String telefono;
    private String password;
    private Rol rol;
    private boolean cuentaActiva;
}
