package co.edu.uniquindio.poo.proyectofinal.Repositorios;

import co.edu.uniquindio.poo.proyectofinal.Enums.Estado;
import co.edu.uniquindio.poo.proyectofinal.Model.entidades.Billetera;
import co.edu.uniquindio.poo.proyectofinal.Model.entidades.Reserva;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Getter;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RepositorioReservas {
    //Instancia de Jackson que convierte objetos a JSON y viceversa.
    private final ObjectMapper objectMapper;
    // Representa el archivo físico donde se guarda la lista de reservas (reservas.json).
    private final File archivo;
    @Getter
    private List<Reserva> reservas;

    public RepositorioReservas() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
        this.archivo = new File("src/main/data/reservas.json");
        this.reservas = cargarReservas();
    }

    /**
     * Metodo que Agrega una nueva Reserva a la lista y guarda todos los datos en el archivo JSON.
     * @param reserva reserva a agregar
     * @throws Exception si ya existe un alojamiento con ese ID
     */
    public void agregarReserva(Reserva reserva) throws Exception {
        if(obtenerPorId(reserva.getIdReserva())==null && !hayCruce(reserva.getFechaInicio(), reserva.getFechaFin(), reserva.getIdAlojamiento())){
            reservas.add(reserva);
            guardarReservas();
        }else{
            throw new Exception("Ya hay una reserva programada para estas fechas, seleccione otra fecha");
        }
    }

    public void actualizarReserva(Reserva reserva) throws Exception {
        for (int i = 0; i < reservas.size(); i++) {
            if (reservas.get(i).getIdReserva().equals(reserva.getIdReserva())) {
                reservas.set(i, reserva);
                guardarReservas();
                return;
            }
        }
        throw new Exception("No existe la reserva");
    }


    /**
     * Metodo que serializa la lista reservas y la guarda en formato JSON en el archivo.
     * La opción INDENT_OUTPUT hace que el archivo quede legible y bien formateado.
     * @throws Exception
     */
    private void guardarReservas() throws Exception {
        if (reservas == null) {
            reservas = new ArrayList<>();
        }
        try {
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writerFor(new TypeReference<List<Reserva>>() {})
                    .writeValue(archivo, reservas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo que busca una Reserva por su UUID. Devuelve el primero que coincida o null si no existe.
     * @param idReserva id de la reserva a buscar
     * @return
     */
    public Reserva obtenerPorId(UUID idReserva) {
        if(idReserva==null){
            throw new IllegalArgumentException("El id no puede ser nulo");
        }
        return reservas.stream()
                .filter(r -> r.getIdReserva().equals(idReserva))
                .findFirst().orElse(null);
    }

    public Boolean hayCruce(LocalDate fechaInicio, LocalDate fechaFin, UUID idAlojamiento) {
        return reservas.stream().anyMatch(r ->
                r.getIdAlojamiento().equals(idAlojamiento)
                        && !(r.getFechaFin().isBefore(fechaInicio) || r.getFechaInicio().isAfter(fechaFin))
        );
    }


    /**
     * Metodo que carga la lista de reservas desde el archivo JSON si existe y no está vacío.
     * Si hay error o no existe, devuelve una lista vacía.
     * @return lista de reservas cargada
     */
    private List<Reserva> cargarReservas() {
        List<Reserva> todos;
        try {
            if (archivo.exists() && archivo.length() > 0) {
                todos = objectMapper.readValue(archivo, new TypeReference<List<Reserva>>() {
                });
                return new ArrayList<>(todos);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }


    /**
     * Metodo que elimina una Reserva buscando por su UUID.
     * Si se encuentra, se borra y se guarda el cambio
     * @param idReserva id de la reserva a borrar
     * @throws Exception si no encuentra la reserva
     */
    public void eliminarReserva(UUID idReserva) throws Exception {
        Reserva reservaAEliminar = obtenerPorId(idReserva);
        if (reservaAEliminar != null) {
            reservas.remove(reservaAEliminar);
            guardarReservas();
        } else {
            throw new Exception("Reserva no encontrada");
        }
    }

    /**
     * Metodo que comprueba si las fechas de la reserva ya cambiaron para actualizar su estado.
     * @param reservas lista de reservas a comprobar
     */
    private void actualizarReservasCompletadas(List<Reserva> reservas) {
        try{
            LocalDate hoy = LocalDate.now();
            for (Reserva reserva : reservas) {
                if ((reserva.getEstado() != Estado.COMPLETADO && reserva.getEstado() == Estado.PAGADO )
                        && reserva.getFechaFin().isBefore(hoy) ) {
                    reserva.setEstado(Estado.COMPLETADO);
                    actualizarReserva(reserva);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Metodo que lista todas las reservas de un usuario dado su cedula.
     * @param cedula cedula del usuario
     * @return su lista de reservas
     * @throws Exception
     */
    public List<Reserva> listarReservasUsuario(String cedula) throws Exception{
        if(cedula == null){
            throw new Exception("La cedula no puede ser nula");
        }
        List<Reserva> reservasUsuario=reservas.stream()
                .filter(r -> cedula.equals(r.getCedulaCliente()))
                .toList();
        actualizarReservasCompletadas(reservasUsuario);
        return reservasUsuario;
    }

}
