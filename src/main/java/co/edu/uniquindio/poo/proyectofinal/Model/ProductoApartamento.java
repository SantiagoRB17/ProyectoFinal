package co.edu.uniquindio.poo.proyectofinal.Model;

import javafx.scene.image.Image;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
@Builder
@Getter
@Setter
public class ProductoApartamento implements Alojamiento {
    private String nombre,ciudad,descripcion;
    private Image foto;
    double precio,valoracion;
    ArrayList<String> servicios,resenas;
    Estado estado;
    int capacidadMaxima;
    double costoExtra;


    @Override
    public double calcularCosto() {
        return precio+costoExtra;
    }
}
