package co.edu.uniquindio.poo.proyectofinal.Repositorios;

import co.edu.uniquindio.poo.proyectofinal.Model.AlojamientoDecorator.Oferta;
import co.edu.uniquindio.poo.proyectofinal.Model.AlojamientosFactory.Alojamiento;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.Getter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        try {
            if (archivo.exists() && archivo.length() > 0) {
                return objectMapper.readValue(archivo, new TypeReference<List<Oferta>>() {
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

}
