package co.edu.uniquindio.poo.proyectofinal.Model;


import co.edu.uniquindio.poo.proyectofinal.Enums.Estado;
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

    Estado getEstado();

    String getNombre();

    String getCiudad();

    String getDescripcion();

    String getRutaFoto();

    double getPrecio();

    double getValoracion();

    ArrayList<String> getServicios();

    int getCapacidadMaxima();

    double getCostoExtra();

}
