package co.edu.uniquindio.poo.proyectofinal.Model.entidades;

import co.edu.uniquindio.poo.proyectofinal.Model.AlojamientosFactory.Alojamiento;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

import java.util.ArrayList;
import java.util.UUID;

@Builder
@Getter
@Setter
@Jacksonized
public class ProductoApartamento implements Alojamiento {
    @Builder .Default
    private UUID id = UUID.randomUUID();
    @Builder.Default
    private ArrayList<String>resenas = new ArrayList<>();
    @Builder.Default
    private double valoracion=0.0;
    @Builder.Default @Setter
    private boolean activo=true;

    private String nombre,ciudad,descripcion;
    private String rutaFoto;
    private double precio;
    private ArrayList<String> servicios;
    private int capacidadMaxima;
    private double costoExtra;

    @Override
    public double calcularCosto() {
        return precio+costoExtra;
    }


}
