package co.edu.uniquindio.poo.proyectofinal.Model.entidades;

import co.edu.uniquindio.poo.proyectofinal.Model.enums.Rol;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
