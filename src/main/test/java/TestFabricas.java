import co.edu.uniquindio.poo.proyectofinal.Enums.TipoAlojamiento;
import co.edu.uniquindio.poo.proyectofinal.Model.AlojamientosFactory.*;
import co.edu.uniquindio.poo.proyectofinal.Model.entidades.ProductoApartamento;
import co.edu.uniquindio.poo.proyectofinal.Model.entidades.ProductoCasa;
import co.edu.uniquindio.poo.proyectofinal.Model.entidades.ProductoHabitacion;
import co.edu.uniquindio.poo.proyectofinal.Model.entidades.ProductoHotel;
import co.edu.uniquindio.poo.proyectofinal.Repositorios.RepositorioAlojamientos;
import co.edu.uniquindio.poo.proyectofinal.Servicios.ServicioAlojamientos;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class TestFabricas {

        @Test
        public void crearAlojamientoCasaTest() {
                String nombre = "Casa Campestre";
                String ciudad = "Quimbaya";
                String descripcion = "Ideal para familias";
                String rutaFoto = "ruta/foto.jpg";
                double precio = 300000;
                ArrayList<String> servicios = new ArrayList<>();
                servicios.add("WiFi");
                servicios.add("Parqueadero");
                int capacidadMaxima = 8;
                double costoExtra = 40000;

                FabricaCasa fabrica = new FabricaCasa(nombre, ciudad, descripcion, rutaFoto, precio, servicios, capacidadMaxima, costoExtra);
                Alojamiento alojamiento = fabrica.crearProducto();

                assertInstanceOf(ProductoCasa.class, alojamiento);
                ProductoCasa casa = (ProductoCasa) alojamiento;
                assertEquals(nombre, casa.getNombre());
                assertEquals(ciudad, casa.getCiudad());
                assertEquals(descripcion, casa.getDescripcion());
                assertEquals(rutaFoto, casa.getRutaFoto());
                assertEquals(precio, casa.getPrecio());
                assertEquals(servicios, casa.getServicios());
                assertEquals(capacidadMaxima, casa.getCapacidadMaxima());
                assertEquals(costoExtra, casa.getCostoExtra());
                assertEquals(precio + costoExtra, casa.calcularCosto());

        }

        @Test
        public void crearAlojamientoApartamentoTest() {
                String nombre = "Apartamento Moderno";
                String ciudad = "Calarcá";
                String descripcion = "Perfecto para ejecutivos";
                String rutaFoto = "ruta/foto.jpg";
                double precio = 180000;
                ArrayList<String> servicios = new ArrayList<>();
                servicios.add("WiFi");
                int capacidadMaxima = 3;
                double costoExtra = 10000;

                FabricaApartamento fabrica = new FabricaApartamento(nombre, ciudad, descripcion, rutaFoto, precio, servicios, capacidadMaxima, costoExtra);
                Alojamiento alojamiento = fabrica.crearProducto();

                assertInstanceOf(ProductoApartamento.class, alojamiento);
                ProductoApartamento apartamento = (ProductoApartamento) alojamiento;
                assertEquals(nombre, apartamento.getNombre());
                assertEquals(ciudad, apartamento.getCiudad());
                assertEquals(descripcion, apartamento.getDescripcion());
                assertNotNull(apartamento.getRutaFoto());
                assertEquals(precio, apartamento.getPrecio());
                assertEquals(servicios, apartamento.getServicios());
                assertEquals(capacidadMaxima, apartamento.getCapacidadMaxima());
                assertEquals(costoExtra, apartamento.getCostoExtra());
                assertEquals(precio + costoExtra, apartamento.calcularCosto());
        }
        @Test
        public void fabricaHotelCreaProductoCorrecto() {
                String nombre = "Hotel Paraíso";
                String ciudad = "Salento";
                String descripcion = "Hotel en medio de la naturaleza";
                String rutaFoto = null;
                double precio = 700000;
                ArrayList<String> servicios = new ArrayList<>();
                servicios.add("Gimnasio");
                servicios.add("Desayuno incluido");
                int capacidadMaxima = 100;
                double costoExtra = 120000;

                FabricaHotel fabricaHotel = new FabricaHotel(nombre, ciudad, descripcion, rutaFoto, servicios, capacidadMaxima);

                Alojamiento alojamientoHotel = fabricaHotel.crearProducto();

                assertInstanceOf(ProductoHotel.class, alojamientoHotel);

                ProductoHotel hotel = (ProductoHotel) alojamientoHotel;
                assertEquals(nombre, hotel.getNombre());
                assertEquals(ciudad, hotel.getCiudad());
                assertEquals(descripcion, hotel.getDescripcion());
                assertNotNull(hotel.getRutaFoto());
                assertEquals(servicios, hotel.getServicios());
                assertEquals(costoExtra, hotel.getCostoExtra());
                assertNotNull(hotel.getHabitaciones());
                assertTrue(hotel.getHabitaciones().isEmpty());
        }
        @Test
        public void agregarHabitacionesAlHotelTest() {
                // Crear un hotel
                String nombre = "Hotel Colonial";
                String ciudad = "Popayán";
                String descripcion = "Ideal para eventos";
                String rutaFoto = "foto/hotel.jpg";
                ArrayList<String> servicios = new ArrayList<>();
                servicios.add("WiFi");
                servicios.add("Desayuno incluido");
                int capacidadMaxima = 200;
                List<ProductoHabitacion> habitaciones = new ArrayList<>();

                // Crear habitaciones
                ProductoHabitacion habitacion1 = ProductoHabitacion.builder()
                        .numeroHabitacion(101)
                        .precio(900000)
                        .capacidad(2)
                        .descripcion("Doble estándar")
                        .idHotel(UUID.randomUUID())
                        .build();

                ProductoHabitacion habitacion2 = ProductoHabitacion.builder()
                        .numeroHabitacion(201)
                        .precio(180000)
                        .capacidad(4)
                        .descripcion("Suite familiar")
                        .idHotel(UUID.randomUUID())
                        .build();

                habitaciones.add(habitacion1);
                habitaciones.add(habitacion2);

                // Crear el hotel con las habitaciones
                FabricaHotel fabricaHotel = new FabricaHotel(nombre, ciudad, descripcion, rutaFoto,servicios, capacidadMaxima);
                ProductoHotel hotel = (ProductoHotel) fabricaHotel.crearProducto();
                hotel.setHabitaciones(habitaciones);
                assertEquals(2, hotel.getHabitaciones().size());
                assertEquals("Doble estándar", hotel.getHabitaciones().get(0).getDescripcion());
                assertEquals(900000, hotel.getHabitaciones().get(0).getPrecio());
                assertEquals("Suite familiar", hotel.getHabitaciones().get(1).getDescripcion());
                assertEquals(180000, hotel.getHabitaciones().get(1).getPrecio());
        }


}
