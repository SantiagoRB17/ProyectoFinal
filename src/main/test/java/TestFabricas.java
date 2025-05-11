import co.edu.uniquindio.poo.proyectofinal.Enums.TipoAlojamiento;
import co.edu.uniquindio.poo.proyectofinal.Model.*;
import co.edu.uniquindio.poo.proyectofinal.Model.AlojamientosFactory.Alojamiento;
import co.edu.uniquindio.poo.proyectofinal.Model.AlojamientosFactory.FabricaAlojamiento;
import co.edu.uniquindio.poo.proyectofinal.Model.AlojamientosFactory.FabricaApartamento;
import co.edu.uniquindio.poo.proyectofinal.Model.AlojamientosFactory.FabricaCasa;
import co.edu.uniquindio.poo.proyectofinal.Repositorios.RepositorioAlojamientos;
import co.edu.uniquindio.poo.proyectofinal.Servicios.ServicioAlojamientos;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestFabricas {
        public ServicioAlojamientos servicioAlojamientos = new ServicioAlojamientos();
        public RepositorioAlojamientos repo = new RepositorioAlojamientos();

        @Test
        public void crearAlojamientoCasaTest() {
                String nombre = "Casa en el Bosque";
                String ciudad = "Manizales";
                String descripcion = "Hermosa casa rural";
                ArrayList<String> resenas = new ArrayList<>();
                resenas.add("Acojedora");
                resenas.add("Espaciosa");
                String rutaFoto = null;
                double precio = 350000;
                double valoracion = 4.8;
                ArrayList<String> servicios = new ArrayList<>();
                servicios.add("WiFi");
                servicios.add("Piscina");
                int capacidadMaxima = 6;
                double costoExtra = 50000;

                FabricaAlojamiento fabricaCasa = new FabricaCasa(nombre, ciudad, descripcion, rutaFoto, precio, servicios,
                        capacidadMaxima, costoExtra);

                Alojamiento alojamientoCasa = fabricaCasa.crearProducto();

                assertInstanceOf(ProductoCasa.class, alojamientoCasa);

                ProductoCasa casa = (ProductoCasa) alojamientoCasa;

                casa.setResenas(resenas);
                casa.setValoracion(valoracion);
                assertNotNull(casa.getId());
                assertEquals(nombre, casa.getNombre());
                assertEquals(ciudad, casa.getCiudad());
                assertEquals(descripcion, casa.getDescripcion());
                assertEquals(2, casa.getResenas().size());
                assertNull(casa.getRutaFoto());
                assertEquals(precio, casa.getPrecio());
                assertEquals(valoracion, casa.getValoracion());
                assertEquals(2, casa.getServicios().size());
                assertEquals(capacidadMaxima, casa.getCapacidadMaxima());
                assertEquals(costoExtra, casa.getCostoExtra());

                assertEquals(precio + costoExtra, casa.calcularCosto());
        }

        @Test
        public void crearAlojamientoApartamentoTest() {
                String nombre = "Habitat";
                String ciudad = "Armenia";
                String descripcion = "Aparta-estudio";
                ArrayList<String> resenas = new ArrayList<>();
                resenas.add("Espacioso");
                resenas.add("Moderno");
                resenas.add("Comodo");
                String rutaFoto = null;
                double precio = 500000;
                double valoracion = 4.8;
                ArrayList<String> servicios = new ArrayList<>();
                servicios.add("WiFi");
                servicios.add("Parqueadero");
                servicios.add("Piscina");
                int capacidadMaxima = 6;
                double costoExtra = 50000;

                FabricaAlojamiento fabricaApartamento = new FabricaApartamento(nombre, ciudad, descripcion, rutaFoto, precio,
                        servicios, capacidadMaxima, costoExtra);

                Alojamiento AlojamientoApartamento = fabricaApartamento.crearProducto();

                assertInstanceOf(ProductoApartamento.class, AlojamientoApartamento);

                ProductoApartamento apartamento = (ProductoApartamento) AlojamientoApartamento;

                apartamento.setResenas(resenas);
                apartamento.setValoracion(valoracion);

                assertNotNull(apartamento.getId());
                assertEquals(nombre, apartamento.getNombre());
                assertEquals(ciudad, apartamento.getCiudad());
                assertEquals(descripcion, apartamento.getDescripcion());
                assertEquals(3, apartamento.getResenas().size());
                assertNull(apartamento.getRutaFoto());
                assertEquals(precio, apartamento.getPrecio());
                assertEquals(valoracion, apartamento.getValoracion());
                assertEquals(3, apartamento.getServicios().size());
                assertEquals(capacidadMaxima, apartamento.getCapacidadMaxima());
                assertEquals(costoExtra, apartamento.getCostoExtra());

                assertEquals(precio + costoExtra, apartamento.calcularCosto());
        }

        @Test
        public void guardarAlojamientosJsonTest() {
                TipoAlojamiento tipo = TipoAlojamiento.CASA;
                String nombre = "Habitat";
                String ciudad = "Armenia";
                String descripcion = "Aparta-estudio";
                String rutaFoto = "imagenInexistenteDePrueba";
                double precio = 500000;
                ArrayList<String> servicios = new ArrayList<>();
                servicios.add("WiFi");
                servicios.add("Parqueadero");
                servicios.add("Piscina");
                int capacidadMaxima = 6;
                double costoExtra = 50000;
                assertDoesNotThrow(() -> servicioAlojamientos.agregarAlojamiento(tipo, nombre, ciudad, descripcion,
                        rutaFoto, precio, servicios, capacidadMaxima, costoExtra));

        }

        @Test
        public void obtenerAlojamientosTest(){
                List<Alojamiento> alojamientos=repo.getAlojamientos();
                assertEquals(1, alojamientos.size());
        }

}
