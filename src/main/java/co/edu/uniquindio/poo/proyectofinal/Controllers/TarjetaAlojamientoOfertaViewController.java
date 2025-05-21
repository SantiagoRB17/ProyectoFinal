package co.edu.uniquindio.poo.proyectofinal.Controllers;

import co.edu.uniquindio.poo.proyectofinal.Model.AlojamientoDecorator.Oferta;
import co.edu.uniquindio.poo.proyectofinal.Model.AlojamientosFactory.Alojamiento;
import co.edu.uniquindio.poo.proyectofinal.Model.entidades.ProductoHotel;
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

public class TarjetaAlojamientoOfertaViewController {
    private VentanaController ventanasController= VentanaController.getInstancia() ;
    @FXML
    private JFXButton btnVerDetallesOfertas;

    @FXML
    private HBox hboxInformacionAlojamientoTarjetaOferta;

    @FXML
    private ImageView imgViewFotoAlojamientoOferta;

    @FXML
    private Label lblDescripcionOferta;

    @FXML
    private Label lblPrecioAlojamientoOferta;

    @FXML
    private Label lblNombreAlojamientoOferta;

    @FXML
    private Label lblUbicacionAlojamientoOferta;

    @FXML
    private VBox vboxInformacionOferta;

    @FXML
    private VBox vboxTarjetaAlojamientoOferta;

    @Getter
    @Setter
    private Alojamiento alojamientoObservable;
    @Getter
    @Setter
    private Oferta ofertaObervable;

    @FXML
    void abrirDetallesOferta(ActionEvent event) throws Exception {
        try {
            // Cargar la vista
            Parent root;
            FXMLLoader loader;

            if (alojamientoObservable instanceof ProductoHotel) {
                loader = new FXMLLoader(getClass().getResource("/detalleHotelesOfertaView.fxml"));
                root = loader.load();

                // Obtener el controlador y pasarle el objeto hotel
                DetalleHotelesOfertaViewController controller = loader.getController();
                controller.cargarDatosHotel((ProductoHotel) alojamientoObservable,ofertaObervable);
                controller.setHotelObservable((ProductoHotel) alojamientoObservable);

            } else {
                loader = new FXMLLoader(getClass().getResource("/DetalleOferta.fxml"));
                root = loader.load();
                // Obtener el controlador y pasarle el objeto alojamiento normal
                DetalleOfertaViewController controller = loader.getController();
                controller.cargarDatosAlojamiento(alojamientoObservable,ofertaObervable);
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

