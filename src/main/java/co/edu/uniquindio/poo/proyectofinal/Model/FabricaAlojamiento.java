package co.edu.uniquindio.poo.proyectofinal.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;

@AllArgsConstructor
@Getter
public abstract class FabricaAlojamiento{
    private String nombre,ciudad,descripcion;
    private String rutaFoto;
    private double precio;
    private ArrayList<String> servicios;
    private int capacidadMaxima;


    public abstract Alojamiento crearProducto();
}
