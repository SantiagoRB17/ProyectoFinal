package co.edu.uniquindio.poo.proyectofinal.Model.AlojamientosFactory;

import co.edu.uniquindio.poo.proyectofinal.Model.ProductoHabitacion;
import co.edu.uniquindio.poo.proyectofinal.Model.ProductoHotel;

import java.util.ArrayList;
import java.util.List;

public class FabricaHotel extends FabricaAlojamiento {
    private List<ProductoHabitacion> habitaciones;
    private int numeroHabitaciones;

    public FabricaHotel(String nombre, String ciudad, String descripcion, String rutaFoto,
                        ArrayList<String> servicios, int numeroHabitaciones) {
        super(nombre, ciudad, descripcion, rutaFoto, servicios);
        this.habitaciones = new ArrayList<>(numeroHabitaciones);
        this.numeroHabitaciones = numeroHabitaciones;
    }

    @Override
    public Alojamiento crearProducto() {
        return ProductoHotel.builder()
                .nombre(getNombre())
                .ciudad(getCiudad())
                .descripcion(getDescripcion())
                .rutaFoto(getRutaFoto())
                .servicios(getServicios())
                .numeroDeHabitaciones(numeroHabitaciones)
                .habitaciones(habitaciones)
                .build();
    }
}
