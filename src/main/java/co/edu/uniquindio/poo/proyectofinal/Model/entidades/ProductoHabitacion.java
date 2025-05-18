package co.edu.uniquindio.poo.proyectofinal.Model.entidades;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

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
    @Builder.Default @Getter
    private UUID id=UUID.randomUUID();

    public double calcularCosto() {
        return 0.0;
    }
}
