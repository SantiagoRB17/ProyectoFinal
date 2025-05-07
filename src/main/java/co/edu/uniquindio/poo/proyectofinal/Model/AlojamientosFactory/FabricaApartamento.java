package co.edu.uniquindio.poo.proyectofinal.Model.AlojamientosFactory;


import co.edu.uniquindio.poo.proyectofinal.Model.ProductoApartamento;

import java.util.ArrayList;

public class FabricaApartamento extends FabricaAlojamiento {
    private double precio;
    private int capacidadMaxima;
    private double costoExtra;
    public FabricaApartamento(String nombre, String ciudad, String descripcion, String rutaFoto,
                              double precio, ArrayList<String> servicios,
                              int capacidadMaxima, double costoExtra) {
        super(nombre, ciudad, descripcion, rutaFoto, servicios);
        this.costoExtra = costoExtra;
        this.precio = precio;
        this.capacidadMaxima = capacidadMaxima;
    }

    @Override
    public Alojamiento crearProducto() {
        return ProductoApartamento.builder()
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
