
import co.edu.uniquindio.poo.proyectofinal.Enums.TipoAlojamiento;
import co.edu.uniquindio.poo.proyectofinal.Model.AlojamientosFactory.*;
import co.edu.uniquindio.poo.proyectofinal.Model.entidades.*;
import co.edu.uniquindio.poo.proyectofinal.Repositorios.RepositorioAlojamientos;
import co.edu.uniquindio.poo.proyectofinal.Repositorios.RepositorioPersonas;
import co.edu.uniquindio.poo.proyectofinal.Repositorios.RepositorioReservas;
import co.edu.uniquindio.poo.proyectofinal.Servicios.ServicioAlojamientos;
import co.edu.uniquindio.poo.proyectofinal.Servicios.ServicioEstadisticas;
import co.edu.uniquindio.poo.proyectofinal.Servicios.ServicioGenerarQr;
import co.edu.uniquindio.poo.proyectofinal.Utils.EncriptacionContrasena;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
public class TestVitales {

    @Test
    void agregarPersonaTest() throws Exception {
        RepositorioPersonas repo = new RepositorioPersonas();
        Persona persona = Persona.builder()
                .cedula("123")
                .nombre("Juan")
                .email("juan@email.com")
                .cuentaActiva(true)
                .build();

        repo.agregarPersona(persona);

        Persona encontrada = repo.obtenerPorId("123");
        assertNotNull(encontrada);
        assertEquals("Juan", encontrada.getNombre());

        // Chequea duplicidad
        Exception ex = assertThrows(Exception.class, () -> repo.agregarPersona(persona));
        assertEquals("Ya existe una persona registrada con ese ID o email", ex.getMessage());
    }

    @Test
    void crearReservaValidaTest() throws Exception {
        Reserva reserva = new Reserva(/* parámetros apropiados */);
        RepositorioReservas repo = new RepositorioReservas();
        repo.agregarReserva(reserva);

        Reserva obtenida = repo.obtenerPorId(reserva.getIdReserva());
        assertNotNull(obtenida);
        assertEquals(reserva.getIdReserva(), obtenida.getIdReserva());
    }

    @Test
    void almacenarYFiltrarAlojamientosTest() {
        RepositorioAlojamientos repo = new RepositorioAlojamientos();
        Alojamiento casa = ProductoCasa.builder().nombre("Casa").activo(true).build();
        Alojamiento apto = ProductoApartamento.builder().nombre("Apto").activo(true).build();
        assertDoesNotThrow(() -> repo.agregarAlojamiento(casa));
        assertDoesNotThrow(() -> repo.agregarAlojamiento(apto));

        List<Alojamiento> lista = repo.listarCasasyApartamentos();
        assertTrue(lista.stream().anyMatch(a -> a instanceof ProductoCasa));
        assertTrue(lista.stream().anyMatch(a -> a instanceof ProductoApartamento));
    }

    @Test
    void calcularCostoAlojamientoTest() {
        ProductoCasa casa = ProductoCasa.builder()
                .precio(100000)
                .costoExtra(20000)
                .build();
        double costo = casa.calcularCosto();
        assertEquals(120000, costo);
    }
    @Test
    void generarQrFacturaTest() throws Exception {
        ServicioGenerarQr servicio = new ServicioGenerarQr();
        String facturaId = UUID.randomUUID().toString();
        File qr = servicio.generarQR(facturaId, "qr_factura_test");
        assertTrue(qr.exists());
        assertTrue(qr.length() > 0);
        qr.delete();
    }
    @Test
    void agregarYRecuperarPersonaPorCedulaYEmail() throws Exception {
        RepositorioPersonas repo = new RepositorioPersonas();
        Persona persona = Persona.builder()
                .cedula("987654")
                .nombre("Ana Prueba")
                .email("ana@ejemplo.com")
                .cuentaActiva(true)
                .build();

        repo.agregarPersona(persona);

        Persona obtenidaPorCedula = repo.obtenerPorId("987654");
        assertNotNull(obtenidaPorCedula);
        assertEquals("Ana Prueba", obtenidaPorCedula.getNombre());

        Persona obtenidaPorEmail = repo.obtenerPorEmail("ana@ejemplo.com");
        assertNotNull(obtenidaPorEmail);
        assertEquals("987654", obtenidaPorEmail.getCedula());
    }
    @Test
    void encriptarSHA256_MismoResultadoParaElMismoTexto() {
        EncriptacionContrasena encriptacionContrasena=new EncriptacionContrasena();
        String texto = "miclaveSuperSegura";
        String hashEsperado = encriptacionContrasena.hashPasswordSHA256(texto);
        String otroHash = encriptacionContrasena.hashPasswordSHA256(texto);

        // El mismo texto siempre debe producir el mismo hash
        assertEquals(hashEsperado, otroHash);
        // Un hash SHA-256 típico siempre tiene 64 caracteres hexadecimales
        assertEquals(64, hashEsperado.length());
    }
    @Test
    void encriptarSHA256_DiferenteTextoDiferenteHash() {
        EncriptacionContrasena encriptacionContrasena=new EncriptacionContrasena();
        String texto1 = "claveUno";
        String texto2 = "claveDos";
        String hash1 = encriptacionContrasena.hashPasswordSHA256(texto1);
        String hash2 = encriptacionContrasena.hashPasswordSHA256(texto2);

        assertNotEquals(hash1, hash2);
    }



}
