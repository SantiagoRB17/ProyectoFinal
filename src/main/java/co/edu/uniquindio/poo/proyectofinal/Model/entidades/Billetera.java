package co.edu.uniquindio.poo.proyectofinal.Model.entidades;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Billetera {
    UUID id= UUID.randomUUID();
    private  double saldo;
}
