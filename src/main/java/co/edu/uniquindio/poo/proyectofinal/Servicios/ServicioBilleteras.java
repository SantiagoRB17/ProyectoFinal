package co.edu.uniquindio.poo.proyectofinal.Servicios;

import co.edu.uniquindio.poo.proyectofinal.Model.entidades.Billetera;
import co.edu.uniquindio.poo.proyectofinal.Model.entidades.Persona;
import co.edu.uniquindio.poo.proyectofinal.Model.enums.Rol;
import co.edu.uniquindio.poo.proyectofinal.Repositorios.RepositorioBilleteras;

import java.util.Random;

public class ServicioBilleteras {

    private final RepositorioBilleteras repositorioBilleteras = new RepositorioBilleteras();


    public void registrarBilletera(Persona usuario) throws Exception {
        if(validarUsuario(usuario)){
            String numeroUnico = generarNumeroUnico();
            Billetera billetera = new Billetera(numeroUnico,0, usuario.getCedula());
            usuario.setNumeroBilletera(billetera.getNumero());
            repositorioBilleteras.agregarBilletera(billetera);
        }
   }

    private String generarNumeroUnico() {
        String numero = generarNumeroAleatorio();
        while (repositorioBilleteras.buscarPorNumero(numero) != null) {
            numero = generarNumeroAleatorio();
        }
        return numero;
    }


    private String generarNumeroAleatorio() {
        Random random = new Random();
        StringBuilder numero = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            numero.append(random.nextInt(10));
        }
        return numero.toString();
    }


    private boolean validarUsuario(Persona usuario) throws Exception {
        if (usuario == null) {
            throw new Exception("El usuario no puede ser nulo");
        }

        return usuario.getRol() == Rol.USUARIO;
    }


    public void recargarBilletera(float monto, String numeroBilletera) throws Exception {
        if (monto <= 0) {
            throw new Exception("El monto a recargar debe ser positivo");
        }

        Billetera billetera = repositorioBilleteras.buscarPorNumero(numeroBilletera);
        if (billetera == null) {
            throw new Exception("La billetera no existe");
        }

        billetera.setSaldo(billetera.getSaldo() + monto);
        repositorioBilleteras.actualizarBilletera(billetera);
    }


    public void realizarPago(float monto, String numeroBilletera) throws Exception {
        Billetera billetera = repositorioBilleteras.buscarPorNumero(numeroBilletera);
        if (billetera == null) {
            throw new Exception("La billetera no existe");
        }
        if (billetera.getSaldo() < monto) {
            throw new Exception("No hay saldo suficiente en la billetera");
        }else{
            billetera.setSaldo(billetera.getSaldo() - monto);
            repositorioBilleteras.actualizarBilletera(billetera);
        }
    }

    public Billetera buscarPorNumero(String numero) {
        return repositorioBilleteras.buscarPorNumero(numero);

    }

}
