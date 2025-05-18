package co.edu.uniquindio.poo.proyectofinal.Servicios;

import co.edu.uniquindio.poo.proyectofinal.Enums.TipoAlojamiento;
import co.edu.uniquindio.poo.proyectofinal.Model.AlojamientosFactory.*;
import co.edu.uniquindio.poo.proyectofinal.Model.entidades.ProductoApartamento;
import co.edu.uniquindio.poo.proyectofinal.Model.entidades.ProductoCasa;
import co.edu.uniquindio.poo.proyectofinal.Model.entidades.ProductoHabitacion;
import co.edu.uniquindio.poo.proyectofinal.Model.entidades.ProductoHotel;
import co.edu.uniquindio.poo.proyectofinal.Repositorios.RepositorioAlojamientos;
import co.edu.uniquindio.poo.proyectofinal.Repositorios.RepositorioImagenes;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ServicioAlojamientos {

    private final RepositorioAlojamientos repositorioAlojamientos=new RepositorioAlojamientos();
    private final RepositorioImagenes repositorioImagenes=new RepositorioImagenes();

    /**
     * Método que valida los campos necesarios para agregar o editar un alojamiento.
     * @param nombre El nombre del alojamiento.
     * @param ciudad La ciudad donde se encuentra el alojamiento.
     * @param descripcion Una breve descripción del alojamiento.
     * @param rutaFoto La ruta de la foto del alojamiento.
     * @param precio El precio del alojamiento.
     * @param servicios Una lista de servicios disponibles en el alojamiento.
     * @param capacidadMaxima La capacidad máxima de personas que pueden hospedarse.
     * @param costoExtra El costo adicional por servicios extra.
     * @throws Exception Si algún campo no cumple con los requisitos de validación.
     */
    private void validarCampos(String nombre, String ciudad, String descripcion, String rutaFoto,
                                  double precio, ArrayList<String> servicios,
                                  int capacidadMaxima, double costoExtra) throws Exception{
        if(nombre == null ||ciudad ==null||descripcion==null || nombre.isEmpty() || ciudad.isEmpty() || descripcion.isEmpty()){
            throw new Exception("Todos los campos son obligatorios");
        }
        if(precio < 0 || costoExtra < 0){
            throw new Exception("El precio debe ser mayor que 0");
        }
        if(servicios == null || servicios.isEmpty()){
            throw new Exception("Debe añadir al menos un servicio");
        }
        if(capacidadMaxima <0){
            throw new Exception("La capacidad maxima debe ser mayor que 0");
        }
        if(rutaFoto == null || rutaFoto.isEmpty()){
            throw new Exception("Debe añadir una foto del alojamiento");
        }

    }
    /**
     * Método que agrega un nuevo alojamiento al repositorio.
     * Este método valida los campos y luego crea un alojamiento según el tipo especificado (CASA o APARTAMENTO).
     * @param tipo El tipo de alojamiento (CASA o APARTAMENTO).
     * @param nombre El nombre del alojamiento.
     * @param ciudad La ciudad donde se encuentra el alojamiento.
     * @param descripcion Una breve descripción del alojamiento.
     * @param rutaFoto La ruta de la foto del alojamiento.
     * @param precio El precio del alojamiento.
     * @param servicios Una lista de servicios disponibles en el alojamiento.
     * @param capacidadMaxima La capacidad máxima de personas que pueden hospedarse.
     * @param costoExtra El costo adicional por servicios extra.
     * @throws Exception Si algún campo es inválido o el tipo de alojamiento es incorrecto.
     */
    public void agregarAlojamiento(TipoAlojamiento tipo, String nombre, String ciudad, String descripcion, String rutaFoto,
                                   double precio, ArrayList<String> servicios,
                                   int capacidadMaxima, double costoExtra) throws Exception {

        validarCampos(nombre, ciudad, descripcion, rutaFoto, precio, servicios, capacidadMaxima, costoExtra);

        Alojamiento alojamiento;
        switch(tipo){
            case CASA:
                FabricaAlojamiento fabricaCasa= new FabricaCasa(nombre,ciudad,descripcion,rutaFoto,precio,servicios,capacidadMaxima,costoExtra);
                alojamiento = fabricaCasa.crearProducto();
                break;
            case APARTAMENTO:
                FabricaAlojamiento fabricaApartamento= new FabricaApartamento(nombre,ciudad,descripcion,rutaFoto,precio,servicios,capacidadMaxima,costoExtra);
                alojamiento = fabricaApartamento.crearProducto();
                break;
            default:
                throw new Exception("Tipo de alojamiento invalido");
            }
            repositorioAlojamientos.agregarAlojamiento(alojamiento);
    }
    /**
     * Método que edita los detalles de un alojamiento existente en el repositorio.
     * @param id El ID único del alojamiento a editar.
     * @param nombre El nombre del alojamiento.
     * @param ciudad La ciudad donde se encuentra el alojamiento.
     * @param descripcion Una breve descripción del alojamiento.
     * @param rutaFoto La ruta de la foto del alojamiento.
     * @param precio El precio del alojamiento.
     * @param servicios Una lista de servicios disponibles en el alojamiento.
     * @param capacidadMaxima La capacidad máxima de personas que pueden hospedarse.
     * @param costoExtra El costo adicional por servicios extra.
     * @throws Exception Si algún campo es inválido, el tipo de alojamiento es incorrecto o el alojamiento no existe.
     */
    public void editarAlojamiento(UUID id, String nombre, String ciudad, String descripcion, String rutaFoto,
                                  double precio, ArrayList<String> servicios,
                                  int capacidadMaxima, double costoExtra) throws Exception {
        Alojamiento alojamiento=repositorioAlojamientos.obtenerPorId(id);
        if(alojamiento==null){
            throw new Exception("Alojamiento inexistente o no seleccionado");
        }

        validarCampos(nombre, ciudad, descripcion, rutaFoto, precio, servicios, capacidadMaxima, costoExtra);
        Alojamiento alojamientoEditado;
        if(alojamiento instanceof ProductoCasa) {
            alojamientoEditado = ProductoCasa.builder()
                    .id(alojamiento.getId())
                    .nombre(nombre)
                    .ciudad(ciudad)
                    .descripcion(descripcion)
                    .rutaFoto(rutaFoto)
                    .precio(precio)
                    .servicios(servicios)
                    .capacidadMaxima(capacidadMaxima)
                    .costoExtra(costoExtra)
                    .build();
        } else if (alojamiento instanceof ProductoApartamento) {
            alojamientoEditado = ProductoApartamento.builder()
                    .id(alojamiento.getId())
                    .nombre(nombre)
                    .ciudad(ciudad)
                    .descripcion(descripcion)
                    .rutaFoto(rutaFoto)
                    .precio(precio)
                    .servicios(servicios)
                    .capacidadMaxima(capacidadMaxima)
                    .costoExtra(costoExtra)
                    .build();
        }else{
            throw new Exception("Tipo de alojamiento invalido");
        }
        repositorioAlojamientos.editarAlojamiento(alojamientoEditado);
    }

    /**
     * Metodo que permite eliminar un alojamiento
     * @param id id del alojamiento a eliminar
     * @param rutaRelativaImagen ruta de la imagen del alojamiento
     * @throws Exception
     */
    public void eliminarAlojamiento(UUID id,String rutaRelativaImagen) throws Exception{
        repositorioAlojamientos.eliminarAlojamiento(id);
        repositorioImagenes.eliminarImagen(rutaRelativaImagen);
    }

    /**
     * Metodo que permite eliminar la imagen de un alojamiento
     * @param rutaRelativaImagen ruta de la imagen a eliminar del alojamiento
     * @throws Exception
     */
    public void eliminarImagen(String rutaRelativaImagen) throws Exception{
        repositorioImagenes.eliminarImagen(rutaRelativaImagen);
    }

    /**
     * Metodo que recupera la lista de alojamientos almacenados en el repositorio
     * @return lista de alojamientos
     */
    public List<Alojamiento> listarAlojamientos(){
        return repositorioAlojamientos.getAlojamientos();
    }

    public List<Alojamiento> recuperarCasasYApartamentos(){
        return repositorioAlojamientos.listarCasasyApartamentos();
    }

    /**
     * Metodo que recupera la lista de hoteles almacenados en el repositorio
     * @return lista de hoteles
     */
    public List<Alojamiento> listarHoteles(){
        return repositorioAlojamientos.listarHoteles();
    }

    /**
     * Metodo que permite buscar un alojamiento en el repositorio por su id
     * @param id id del alojamiento
     * @return alojamiento encontrado o null si no existe
     */
    public Alojamiento obtenerPorId(UUID id){
        return repositorioAlojamientos.obtenerPorId(id);
    }

    /**
     * Método que crea un nuevo hotel y lo agrega en el repositorio de alojamientos.
     * @param nombre El nombre del hotel.
     * @param ciudad La ciudad donde se encuentra el hotel.
     * @param descripcion Una descripción breve del hotel.
     * @param rutaFoto La ruta de la foto que representa al hotel.
     * @param servicios Una lista de servicios ofrecidos por el hotel.
     * @param numeroHabitaciones El número de habitaciones disponibles en el hotel.
     * @return El objeto del hotel creado y almacenado en el repositorio.
     * @throws Exception Si algún campo es inválido o no cumple con los requisitos de validación.
     */
    public Alojamiento agregarHotel(String nombre, String ciudad, String descripcion, String rutaFoto,
                             ArrayList<String> servicios, int numeroHabitaciones) throws Exception {
        validarCamposHotel(nombre,ciudad,descripcion,rutaFoto,servicios,numeroHabitaciones);
        FabricaAlojamiento fabricaHotel= new FabricaHotel(nombre,ciudad,descripcion,rutaFoto,servicios,numeroHabitaciones);
        return fabricaHotel.crearProducto();
    }

    /**
     * Crea una nueva habitación y la agrega al listado de habitaciones de un hotel existente.
     * @param numeroHabitacion El número de la nueva habitación que se desea agregar.
     * @param precio El precio por noche de la habitación.
     * @param capacidad La capacidad máxima de personas que pueden hospedarse en la habitación.
     * @param rutaImagenHabitacion La ruta de la imagen representativa de la habitación.
     * @param descripcion Una breve descripción de las características de la habitación.
     * @throws Exception Si el número de la habitación, precio, capacidad, descripción,
     * o la ruta de imagen no cumplen con las validaciones definidas.
     */
    public void crearHabitacion(ProductoHotel hotel, int numeroHabitacion, double precio, int capacidad, String rutaImagenHabitacion
            , String descripcion) throws Exception{
        if(numeroHabitacion < 1){
            throw new Exception("El numero de la habitation debe ser mayor a 0");
        }
        if(precio < 0){
            throw new Exception("El precio debe ser mayor que 0");
        }
        if(capacidad < 0){
            throw new Exception("La capacidad debe ser mayor que 0");
        }
        if(rutaImagenHabitacion==null || rutaImagenHabitacion.isEmpty() || descripcion==null || descripcion.isEmpty()){
            throw new Exception("Debe agregar una imagen y descripcion de la habitacion");
        }

        ProductoHotel alojamiento = (ProductoHotel) repositorioAlojamientos.obtenerPorId(hotel.getId());
        if(alojamiento == null){
            repositorioAlojamientos.agregarAlojamiento(hotel);
        }else {
            if (alojamiento.getHabitaciones().size() >= alojamiento.getNumeroDeHabitaciones()) {
                throw new Exception("No se pueden agregar mas habitaciones al hotel");
            }
            if (alojamiento.getHabitaciones().stream().anyMatch(h -> h.getNumeroHabitacion() == numeroHabitacion)) {
                throw new Exception("Ya existe una habitacion con ese numero");
            }
            ProductoHabitacion habitacion = ProductoHabitacion.builder()
                    .numeroHabitacion(numeroHabitacion)
                    .precio(precio)
                    .capacidad(capacidad)
                    .rutaImagenHabitacion(rutaImagenHabitacion)
                    .descripcion(descripcion)
                    .build();
            alojamiento.getHabitaciones().add(habitacion);
            repositorioAlojamientos.editarAlojamiento(alojamiento);
        }
    }

    public List<ProductoHabitacion> recuperarHabitaciones(UUID id){
        return repositorioAlojamientos.cargarHabitaciones(id);
    }


    /**
     * Metodo que permite borrar un hotel junto con sus habitacion
     * @param id id del hotel
     * @param rutaRelativa ruta relativa de la imagen del hotel a eliminar
     * @throws Exception
     */
    public void eliminarHotel(UUID id, String rutaRelativa) throws Exception{
        ProductoHotel hotel= (ProductoHotel) repositorioAlojamientos.obtenerPorId(id);
        for(ProductoHabitacion habitacion: hotel.getHabitaciones()){
            repositorioImagenes.eliminarImagen(habitacion.getRutaImagenHabitacion());
        }
        repositorioAlojamientos.eliminarHotel(id);
        repositorioImagenes.eliminarImagen(rutaRelativa);
    }

    /**
     * Método que valida los campos necesarios para la creación o modificación de un hotel.
     * @param nombre El nombre del hotel. Debe ser no nulo y no estar vacío.
     * @param ciudad La ciudad donde se encuentra el hotel. Debe ser no nula y no estar vacía.
     * @param descripcion Una descripción breve del hotel. Debe ser no nula y no estar vacía.
     * @param rutaFoto La ruta de la foto representativa del hotel. Debe ser no nula y no estar vacía.
     * @param servicios Una lista de servicios ofrecidos por el hotel. Debe contener al menos un servicio y no ser nula.
     * @param numeroHabitaciones El número de habitaciones disponibles en el hotel. Debe ser mayor a 1.
     * @throws Exception
     */
    private void validarCamposHotel(String nombre, String ciudad, String descripcion, String rutaFoto,
                                    ArrayList<String> servicios, int numeroHabitaciones) throws Exception {
        if(nombre == null || ciudad == null || descripcion == null ||
                nombre.isEmpty() || ciudad.isEmpty() || descripcion.isEmpty()) {
            throw new Exception("Todos los campos son obligatorios");
        }
        if(numeroHabitaciones <= 1) {
            throw new Exception("El número de habitaciones debe ser mayor a 1");
        }
        if(servicios == null || servicios.isEmpty()) {
            throw new Exception("Debe añadir al menos un servicio");
        }
        if(rutaFoto == null || rutaFoto.isEmpty()) {
            throw new Exception("Debe añadir una foto del hotel");
        }
    }

    /**
     * Metodo para crear una lista con las opciones de alojamiento para el combo box
     * @return lista de tipos de alojamientos
     */
    public ArrayList<TipoAlojamiento> listarOpcionesAlojamiento(){
        ArrayList<TipoAlojamiento> opciones=new ArrayList<>();
        opciones.add(TipoAlojamiento.APARTAMENTO);
        opciones.add(TipoAlojamiento.CASA);
        return opciones;
    }

}
