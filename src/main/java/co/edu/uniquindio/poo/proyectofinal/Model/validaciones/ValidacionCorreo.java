package co.edu.uniquindio.poo.proyectofinal.Model.validaciones;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidacionCorreo {
         /**
         * Metodo que válida si una direccion de correo es válida
         */
        public static boolean validarExpresionRegular(String correo){
            String regexEmail="^[a-zA-Z0-9._%+-]+@[a-zA-z0-9.-]+\\.[a-zA-Z]{2,6}$";
            Pattern expresionValida=Pattern.compile(regexEmail);
            Matcher matcher=expresionValida.matcher(correo);
            boolean valido;
            valido= matcher.matches();
            return valido;
        }
 }


