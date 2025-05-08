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

public class TarjetaAlojamientoViewController {
    private VentanasController ventanasController= VentanasController.getInstancia();
    @FXML
    private JFXButton btnVerDetalles;

    @FXML
    private HBox hboxInformacionAlojamientoTarjeta;

    @FXML
    private HBox hboxUbicacionAlojamientoTarjeta;

    @FXML
    private ImageView imgViewFotoAlojamiento;

    @FXML
    private Label lblEstrellasAlojamiento;

    @FXML
    private Label lblNombreAlojamiento;

    @FXML
    private Label lblUbicacionAlojamiento;

    @FXML
    private VBox vboxTarjetaAlojamiento;

    @FXML
    void abrirDetallesAlojamientos(ActionEvent event) {
        try {

            // Cargar la vista
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/DetalleAlojamientoView.fxml"));
            Parent root = loader.load();

            // Crear la escena
            Scene scene = new Scene(root);

            // Crear un nuevo escenario (ventana)
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(true);
            stage.setTitle("Servicio Habitacion");

            // Mostrar la nueva ventana
            stage.show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

