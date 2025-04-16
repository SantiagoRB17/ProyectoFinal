package co.edu.uniquindio.poo.proyectofinal.Controllers;

import com.jfoenix.controls.JFXButton;
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
import java.util.ResourceBundle;

public class InicioViewcontroller implements Initializable {

    @FXML
    private JFXButton btnIniciarSesion;

    @FXML
    private JFXButton btnRegistro;

    @FXML
    private JFXButton btnVerMas;

    @FXML
    private DatePicker datePickerLLegada;

    @FXML
    private DatePicker datePickerSalida;

    @FXML
    private FlowPane flowPaneVistaTarjetasAlojamiento;

    @FXML
    private HBox hboxFiltroAlojamientosFields;

    @FXML
    private HBox hboxFiltroAlojamientosLabels;

    @FXML
    private HBox hboxIniSesionRegistro;

    @FXML
    private HBox hboxSaludoIniSesionRegistro;

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
    private ComboBox<?> textFieldFiltroEstrellas;

    @FXML
    private TextField txtFieldFiltroCantidadHuespedes;

    @FXML
    private TextField txtFieldFiltroCiudad;

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*cargarAlojamientos();
        cargarOfertas();*/
    }
    /*private void cargarAlojamientos() {
        for (int i = 0; i < 8; i++) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/TarjetaAlojamientoView.fxml"));
                Node nodoTarjeta = loader.load();

                Label lblNombre = (Label) nodoTarjeta.lookup("#lblNombreAlojamiento");
                Label lblUbicacion = (Label) nodoTarjeta.lookup("#lblUbicacionAlojamiento");
                Label lblEstrellas = (Label) nodoTarjeta.lookup("#lblEstrellasAlojamiento");
                ImageView img = (ImageView) nodoTarjeta.lookup("#imgViewFotoAlojamiento");

                lblNombre.setText("Alojamiento " + (i + 1));
                lblUbicacion.setText("Ubicacion " + (i + 1));
                lblEstrellas.setText((i % 5 + 1) +"★" );
                img.setImage(img.getImage());

                flowPaneVistaTarjetasAlojamiento.getChildren().add(nodoTarjeta);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void cargarOfertas() {
        for (int i = 0; i < 10; i++) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/TarjetaAlojamientoOfertaView.fxml"));
                Node nodoTarjeta = loader.load();

                Label lblNombre = (Label) nodoTarjeta.lookup("#lblNombreAlojamientoOferta");
                lblNombre.setText("Oferta " + (i + 1));

                hboxVistaTarjetaOfertas.getChildren().add(nodoTarjeta);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }*/
}

