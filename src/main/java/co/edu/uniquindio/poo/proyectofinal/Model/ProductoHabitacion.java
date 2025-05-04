package co.edu.uniquindio.poo.proyectofinal.Model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Builder
@Getter
@Setter
@Jacksonized
public class ProductoHabitacion {
    private int numeroHabitacion;
    private double precio;
    private int capacidad;
    private String rutaImagenHabitacion;
    private String descripcion;
    @Builder.Default @Setter @Getter
    private boolean activo=true;

}
