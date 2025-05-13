package co.edu.uniquindio.poo.proyectofinal.Servicios;

import co.edu.uniquindio.poo.proyectofinal.Enums.TipoAlojamiento;
import co.edu.uniquindio.poo.proyectofinal.Model.AlojamientoDecorator.Oferta;
import co.edu.uniquindio.poo.proyectofinal.Model.AlojamientosFactory.Alojamiento;
import co.edu.uniquindio.poo.proyectofinal.Model.ProductoHotel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface IServiciosPlataforma {

    void agregarAlojamiento(TipoAlojamiento tipo, String nombre, String ciudad, String descripcion, String rutaFoto,
                            double precio, ArrayList<String> servicios,
                            int capacidadMaxima, double costoExtra) throws Exception;
    void editarAlojamiento(UUID id, String nombre, String ciudad, String descripcion, String rutaFoto,
                                double precio, ArrayList<String> servicios,
                                int capacidadMaxima, double costoExtra) throws Exception;
    void eliminarAlojamiento(UUID id,String rutaRelativa) throws Exception;
    ArrayList<TipoAlojamiento> listarOpcionesAlojamiento();
    void eliminarImagen(String rutaRelativa)throws Exception;
    List<Alojamiento> listarAlojamientos();
    List<Alojamiento> listarHoteles();
    Alojamiento agregarHotel(String nombre, String ciudad, String descripcion, String rutaFoto,
                      ArrayList<String> servicios, int numeroHabitaciones) throws Exception;
    void editarHotel(UUID id, String nombre, String ciudad, String descripcion, String rutaFoto, ArrayList<String> servicios,
                     double costoExtra);
    void eliminarHotel(UUID id,String rutaRelativa) throws Exception;
    void crearHabitacion(ProductoHotel hotel, int numeroHabitacion, double precio, int capacidad, String rutaImagenHabitacion, String descripcion) throws Exception;
    void eliminarHabitacion(UUID id,String rutaRelativa);
    void crearOferta(Alojamiento alojamiento, double porcentajeDesuento, String descripcion, LocalDate fechaInicio, LocalDate fechaFin)
        throws Exception;
    List<Oferta> listarOfertas();
}
