package co.edu.uniquindio.poo.proyectofinal.Servicios;

import co.edu.uniquindio.poo.proyectofinal.Enums.TipoAlojamiento;
import co.edu.uniquindio.poo.proyectofinal.Model.entidades.Oferta;
import co.edu.uniquindio.poo.proyectofinal.Model.AlojamientosFactory.Alojamiento;
import co.edu.uniquindio.poo.proyectofinal.Model.entidades.*;
import co.edu.uniquindio.poo.proyectofinal.Model.enums.Rol;
import co.edu.uniquindio.poo.proyectofinal.Utils.RangoPrecio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Interface que define los servicios principales de la plataforma.
 * Proporciona métodos para gestionar alojamientos, usuarios, ofertas y billeteras electrónicas.
 */
public interface IServiciosPlataforma {

    /**
     * Agrega un nuevo alojamiento a la plataforma.
     *
     * @param tipo            El tipo de alojamiento (CASA, HOTEL, APARTAMENTO).
     * @param nombre          El nombre del alojamiento.
     * @param ciudad          La ciudad donde se encuentra el alojamiento.
     * @param descripcion     La descripción del alojamiento.
     * @param rutaFoto        La ruta de la foto del alojamiento.
     * @param precio          El precio del alojamiento.
     * @param servicios       Una lista de servicios que ofrece el alojamiento.
     * @param capacidadMaxima La capacidad máxima del alojamiento.
     * @param costoExtra      Costos adicionales asociados al alojamiento.
     * @throws Exception Si hay errores al agregar el alojamiento.
     */
    void agregarAlojamiento(TipoAlojamiento tipo, String nombre, String ciudad, String descripcion, String rutaFoto,
                            double precio, ArrayList<String> servicios,
                            int capacidadMaxima, double costoExtra) throws Exception;

    /**
     * Edita los detalles de un alojamiento existente.
     *
     * @param id              El identificador único del alojamiento.
     * @param nombre          El nombre del alojamiento.
     * @param ciudad          La ciudad donde se encuentra el alojamiento.
     * @param descripcion     La descripción del alojamiento.
     * @param rutaFoto        La ruta de la foto del alojamiento.
     * @param precio          El precio del alojamiento.
     * @param servicios       Una lista de servicios que ofrece el alojamiento.
     * @param capacidadMaxima La capacidad máxima del alojamiento.
     * @param costoExtra      Costos adicionales asociados al alojamiento.
     * @throws Exception Si hay errores al editar el alojamiento.
     */
    void editarAlojamiento(UUID id, String nombre, String ciudad, String descripcion, String rutaFoto,
                           double precio, ArrayList<String> servicios,
                           int capacidadMaxima, double costoExtra) throws Exception;

    /**
     * Elimina un alojamiento de la plataforma.
     *
     * @param id           El identificador único del alojamiento.
     * @param rutaRelativa La ruta relativa del recurso asociado al alojamiento.
     * @throws Exception Si hay errores al eliminar el alojamiento.
     */
    void eliminarAlojamiento(UUID id, String rutaRelativa) throws Exception;

    /**
     * Lista las opciones disponibles de tipos de alojamiento.
     *
     * @return Una lista con los tipos de alojamiento.
     */
    ArrayList<TipoAlojamiento> listarOpcionesAlojamiento();

    /**
     * Elimina una imagen asociada a un alojamiento.
     *
     * @param rutaRelativa La ruta relativa de la imagen.
     * @throws Exception Si hay errores al eliminar la imagen.
     */
    void eliminarImagen(String rutaRelativa) throws Exception;

    /**
     * Lista todos los alojamientos disponibles en la plataforma.
     *
     * @return Una lista de objetos de tipo Alojamiento.
     */
    List<Alojamiento> listarAlojamientos();

    /**
     * Lista todos los hoteles disponibles en la plataforma.
     *
     * @return Una lista de objetos de tipo Alojamiento que representan hoteles.
     */
    List<Alojamiento> listarHoteles();

