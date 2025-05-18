package co.edu.uniquindio.poo.proyectofinal.Servicios;

import co.edu.uniquindio.poo.proyectofinal.Controllers.VentanasController;
import co.edu.uniquindio.poo.proyectofinal.Model.entidades.Billetera;
import co.edu.uniquindio.poo.proyectofinal.Model.entidades.Persona;
import co.edu.uniquindio.poo.proyectofinal.Model.enums.Rol;
import co.edu.uniquindio.poo.proyectofinal.Repositorios.RepositorioPersonas;
import javafx.scene.control.Alert;

import static co.edu.uniquindio.poo.proyectofinal.Model.validaciones.ValidacionCorreo.validarExpresionRegular;
import static co.edu.uniquindio.poo.proyectofinal.Model.validaciones.ValidarTelefono.validarTelefono;
public class ServicioPersonas {
    
        private  static  RepositorioPersonas repositorioPersonas=RepositorioPersonas.getInstancia();

        private static ServicioPersonas INSTANCE;


    private void validarCampos(String nombre, String apellidos, String cedula, String email, String telefono) throws Exception {
        if (nombre == null || apellidos == null || cedula == null || nombre.isEmpty() || apellidos.isEmpty() || cedula.isEmpty()) {
            throw new Exception("Todos los campos son obligatorios");
        }
        if (!validarExpresionRegular(email)) {
            throw new Exception("El formato de su email es incorrecto");
        }
        if (!validarTelefono(telefono)) {
            throw new Exception("El formato de su teléfono es incorrecto");
        }
    }


    public  static  ServicioPersonas getInstancia() {
            if(INSTANCE==null){
                INSTANCE= new ServicioPersonas();
            }
            return INSTANCE;

        }


        private void validarCampos(String nombre,String apellidos, String cedula,String email,String telefono,String password,Rol rol)throws Exception{
            if(nombre == null ||apellidos ==null||cedula==null || nombre.isEmpty() || apellidos.isEmpty() || cedula.isEmpty()){
                throw new Exception("Todos los campos son obligatorios");
            }
            if(!validarExpresionRegular(email)  ){
                throw new Exception("El formato de su email es incorrecto");
            }
            if(!validarTelefono(telefono)) {
                throw new Exception("El formato de su telefono es incorrecto");
            }
            if(rol==null){
                throw new Exception("Debe Seleccionar el rol");
            }

        }

        public void agregarPersona(String nombre,String apellidos, String cedula,String email,String telefono,String password,Rol rol) throws Exception {

            validarCampos(nombre, apellidos, cedula, email, telefono, password, rol);

            if(rol==Rol.ADMINISTRADOR) {
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
                    .password(password)
                    .rol(rol)
                    .cuentaActiva(true)
                    .build();  // Llama

            if(rol==Rol.USUARIO) {
                persona.setBilletera(new Billetera());
            }
            repositorioPersonas.agregarPersona(persona);
        }

        public void editarPersona(String nombre,String apellidos, String cedula,String email,String telefono) throws Exception {
            Persona persona=repositorioPersonas.obtenerPorId(cedula);

            validarCampos(nombre, apellidos, cedula, email, telefono);
            persona.setNombre(nombre);
            persona.setApellidos(apellidos);
            persona.setCedula(cedula);
            persona.setEmail(email);
            persona.setTelefono(telefono);

            repositorioPersonas.editarPersona(persona);
        }

        public  void cambiarPassword(String correo,String newPassword) throws Exception {

        if(newPassword==null || newPassword.isBlank()){
            throw new Exception("Debe ingresar una contraseña valida");
        }

        Persona persona=repositorioPersonas.obtenerPorEmail(correo);

        if(persona==null){
            throw new Exception("No existe un persona con el correo: "+correo);
        }
        if(persona.getPassword().equals(newPassword)){
            throw new Exception("La nueva contraseña debe ser diferente a la anterior");
        }
        persona.setPassword(newPassword);
        repositorioPersonas.editarPersona(persona);
        }

        public Persona inicarSesion(String email,String password)throws Exception{
            Persona persona=repositorioPersonas.obtenerPorEmail(email);
            if(persona==null){
                throw new Exception("La persona no existe");
            }
            if(!persona.getPassword().equals(password)){
                throw new Exception("Credenciales incorrectas");
            }

            if (!persona.isCuentaActiva()){
                throw new Exception("La cuenta ha sido inhabilitada");
            }
            return persona;

        }
    }

