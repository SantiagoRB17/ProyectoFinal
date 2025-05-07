package co.edu.uniquindio.poo.proyectofinal.Controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import jfxtras.scene.layout.HBox;
import jfxtras.scene.layout.VBox;

public class TarjetaAlojamientoOfertaViewController {
    private VentanasController ventanasController= VentanasController.getInstancia() ;
    @FXML
    private JFXButton btnVerDetallesOfertas;

    @FXML
    private HBox hboxInformacionAlojamientoTarjetaOferta;

    @FXML
    private ImageView imgViewFotoAlojamientoOferta;

    @FXML
    private Label lblDescripcionOferta;

    @FXML
    private Label lblEstrellasAlojamientoOferta;

    @FXML
    private Label lblNombreAlojamientoOferta;

    @FXML
    private Label lblUbicacionAlojamientoOferta;

    @FXML
    private VBox vboxInformacionOferta;

    @FXML
    private VBox vboxTarjetaAlojamientoOferta;

    @FXML
    void abrirDetallesOferta(ActionEvent event) throws Exception {

    }

}