    /**
     * Agrega un nuevo hotel a la plataforma.
     *
     * @param nombre             El nombre del hotel.
     * @param ciudad             La ciudad donde se encuentra el hotel.
     * @param descripcion        La descripción del hotel.
     * @param rutaFoto           La ruta de la foto del hotel.
     * @param servicios          Una lista de servicios que ofrece el hotel.
     * @param numeroHabitaciones El número de habitaciones del hotel.
     * @return El objeto de tipo Alojamiento creado.
     * @throws Exception Si hay errores al agregar el hotel.
     */
    Alojamiento agregarHotel(String nombre, String ciudad, String descripcion, String rutaFoto,
                      ArrayList<String> servicios, int numeroHabitaciones) throws Exception;
    /**
     * Edita los detalles de un hotel existente.
     *
     * @param id          El identificador único del hotel.
     * @param nombre      El nombre del hotel.
     * @param ciudad      La ciudad donde se encuentra el hotel.
     * @param descripcion La descripción del hotel.
     * @param rutaFoto    La ruta de la foto del hotel.
     * @param servicios   Una lista de servicios que ofrece el hotel.
     */
    void editarHotel(UUID id, String nombre, String ciudad, String descripcion, String rutaFoto, ArrayList<String> servicios
                     )throws Exception;
    /**
     * Elimina un hotel de la plataforma.
     *
     * @param id           El identificador único del hotel.
     * @param rutaRelativa La ruta relativa del recurso asociado al hotel.
     * @throws Exception Si hay errores al eliminar el hotel.
     */
    void eliminarHotel(UUID id,String rutaRelativa) throws Exception;
    /**
     * Crea una nueva habitación dentro de un hotel.
     *
     * @param hotel                El hotel donde se agregará la habitación.
     * @param numeroHabitacion     El número de la habitación.
     * @param precio               El precio de la habitación.
     * @param capacidad            La capacidad máxima de la habitación.
     * @param rutaImagenHabitacion La ruta de la imagen de la habitación.
     * @param descripcion          La descripción de la habitación.
     * @throws Exception Si hay errores al crear la habitación.
     */
    ProductoHabitacion crearHabitacion(ProductoHotel hotel, int numeroHabitacion, double precio, int capacidad, String rutaImagenHabitacion, String descripcion) throws Exception;
    void asignarHabitaciones(List<ProductoHabitacion> habitaciones,UUID idHotel) throws Exception;
    /**
     * Elimina una habitación de un hotel.
     *
     * @param id           El identificador único de la habitación.
     * @param rutaRelativa La ruta relativa del recurso asociado a la habitación.
     */
    void eliminarHabitacion(UUID id,String rutaRelativa) throws Exception;
    List<ProductoHabitacion> recuperarHabitaciones(UUID id);
    ProductoHabitacion obtenerHabitacionPorId(UUID idHabitacion);
    void editarHabitacion(UUID idHabitacion, int numeroHabitacion, double precio, int capacidad,
                          String descripcion, String rutaImagen, UUID idHotel) throws Exception;

