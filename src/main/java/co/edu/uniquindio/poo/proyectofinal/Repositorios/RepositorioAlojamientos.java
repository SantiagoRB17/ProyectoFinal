package co.edu.uniquindio.poo.proyectofinal.Repositorios;

import co.edu.uniquindio.poo.proyectofinal.Enums.TipoAlojamiento;
import co.edu.uniquindio.poo.proyectofinal.Model.AlojamientosFactory.Alojamiento;
import co.edu.uniquindio.poo.proyectofinal.Model.entidades.ProductoApartamento;
import co.edu.uniquindio.poo.proyectofinal.Model.entidades.ProductoCasa;
import co.edu.uniquindio.poo.proyectofinal.Model.entidades.ProductoHabitacion;
import co.edu.uniquindio.poo.proyectofinal.Model.entidades.ProductoHotel;
import co.edu.uniquindio.poo.proyectofinal.Utils.RangoPrecio;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.Getter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Clase encargada de manejar la persistencia de los alojamientos
 */
public class RepositorioAlojamientos {
    //Instancia de Jackson que convierte objetos a JSON y viceversa.
    private final ObjectMapper objectMapper;
    // Representa el archivo físico donde se guarda la lista de alojamientos (alojamientos.json).
    private final File archivo;
    @Getter
    private List<Alojamiento> alojamientos;
    @Getter
    private List<Alojamiento> hoteles;

    /**
     * Constructor privado (por el patrón Singleton).
     * Crea el ObjectMapper, define el archivo JSON
     * carga los alojamientos existentes desde ese archivo.
     */
    public RepositorioAlojamientos() {
        this.objectMapper = new ObjectMapper();
        this.archivo = new File("src/main/data/alojamientos.json");
        this.alojamientos = cargarAlojamientos();
        this.hoteles = cargarHoteles();
    }

    /**
     * Metodo que Agrega un nuevo Alojamiento a la lista y guarda todos los datos en el archivo JSON.
     *
     * @param alojamiento alojamiento a agregar
     * @throws Exception si ya existe un alojamiento con ese ID
     */
    public void agregarAlojamiento(Alojamiento alojamiento) throws Exception {
        if (obtenerPorId(alojamiento.getId()) == null) {
            alojamientos.add(alojamiento);
            guardarAlojamiento();
        } else {
            throw new Exception("Ya existe una alojamiento con ese id");
        }
    }

