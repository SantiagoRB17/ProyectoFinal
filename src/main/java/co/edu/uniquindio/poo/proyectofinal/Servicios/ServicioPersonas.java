package co.edu.uniquindio.poo.proyectofinal.Servicios;

import co.edu.uniquindio.poo.proyectofinal.Model.entidades.Persona;
import co.edu.uniquindio.poo.proyectofinal.Model.enums.Rol;
import co.edu.uniquindio.poo.proyectofinal.Repositorios.RepositorioPersonas;

import static co.edu.uniquindio.poo.proyectofinal.Model.validaciones.ValidacionCorreo.validarExpresionRegular;
import static co.edu.uniquindio.poo.proyectofinal.Model.validaciones.ValidarTelefono.validarTelefono;
public class ServicioPersonas {
    
        private  static  RepositorioPersonas repositorioPersonas=RepositorioPersonas.getInstancia();


        private static ServicioPersonas INSTANCE;

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
            repositorioPersonas.agregarPersona(persona);


                ServicioBilleteras servicioBilleteras = ServicioBilleteras.getInstancia();
                servicioBilleteras.registrarBilletera(persona);

        }

        public void editarPersona(String nombre,String apellidos, String cedula,String email,String telefono,String password,Rol rol) throws Exception {
            Persona persona=repositorioPersonas.obtenerPorId(cedula);
            if(persona==null){
                throw new Exception("La persona no existe");
            }

            validarCampos(nombre, apellidos, cedula, email, telefono, password, rol);

        }
    }

