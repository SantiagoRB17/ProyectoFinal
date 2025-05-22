package co.edu.uniquindio.poo.proyectofinal.Utils;

import lombok.Getter;
import lombok.Setter;


import co.edu.uniquindio.poo.proyectofinal.Model.enums.Rol;


public class CodigoTemporal {
    private  static  String correo;
    private  static  String codigo;
    private  static  String modo;

    //Datos de registro

    private  static  String nombre;
    private  static  String apellido;
    private  static  String telefono;
    private  static  String cedula;
    private static String password;
    private static Rol rol;


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

    public static String getModo() {
        return modo;
    }

    public static void setModo(String modo) {
        CodigoTemporal.modo = modo;
    }

    public static String getNombre() {
        return nombre;
    }
    public static void setNombre(String nombre) {
        CodigoTemporal.nombre = nombre;
    }

    public static String getApellido() {
        return apellido;
    }
    public static void setApellido(String apellido) {
        CodigoTemporal.apellido = apellido;
    }
    public static String getTelefono() {
        return telefono;
    }
    public static void setTelefono(String telefono) {
        CodigoTemporal.telefono = telefono;
    }
    public static String getCedula() {
        return cedula;
    }
    public static void setCedula(String cedula) {
        CodigoTemporal.cedula = cedula;
    }
    public static String getPassword() {
        return password;
    }
    public static void setPassword(String password) {
        CodigoTemporal.password = password;
    }
    public static Rol getRol() {
        return rol;
    }
    public static void setRol(Rol rol) {
        CodigoTemporal.rol = rol;
    }


    public  static  boolean esCodigoValido(String codigoIngresado) {
        return  codigo !=null && codigoIngresado!=null && codigoIngresado.equals(codigo);
    }
}
