package co.edu.uniquindio.poo.proyectofinal.Model.AlojamientosFactory;

import co.edu.uniquindio.poo.proyectofinal.Model.entidades.ProductoCasa;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class FabricaCasa extends FabricaAlojamiento {

    private double precio;
    private int capacidadMaxima;
    private double costoExtra;
    public FabricaCasa(String nombre, String ciudad, String descripcion, String rutaFoto,
                       double precio, ArrayList<String> servicios,
                       int capacidadMaxima, double costoExtra) {
        super(nombre, ciudad, descripcion, rutaFoto, servicios);
        this.costoExtra = costoExtra;
        this.capacidadMaxima = capacidadMaxima;
        this.precio = precio;
    }
    @Override
    public Alojamiento crearProducto() {
        return ProductoCasa.builder()
                .nombre(getNombre())
                .ciudad(getCiudad())
                .descripcion(getDescripcion())
                .rutaFoto(getRutaFoto())
                .precio(precio)
                .servicios(getServicios())
                .capacidadMaxima(capacidadMaxima)
                .costoExtra(costoExtra).build();
    }
}
