package co.edu.uniquindio.poo.proyectofinal.Model.entidades;
 import lombok.AllArgsConstructor;
 import lombok.Builder;
 import lombok.Getter;
 import lombok.Setter;

 @AllArgsConstructor
 @Getter
 @Setter
public class Billetera {
    private final String numero;
    private float saldo;
    private Persona Usuario;
}
