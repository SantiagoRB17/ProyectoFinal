package co.edu.uniquindio.poo.proyectofinal.Servicios;

import co.edu.uniquindio.poo.proyectofinal.Enums.TipoAlojamiento;
import co.edu.uniquindio.poo.proyectofinal.Model.AlojamientosFactory.Alojamiento;
import co.edu.uniquindio.poo.proyectofinal.Model.ProductoHotel;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ServiciosPlataforma implements IServiciosPlataforma {

    ServicioAlojamientos servicioAlojamientos=new ServicioAlojamientos();

    @Override
    public void agregarAlojamiento(TipoAlojamiento tipo, String nombre, String ciudad, String descripcion,
                                   String rutaFoto, double precio, ArrayList<String> servicios, int capacidadMaxima,
                                   double costoExtra) throws Exception {
        servicioAlojamientos.agregarAlojamiento(tipo,nombre,ciudad,descripcion,rutaFoto,precio,servicios,capacidadMaxima,costoExtra);
    }

    @Override
    public void editarAlojamiento(UUID id, String nombre, String ciudad, String descripcion, String rutaFoto,
                                  double precio, ArrayList<String> servicios, int capacidadMaxima, double costoExtra) throws Exception {
        servicioAlojamientos.editarAlojamiento(id,nombre,ciudad,descripcion,rutaFoto,precio,servicios,capacidadMaxima,costoExtra);
    }

    @Override
    public void eliminarAlojamiento(UUID id,String rutaRelativa) throws Exception {
        servicioAlojamientos.eliminarAlojamiento(id,rutaRelativa);
    }

    @Override
    public ArrayList<TipoAlojamiento> listarOpcionesAlojamiento() {
        return servicioAlojamientos.listarOpcionesAlojamiento();
    }

    @Override
    public void eliminarImagen(String rutaRelativa) throws Exception {
        servicioAlojamientos.eliminarImagen(rutaRelativa);
    }

    @Override
    public List<Alojamiento> listarAlojamientos(){
        return servicioAlojamientos.listarAlojamientos();
    }
    @Override
    public List<Alojamiento> listarHoteles(){
        return servicioAlojamientos.listarHoteles();
    }

    public Alojamiento buscarAlojamientoPorId(UUID id){
        return servicioAlojamientos.obtenerPorId(id);
    }

    @Override
    public Alojamiento agregarHotel(String nombre, String ciudad, String descripcion, String rutaFoto, ArrayList<String> servicios, int numeroHabitaciones) throws Exception {
        return servicioAlojamientos.agregarHotel(nombre,ciudad,descripcion,rutaFoto,servicios,numeroHabitaciones);
    }

    @Override
    public void editarHotel(UUID id, String nombre, String ciudad, String descripcion, String rutaFoto, ArrayList<String> servicios, double costoExtra) {

    }

    @Override
    public void eliminarHotel(UUID id, String rutaRelativa) {

    }

    @Override
    public void agregarHabitacion(UUID id, int numeroHabitacion, double precio, int capacidad, String rutaImagenHabitacion, String descripcion) throws Exception {
        servicioAlojamientos.crearHabitacion(id,numeroHabitacion,precio,capacidad,rutaImagenHabitacion,descripcion);
    }

    @Override
    public void eliminarHabitacion(UUID id, String rutaRelativa) {

    }

}