    /**
     * Crea una nueva oferta para un alojamiento.
     *
     * @param idAlojamiento       El identificador único del alojamiento.
     * @param porcentajeDescuento El porcentaje de descuento de la oferta.
     * @param descripcion         La descripción de la oferta.
     * @param fechaInicio         La fecha de inicio de la oferta.
     * @param fechaFin            La fecha de finalización de la oferta.
     * @throws Exception Si hay errores al crear la oferta.
     */
    void crearOferta(UUID idAlojamiento, double porcentajeDescuento, String descripcion, LocalDate fechaInicio, LocalDate fechaFin)
        throws Exception;
    /**
     * Lista todas las ofertas registradas.
     *
     * @return Una lista de objetos de tipo Oferta.
     */
    List<Oferta> listarOfertas();
    /**
     * Recupera las habitaciones asociadas a un hotel específico.
     *
     * @param idHotel El identificador único del hotel.
     * @return Una lista de habitaciones pertenecientes al hotel.
     */
    List<ProductoHabitacion> recuperarHabitacionesPorHotel(UUID idHotel);
    /**
     * Elimina una oferta registrada.
     *
     * @param id El identificador único de la oferta.
     * @throws Exception Si hay errores al eliminar la oferta.
     */
    void eliminarOferta(UUID id) throws Exception;
    /**
     * Busca un alojamiento por su identificador único.
     *
     * @param id El identificador único del alojamiento.
     * @return El objeto de tipo Alojamiento encontrado, o null si no se encuentra.
     */
    Alojamiento buscarAlojamientoPorId(UUID id);
    /**
     * Recupera las casas y apartamentos registrados en la plataforma.
     *
     * @return Una lista de objetos de tipo Alojamiento que representan casas y apartamentos.
     */
    List<Alojamiento> recuperarCasasYApartamentos();
    /**
     * Crea un nuevo usuario en la plataforma.
     *
     * @param nombre    El nombre del usuario.
     * @param apellidos Los apellidos del usuario.
     * @param cedula    La cédula del usuario.
     * @param email     El correo electrónico del usuario.
     * @param telefono  El número de teléfono del usuario.
     * @param password  La contraseña del usuario.
     * @param rol       El rol asignado al usuario (USUARIO o ADMINISTRADOR).
     * @throws Exception Si hay errores al crear el usuario.
     */
    void crearUsuario(String nombre, String apellidos, String cedula, String email, String telefono, String password, Rol rol) throws Exception;
    /**
     * Inicia sesión en la plataforma.
     *
     * @param email    El correo electrónico del usuario.
     * @param password La contraseña del usuario.
     * @return El objeto Persona que representa al usuario conectado.
     * @throws Exception Si hay errores de autenticación.
     */
    Persona iniciarSesion(String email, String password) throws Exception;
    /**
     * Cambia la contraseña de un usuario.
     *
     * @param correo      El correo electrónico del usuario.
     * @param newPassword La nueva contraseña del usuario.
     * @throws Exception Si hay errores al cambiar la contraseña.
     */
    void cambiarContrasena(String correo, String newPassword) throws Exception;
    /**
     * Recupera la información de una persona por su correo electrónico.
     *
     * @param email El correo electrónico a buscar.
     * @return El objeto Persona encontrado, o null si no existe.
     */
    Persona recuperarPersonaPorEmail(String email);
    /**
     * Edita la información de un usuario específico.
     *
     * @param nombre    El nombre del usuario.
     * @param apellidos Los apellidos del usuario.
     * @param cedula    La cédula del usuario.
     * @param email     El correo electrónico del usuario.
     * @param telefono  El número de teléfono del usuario.
     * @param password  La contraseña del usuario.
     * @throws Exception Si hay errores al editar la persona.
     */
    void editarPersona(String nombre, String apellidos, String cedula, String email, String telefono,String password) throws Exception;
    /**
     * Recarga dinero en la billetera electrónica de un usuario.
     *
     * @param monto           La cantidad de dinero a recargar.
     * @param numeroBilletera El número de la billetera del usuario.
     * @throws Exception Si hay errores al recargar la billetera.
     */
    void recargarBilletera(float monto, String numeroBilletera) throws Exception;
    void crearReserva(String cedulaCliente,UUID idAlojamiento, LocalDate fechaInicio,LocalDate fechaFin,
                             int numeroHuespedes) throws Exception;
    void crearReservaHoteles(String cedulaCliente, Alojamiento alojamiento, ProductoHabitacion habitacion, LocalDate fechaInicio, LocalDate fechaFin,
                             int numeroHuespedes) throws Exception;
    void cancelarReserva(UUID idReserva) throws Exception;
    List<Reserva> recuperarReservasUsuario(String cedula) throws Exception;
    void pagarReserva(UUID idReserva) throws Exception;
    Factura recuperarFactura(Reserva reserva) throws Exception;
    double calcularPorcentajeDescuentoAplicable(Alojamiento alojamiento, LocalDate fechaInicio, LocalDate fechaFin)
            throws Exception;
    void anadirResena(UUID idReserva, int valoracion, String resena, UUID idAlojamiento) throws Exception;
    void verificarEstadoReservaCompletado(UUID idReserva) throws Exception;
    List<Alojamiento> filtrarAlojamientos(String ciudadFiltro, String nombreFiltro, TipoAlojamiento tipoFiltro, RangoPrecio rangoPrecio);

    /**
     * Consulta el saldo de la billetera de un usuario.
     *
     * @param email El correo electrónico del usuario asociado a la billetera.
     * @return El saldo disponible en la billetera.
     * @throws Exception Si hay errores al consultar el saldo.
     */
    double consultarSaldo(String email,Billetera billetera) throws Exception;
    Map<Alojamiento, Double> calcularOcupacionPorAlojamiento();
    Map<Alojamiento, Double> obtenerGananciasPorAlojamiento();
    Map<Alojamiento, Long> obtenerNumeroReservasPorAlojamiento();
    Map<String, Double> obtenerTiposAlojamientoMasRentables();
}
