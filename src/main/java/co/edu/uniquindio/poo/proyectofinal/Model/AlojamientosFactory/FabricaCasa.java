package co.edu.uniquindio.poo.proyectofinal.Model.AlojamientosFactory;

import co.edu.uniquindio.poo.proyectofinal.Model.ProductoCasa;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class FabricaCasa extends FabricaAlojamiento {

    private double costoExtra;

    public FabricaCasa(String nombre, String ciudad, String descripcion, String rutaFoto,
                       double precio, ArrayList<String> servicios,
                       int capacidadMaxima, double costoExtra) {
        super(nombre, ciudad, descripcion, rutaFoto, precio, servicios,capacidadMaxima);
        this.costoExtra = costoExtra;
    }
    @Override
    public Alojamiento crearProducto() {
        return ProductoCasa.builder()
                .nombre(getNombre())
                .ciudad(getCiudad())
                .descripcion(getDescripcion())
                .rutaFoto(getRutaFoto())
                .precio(getPrecio())
                .servicios(getServicios())
                .capacidadMaxima(getCapacidadMaxima())
                .costoExtra(costoExtra).build();
    }
}
