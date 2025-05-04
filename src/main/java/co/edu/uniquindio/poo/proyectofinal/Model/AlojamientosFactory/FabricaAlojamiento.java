package co.edu.uniquindio.poo.proyectofinal.Model.AlojamientosFactory;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;

@AllArgsConstructor
@Getter
public abstract class FabricaAlojamiento{
    private String nombre,ciudad,descripcion;
    private String rutaFoto;
    private ArrayList<String> servicios;

    public abstract Alojamiento crearProducto();
}
