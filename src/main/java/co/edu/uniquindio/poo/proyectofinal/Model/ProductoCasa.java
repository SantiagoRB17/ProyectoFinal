package co.edu.uniquindio.poo.proyectofinal.Model;

import co.edu.uniquindio.poo.proyectofinal.Enums.Estado;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.util.ArrayList;
import java.util.UUID;

@Builder
@Getter
@Setter
@Jacksonized
public class ProductoCasa implements Alojamiento {
    @Builder .Default
    private UUID id = UUID.randomUUID();
    @Builder.Default
    private ArrayList<String>resenas = new ArrayList<>();
    @Builder.Default
    private double valoracion=0.0;
    @Builder.Default
    private Estado estado=Estado.DISPONIBLE;

    private String nombre,ciudad,descripcion;
    private String rutaFoto;
    private double precio;
    private ArrayList<String> servicios;
    private int capacidadMaxima;
    private double costoExtra;

    @Override
    public double calcularCosto() {
        return precio + costoExtra;
    }
}
