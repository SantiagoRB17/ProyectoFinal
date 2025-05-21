package co.edu.uniquindio.poo.proyectofinal.Repositorios;

import co.edu.uniquindio.poo.proyectofinal.Model.entidades.Factura;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Getter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RepositorioFacturas {
    //Instancia de Jackson que convierte objetos a JSON y viceversa.
    private final ObjectMapper objectMapper;
    // Representa el archivo físico donde se guarda la lista de facturas (facturas.json).
    private final File archivo;
    @Getter
    private List<Factura> facturas;

    public RepositorioFacturas() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
        this.archivo = new File("src/main/data/facturas.json");
        this.facturas = cargarFacturas();
    }

    /**
     * Metodo que Agrega una nueva Reserva a la lista y guarda todos los datos en el archivo JSON.
     * @param factura reserva a agregar
     * @throws Exception si ya existe un alojamiento con ese ID
     */
    public void agregarFactura(Factura factura) throws Exception {
            facturas.add(factura);
            guardarFactura();
    }



    /**
     * Metodo que serializa la lista de facturas y la guarda en formato JSON en el archivo.
     * La opción INDENT_OUTPUT hace que el archivo quede legible y bien formateado.
     * @throws Exception
     */
    private void guardarFactura() throws Exception {
        try {
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writerFor(new TypeReference<List<Factura>>() {})
                    .writeValue(archivo, facturas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo que busca una Factura por su UUID. Devuelve el primero que coincida o null si no existe.
     * @param idFactura id de la factura a buscar
     * @return
     */
    public Factura obtenerPorId(UUID idFactura) {
        if(idFactura==null){
            throw new IllegalArgumentException("El id no puede ser nulo");
        }
        return facturas.stream()
                .filter(f -> f.getIdFactura().equals(idFactura))
                .findFirst().orElse(null);
    }

    /**
     * Metodo que carga la lista de facturas desde el archivo JSON si existe y no está vacío.
     * Si hay error o no existe, devuelve una lista vacía.
     * @return lista de facturas cargada
     */
    private List<Factura> cargarFacturas() {
        List<Factura> todos;
        try {
            if (archivo.exists() && archivo.length() > 0) {
                todos = objectMapper.readValue(archivo, new TypeReference<List<Factura>>() {
                });
                return new ArrayList<>(todos);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

}
