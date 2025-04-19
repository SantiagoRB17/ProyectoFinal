import co.edu.uniquindio.poo.proyectofinal.Model.*;
import javafx.scene.image.Image;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestFabricas {
    @Test
    public void crearAlojamientoCasaTest(){
            String nombre = "Casa en el Bosque";
            String ciudad = "Manizales";
            String descripcion = "Hermosa casa rural";
            ArrayList<String> resenas = new ArrayList<>();
            resenas.add("Acojedora");
            resenas.add("Espaciosa");
            Image foto = null;
            double precio = 350000;
            double valoracion = 4.8;
            ArrayList<String> servicios = new ArrayList<>();
            servicios.add("WiFi");
            servicios.add("Piscina");
            Estado estado = Estado.DISPONIBLE;
            int capacidadMaxima = 6;
            double costoExtra = 50000;

            FabricaAlojamiento fabricaCasa = new FabricaCasa(nombre, ciudad, descripcion, resenas, foto, precio, valoracion, servicios
                    , estado, capacidadMaxima, costoExtra);

            ProductoCasa casa = (ProductoCasa) fabricaCasa.crearProducto();

            assertEquals(nombre, casa.getNombre());
            assertEquals(ciudad, casa.getCiudad());
            assertEquals(descripcion, casa.getDescripcion());
            assertEquals(2, casa.getResenas().size());
            assertNull(casa.getFoto());
            assertEquals(precio, casa.getPrecio());
            assertEquals(valoracion, casa.getValoracion());
            assertEquals(2, casa.getServicios().size());
            assertEquals(estado, casa.getEstado());
            assertEquals(capacidadMaxima, casa.getCapacidadMaxima());
            assertEquals(costoExtra, casa.getCostoExtra());

            assertEquals(precio+costoExtra,casa.calcularCosto());
    }
    @Test
    public void crearAlojamientoApartamentoTest(){
            String nombre = "Habitat";
            String ciudad = "Armenia";
            String descripcion = "Aparta-estudio";
            ArrayList<String> resenas = new ArrayList<>();
            resenas.add("Espacioso");
            resenas.add("Moderno");
            resenas.add("Comodo");
            Image foto = null;
            double precio = 500000;
            double valoracion = 4.8;
            ArrayList<String> servicios = new ArrayList<>();
            servicios.add("WiFi");
            servicios.add("Parqueadero");
            servicios.add("Piscina");
            Estado estado = Estado.DISPONIBLE;
            int capacidadMaxima = 6;
            double costoExtra = 50000;

            FabricaAlojamiento fabricaCasa = new FabricaCasa(nombre, ciudad, descripcion, resenas, foto, precio, valoracion, servicios
                    , estado, capacidadMaxima, costoExtra);

            ProductoCasa casa = (ProductoCasa) fabricaCasa.crearProducto();

            assertEquals(nombre, casa.getNombre());
            assertEquals(ciudad, casa.getCiudad());
            assertEquals(descripcion, casa.getDescripcion());
            assertEquals(3, casa.getResenas().size());
            assertNull(casa.getFoto());
            assertEquals(precio, casa.getPrecio());
            assertEquals(valoracion, casa.getValoracion());
            assertEquals(3, casa.getServicios().size());
            assertEquals(estado, casa.getEstado());
            assertEquals(capacidadMaxima, casa.getCapacidadMaxima());
            assertEquals(costoExtra, casa.getCostoExtra());

            assertEquals(precio+costoExtra,casa.calcularCosto());
    }
}
