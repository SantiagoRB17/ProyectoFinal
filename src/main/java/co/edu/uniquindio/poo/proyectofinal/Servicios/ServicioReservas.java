package co.edu.uniquindio.poo.proyectofinal.Servicios;

import co.edu.uniquindio.poo.proyectofinal.Enums.Estado;
import co.edu.uniquindio.poo.proyectofinal.Model.AlojamientosFactory.Alojamiento;
import co.edu.uniquindio.poo.proyectofinal.Model.entidades.*;
import co.edu.uniquindio.poo.proyectofinal.Repositorios.RepositorioFacturas;
import co.edu.uniquindio.poo.proyectofinal.Repositorios.RepositorioPersonas;
import co.edu.uniquindio.poo.proyectofinal.Repositorios.RepositorioReservas;

import java.io.File;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * Servicio encargado de gestionar la lógica relacionada con las reservas,
 * incluyendo validaciones, cálculos de costos, generación de facturas y notificaciones.
 */
public class ServicioReservas {

    private final RepositorioReservas repositorioReservas = new RepositorioReservas();
    private final RepositorioFacturas repositorioFacturas = new RepositorioFacturas();
    private final RepositorioPersonas repositorioPersonas = new RepositorioPersonas();
    private final double topeMaxDescuento = 60.0;

    /**
     * Crea una reserva para un alojamiento distinto de hotel (casa o apartamento).
     */
    public void crearReserva(String cedulaCliente, Alojamiento alojamiento, LocalDate fechaInicio, LocalDate fechaFin,
                             int numeroHuespedes) throws Exception {
        if (fechaInicio == null || fechaFin == null) {
            throw new Exception("La fecha de inicio o de fin no puede ser nula");
        }
        if (fechaInicio.isAfter(fechaFin)) {
            throw new Exception("La fecha de inicio debe ser anterior a la fecha final");
        }
        if (fechaInicio.isBefore(LocalDate.now())) {
            throw new Exception("La fecha de inicio no puede ser anterior a la fecha actual");
        }
        if (numeroHuespedes < 1) {
            throw new Exception("El número de huéspedes debe ser mayor a 0");
        }

        if (alojamiento instanceof ProductoCasa casa && numeroHuespedes > casa.getCapacidadMaxima()) {
            throw new Exception("Este alojamiento no puede soportar la cantidad de huéspedes solicitada");
        } else if (alojamiento instanceof ProductoApartamento apartamento && numeroHuespedes > apartamento.getCapacidadMaxima()) {
            throw new Exception("Este alojamiento no puede soportar la cantidad de huéspedes solicitada");
        }

        Reserva reserva = Reserva.builder()
                .cedulaCliente(cedulaCliente)
                .idAlojamiento(alojamiento.getId())
                .fechaInicio(fechaInicio)
                .fechaFin(fechaFin)
                .estado(Estado.PENDIENTE)
                .numeroHuespedes(numeroHuespedes)
                .build();
        repositorioReservas.agregarReserva(reserva);
    }

    /**
     * Crea una reserva para una habitación de hotel.
     */
    public void crearReservaHoteles(String cedulaCliente, Alojamiento alojamiento, ProductoHabitacion habitacion,
                                    LocalDate fechaInicio, LocalDate fechaFin, int numeroHuespedes) throws Exception {
        if (fechaInicio == null || fechaFin == null) {
            throw new Exception("La fecha de inicio o de fin no puede ser nula");
        }
        if (fechaInicio.isAfter(fechaFin)) {
            throw new Exception("La fecha de inicio debe ser anterior a la fecha final");
        }
        if (fechaInicio.isBefore(LocalDate.now())) {
            throw new Exception("La fecha de inicio no puede ser anterior a la fecha actual");
        }
        if (numeroHuespedes < 1) {
            throw new Exception("El número de huéspedes debe ser mayor a 0");
        }
        if (numeroHuespedes > habitacion.getCapacidad()) {
            throw new Exception("Esta habitación no puede soportar la cantidad de huéspedes solicitada");
        }

        Reserva reserva = Reserva.builder()
                .cedulaCliente(cedulaCliente)
                .idAlojamiento(alojamiento.getId())
                .idHabitacion(habitacion.getId())
                .fechaInicio(fechaInicio)
                .fechaFin(fechaFin)
                .estado(Estado.PENDIENTE)
                .numeroHuespedes(numeroHuespedes)
                .build();
        repositorioReservas.agregarReserva(reserva);
    }

    /**
     * Elimina una reserva según su ID.
     */
    public void cancelarReserva(UUID idReserva) throws Exception {
        repositorioReservas.eliminarReserva(idReserva);
    }

    /**
     * Devuelve la lista de reservas realizadas por un usuario según su cédula.
     */
    public List<Reserva> recuperarReservasUsuario(String cedula) throws Exception {
        return repositorioReservas.listarReservasUsuario(cedula);
    }

