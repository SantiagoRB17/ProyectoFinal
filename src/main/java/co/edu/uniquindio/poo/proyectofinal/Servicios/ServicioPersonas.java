package co.edu.uniquindio.poo.proyectofinal.Servicios;

import co.edu.uniquindio.poo.proyectofinal.Model.entidades.Billetera;
import co.edu.uniquindio.poo.proyectofinal.Model.entidades.Persona;
import co.edu.uniquindio.poo.proyectofinal.Model.enums.Rol;
import co.edu.uniquindio.poo.proyectofinal.Repositorios.RepositorioPersonas;
import co.edu.uniquindio.poo.proyectofinal.Utils.EncriptacionContrasena;

import static co.edu.uniquindio.poo.proyectofinal.Model.validaciones.ValidacionCorreo.validarExpresionRegular;
import static co.edu.uniquindio.poo.proyectofinal.Model.validaciones.ValidarTelefono.validarTelefono;
public class ServicioPersonas {

    private final RepositorioPersonas repositorioPersonas = new RepositorioPersonas();
    private final EncriptacionContrasena encriptacionContrasena = new EncriptacionContrasena();

    private void validarCampos(String nombre, String apellidos, String cedula, String email, String telefono, String password) throws Exception {
        if (nombre == null || apellidos == null || cedula == null || nombre.isEmpty() || apellidos.isEmpty() || cedula.isEmpty() || password.isEmpty()) {
            throw new Exception("Todos los campos son obligatorios");
        }
        if (!validarExpresionRegular(email)) {
            throw new Exception("El formato de su email es incorrecto");
        }
        if (!validarTelefono(telefono)) {
            throw new Exception("El formato de su teléfono es incorrecto");
        }
    }

    public Persona recuperarPorEmail(String email) {
        return repositorioPersonas.obtenerPorEmail(email);
    }

    private void validarCampos(String nombre, String apellidos, String cedula, String email, String telefono, String password, Rol rol) throws Exception {
        if (nombre == null || apellidos == null || cedula == null || nombre.isEmpty() || apellidos.isEmpty() || cedula.isEmpty()) {
            throw new Exception("Todos los campos son obligatorios");
        }
        if (!validarExpresionRegular(email)) {
            throw new Exception("El formato de su email es incorrecto");
        }
        if (!validarTelefono(telefono)) {
            throw new Exception("El formato de su telefono es incorrecto");
        }
        if (rol == null) {
            throw new Exception("Debe Seleccionar el rol");
        }

    }

    public void agregarPersona(String nombre, String apellidos, String cedula, String email, String telefono, String password, Rol rol) throws Exception {

        validarCampos(nombre, apellidos, cedula, email, telefono, password, rol);


        String hashedPassword = encriptacionContrasena.hashPasswordSHA256(password);

        if (rol == Rol.ADMINISTRADOR) {
            boolean yaExisteAdministrador = repositorioPersonas.getPersonas().stream()
                    .anyMatch(persona -> persona.getRol() == Rol.ADMINISTRADOR);

            if (yaExisteAdministrador) {
                throw new Exception("Ya existe un administrador");
            }
        }
        Persona persona = Persona.builder()
                .nombre(nombre)
                .apellidos(apellidos)
                .cedula(cedula)
                .email(email)
                .telefono(telefono)
                .password(hashedPassword)
                .rol(rol)
                .cuentaActiva(false)
                .build();
        repositorioPersonas.agregarPersona(persona);
    }

    public void editarPersona(String nombre, String apellidos, String cedula, String email, String telefono, String password) throws Exception {
        Persona persona = repositorioPersonas.obtenerPorId(cedula);

        validarCampos(nombre, apellidos, cedula, email, telefono, password);
        persona.setNombre(nombre);
        persona.setApellidos(apellidos);
        persona.setEmail(email);
        persona.setTelefono(telefono);
        persona.setPassword(password);

        repositorioPersonas.editarPersona(persona);
    }

    public Persona recuperarPersona(String cedula) throws Exception {
        return repositorioPersonas.obtenerPorId(cedula);
    }

    public void actualizarPersona(Persona persona) throws Exception {
        repositorioPersonas.editarPersona(persona);
    }

    public void cambiarPassword(String correo, String newPassword) throws Exception {

        if (newPassword == null || newPassword.isBlank()) {
            throw new Exception("Debe ingresar una contraseña valida");
        }

        Persona persona = repositorioPersonas.obtenerPorEmail(correo);

        if (persona == null) {
            throw new Exception("No existe un persona con el correo: " + correo);
        }

        String newHashedPassword = encriptacionContrasena.hashPasswordSHA256(newPassword);

        if (persona.getPassword().equals(newHashedPassword)) {
            throw new Exception("La nueva contraseña debe ser diferente a la anterior");
        }
        persona.setPassword(newHashedPassword);
        repositorioPersonas.editarPersona(persona);
    }

    public Persona inciarSesion(String email, String password) throws Exception {
        if (email == null || email.isBlank()) {
            throw new Exception("El correo no puede estar vacío");
        }
        if (!validarExpresionRegular(email)) { // Valida el formato del correo
            throw new Exception("El formato del correo es incorrecto");
        }
        Persona persona = repositorioPersonas.obtenerPorEmail(email);
        if (persona == null) {
            throw new Exception("La persona no existe");
        }

        String hashedInput = encriptacionContrasena.hashPasswordSHA256(password);
        if (!persona.getPassword().equals(hashedInput)) {
            throw new Exception("Credenciales incorrectas");
        }

        if (!persona.isCuentaActiva()) {
            throw new Exception("La cuenta ha sido inhabilitada");
        }
        return persona;
    }




    public double consultarSaldo(String email,Billetera billetera) throws Exception {
        {
            if (email == null || email.isBlank()) {
                throw new Exception("El email no puede ser nulo o vacío");
            }

            // Buscar a la persona por el email
            Persona persona = repositorioPersonas.obtenerPorEmail(email);
            if (persona == null) {
                throw new Exception("No se encontró ninguna persona con el email: " + email);
            }

            // Buscar la billetera asociada
            if (billetera == null) {
                throw new Exception("No se encontró una billetera asociada al usuario con email: " + email);
            }

            // Retornar el saldo disponible
            return billetera.getSaldo();
        }

    }
}

