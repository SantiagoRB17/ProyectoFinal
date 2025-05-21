package co.edu.uniquindio.poo.proyectofinal.Controllers;

import co.edu.uniquindio.poo.proyectofinal.Model.AlojamientoDecorator.Oferta;
import co.edu.uniquindio.poo.proyectofinal.Model.entidades.ProductoHabitacion;
import co.edu.uniquindio.poo.proyectofinal.Model.entidades.ProductoHotel;
import co.edu.uniquindio.poo.proyectofinal.Repositorios.RepositorioImagenes;
import com.dlsc.gemsfx.ResizableTextArea;
import com.jfoenix.controls.JFXButton;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import lombok.Setter;

import java.net.URL;
import java.util.ResourceBundle;

public class DetalleHotelesOfertaViewController implements Initializable {

    @FXML
    private JFXButton btnLimpiarSeleccion;

    @FXML
    private TableColumn<ProductoHabitacion, Integer> clCantidadHuespedesHabitacionHotelDetalle;

    @FXML
    private TableColumn<ProductoHabitacion, Integer> clNumeroHabitacionHotelDetalle;

    @FXML
    private TableColumn<ProductoHabitacion, Double> clPrecioHabitacionHotelDetalle;

    @FXML
    private GridPane gridPaneInformacionAlojamiento;

    @FXML
    private GridPane gridPaneInformacionOferta;

    @FXML
    private ImageView imgViewFotoHotel;

    @FXML
    private Label lblCantidadDeHuespedes;

    @FXML
    private Label lblCiudad;

    @FXML
    private Label lblEsloganOferta;

    @FXML
    private Label lblNombre;

    @FXML
    private Label lblPrecio;

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
    private VBox oferta;

    @FXML
    private TableView<ProductoHabitacion> tbHabitacionesDetalleAlojamiento;

    @FXML
    private ResizableTextArea txtAreaDescripcion;

    @FXML
    private ResizableTextArea txtAreaDescripcionOferta;

    @FXML
    private TextField txtFielDescuento;

    @FXML
    private TextField txtFieldCapacidadHuespedesHabitacion;

    @FXML
    private TextField txtFieldCiudadHotel;

    @FXML
    private TextField txtFieldFinOferta;

    @FXML
    private TextField txtFieldInicioOferta;

    @FXML
    private TextField txtFieldNombreHotel;

    @FXML
    private TextField txtFieldPrecioHabitacion;

    @FXML
    private TextField txtFieldValoracionHotel;


    private ProductoHabitacion habitacionSeleccionada;
    @Setter
    private ProductoHotel hotelObservable;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clNumeroHabitacionHotelDetalle.setCellValueFactory(cellData-> new SimpleObjectProperty<>(cellData.getValue().getNumeroHabitacion()));
        clPrecioHabitacionHotelDetalle.setCellValueFactory(cellData-> new SimpleObjectProperty<>(cellData.getValue().getPrecio()));
        clCantidadHuespedesHabitacionHotelDetalle.setCellValueFactory(cellData->new SimpleObjectProperty<>(cellData.getValue().getCapacidad()));
        listViewResenasHotel.setItems(FXCollections.observableArrayList(hotelObservable.getResenas()));

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
                lblCantidadDeHuespedes.setVisible(true);
                lblCantidadDeHuespedes.setManaged(true);
                txtFieldCapacidadHuespedesHabitacion.setVisible(true);
                txtFieldCapacidadHuespedesHabitacion.setText(String.valueOf(habitacionSeleccionada.getCapacidad()));
                try{
                    imgViewFotoHotel.setImage(RepositorioImagenes.cargarImagen(habitacionSeleccionada.getRutaImagenHabitacion()));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }

    public void cargarDatosHotel(ProductoHotel hotelObservable,Oferta ofertaObservable){
        txtFieldNombreHotel.setText(hotelObservable.getNombre());
        txtFieldCiudadHotel.setText(hotelObservable.getCiudad());
        txtAreaDescripcion.setText(hotelObservable.getDescripcion());
        lblPrecio.setVisible(false);
        lblPrecio.setManaged(false);
        txtFieldPrecioHabitacion.setVisible(false);
        txtFieldPrecioHabitacion.setManaged(false);
        lblCantidadDeHuespedes.setVisible(false);
        lblCantidadDeHuespedes.setManaged(false);
        txtFieldCapacidadHuespedesHabitacion.setVisible(false);
        listViewServiciosHotel.setItems(FXCollections.observableArrayList(hotelObservable.getServicios()));
        txtFieldValoracionHotel.setText(String.valueOf(hotelObservable.getValoracion()));
        try{
            imgViewFotoHotel.setImage(RepositorioImagenes.cargarImagen(hotelObservable.getRutaFoto()));
        }catch (Exception e){
            e.printStackTrace();
        }
        tbHabitacionesDetalleAlojamiento.setItems(FXCollections.observableArrayList(hotelObservable.getHabitaciones()));
        habitacionSeleccionada=null;
        listViewResenasHotel.setItems(FXCollections.observableArrayList(hotelObservable.getResenas()));

        txtFielDescuento.setText(String.valueOf(ofertaObservable.getPorcentajeDescuento()));
        txtAreaDescripcionOferta.setText(ofertaObservable.getDescripcionOferta());
        txtFieldInicioOferta.setText(ofertaObservable.getFechaInicio().toString());
        txtFieldFinOferta.setText(ofertaObservable.getFechaFin().toString());
    }

    public void limpiarSeleccion(ActionEvent event){
        limpiarSeleccionHabitacion();
    }

    private void limpiarSeleccionHabitacion(){
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
        habitacionSeleccionada=null;
    }
}