    /**
     * Metodo que serializa la lista alojamientos y la guarda en formato JSON en el archivo.
     * La opción INDENT_OUTPUT hace que el archivo quede legible y bien formateado.
     *
     * @throws Exception
     */
    private void guardarAlojamiento() throws Exception {
        try {
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writerFor(new TypeReference<List<Alojamiento>>() {
                    })
                    .writeValue(archivo, alojamientos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo que busca un Alojamiento por su UUID. Devuelve el primero que coincida o null si no existe.
     *
     * @param id id del alojamiento a buscar
     * @return
     */
    public Alojamiento obtenerPorId(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("Seleccione un alojamiento");
        }
        return alojamientos.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst().orElse(null);
    }

    /**
     * Metodo que reemplaza un alojamiento existente con una versión actualizada buscandolo por ID.
     * Luego guarda los cambios en el archivo.
     *
     * @param actualizado Alojamiento actualizado
     * @throws Exception si no encuentra el alojamiento
     */
    public void editarAlojamiento(Alojamiento actualizado) throws Exception {
        for (int i = 0; i < alojamientos.size(); i++) {
            if (alojamientos.get(i).getId().equals(actualizado.getId())) {
                alojamientos.set(i, actualizado);
                guardarAlojamiento();
                return;
            }
        }
        throw new Exception("Alojamiento no encontrado");
    }

    /**
     * Metodo que carga la lista de alojamientos desde el archivo JSON si existe y no está vacío.
     * Si hay error o no existe, devuelve una lista vacía.
     *
     * @return lista de alojamientos cargados
     */
    private List<Alojamiento> cargarAlojamientos() {
        List<Alojamiento> todos;
        try {
            if (archivo.exists() && archivo.length() > 0) {
                todos = objectMapper.readValue(archivo, new TypeReference<List<Alojamiento>>() {
                });
                return new ArrayList<>(todos.stream().filter(Alojamiento::isActivo).toList());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<Alojamiento> listarCasasyApartamentos() {
        return alojamientos.stream()
                .filter(a -> a instanceof ProductoCasa && a.isActivo()
                        || a.isActivo() && a instanceof ProductoApartamento && a.isActivo())
                .toList();
    }

    public List<Alojamiento> listarHoteles() {
        return alojamientos.stream().filter(a -> a instanceof ProductoHotel && a.isActivo()).toList();
    }

    /**
     * Metodo que carga una lista de hoteles desde el arhivo JSON
     *
     * @return lista de hoteles cargados
     */
    private List<Alojamiento> cargarHoteles() {
        try {
            if (archivo.exists() && archivo.length() > 0) {
                List<Alojamiento> todos = objectMapper.readValue(archivo, new TypeReference<List<Alojamiento>>() {
                });
                return todos.stream()
                        .filter(a -> a instanceof ProductoHotel && a.isActivo())
                        .toList();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    /**
     * Metodo que elimina un alojamiento buscando por su UUID.
     * Si se encuentra, se borra y se guarda el cambio
     *
     * @param id id del alojamiento a borrar
     * @throws Exception si no encuentra el alojamiento
     */
    public void eliminarAlojamiento(UUID id) throws Exception {
        Alojamiento alojamientoAEliminar = obtenerPorId(id);
        if (alojamientoAEliminar != null) {
            alojamientoAEliminar.setActivo(false);
            guardarAlojamiento();
        } else {
            throw new Exception("Alojamiento no encontrado");
        }
    }


    public List<ProductoHabitacion> cargarHabitaciones(UUID id) {
        ProductoHotel hotel = (ProductoHotel) obtenerPorId(id);
        if (hotel != null) {
            List<ProductoHabitacion> habitaciones = hotel.getHabitaciones();
            return habitaciones.stream().filter(ProductoHabitacion::isActivo).toList();
        }
        return new ArrayList<>();
    }

    /**
     * Metodo que elimina un hotel y sus habitacion buscando por su UUID.
     *
     * @param id id del alojamiento a borrar
     * @throws Exception
     */
    public void eliminarHotel(UUID id) throws Exception {
        Alojamiento alojamientoAEliminar = obtenerPorId(id);
        if (alojamientoAEliminar == null) {
            throw new Exception("Alojamiento no encontrado");
        }
        ProductoHotel hotelAEliminar = (ProductoHotel) alojamientoAEliminar;
        for (ProductoHabitacion habitacion : hotelAEliminar.getHabitaciones()) {
            habitacion.setActivo(false);
            guardarAlojamiento();
        }
        alojamientoAEliminar.setActivo(false);
        guardarAlojamiento();
    }

    /**
     * Recupera una habitación a partir de su id, recorriendo todos los hoteles y sus habitaciones.
     *
     * @param idHabitacion UUID de la habitación a buscar
     * @return la instancia de ProductoHabitacion o null si no la encuentra
     */
    public ProductoHabitacion obtenerHabitacionPorId(UUID idHabitacion) {
        if (idHabitacion == null) {
            return null;
        }
        for (Alojamiento alojamiento : alojamientos) {
            if (alojamiento instanceof ProductoHotel productoHotel) {
                for (ProductoHabitacion habitacion : productoHotel.getHabitaciones()) {
                    if (habitacion.getId().equals(idHabitacion) && habitacion.isActivo()) {
                        return habitacion;
                    }
                }
            }
        }
        return null;
    }
    // Evalúa si un alojamiento cumple con el rango de precio
    public boolean cumpleFiltroPrecio(Alojamiento alojamiento, RangoPrecio rangoPrecio) {
        if (rangoPrecio == null) return true;
        if (alojamiento instanceof ProductoHotel hotel) {
            return hotelCumpleRangoPrecio(hotel, rangoPrecio);
        } else {
            double costo = alojamiento.calcularCosto();
            return cumpleRango(costo, rangoPrecio);
        }
    }
    // Evalúa si al menos una habitación activa del hotel cumple el rango de precio
    private boolean hotelCumpleRangoPrecio(ProductoHotel hotel, RangoPrecio rangoPrecio) {
        return hotel.getHabitaciones().stream()
                .filter(ProductoHabitacion::isActivo)
                .anyMatch(habitacion -> cumpleRango(habitacion.getPrecio(), rangoPrecio));
    }
    // Evalúa si un precio está dentro del rango
    private boolean cumpleRango(double precio, RangoPrecio rangoPrecio) {
        boolean minimo = rangoPrecio.getMin() == null || precio >= rangoPrecio.getMin();
        boolean maximo = rangoPrecio.getMax() == null || precio < rangoPrecio.getMax();
        return minimo && maximo;
    }

    /**
     * Filtra los alojamientos de acuerdo a los filtros proporcionados, como ciudad, nombre, tipo de alojamiento y rango de precios.
     * Los filtros son opcionales, es decir, si alguno de ellos es nulo o está vacío, no se aplicará dicho filtro.
     * @param ciudadFiltro la ciudad que se usará como filtro; si es nulo o está en blanco, no filtrará por ciudad
     * @param nombreFiltro parte del nombre del alojamiento que se usará como filtro; si es nulo o está en blanco, no filtrará por nombre
     * @param tipoFiltro el tipo de alojamiento (casa, apartamento u hotel) que se usará como filtro; si es nulo, no filtrará por tipo
     * @param rangoPrecio el rango de precios que se usará como filtro; si es nulo, no filtrará por precio
     * @return una lista de alojamientos que cumplen con los filtros indicados
     */
    public List<Alojamiento> filtrarAlojamientos(
            String ciudadFiltro,
            String nombreFiltro,
            TipoAlojamiento tipoFiltro,
            RangoPrecio rangoPrecio
    ) {
        return alojamientos.stream()
                .filter(a -> ciudadFiltro == null || ciudadFiltro.isBlank() ||
                        a.getCiudad().equalsIgnoreCase(ciudadFiltro))
                .filter(a -> nombreFiltro == null || nombreFiltro.isBlank() ||
                        a.getNombre().toLowerCase().contains(nombreFiltro.toLowerCase()))
                .filter(a -> {
                    if (tipoFiltro == null) return true;
                    return switch (tipoFiltro) {
                        case CASA -> a instanceof ProductoCasa;
                        case APARTAMENTO -> a instanceof ProductoApartamento;
                        case HOTEL -> a instanceof ProductoHotel;
                    };
                })
                .filter(a -> cumpleFiltroPrecio(a, rangoPrecio))
                .toList();
    }

    /**
     * Metodo que elimina una habitación específica de un hotel, identificada por su UUID.
     * Busca la habitación en los alojamientos del repositorio, la elimina si existe y actualiza el alojamiento correspondiente.
     * @param idHabitacion UUID de la habitación que se desea eliminar
     */
    public void eliminarHabitacion(UUID idHabitacion) {
        for (Alojamiento alojamiento : alojamientos) {
            if (alojamiento instanceof ProductoHotel hotel) {
                List<ProductoHabitacion> habitaciones = hotel.getHabitaciones();
                ProductoHabitacion habitacionAEliminar = habitaciones.stream()
                        .filter(h -> h.getId().equals(idHabitacion))
                        .findFirst()
                        .orElse(null);
                if (habitacionAEliminar != null) {
                    habitacionAEliminar.setActivo(false);
                    try{
                        editarAlojamiento(alojamiento);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }
    }

    /**
     * Edita los datos de una habitación de un hotel.
     * @param habitacionEditada habitación editada.
     * @param idHotel id del hotel al que pertenece la habitación.
     * @throws Exception si no encuentra la habitación.
     */
    public void editarHabitacion(ProductoHabitacion habitacionEditada, UUID idHotel) throws Exception {
        ProductoHotel hotel = (ProductoHotel) obtenerPorId(idHotel);
        List<ProductoHabitacion> habitaciones = hotel.getHabitaciones();

        boolean encontrada = false;
        for (int i = 0; i < habitaciones.size(); i++) {
            if (habitaciones.get(i).getId().equals(habitacionEditada.getId())) {
                habitaciones.set(i, habitacionEditada);
                encontrada = true;
                break;
            }
        }

        if (!encontrada) {
            throw new Exception("Habitación no encontrada en el hotel.");
        }

        hotel.setHabitaciones(habitaciones);
        editarAlojamiento(hotel);
    }


}
