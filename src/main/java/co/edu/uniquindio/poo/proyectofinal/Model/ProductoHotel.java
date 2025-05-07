package co.edu.uniquindio.poo.proyectofinal.Model;

import co.edu.uniquindio.poo.proyectofinal.Model.AlojamientosFactory.Alojamiento;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
@Jacksonized
public class ProductoHotel implements Alojamiento {
    @Builder.Default
    private UUID id = UUID.randomUUID();
    @Builder.Default
    private ArrayList<String> resenas = new ArrayList<>();
    @Builder.Default
    private double valoracion=0.0;
    @Builder.Default @Setter @Getter
    private boolean activo=true;
    @Builder.Default
    private List<ProductoHabitacion> habitaciones=new ArrayList<>();
    private String nombre,ciudad,descripcion;
    private String rutaFoto;
    private ArrayList<String> servicios;
    private double costoExtra;
    private int numeroDeHabitaciones;

    @Override
    public double calcularCosto() {
        return 0;
    }
}
