package co.edu.uniquindio.poo.proyectofinal.Repositorios;

import co.edu.uniquindio.poo.proyectofinal.Model.entidades.Billetera;
import co.edu.uniquindio.poo.proyectofinal.Model.entidades.Persona;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RepositorioBilleteras {

    private static RepositorioBilleteras INSTANCE;
    private final ObjectMapper objectMapper;
    private final File archivo;

    private final List<Billetera> billeteras;

    public RepositorioBilleteras() {
        objectMapper = new ObjectMapper();
        archivo = new File("src/main/data/billeteras.json");
        this.billeteras = cargarBilleteras();
    }


    public static RepositorioBilleteras getInstancia() {
        if (INSTANCE == null) {
            INSTANCE = new RepositorioBilleteras();
        }
        return INSTANCE;
    }



    public void agregarBilletera(Billetera billetera)throws Exception{
        if(buscarPorNumero(billetera.getNumero())==null){
            billeteras.add(billetera);
            guardarBilleteras();
            System.out.println("Billetera agregada: " + billetera);
        }else{
            throw new Exception("Ya existe una billetera con el numero " + billetera.getNumero());
        }

    }

    private void guardarBilleteras() throws Exception{
        try {
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writerFor(new TypeReference<List<Billetera>>() {})
                    .writeValue(archivo, billeteras);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Billetera> cargarBilleteras() {
        try {
            if (archivo.exists() && archivo.length() > 0) {
                return objectMapper.readValue(archivo, new TypeReference<List<Billetera>>() {
                });
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
        return new ArrayList<>();
    }



    public Billetera buscarPorNumero(String numero) {
        return billeteras.stream()
                .filter(b -> b.getNumero().equals(numero))
                .findFirst()
                .orElse(null);
    }


}
