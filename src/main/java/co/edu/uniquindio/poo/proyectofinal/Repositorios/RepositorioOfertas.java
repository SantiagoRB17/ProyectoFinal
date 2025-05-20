package co.edu.uniquindio.poo.proyectofinal.Repositorios;

import co.edu.uniquindio.poo.proyectofinal.Model.AlojamientoDecorator.Oferta;
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

public class RepositorioOfertas {
    //Instancia de Jackson que convierte objetos a JSON y viceversa.
    private final ObjectMapper objectMapper;
    // Representa el archivo físico donde se guarda la lista de alojamientos (alojamientos.json).
    private final File archivo;
    @Getter
    private final List<Oferta> ofertas;

    public RepositorioOfertas() {
        this.archivo = new File("src/main/data/ofertas.json");
        this.objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        this.ofertas = cargarOfertas();
    }

    /**
     * Metodo que Agrega una nueva oferta a la lista y guarda todos los datos en el archivo JSON.
     * @param oferta oferta a agregar
     * @throws Exception
     */
    public void agregarOferta(Oferta oferta) throws Exception {
            ofertas.add(oferta);
            guardarOferta();
    }

    /**
     * Metodo que serializa la lista ofertas y la guarda en formato JSON en el archivo.
     * La opción INDENT_OUTPUT hace que el archivo quede legible y bien formateado.
     * @throws Exception
     */
    private void guardarOferta() throws Exception {
        try {
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writerFor(new TypeReference<List<Oferta>>() {})
                    .writeValue(archivo, ofertas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo que carga la lista de ofertas desde el archivo JSON si existe y no está vacío.
     * Si hay error o no existe, devuelve una lista vacía.
     * @return lista de alojamientos cargados
     */
    private List<Oferta> cargarOfertas() {
        List<Oferta> todos;
        try {
            if (archivo.exists() && archivo.length() > 0) {
                todos= objectMapper.readValue(archivo, new TypeReference<List<Oferta>>() {
                });
                validarVigenciaOferta(todos);
                return new ArrayList<>(todos);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public Oferta obtenerOfertaPorId(UUID id) throws Exception{
        if(id==null){
            throw new IllegalArgumentException("Seleccione una oferta");
        }
        return ofertas.stream()
                .filter(o -> o.getIdAlojamiento().equals(id))
                .findFirst().orElse(null);
    }

    /**
     * Metodo que valida la vigencia de las ofertas
     * @param ofertas todas las ofertas
     * @return ofertas que siguen vigentes con el dia
     * @throws Exception
     */
    private List<Oferta> validarVigenciaOferta(List<Oferta> ofertas) {
        List<Oferta> ofertasVigentes = new ArrayList<>();
        LocalDate hoy = LocalDate.now();
        for (Oferta oferta : ofertas) {
            if (oferta.getFechaFin().isBefore(hoy)) {
                try {
                    eliminarOferta(oferta.getIdAlojamiento());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                ofertasVigentes.add(oferta);
            }
        }
        return ofertasVigentes;
    }


    public void eliminarOferta(UUID idOferta) throws Exception {
        Oferta ofertaAEliminar = obtenerOfertaPorId(idOferta);
        if (ofertaAEliminar != null) {
            ofertas.remove(ofertaAEliminar);
            guardarOferta();
        }else{
            throw new Exception("Oferta no encontrada");
        }
    }
}
