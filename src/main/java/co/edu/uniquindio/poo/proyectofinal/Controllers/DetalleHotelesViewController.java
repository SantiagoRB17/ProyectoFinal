package co.edu.uniquindio.poo.proyectofinal.Controllers;

import co.edu.uniquindio.poo.proyectofinal.Model.AlojamientosFactory.Alojamiento;
import co.edu.uniquindio.poo.proyectofinal.Model.ProductoHabitacion;
import co.edu.uniquindio.poo.proyectofinal.Model.ProductoHotel;
import co.edu.uniquindio.poo.proyectofinal.Repositorios.RepositorioImagenes;
import com.dlsc.gemsfx.ResizableTextArea;
import com.jfoenix.controls.JFXButton;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import lombok.Setter;

import java.net.URL;
import java.util.ResourceBundle;

public class DetalleHotelesViewController implements Initializable {

    @FXML
    private JFXButton btnAnadirReserva;

    @FXML
    private TableColumn<ProductoHabitacion, Integer> clCantidadHuespedesHabitacionHotelDetalle;

    @FXML
    private TableColumn<ProductoHabitacion, Integer> clNumeroHabitacionHotelDetalle;

    @FXML
    private TableColumn<ProductoHabitacion, Double> clPrecioHabitacionHotelDetalle;

    @FXML
    private TextField datePickerCantidadHuespedes;

    @FXML
    private DatePicker datePickerLLegada;


    @FXML
    private JFXButton btnLimpiarSeleccion;

    @FXML
    private DatePicker datePickerSalida;

    @FXML
    private GridPane gridPaneFormularioReserva;

    @FXML
    private GridPane gridPaneInformacionAlojamiento;

    @FXML
    private ImageView imgViewFotoHotel;

    @FXML
    private Label lblResenas;

    @FXML
    private Label lblSaludoDetalles;

    @FXML
    private Label lblSaludoReservaDetalles;

    @FXML
    private ListView<String> listViewResenasHotel;

    @FXML
    private ListView<String> listViewServiciosHotel;

    @FXML
    private TableView<ProductoHabitacion> tbHabitacionesDetalleAlojamiento;

    @FXML
    private ResizableTextArea txtAreaDescripcion;

    @FXML
    private TextField txtFieldCapacidadHuespedesHabitacion;

    @FXML
    private TextField txtFieldCiudadHotel;

    @FXML
    private TextField txtFieldNombreHotel;

    @FXML
    private TextField txtFieldPrecioHabitacion;

    @FXML
    private TextField txtFieldValoracionHotel;

    @FXML
    private Label lblCantidadDeHuespedes;

    @FXML
    private Label lblNombre;

    @FXML
    private Label lblCiudad;

    @FXML
    private Label lblPrecio;

    private ProductoHabitacion habitacionSeleccionada;
    @Setter
    ProductoHotel hotelObservable;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clNumeroHabitacionHotelDetalle.setCellValueFactory(cellData-> new SimpleObjectProperty<>(cellData.getValue().getNumeroHabitacion()));
        clPrecioHabitacionHotelDetalle.setCellValueFactory(cellData-> new SimpleObjectProperty<>(cellData.getValue().getPrecio()));
        clCantidadHuespedesHabitacionHotelDetalle.setCellValueFactory(cellData->new SimpleObjectProperty<>(cellData.getValue().getCapacidad()));

        tbHabitacionesDetalleAlojamiento.setOnMouseClicked(mouseEvent -> {
            habitacionSeleccionada=tbHabitacionesDetalleAlojamiento.getSelectionModel().getSelectedItem();
            if(habitacionSeleccionada!=null){
                lblNombre.setText("Numero");
                txtFieldNombreHotel.setText(String.valueOf(habitacionSeleccionada.getNumeroHabitacion()));
                lblCiudad.setVisible(false);
                lblCiudad.setManaged(false);
                txtFieldCiudadHotel.setVisible(false);
                txtFieldCiudadHotel.setManaged(false);
                txtAreaDescripcion.setText(habitacionSeleccionada.getDescripcion());
                lblPrecio.setVisible(true);
                lblPrecio.setManaged(true);
                txtFieldPrecioHabitacion.setVisible(true);
                txtFieldPrecioHabitacion.setManaged(true);
                txtFieldPrecioHabitacion.setText(String.valueOf(habitacionSeleccionada.getPrecio()));
                txtFieldCapacidadHuespedesHabitacion.setText(String.valueOf(habitacionSeleccionada.getCapacidad()));
                try{
                    imgViewFotoHotel.setImage(RepositorioImagenes.cargarImagen(habitacionSeleccionada.getRutaImagenHabitacion()));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }

    public void limpiarSeleccion(ActionEvent event){
        limpiarSeleccionHabitacion();
    }

    public void limpiarSeleccionHabitacion(){
            lblNombre.setText("Nombre");
            txtFieldNombreHotel.setText(hotelObservable.getNombre());
            lblCiudad.setVisible(true);
            lblCiudad.setManaged(true);
            txtFieldCiudadHotel.setVisible(true);
            txtFieldCiudadHotel.setManaged(true);
            txtAreaDescripcion.setText(hotelObservable.getDescripcion());
            lblPrecio.setVisible(false);
            lblPrecio.setManaged(false);
            txtFieldPrecioHabitacion.setVisible(false);
            txtFieldPrecioHabitacion.setManaged(false);
            lblCantidadDeHuespedes.setVisible(false);
            lblCantidadDeHuespedes.setManaged(false);
            txtFieldCapacidadHuespedesHabitacion.setVisible(false);
            try{
                imgViewFotoHotel.setImage(RepositorioImagenes.cargarImagen(hotelObservable.getRutaFoto()));
            }catch (Exception e){
                e.printStackTrace();
            }
    }

    public void cargarDatosHotel(ProductoHotel hotel){
        txtFieldNombreHotel.setText(hotel.getNombre());
        txtFieldCiudadHotel.setText(hotel.getCiudad());
        txtAreaDescripcion.setText(hotel.getDescripcion());
        lblPrecio.setVisible(false);
        lblPrecio.setManaged(false);
        txtFieldPrecioHabitacion.setVisible(false);
        txtFieldPrecioHabitacion.setManaged(false);
        lblCantidadDeHuespedes.setVisible(false);
        lblCantidadDeHuespedes.setManaged(false);
        txtFieldCapacidadHuespedesHabitacion.setVisible(false);
        listViewServiciosHotel.setItems(FXCollections.observableArrayList(hotel.getServicios()));
        txtFieldValoracionHotel.setText(String.valueOf(hotel.getValoracion()));
        try{
            imgViewFotoHotel.setImage(RepositorioImagenes.cargarImagen(hotel.getRutaFoto()));
        }catch (Exception e){
            e.printStackTrace();
        }
        tbHabitacionesDetalleAlojamiento.setItems(FXCollections.observableArrayList(hotel.getHabitaciones()));
        habitacionSeleccionada=null;
        listViewResenasHotel.setItems(FXCollections.observableArrayList(hotel.getResenas()));
    }
}

