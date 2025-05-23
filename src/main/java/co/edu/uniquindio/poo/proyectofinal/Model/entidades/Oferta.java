package co.edu.uniquindio.poo.proyectofinal.Model.entidades;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;


@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "tipo"
)
@NoArgsConstructor
public class Oferta {

    @Getter
    private UUID idAlojamiento;
    @Getter
    private double porcentajeDescuento;
    @Getter
    private String descripcionOferta;
    @Getter
    private LocalDate fechaInicio, fechaFin;


    public Oferta(UUID idAlojamiento, double porcentajeDescuento, String descripcion, LocalDate fechaInicio, LocalDate fechaFin){
        this.idAlojamiento = idAlojamiento;
        this.porcentajeDescuento = porcentajeDescuento;
        this.descripcionOferta = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

}
