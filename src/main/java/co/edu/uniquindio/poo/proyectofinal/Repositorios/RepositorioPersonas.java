package co.edu.uniquindio.poo.proyectofinal.Repositorios;


import co.edu.uniquindio.poo.proyectofinal.Model.entidades.Persona;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Getter
public class RepositorioPersonas {

    private final ObjectMapper objectMapper;
    private final File archivo;
    private List<Persona> personas;

    public RepositorioPersonas() {
        this.objectMapper = new ObjectMapper();
        this.archivo = new File("src/main/data/personas.json"); //;
        this.personas= cargarPersonas();
    }

    public void agregarPersona(Persona persona) throws Exception {
        if (obtenerPorId(persona.getCedula()) == null) {
            personas.add(persona);
            guardarPersonas();
        } else {
            throw new Exception("Ya existe una persona con la cédula " + persona.getCedula());
        }
    }


    private void guardarPersonas() throws Exception{
        try {
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writerFor(new TypeReference<List<Persona>>() {})
                    .writeValue(archivo, personas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private List<Persona> cargarPersonas() {
        try {
            if (archivo.exists() && archivo.length() > 0) {
                return objectMapper.readValue(archivo, new TypeReference<List<Persona>>() {
                });
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public Persona obtenerPorId(String cedula) {
        return personas.stream()
                .filter(persona -> persona.getCedula().equals(cedula))
                .findFirst()
                .orElse(null);
    }

    public Persona obtenerPorEmail(String email) {
        return personas.stream()
                .filter(persona -> persona.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }
    public void editarPersona(Persona persona) throws Exception {
        for (int i = 0; i < personas.size(); i++) {
            if (personas.get(i).getCedula().equals(persona.getCedula())) {
                personas.set(i, persona);
                guardarPersonas();
                return;
            }
        }
        throw new Exception("Persona no encontrada");
    }

    public void desactivarCuenta(String cedula) throws Exception {
        Persona persona = obtenerPorId(cedula);
        if (persona != null) {
            persona.setCuentaActiva(false);
            guardarPersonas();
        } else {
            throw new Exception("Persona no encontrada");
        }
    }
}