    /**
     * Cambia el estado de la reserva a "PAGADO".
     */
    public void pagarReserva(UUID idReserva) throws Exception {
        Reserva reserva = repositorioReservas.obtenerPorId(idReserva);
        reserva.setEstado(Estado.PAGADO);
        repositorioReservas.actualizarReserva(reserva);
    }

    /**
     * Verifica si una reserva ya terminó según la fecha de fin.
     */
    public void verificarVigencia(UUID idReserva) throws Exception {
        Reserva reserva = repositorioReservas.obtenerPorId(idReserva);
        if (reserva.getFechaFin().isBefore(LocalDate.now())) {
            throw new Exception("La reserva ya no es vigente");
        }
    }

    /**
     * Calcula el costo total de una reserva aplicando descuentos si es necesario.
     */
    public float calcularCostoReserva(UUID idReserva, ProductoHabitacion habitacion,
                                      Alojamiento alojamiento, double descuento) throws Exception {
        Reserva reserva = repositorioReservas.obtenerPorId(idReserva);
        if (reserva == null) {
            throw new Exception("Reserva no encontrada");
        }
        if (reserva.getEstado().equals(Estado.PAGADO)) {
            throw new Exception("La reserva ya se encuentra pagada");
        }

        int noches = (int) DAYS.between(reserva.getFechaInicio(), reserva.getFechaFin());
        double subTotal = calcularSubTotalReserva(habitacion, alojamiento);
        float total = (float) (subTotal * noches);

        if (descuento > 0.0) {
            double descuentoAplicable = Math.min(descuento, topeMaxDescuento);
            total = (float) (total * (1 - descuentoAplicable / 100));
        }

        return total;
    }

    /**
     * Calcula el precio base según si es habitación u otro tipo de alojamiento.
     */
    private double calcularSubTotalReserva(ProductoHabitacion habitacion, Alojamiento alojamiento) {
        return (habitacion != null) ? habitacion.getPrecio() : alojamiento.calcularCosto();
    }

    /**
     * Genera una factura para una reserva y envía un correo al cliente con un código QR del ID de la factura.
     */
    public void generarFactura(UUID idReserva, ProductoHabitacion habitacion, Alojamiento alojamiento,
                               double descuento) throws Exception {
        Reserva reserva = repositorioReservas.obtenerPorId(idReserva);
        double subTotal = calcularSubTotalReserva(habitacion, alojamiento);
        float total = calcularCostoReserva(idReserva, habitacion, alojamiento, descuento);

        Factura factura = Factura.builder()
                .subtotal(subTotal)
                .descuento(descuento)
                .idReserva(reserva.getIdReserva())
                .total(total)
                .build();

        reserva.setIdFactura(factura.getIdFactura());
        repositorioFacturas.agregarFactura(factura);
        repositorioReservas.actualizarReserva(reserva);

        try {
            ServicioGenerarQr servicioQr = new ServicioGenerarQr();
            File archivoQr = servicioQr.generarQR(factura.getIdFactura().toString(), "qr_factura_" + factura.getIdFactura());

            Persona persona = repositorioPersonas.obtenerPorId(reserva.getCedulaCliente());
            String email = persona.getEmail();

            ServicioEnvioEmail servicioEnvioEmail = new ServicioEnvioEmail();
            servicioEnvioEmail.enviarFcaturaConQr(
                    email,
                    "Tu factura #" + factura.getIdFactura(),
                    "Hola " + persona.getNombre() + ",\n\nGracias por tu reserva.\nAdjunto encontrarás el código QR de tu factura.",
                    archivoQr
            );
        } catch (Exception e) {
            System.err.println("Error al generar o enviar el QR de la factura: " + e.getMessage());
            throw new Exception("No fue posible generar y enviar el QR de la factura.");

        }
    }

        /**
         * Recupera una factura por su ID.
         */
        public Factura recuperarFacturaReservaPorId (UUID idFactura) throws Exception {
            return repositorioFacturas.obtenerPorId(idFactura);
        }

        /**
         * Recupera una reserva por su ID.
         */
        public Reserva recuperarReservaPorId (UUID idReserva) throws Exception {
            Reserva reserva = repositorioReservas.obtenerPorId(idReserva);
            if (reserva == null) {
                throw new Exception("No existe reserva");
            }
            return reserva;
        }

        /**
         * Verifica que una reserva esté en estado COMPLETADO y PAGADO.
         */
        public void verificarEstadoReservaCompletado (UUID idReserva) throws Exception {
            Reserva reserva = repositorioReservas.obtenerPorId(idReserva);
            if (reserva.getEstado() != Estado.COMPLETADO && reserva.getEstado() == Estado.PAGADO) {
                throw new Exception("La reserva no se encuentra pagada o no se ha completado");
            }

    }
}
