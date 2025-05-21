package co.edu.uniquindio.poo.proyectofinal.Servicios;

import co.edu.uniquindio.poo.proyectofinal.Enums.TipoAlojamiento;
import co.edu.uniquindio.poo.proyectofinal.Model.AlojamientoDecorator.Oferta;
import co.edu.uniquindio.poo.proyectofinal.Model.AlojamientosFactory.Alojamiento;
import co.edu.uniquindio.poo.proyectofinal.Model.entidades.*;
import co.edu.uniquindio.poo.proyectofinal.Model.enums.Rol;
import co.edu.uniquindio.poo.proyectofinal.Observers.AlojamientosObserver;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ServiciosPlataforma implements IServiciosPlataforma {

    private final ServicioAlojamientos servicioAlojamientos=new ServicioAlojamientos();
    private final ServicioOfertas servicioOfertas=new ServicioOfertas();
    private final ServicioPersonas servicioPersonas=new ServicioPersonas();
    private final List<AlojamientosObserver> observadores=new ArrayList<>();
    private final ServicioBilleteras servicioBilleteras=new ServicioBilleteras();
    private final ServicioReservas servicioReserva=new ServicioReservas();

    public void registrarObservador(AlojamientosObserver observador){
        observadores.add(observador);
    }
    public void eliminarObservador(AlojamientosObserver observador){
        observadores.remove(observador);
    }
    public void notificarObservadores(){
        for(AlojamientosObserver observador:observadores){
            observador.actualizar();
        }
    }
    @Override
    public Persona iniciarSesion(String email, String password) throws Exception{
        return servicioPersonas.inicarSesion(email,password);
    }
    @Override
    public void crearUsuario(String nombre, String apellidos, String cedula, String email, String telefono, String password, Rol rol) throws Exception{
        servicioPersonas.agregarPersona(nombre,apellidos,cedula,email,telefono,password,rol);
        Persona persona=servicioPersonas.recuperarPersona(cedula);
        servicioBilleteras.registrarBilletera(persona);
        servicioPersonas.actualizarPersona(persona);
    }

    @Override
    public void editarPersona(String nombre, String apellidos, String cedula, String email, String telefono) throws Exception{
        servicioPersonas.editarPersona(nombre,apellidos,cedula,email,telefono);
    }

    @Override
    public Persona recuperarPersonaPorEmail(String email){
        return servicioPersonas.recuperarPorEmail(email);
    }

    @Override
    public void cambiarContrasena(String correo, String newPassword) throws Exception{
        servicioPersonas.cambiarPassword(correo,newPassword);
    }

    @Override
    public void recargarBilletera(float monto, String numeroBilletera) throws Exception{
        servicioBilleteras.recargarBilletera(monto, numeroBilletera);
    }

    @Override
    public void agregarAlojamiento(TipoAlojamiento tipo, String nombre, String ciudad, String descripcion,
                                   String rutaFoto, double precio, ArrayList<String> servicios, int capacidadMaxima,
                                   double costoExtra) throws Exception {
        servicioAlojamientos.agregarAlojamiento(tipo,nombre,ciudad,descripcion,rutaFoto,precio,servicios,capacidadMaxima,costoExtra);
        notificarObservadores();
    }

    @Override
    public void editarAlojamiento(UUID id, String nombre, String ciudad, String descripcion, String rutaFoto,
                                  double precio, ArrayList<String> servicios, int capacidadMaxima, double costoExtra) throws Exception {
        servicioAlojamientos.editarAlojamiento(id,nombre,ciudad,descripcion,rutaFoto,precio,servicios,capacidadMaxima,costoExtra);
        notificarObservadores();
    }

    @Override
    public void eliminarAlojamiento(UUID id,String rutaRelativa) throws Exception {
        servicioAlojamientos.eliminarAlojamiento(id,rutaRelativa);
        notificarObservadores();
    }

    @Override
    public ArrayList<TipoAlojamiento> listarOpcionesAlojamiento() {
        return servicioAlojamientos.listarOpcionesAlojamiento();
    }

    @Override
    public void eliminarImagen(String rutaRelativa) throws Exception {
        servicioAlojamientos.eliminarImagen(rutaRelativa);
    }

    @Override
    public List<Alojamiento> listarAlojamientos(){
        return servicioAlojamientos.listarAlojamientos();
    }
    @Override
    public List<Alojamiento> listarHoteles(){
        return servicioAlojamientos.listarHoteles();
    }

    @Override
    public List<Alojamiento> recuperarCasasYApartamentos(){
        return servicioAlojamientos.recuperarCasasYApartamentos();
    }

    @Override
    public Alojamiento buscarAlojamientoPorId(UUID id){
        return servicioAlojamientos.obtenerPorId(id);
    }

    @Override
    public Alojamiento agregarHotel(String nombre, String ciudad, String descripcion, String rutaFoto, ArrayList<String> servicios, int numeroHabitaciones) throws Exception {
        return servicioAlojamientos.agregarHotel(nombre,ciudad,descripcion,rutaFoto,servicios,numeroHabitaciones);
    }

    @Override
    public void editarHotel(UUID id, String nombre, String ciudad, String descripcion, String rutaFoto, ArrayList<String> servicios) throws Exception{
        servicioAlojamientos.editarHotel(id,nombre,ciudad,descripcion,rutaFoto,servicios);
        notificarObservadores();
    }

    @Override
    public void eliminarHotel(UUID id, String rutaRelativa) throws Exception {
        servicioAlojamientos.eliminarHotel(id,rutaRelativa);
        notificarObservadores();
    }

    @Override
    public ProductoHabitacion crearHabitacion(ProductoHotel hotel, int numeroHabitacion, double precio, int capacidad, String rutaImagenHabitacion
            , String descripcion) throws Exception {
        return servicioAlojamientos.crearHabitacion(hotel,numeroHabitacion,precio,capacidad,rutaImagenHabitacion,descripcion);
    }

    @Override
    public void asignarHabitaciones(List<ProductoHabitacion> habitaciones,UUID idHotel) throws Exception{
        servicioAlojamientos.asignarHabitaciones(habitaciones,idHotel);
        notificarObservadores();
    }

    @Override
    public List<ProductoHabitacion> recuperarHabitaciones(UUID id){
        return servicioAlojamientos.recuperarHabitaciones(id);
    }

    @Override
    public ProductoHabitacion obtenerHabitacionPorId(UUID idHabitacion){
        return servicioAlojamientos.obtenerHabitacionPorId(idHabitacion);
    }

    @Override
    public void eliminarHabitacion(UUID id, String rutaRelativa) {

    }

    @Override
    public void crearOferta(UUID idAlojamiento, double porcentajeDescuento, String descripcion, LocalDate fechaInicio, LocalDate fechaFin)
        throws Exception {
        servicioOfertas.crearOferta(idAlojamiento,porcentajeDescuento,descripcion,fechaInicio,fechaFin);
        notificarObservadores();
    }

    @Override
    public List<Oferta> listarOfertas(){
        return servicioOfertas.listarOfertas();
    }

    @Override
    public List<ProductoHabitacion> recuperarHabitacionesPorHotel(UUID idHotel) {
        return servicioAlojamientos.recuperarHabitaciones(idHotel);
    }

    @Override
    public void eliminarOferta(UUID id) throws Exception {
        servicioOfertas.eliminarOferta(id);
        notificarObservadores();
    }
    @Override
    public void crearReserva(String cedulaCliente,UUID idAlojamiento,LocalDate fechaInicio,LocalDate fechaFin,
                      int numeroHuespedes) throws Exception{
        Alojamiento alojamiento=servicioAlojamientos.obtenerPorId(idAlojamiento);
        servicioReserva.crearReserva(cedulaCliente,alojamiento,fechaInicio,fechaFin,numeroHuespedes);
    }
    @Override
    public void crearReservaHoteles(String cedulaCliente, Alojamiento alojamiento, ProductoHabitacion habitacion, LocalDate fechaInicio, LocalDate fechaFin,
                                    int numeroHuespedes) throws Exception{
        Alojamiento alojamientoHotel=servicioAlojamientos.obtenerPorId(alojamiento.getId());
        ProductoHabitacion habitacionHotel=servicioAlojamientos.obtenerHabitacionPorId(habitacion.getId());
        servicioReserva.crearReservaHoteles(cedulaCliente,alojamientoHotel,habitacionHotel,fechaInicio,fechaFin,numeroHuespedes);
    }

    @Override
    public void cancelarReserva(UUID idReserva) throws Exception{
        servicioReserva.cancelarReserva(idReserva);
        notificarObservadores();
    }

    @Override
    public List<Reserva> recuperarReservasUsuario(String cedula) throws Exception{
        return servicioReserva.recuperarReservasUsuario(cedula);
    }

    @Override
    public void pagarReserva(UUID idReserva) throws Exception{
        Reserva reserva = servicioReserva.recuperarReservaPorId(idReserva);

        ProductoHabitacion habitacion=servicioAlojamientos.obtenerHabitacionPorId(reserva.getIdHabitacion());


        Alojamiento alojamiento=servicioAlojamientos.obtenerPorId(reserva.getIdAlojamiento());


        Persona cliente = servicioPersonas.recuperarPersona(reserva.getCedulaCliente());

        double descuentoAplicado=calcularPorcentajeDescuentoAplicable(alojamiento,reserva.getFechaInicio(),reserva.getFechaFin());
        float monto=servicioReserva.calcularCostoReserva(idReserva,habitacion,alojamiento,descuentoAplicado);

        servicioBilleteras.realizarPago(monto, cliente.getNumeroBilletera());

        servicioReserva.generarFactura(idReserva, habitacion, alojamiento,descuentoAplicado);

        servicioReserva.pagarReserva(idReserva);

        notificarObservadores();

    }
    @Override
    public Factura recuperarFactura(Reserva reserva) throws Exception{
        return servicioReserva.recuperarFacturaReservaPorId(reserva.getIdFactura());
    }

    public double calcularPorcentajeDescuentoAplicable(Alojamiento alojamiento, LocalDate fechaInicio, LocalDate fechaFin)
            throws Exception{
        return servicioOfertas.calcularPorcentajeDescuentoAplicable(alojamiento,fechaInicio,fechaFin);
    }
}
