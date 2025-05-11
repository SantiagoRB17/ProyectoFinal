package co.edu.uniquindio.poo.proyectofinal.Servicios;

import co.edu.uniquindio.poo.proyectofinal.Model.AlojamientoDecorator.AlojamientoDecorator;
import co.edu.uniquindio.poo.proyectofinal.Model.AlojamientoDecorator.Oferta;
import co.edu.uniquindio.poo.proyectofinal.Model.AlojamientosFactory.Alojamiento;
import co.edu.uniquindio.poo.proyectofinal.Repositorios.RepositorioOfertas;

import java.time.LocalDate;
import java.util.List;

public class ServicioOfertas {

    private final RepositorioOfertas repositorioOfertas=new RepositorioOfertas();

    public void crearOferta(Alojamiento alojamiento, double porcentajeDescuento,
                            String descripcion, LocalDate fechaInicio, LocalDate fechaFin) throws Exception{
        if(alojamiento==null){
            throw new Exception("No se selecciono un alojamiento");
        }
        if(fechaInicio.isBefore(LocalDate.now())){
            throw new Exception("La fecha de inicio no puede ser anterior a la fecha actual");
        }
        if(fechaInicio.isAfter(fechaFin)){
            throw new Exception("La fecha de inicio debe ser anterior a la fecha final");
        }
        if(porcentajeDescuento<0 || porcentajeDescuento>100){
            throw new Exception("El porcentaje de descuento debe estar entre 0 y 100");
        }
        Oferta oferta=new
                Oferta(alojamiento,porcentajeDescuento,descripcion,fechaInicio,fechaFin);
        repositorioOfertas.agregarOferta(oferta);
    }

    public List<Oferta> listarOfertas(){
        return repositorioOfertas.getOfertas();
    }

}
