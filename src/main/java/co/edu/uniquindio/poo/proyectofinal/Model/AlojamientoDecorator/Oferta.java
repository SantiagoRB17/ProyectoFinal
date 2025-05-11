package co.edu.uniquindio.poo.proyectofinal.Model.AlojamientoDecorator;

import co.edu.uniquindio.poo.proyectofinal.Model.AlojamientosFactory.Alojamiento;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

public class Oferta extends AlojamientoDecorator {

    @Getter
    private final double porcentajeDescuento;
    @Setter
    private String descripcionOferta;
    @Getter
    private LocalDate fechaInicio,fechaFin;

    public Oferta(Alojamiento alojamiento, double porcentajeDescuento, String descripcion, LocalDate fechaInicio, LocalDate fechaFin){
        super(alojamiento);
        this.porcentajeDescuento = porcentajeDescuento;
        this.descripcionOferta = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    @Override
    public double calcularCosto(){
        double costoBase=super.calcularCosto();
        return costoBase-(costoBase*porcentajeDescuento/100);
    }

    @Override
    public String getDescripcion(){
        return super.getDescripcion()+"\n"+descripcionOferta;
    }
}
