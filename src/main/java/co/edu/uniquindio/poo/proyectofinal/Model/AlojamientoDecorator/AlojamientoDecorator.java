package co.edu.uniquindio.poo.proyectofinal.Model.AlojamientoDecorator;

import co.edu.uniquindio.poo.proyectofinal.Model.AlojamientosFactory.Alojamiento;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.*;

import java.util.ArrayList;
import java.util.UUID;

@AllArgsConstructor
public abstract class AlojamientoDecorator implements Alojamiento {
    private final Alojamiento alojamiento;

    @Override
    public UUID getId() {
        return alojamiento.getId();
    }
    @Override
    public double calcularCosto() {
        return alojamiento.calcularCosto();
    }
    @Override
    public String getNombre() {
        return alojamiento.getNombre();
    }
    @Override
    public String getCiudad() {
        return alojamiento.getCiudad();
    }
    @Override
    public String getDescripcion() {
        return alojamiento.getDescripcion();
    }
    @Override
    public String getRutaFoto() {
        return alojamiento.getRutaFoto();
    }
    @Override
    public double getValoracion(){
        return alojamiento.getValoracion();
    }

    @Override
    public void setActivo(boolean activo){
       alojamiento.setActivo(activo);
    }

    @Override
    public boolean isActivo(){
        return alojamiento.isActivo();
    }

    @Override
    public ArrayList<String> getServicios(){
        return alojamiento.getServicios();
    }

    @Override
    public double getCostoExtra(){
        return alojamiento.getCostoExtra();
    }

}
