package co.edu.uniquindio.poo.proyectofinal.Model;

import co.edu.uniquindio.poo.proyectofinal.Enums.TipoAlojamiento;
import co.edu.uniquindio.poo.proyectofinal.Model.AlojamientosFactory.Alojamiento;
import co.edu.uniquindio.poo.proyectofinal.Servicios.ServicioAlojamientos;

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
}
