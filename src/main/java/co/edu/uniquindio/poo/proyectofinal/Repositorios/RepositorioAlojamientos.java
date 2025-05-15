package co.edu.uniquindio.poo.proyectofinal.Repositorios;

import co.edu.uniquindio.poo.proyectofinal.Model.AlojamientosFactory.Alojamiento;
import co.edu.uniquindio.poo.proyectofinal.Model.ProductoHabitacion;
import co.edu.uniquindio.poo.proyectofinal.Model.ProductoHotel;
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
        this.archivo=new File("src/main/data/alojamientos.json");
        this.alojamientos = cargarAlojamientos();
        this.hoteles=cargarHoteles();
    }

    /**
     * Metodo que Agrega un nuevo Alojamiento a la lista y guarda todos los datos en el archivo JSON.
     * @param alojamiento alojamiento a agregar
     * @throws Exception si ya existe un alojamiento con ese ID
     */
    public void agregarAlojamiento(Alojamiento alojamiento) throws Exception {
        if(obtenerPorId(alojamiento.getId())==null){
            alojamientos.add(alojamiento);
            guardarAlojamiento();
        }else{
            throw new Exception("Ya existe una alojamiento con ese id");
        }
    }

    /**
     * Metodo que serializa la lista alojamientos y la guarda en formato JSON en el archivo.
     * La opción INDENT_OUTPUT hace que el archivo quede legible y bien formateado.
     * @throws Exception
     */
    private void guardarAlojamiento() throws Exception {
        try {
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writerFor(new TypeReference<List<Alojamiento>>() {})
                    .writeValue(archivo, alojamientos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo que busca un Alojamiento por su UUID. Devuelve el primero que coincida o null si no existe.
     * @param id id del alojamiento a buscar
     * @return
     */
    public Alojamiento obtenerPorId(UUID id) {
        if(id==null){
            throw new IllegalArgumentException("Seleccione un alojamiento");
        }
        return alojamientos.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst().orElse(null);
    }

    /**
     * Metodo que reemplaza un alojamiento existente con una versión actualizada buscandolo por ID.
     * Luego guarda los cambios en el archivo.
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
     * @return lista de alojamientos cargados
     */
    private List<Alojamiento> cargarAlojamientos() {
        List<Alojamiento> todos;
        try {
            if (archivo.exists() && archivo.length() > 0) {
                todos = objectMapper.readValue(archivo, new TypeReference<List<Alojamiento>>() {
                });
                return todos.stream().filter(Alojamiento::isActivo).toList();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    /**
     * Metodo que carga una lista de hoteles desde el arhivo JSON
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
     * @param id id del alojamiento a borrar
     * @throws Exception si no encuentra el alojamiento
     */
    public void eliminarAlojamiento(UUID id) throws Exception {
        Alojamiento alojamientoAEliminar=obtenerPorId(id);
        if (alojamientoAEliminar != null) {
            alojamientoAEliminar.setActivo(false);
            guardarAlojamiento();
        }else{
            throw new Exception("Alojamiento no encontrado");
        }
    }

    public List<ProductoHabitacion> cargarHabitaciones(UUID id){
        ProductoHotel hotel= (ProductoHotel) obtenerPorId(id);
        if(hotel!=null){
            return hotel.getHabitaciones();
        }
        return new ArrayList<>();
    }

    /**
     * Metodo que elimina un hotel y sus habitacion buscando por su UUID.
     * @param id id del alojamiento a borrar
     * @throws Exception
     */
    public void eliminarHotel(UUID id)throws Exception{
        Alojamiento alojamientoAEliminar=obtenerPorId(id);
        if (alojamientoAEliminar == null) {
            throw new Exception("Alojamiento no encontrado");
        }
        ProductoHotel hotelAEliminar=(ProductoHotel) alojamientoAEliminar;
        for(ProductoHabitacion habitacion:hotelAEliminar.getHabitaciones()){
            habitacion.setActivo(false);
            guardarAlojamiento();
        }
        alojamientoAEliminar.setActivo(false);
        guardarAlojamiento();
    }
}
