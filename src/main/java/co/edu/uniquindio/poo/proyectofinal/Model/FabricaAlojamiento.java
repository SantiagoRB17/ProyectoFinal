package co.edu.uniquindio.poo.proyectofinal.Model;

import javafx.scene.image.Image;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;

@AllArgsConstructor
@Getter
public abstract class FabricaAlojamiento{
    private String nombre,ciudad,descripcion;
    private Image foto;
    private double precio,valoracion;
    private ArrayList<String> servicios, resenas;
    private Estado estado;
    private int capacidadMaxima;

    public abstract Alojamiento crearProducto();
}
