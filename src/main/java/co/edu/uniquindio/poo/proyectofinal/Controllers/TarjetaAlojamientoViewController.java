package co.edu.uniquindio.poo.proyectofinal.Controllers;

import co.edu.uniquindio.poo.proyectofinal.Model.AlojamientosFactory.Alojamiento;
import co.edu.uniquindio.poo.proyectofinal.Model.ProductoHotel;
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
import lombok.Getter;
import lombok.Setter;

public class TarjetaAlojamientoViewController {
    @FXML
    private JFXButton btnVerDetalles;

    @FXML
    private HBox hboxInformacionAlojamientoTarjeta;

    @FXML
    private HBox hboxUbicacionAlojamientoTarjeta;

    @FXML
    private ImageView imgViewFotoAlojamiento;

    @FXML
    private Label lblPrecioAlojamiento;

    @FXML
    private Label lblNombreAlojamiento;

    @FXML
    private Label lblUbicacionAlojamiento;

    @FXML
    private VBox vboxTarjetaAlojamiento;

    @Getter
    @Setter
    Alojamiento alojamientoObservable;
    @FXML
    void abrirDetallesAlojamientos(ActionEvent event) {
        try {
            // Cargar la vista
            Parent root;
            FXMLLoader loader;

            if (alojamientoObservable instanceof ProductoHotel) {
                loader = new FXMLLoader(getClass().getResource("/DetalleHotelesView.fxml"));
                root = loader.load();

                // Obtener el controlador y pasarle el objeto hotel
                DetalleHotelesViewController controller = loader.getController();
                controller.cargarDatosHotel((ProductoHotel) alojamientoObservable);
                controller.setHotelObservable((ProductoHotel) alojamientoObservable);

            } else {
                loader = new FXMLLoader(getClass().getResource("/DetalleAlojamientoView.fxml"));
                root = loader.load();
                // Obtener el controlador y pasarle el objeto alojamiento normal
                DetalleAlojamientoViewController controller = loader.getController();
                controller.cargarDatosAlojamiento(alojamientoObservable);
            }


            // Crear la escena
            Scene scene = new Scene(root);

            // Crear un nuevo escenario (ventana)
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(true);
            stage.setTitle("Detalle alojamiento");

            // Mostrar la nueva ventana
            stage.show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

