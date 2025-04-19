package co.edu.uniquindio.poo.proyectofinal.Model;


import javafx.scene.image.Image;

import java.util.ArrayList;

public class FabricaApartamento extends FabricaAlojamiento{
    double costoExtra;
    public FabricaApartamento(String nombre, String ciudad, String descripcion, ArrayList<String> resenas, Image foto,
                              double precio, double valoracion, ArrayList<String> servicios, Estado estado,
                              int capacidadMaxima, double costoExtra) {
        super(nombre, ciudad, descripcion, foto, precio, valoracion, servicios, resenas, estado, capacidadMaxima);
        this.costoExtra = costoExtra;
    }

    @Override
    public Alojamiento crearProducto() {
        return ProductoApartamento.builder()
                .nombre(getNombre())
                .ciudad(getCiudad())
                .descripcion(getDescripcion())
                .resenas(getResenas())
                .estado(getEstado())
                .capacidadMaxima(getCapacidadMaxima())
                .costoExtra(costoExtra).build();
    }
}
