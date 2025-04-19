package co.edu.uniquindio.poo.proyectofinal.Model;

import javafx.scene.image.Image;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class FabricaCasa extends FabricaAlojamiento {

    private double costoExtra;

    public FabricaCasa(String nombre, String ciudad, String descripcion, ArrayList<String> resena, Image foto,
                       double precio, double valoracion, ArrayList<String> servicios, Estado estado,
                       int capacidadMaxima, double costoExtra) {
        super(nombre, ciudad, descripcion, foto, precio, valoracion, servicios, resena, estado, capacidadMaxima);
        this.costoExtra = costoExtra;
    }

    @Override
    public Alojamiento crearProducto() {
        return ProductoCasa.builder()
                .nombre(getNombre())
                .ciudad(getCiudad())
                .descripcion(getDescripcion())
                .resenas(getResenas())
                .foto(getFoto())
                .precio(getPrecio())
                .valoracion(getValoracion())
                .servicios(getServicios())
                .estado(getEstado())
                .capacidadMaxima(getCapacidadMaxima())
                .costoExtra(costoExtra).build();
    }
}
