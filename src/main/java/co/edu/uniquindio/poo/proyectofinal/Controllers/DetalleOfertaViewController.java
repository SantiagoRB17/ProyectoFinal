package co.edu.uniquindio.poo.proyectofinal.Controllers;

import co.edu.uniquindio.poo.proyectofinal.Model.entidades.Oferta;
import co.edu.uniquindio.poo.proyectofinal.Model.AlojamientosFactory.Alojamiento;
import co.edu.uniquindio.poo.proyectofinal.Model.entidades.ProductoApartamento;
import co.edu.uniquindio.poo.proyectofinal.Model.entidades.ProductoCasa;
import co.edu.uniquindio.poo.proyectofinal.Repositorios.RepositorioImagenes;
import com.dlsc.gemsfx.ResizableTextArea;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class DetalleOfertaViewController {

    @FXML
    private JFXButton btnAnadirReserva;

    @FXML
    private TextField datePickerCantidadHuespedes;

    @FXML
    private DatePicker datePickerLLegada;

    @FXML
    private DatePicker datePickerSalida;

    @FXML
    private GridPane gridPaneFormularioReserva;

    @FXML
    private GridPane gridPaneInformacionAlojamiento;

    @FXML
    private GridPane gridPaneInformacionOferta;

    @FXML
    private ImageView imgViewFotoAlojamientoOferta;

    @FXML
    private Label lblEsloganOferta;

    @FXML
    private Label lblResenas;

    @FXML
    private Label lblSaludoOferta;

    @FXML
    private Label lblSaludoReservaDetalles;

    @FXML
    private ListView<String> listViewResenasAlojamientoOferta;

    @FXML
    private ListView<String> listViewServiciosAlojamientoOferta;

    @FXML
    private VBox oferta;

    @FXML
    private ResizableTextArea txtAreaDescripcionOferta;
    @FXML
    private ResizableTextArea txtAreaDescripcionAlojamientoOferta;

    @FXML
    private TextField txtFielDescuento;

    @FXML
    private TextField txtFieldCapacidadHuespedesOferta;

    @FXML
    private TextField txtFieldCiudadAlojamientoOferta;

    @FXML
    private TextField txtFieldFinOferta;

    @FXML
    private TextField txtFieldInicioOferta;

    @FXML
    private TextField txtFieldNombreAlojamientoOferta;

    @FXML
    private TextField txtFieldPrecioAlojamientoOferta;

    @FXML
    private TextField txtFieldValoracionAlojamientoOferta;

    public void cargarDatosAlojamiento(Alojamiento alojamientoObservable, Oferta ofertaObservable) {
        txtFieldNombreAlojamientoOferta.setText(alojamientoObservable.getNombre());
        txtFieldCiudadAlojamientoOferta.setText(alojamientoObservable.getCiudad());
        txtAreaDescripcionAlojamientoOferta.setText(alojamientoObservable.getDescripcion());
        txtFieldPrecioAlojamientoOferta.setText(String.valueOf(alojamientoObservable.calcularCosto()));
        listViewResenasAlojamientoOferta.setItems(FXCollections.observableArrayList(alojamientoObservable.getResenas()));

        int cantidadHuespedes = 0;
        if (alojamientoObservable instanceof ProductoCasa casa) {
            cantidadHuespedes = casa.getCapacidadMaxima();
        } else if (alojamientoObservable instanceof ProductoApartamento apartamento) {
            cantidadHuespedes = apartamento.getCapacidadMaxima();
        }
        txtFieldCapacidadHuespedesOferta.setText(String.valueOf(cantidadHuespedes));
        listViewServiciosAlojamientoOferta.setItems(FXCollections.observableArrayList(alojamientoObservable.getServicios()));
        txtFieldValoracionAlojamientoOferta.setText(String.valueOf(alojamientoObservable.getValoracion()));
        try{
            imgViewFotoAlojamientoOferta.setImage(RepositorioImagenes.cargarImagen(alojamientoObservable.getRutaFoto()));
        }catch (Exception e){
            e.printStackTrace();
        }
        txtFielDescuento.setText(String.valueOf(ofertaObservable.getPorcentajeDescuento()));
        txtAreaDescripcionOferta.setText(ofertaObservable.getDescripcionOferta());
        txtFieldInicioOferta.setText(ofertaObservable.getFechaInicio().toString());
        txtFieldFinOferta.setText(ofertaObservable.getFechaFin().toString());
    }

}
