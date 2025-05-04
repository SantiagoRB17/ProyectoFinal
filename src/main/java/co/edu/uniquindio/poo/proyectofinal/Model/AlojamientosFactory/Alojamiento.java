package co.edu.uniquindio.poo.proyectofinal.Model.AlojamientosFactory;


import co.edu.uniquindio.poo.proyectofinal.Model.ProductoApartamento;
import co.edu.uniquindio.poo.proyectofinal.Model.ProductoCasa;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.ArrayList;
import java.util.UUID;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "tipo"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ProductoCasa.class, name = "casa"),
        @JsonSubTypes.Type(value = ProductoApartamento.class, name = "apartamento"),
})

public interface Alojamiento {
    double calcularCosto();

    UUID getId();

    String getNombre();

    String getCiudad();

    String getDescripcion();

    String getRutaFoto();

    double getPrecio();

    double getValoracion();

    void setActivo(boolean activo);

    boolean isActivo();

    ArrayList<String> getServicios();

    int getCapacidadMaxima();

    double getCostoExtra();

}
