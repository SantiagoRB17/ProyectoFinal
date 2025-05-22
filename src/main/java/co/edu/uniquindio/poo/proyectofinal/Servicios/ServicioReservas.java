package co.edu.uniquindio.poo.proyectofinal.Servicios;

import co.edu.uniquindio.poo.proyectofinal.Enums.Estado;
import co.edu.uniquindio.poo.proyectofinal.Model.AlojamientosFactory.Alojamiento;
import co.edu.uniquindio.poo.proyectofinal.Model.entidades.*;
import co.edu.uniquindio.poo.proyectofinal.Repositorios.RepositorioFacturas;
import co.edu.uniquindio.poo.proyectofinal.Repositorios.RepositorioReservas;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static java.time.temporal.ChronoUnit.DAYS;

public class ServicioReservas {

    private final RepositorioReservas repositorioReservas = new RepositorioReservas();
    private final RepositorioFacturas repositorioFacturas = new RepositorioFacturas();
    private double topeMaxDescuento = 60.0;

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
        if (fechaFin.isBefore(fechaInicio)) {
            throw new Exception("La fecha de fin debe ser posterior a la fecha de inicio");
        }
        if (numeroHuespedes < 1) {
            throw new Exception("El numero de huespedes debe ser mayor a 0");
        }
        if (alojamiento instanceof ProductoCasa casa) {
            if (numeroHuespedes > casa.getCapacidadMaxima()) {
                throw new Exception("Este alojamiento no puede soportar la cantidad de huespedes solicitada");
            }
        } else if (alojamiento instanceof ProductoApartamento apartamento) {
            if (numeroHuespedes > apartamento.getCapacidadMaxima()) {
                throw new Exception("Este alojamiento no puede soportar la cantidad de huespedes solicitada");
            }
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

    public void crearReservaHoteles(String cedulaCliente, Alojamiento alojamiento, ProductoHabitacion habitacion, LocalDate fechaInicio, LocalDate fechaFin,
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
            throw new Exception("El numero de huespedes debe ser mayor a 0");
        }
        if (numeroHuespedes > habitacion.getCapacidad()) {
            throw new Exception("Esta habitacion no puede soportar la cantidad de huespedes solicitada");
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


    public void cancelarReserva(UUID idReserva) throws Exception {
        repositorioReservas.eliminarReserva(idReserva);
    }

    public List<Reserva> recuperarReservasUsuario(String cedula) throws Exception {
        return repositorioReservas.listarReservasUsuario(cedula);
    }

    public void pagarReserva(UUID idReserva) throws Exception {
        Reserva reserva = repositorioReservas.obtenerPorId(idReserva);
        reserva.setEstado(Estado.PAGADO);
        repositorioReservas.actualizarReserva(reserva);
    }

    public void verificarVigencia(UUID idReserva) throws Exception{
        Reserva reserva = repositorioReservas.obtenerPorId(idReserva);
        if(reserva.getFechaFin().isBefore(LocalDate.now())){
            throw new Exception("La reserva ya no es vigente");
        }
    }

    public float calcularCostoReserva(UUID idReserva, ProductoHabitacion habitacion, Alojamiento alojamiento, double descuento) throws Exception {
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

    private double calcularSubTotalReserva(ProductoHabitacion habitacion, Alojamiento alojamiento) {
        double precioBase;
        if (habitacion != null) {
            precioBase = habitacion.getPrecio();
        } else {
            precioBase = alojamiento.calcularCosto();
        }
        return precioBase;
    }

    public void generarFactura(UUID idReserva, ProductoHabitacion habitacion, Alojamiento alojamiento, double descuento) throws Exception {
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
    }

    public Factura recuperarFacturaReservaPorId(UUID idFactura) throws Exception {
        return repositorioFacturas.obtenerPorId(idFactura);
    }

    public Reserva recuperarReservaPorId(UUID idReserva) throws Exception {
        Reserva reserva = repositorioReservas.obtenerPorId(idReserva);
        if (reserva == null) {
            throw new Exception("No existe reserva");
        }
        return reserva;
    }

    public void verificarEstadoReservaCompletado(UUID idReserva) throws Exception{
        Reserva reserva = repositorioReservas.obtenerPorId(idReserva);
        if(reserva.getEstado() != Estado.COMPLETADO){
            throw new Exception("La reserva no se encuentra pagada o no se ha completado");
        }
    }
}
