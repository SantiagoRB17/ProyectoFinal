package co.edu.uniquindio.poo.proyectofinal.Utils;

public class CodigoVerificacion {
    public static String generarCodigo() {
        int codigo = (int) (Math.random() * 90000) + 100000;
        return String.valueOf(codigo);
    }
}
