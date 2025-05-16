package co.edu.uniquindio.poo.proyectofinal.Controllers;

import co.edu.uniquindio.poo.proyectofinal.Model.AlojamientosFactory.Alojamiento;
import co.edu.uniquindio.poo.proyectofinal.Model.ProductoApartamento;
import co.edu.uniquindio.poo.proyectofinal.Model.ProductoCasa;
import co.edu.uniquindio.poo.proyectofinal.Model.ProductoHotel;
import co.edu.uniquindio.poo.proyectofinal.Repositorios.RepositorioImagenes;
import com.dlsc.gemsfx.ResizableTextArea;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import lombok.Setter;

import java.net.URL;
import java.util.ResourceBundle;

public class DetalleAlojamientoViewController{

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
    private ImageView imgViewFotoAlojamiento;

    @FXML
    private Label lblResenas;

    @FXML
    private Label lblSaludoDetalles;

    @FXML
    private Label lblSaludoReservaDetalles;

    @FXML
    private ListView<String> listViewResenasAlojamiento;

    @FXML
    private ListView<String> listViewServiciosAlojamiento;

    @FXML
    private ResizableTextArea txtAreaDescripcion;

    @FXML
    private TextField txtFieldCapacidadHuespedes;

    @FXML
    private TextField txtFieldCiudadAlojamiento;

    @FXML
    private TextField txtFieldNombreAlojamiento;

    @FXML
    private TextField txtFieldPrecioAlojamiento;

    @FXML
    private TextField txtFieldValoracionAlojamiento;


    public void cargarDatosAlojamiento(Alojamiento alojamientoObservable) {
        txtFieldNombreAlojamiento.setText(alojamientoObservable.getNombre());
        txtFieldCiudadAlojamiento.setText(alojamientoObservable.getCiudad());
        txtAreaDescripcion.setText(alojamientoObservable.getDescripcion());
        double precioAlojamiento = 0;
        if (alojamientoObservable instanceof ProductoCasa casa) {
            precioAlojamiento = casa.getPrecio();
        } else if (alojamientoObservable instanceof ProductoApartamento apartamento) {
            precioAlojamiento = apartamento.getPrecio();
        }
        txtFieldPrecioAlojamiento.setText(String.valueOf(precioAlojamiento));
        int cantidadHuespedes = 0;
        if (alojamientoObservable instanceof ProductoCasa casa) {
            cantidadHuespedes = casa.getCapacidadMaxima();
        } else if (alojamientoObservable instanceof ProductoApartamento apartamento) {
            cantidadHuespedes = apartamento.getCapacidadMaxima();
        }
        txtFieldCapacidadHuespedes.setText(String.valueOf(cantidadHuespedes));
        listViewServiciosAlojamiento.setItems(FXCollections.observableArrayList(alojamientoObservable.getServicios()));
        txtFieldValoracionAlojamiento.setText(String.valueOf(alojamientoObservable.getValoracion()));
        try{
            imgViewFotoAlojamiento.setImage(RepositorioImagenes.cargarImagen(alojamientoObservable.getRutaFoto()));
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}

