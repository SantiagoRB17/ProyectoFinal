package co.edu.uniquindio.poo.proyectofinal.Servicios;

import co.edu.uniquindio.poo.proyectofinal.Model.entidades.Persona;
import co.edu.uniquindio.poo.proyectofinal.Model.enums.Rol;
import co.edu.uniquindio.poo.proyectofinal.Repositorios.RepositorioPersonas;

import static co.edu.uniquindio.poo.proyectofinal.Model.validaciones.ValidacionCorreo.validarExpresionRegular;
import static co.edu.uniquindio.poo.proyectofinal.Model.validaciones.ValidarTelefono.validarTelefono;
public class ServicioPersonas {
    




        private RepositorioPersonas repositorioPersonas=RepositorioPersonas.getInstancia();


        private void validarCampos(String nombre,String apellidos, String cedula,String email,String telefono,String password,Rol rol)throws Exception{
            if(nombre == null ||apellidos ==null||cedula==null || nombre.isEmpty() || apellidos.isEmpty() || cedula.isEmpty()){
                throw new Exception("Todos los campos son obligatorios");
            }
            if(!validarExpresionRegular(email)  ){
                throw new Exception("El formato de su email es incorrecto");
            }
            if(validarTelefono(telefono)) {
                throw new Exception("El formato de su telefono es inocrrecto");
            }
            if(rol==null){
                throw new Exception("Debe Seleccionar el rol");
            }

        }

        public void agregarPersona(String nombre,String apellidos, String cedula,String email,String telefono,String password,Rol rol) throws Exception {

            validarCampos(nombre, apellidos, cedula, email, telefono, password, rol);
            Persona persona= new Persona();
            repositorioPersonas.agregarPersona(persona);
        }

        public void editarPersona(String nombre,String apellidos, String cedula,String email,String telefono,String password,Rol rol) throws Exception {
            Persona persona=repositorioPersonas.obtenerPorId(cedula);
            if(persona==null){
                throw new Exception("La persona no existe");
            }

            validarCampos(nombre, apellidos, cedula, email, telefono, password, rol);

        }



    }

