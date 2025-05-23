package co.edu.uniquindio.poo.proyectofinal.Servicios;

import co.edu.uniquindio.poo.proyectofinal.Enums.Estado;
import co.edu.uniquindio.poo.proyectofinal.Model.AlojamientosFactory.Alojamiento;
import co.edu.uniquindio.poo.proyectofinal.Model.AlojamientosFactory.FabricaApartamento;
import co.edu.uniquindio.poo.proyectofinal.Model.AlojamientosFactory.FabricaCasa;
import co.edu.uniquindio.poo.proyectofinal.Model.AlojamientosFactory.FabricaHotel;
import co.edu.uniquindio.poo.proyectofinal.Model.entidades.Factura;
import co.edu.uniquindio.poo.proyectofinal.Model.entidades.Reserva;
import co.edu.uniquindio.poo.proyectofinal.Repositorios.RepositorioAlojamientos;
import co.edu.uniquindio.poo.proyectofinal.Repositorios.RepositorioFacturas;
import co.edu.uniquindio.poo.proyectofinal.Repositorios.RepositorioReservas;

import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class ServicioEstadisticas {

    private final RepositorioReservas repositorioReservas;
    private final RepositorioAlojamientos repositorioAlojamientos;
    private final RepositorioFacturas repositorioFacturas;

    public ServicioEstadisticas(RepositorioReservas repositorioReservas, RepositorioAlojamientos repositorioAlojamientos, RepositorioFacturas repositorioFacturas) {
        this.repositorioReservas = repositorioReservas;
        this.repositorioAlojamientos =  repositorioAlojamientos;
        this.repositorioFacturas = repositorioFacturas;
    }


    public Map<Alojamiento, Long> obtenerNumeroReservasPorAlojamiento(){
        return repositorioReservas.getReservas().stream()
                .collect(Collectors.groupingBy(reserva -> repositorioAlojamientos.obtenerPorId(reserva.getIdAlojamiento()), Collectors.counting()));
    }

    public Map<Alojamiento, Double> obtenerGananciasPorAlojamiento(){
        Map<Alojamiento, Double> gananciasPorAlojamiento = new HashMap<>();
        for(Reserva reserva : repositorioReservas.getReservas()){
            Alojamiento alojamiento = repositorioAlojamientos.obtenerPorId(reserva.getIdAlojamiento());

            if (alojamiento == null){
                continue;
            }

            Factura factura = repositorioFacturas.obtenerPorId(reserva.getIdFactura());

            if (factura == null){
                continue;
            }
            gananciasPorAlojamiento.merge(alojamiento, factura.getTotal(), Double::sum);
        }
        return gananciasPorAlojamiento;
    }



    public Map<Alojamiento, Double> calcularOcupacionPorAlojamiento(){
        Map<Alojamiento, Double> ocupacion = new HashMap<>();
        for(Alojamiento alojamiento : repositorioAlojamientos.getAlojamientos()){
            List<Reserva> reservas = repositorioReservas.getReservas().stream()
                    .filter(r->r.getIdAlojamiento().equals(alojamiento.getId())).toList();

            long diasReservados = reservas.stream()
                    .mapToLong(r-> ChronoUnit.DAYS.between(r.getFechaInicio(), r.getFechaFin())).sum();


            long diasTotales = 365;

            double porcentajeOcupacion = diasTotales>0 ? (diasReservados * 100.0 / diasTotales) : 0;
            ocupacion.put(alojamiento, porcentajeOcupacion);
        }
        return ocupacion;
    }



    public Map<String, Alojamiento> obtenerAlojamientosMasPopularesPorCiudad() {
        return repositorioReservas.getReservas().stream()
                .collect(Collectors.groupingBy(
                        reserva -> {
                            Alojamiento alojamiento = repositorioAlojamientos.obtenerPorId(reserva.getIdAlojamiento());
                            return alojamiento != null ? alojamiento.getCiudad() : "Desconocida";
                        },
                        Collectors.collectingAndThen(
                                Collectors.groupingBy(
                                        reserva -> repositorioAlojamientos.obtenerPorId(reserva.getIdAlojamiento()),
                                        Collectors.counting()
                                ),
                                mapa -> mapa.entrySet().stream()
                                        .max(Map.Entry.comparingByValue())
                                        .map(Map.Entry::getKey)
                                        .orElse(null)
                        )
                ));
    }


    public Map<String, Double> obtenerTiposAlojamientoMasRentables() {
        Map<String, Double> gananciasPorTipo = new HashMap<>();

        for (Map.Entry<Alojamiento, Double> entry : obtenerGananciasPorAlojamiento().entrySet()) {
            Alojamiento alojamiento = entry.getKey();
            Double ganancia = entry.getValue();

            String tipo;

            if (alojamiento instanceof FabricaApartamento) {
                tipo = "Apartamento";
            } else if (alojamiento instanceof FabricaCasa) {
                tipo = "Casa";
            } else if (alojamiento instanceof FabricaHotel) {
                tipo = "Hotel";
            } else {
                tipo = "Desconocido";
            }

            gananciasPorTipo.merge(tipo, ganancia, Double::sum);
        }

        return gananciasPorTipo;
    }












}
