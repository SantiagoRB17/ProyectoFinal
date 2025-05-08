package co.edu.uniquindio.poo.proyectofinal.Model.validaciones;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidarTelefono {
    public static boolean validarTelefono(String telefono) {
        String regexTelefono = "^\\+?\\d{1,3}?\\d{7,15}$";
        Pattern expresionValida = Pattern.compile(regexTelefono);
        Matcher matcherTelefono = expresionValida.matcher(telefono);
        boolean valido;
        valido = matcherTelefono.matches();
        return valido;
    }
}
