package co.edu.uniquindio.poo.proyectofinal.Model.AlojamientoDecorator;

import co.edu.uniquindio.poo.proyectofinal.Model.AlojamientosFactory.Alojamiento;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.UUID;

public abstract class AlojamientoDecorator implements Alojamiento {
    @JsonProperty("alojamiento")
    private Alojamiento alojamiento;

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

    protected AlojamientoDecorator(Alojamiento alojamiento) {
        this.alojamiento = alojamiento;
    }

    protected AlojamientoDecorator() {
        // Deja el campo sin inicializar o asígnale null si lo necesitas
    }


}
