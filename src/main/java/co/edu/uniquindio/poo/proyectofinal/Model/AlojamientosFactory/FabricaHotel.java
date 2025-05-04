package co.edu.uniquindio.poo.proyectofinal.Model.AlojamientosFactory;

import co.edu.uniquindio.poo.proyectofinal.Model.ProductoHabitacion;

import java.util.ArrayList;

public class FabricaHotel extends FabricaAlojamiento {
    private ArrayList<ProductoHabitacion> habitaciones;
    private int numeroHabitaciones;

    public FabricaHotel(String nombre, String ciudad, String descripcion, String rutaFoto, double precio,
                        ArrayList<String> servicios, int capacidadMaxima, int numeroHabitaciones) {
        super(nombre, ciudad, descripcion, rutaFoto, precio, servicios, capacidadMaxima);
        this.habitaciones = new ArrayList<>();
        this.numeroHabitaciones = numeroHabitaciones;
    }

    @Override
    public Alojamiento crearProducto() {
        return null;
    }
}
