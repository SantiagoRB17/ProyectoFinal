package co.edu.uniquindio.poo.proyectofinal.Model.entidades;

import co.edu.uniquindio.poo.proyectofinal.Enums.Estado;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reserva {
    @Builder.Default
    private UUID idReserva=UUID.randomUUID();
    private String cedulaCliente;
    private UUID idAlojamiento;
    private UUID idHabitacion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int numeroHuespedes;
    private Estado estado;
    private UUID idFactura;

}
