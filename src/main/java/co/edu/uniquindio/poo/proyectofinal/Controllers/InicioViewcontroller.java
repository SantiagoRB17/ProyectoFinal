package co.edu.uniquindio.poo.proyectofinal.Controllers;

import co.edu.uniquindio.poo.proyectofinal.Enums.TipoAlojamiento;
import co.edu.uniquindio.poo.proyectofinal.Model.AlojamientoDecorator.Oferta;
import co.edu.uniquindio.poo.proyectofinal.Model.AlojamientosFactory.Alojamiento;
import co.edu.uniquindio.poo.proyectofinal.Model.ProductoApartamento;
import co.edu.uniquindio.poo.proyectofinal.Model.ProductoCasa;
import co.edu.uniquindio.poo.proyectofinal.Model.ProductoHotel;
import co.edu.uniquindio.poo.proyectofinal.Observers.AlojamientosObserver;
import co.edu.uniquindio.poo.proyectofinal.Repositorios.RepositorioImagenes;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import jfxtras.scene.layout.HBox;
import jfxtras.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class InicioViewcontroller implements Initializable, AlojamientosObserver {

    @FXML
    private JFXButton btnIniciarSesion;

    @FXML
    private JFXButton btnRegistro;

    @FXML
    private JFXButton btnVerMas;

    @FXML
    private ComboBox<TipoAlojamiento> cmbTipoAlojamiento;

    @FXML
    private FlowPane flowPaneVistaTarjetasAlojamiento;

    @FXML
    private HBox hboxFiltroAlojamientosFields;

    @FXML
    private Label lblBienvenidoUsuario;


    @FXML
    private HBox hboxFiltroAlojamientosLabels;

    @FXML
    private HBox hboxIniSesionRegistro;

    @FXML
    private HBox hboxSaludoIniSesionRegistro;

    @FXML
    private HBox hboxUsuarioLogeado;

    @FXML
    private HBox hboxVistaTarjetaOfertas;

    @FXML
    private Label lblBienvenido;

    @FXML
    private Label lblEslogan;

    @FXML
    private Label lblEslogan2;

    @FXML
    private Label lblOfertas;

    @FXML
    private MenuButton menItemOpcionesUsuario;

    @FXML
    private ScrollPane scrollPaneAlojamientos;

    @FXML
    private ScrollPane scrollPaneOfertas;

    @FXML
    private ScrollPane scrollPanePrincipal;

    @FXML
    private Separator sepSeparadorAlojamientosOfertas;

    @FXML
    private Separator sepSeparadorSaludoYAlojamientos;

    @FXML
    private ComboBox<Double> textFieldFiltroPrecio;

    @FXML
    private TextField txtFieldFiltroCiudad;

    @FXML
    private TextField txtFieldNombre;

    @FXML
    private VBox vboxAlojamientos;

    @FXML
    private VBox vboxContenedorAlojamientosOfertas;

    @FXML
    private VBox vboxEslogans;

    @FXML
    private VBox vboxFiltroAlojamientos;

    @FXML
    private VBox vboxPrincipal;

    private final VentanaController ventanasController= VentanaController.getInstancia();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ventanasController.getPlataforma().registrarObservador(this);
        cmbTipoAlojamiento.getItems().addAll(TipoAlojamiento.values());
        cargarAlojamientos();
        cargarOfertas();

    }

    public void abrirVistaIniciarSesion(ActionEvent actionEvent) {
    }

    public void abrirVistaRegistrarse(ActionEvent actionEvent) {

    }

    public void verMasAlojamientos(ActionEvent actionEvent) {
    }
    private void cargarAlojamientos() {
        List<Alojamiento> alojamientos=ventanasController.getPlataforma().listarAlojamientos();
        for (Alojamiento alojamiento : alojamientos) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/TarjetaAlojamientoView.fxml"));
                Node nodoTarjeta = loader.load();
                TarjetaAlojamientoViewController controller = loader.getController();
                controller.setAlojamientoObservable(alojamiento);

                Label lblNombre = (Label) nodoTarjeta.lookup("#lblNombreAlojamiento");
                Label lblUbicacion = (Label) nodoTarjeta.lookup("#lblUbicacionAlojamiento");
                Label lblPrecio = (Label) nodoTarjeta.lookup("#lblPrecioAlojamiento");
                ImageView img = (ImageView) nodoTarjeta.lookup("#imgViewFotoAlojamiento");

                double precioAlojamiento = 0;
                if (alojamiento instanceof ProductoCasa casa) {
                    precioAlojamiento = casa.getPrecio();
                } else if (alojamiento instanceof ProductoApartamento apartamento) {
                    precioAlojamiento = apartamento.getPrecio();
                } else if (alojamiento instanceof ProductoHotel hotel) {
                    precioAlojamiento = 0.0;
                }


                lblNombre.setText("Alojamiento " + (alojamiento.getNombre()));
                lblUbicacion.setText("Ubicacion " + (alojamiento.getCiudad()));
                lblPrecio.setText("Precio " + (precioAlojamiento));

                img.setImage(RepositorioImagenes.cargarImagen(alojamiento.getRutaFoto()));

                flowPaneVistaTarjetasAlojamiento.getChildren().add(nodoTarjeta);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void cargarOfertas() {
        List<Oferta> ofertas=ventanasController.getPlataforma().listarOfertas();
        for (Oferta oferta : ofertas) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/TarjetaAlojamientoOfertaView.fxml"));
                Node nodoTarjeta = loader.load();

                Label lblNombre = (Label) nodoTarjeta.lookup("#lblNombreAlojamientoOferta");
                Label lblUbicacion = (Label) nodoTarjeta.lookup("#lblUbicacionAlojamientoOferta");
                Label lblPrecio = (Label) nodoTarjeta.lookup("#lblPrecioAlojamientoOferta");
                ImageView img = (ImageView) nodoTarjeta.lookup("#imgViewFotoAlojamientoOferta");
                Label lblDescripcionOferta = (Label) nodoTarjeta.lookup("#lblDescripcionOferta");

                lblNombre.setText("Oferta " + ventanasController.getPlataforma().buscarAlojamientoPorId(oferta.getIdAlojamiento()).getNombre() );
                lblUbicacion.setText("Ubicacion "+ventanasController.getPlataforma().buscarAlojamientoPorId(oferta.getIdAlojamiento()).getCiudad());
                lblPrecio.setText("Descuento "+ oferta.getPorcentajeDescuento());
                lblDescripcionOferta.setText("Oferta "+ oferta.getDescripcionOferta());

                img.setImage(RepositorioImagenes.cargarImagen(ventanasController.getPlataforma().buscarAlojamientoPorId(oferta.getIdAlojamiento()).getRutaFoto()));


                    hboxVistaTarjetaOfertas.getChildren().add(nodoTarjeta);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void actualizar() {
        cargarAlojamientos();
        cargarOfertas();
    }
}

