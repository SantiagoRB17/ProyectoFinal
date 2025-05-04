package co.edu.uniquindio.poo.proyectofinal.Model.AlojamientosFactory;


import co.edu.uniquindio.poo.proyectofinal.Model.ProductoApartamento;

import java.util.ArrayList;

public class FabricaApartamento extends FabricaAlojamiento {
    double costoExtra;
    public FabricaApartamento(String nombre, String ciudad, String descripcion, String rutaFoto,
                              double precio, ArrayList<String> servicios,
                              int capacidadMaxima, double costoExtra) {
        super(nombre, ciudad, descripcion, rutaFoto, precio, servicios, capacidadMaxima);
        this.costoExtra = costoExtra;
    }

    @Override
    public Alojamiento crearProducto() {
        return ProductoApartamento.builder()
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
