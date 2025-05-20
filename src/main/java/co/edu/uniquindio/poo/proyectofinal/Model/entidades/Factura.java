package co.edu.uniquindio.poo.proyectofinal.Model.entidades;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Factura {
    @Builder.Default
    private UUID idFactura=UUID.randomUUID();
    private double subtotal;
    private double total;
    UUID idReserva;
    @Builder.Default
    private LocalDateTime fechaEmision=LocalDateTime.now();
}
