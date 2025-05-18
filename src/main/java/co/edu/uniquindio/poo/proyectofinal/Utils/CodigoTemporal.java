package co.edu.uniquindio.poo.proyectofinal.Utils;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CodigoTemporal {
    private  static  String correo;
    private  static  String codigo;

    public static String getCorreo() {
        return correo;
    }
    public static void setCorreo(String correo) {
        CodigoTemporal.correo = correo;
    }
    public static String getCodigo() {
        return codigo;
    }
    public static void setCodigo(String codigo) {
        CodigoTemporal.codigo = codigo;
    }

    public  static  boolean esCodigoValido(String codigoIngresado) {
        return  codigo !=null && codigoIngresado!=null && codigoIngresado.equals(codigo);
    }
}
