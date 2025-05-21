package co.edu.uniquindio.poo.proyectofinal.Controllers;

import co.edu.uniquindio.poo.proyectofinal.Model.AlojamientosFactory.Alojamiento;
import co.edu.uniquindio.poo.proyectofinal.Model.entidades.ProductoApartamento;
import co.edu.uniquindio.poo.proyectofinal.Model.entidades.ProductoCasa;
import co.edu.uniquindio.poo.proyectofinal.Model.entidades.Sesion;
import co.edu.uniquindio.poo.proyectofinal.Repositorios.RepositorioImagenes;
import com.dlsc.gemsfx.ResizableTextArea;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import lombok.Setter;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class DetalleAlojamientoViewController {

    @FXML
    private JFXButton btnAnadirReserva;

    @FXML
    private TextField txtFieldCantidadHuespedes;

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

    private final Sesion sesion= Sesion.getInstancia();
    private final VentanaController ventanaController=VentanaController.getInstancia();
    @Setter
    private Alojamiento alojamientoObservable;

    public void cargarDatosAlojamiento(Alojamiento alojamientoObservable) {
        txtFieldNombreAlojamiento.setText(alojamientoObservable.getNombre());
        txtFieldCiudadAlojamiento.setText(alojamientoObservable.getCiudad());
        txtAreaDescripcion.setText(alojamientoObservable.getDescripcion());
        txtFieldPrecioAlojamiento.setText(String.valueOf(alojamientoObservable.calcularCosto()));
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

    public void limpiarCamposReserva(){
        datePickerLLegada.setValue(null);
        datePickerSalida.setValue(null);
        txtFieldCantidadHuespedes.clear();
    }

    public void crearReserva(javafx.event.ActionEvent event) {
        if(sesion.getPersona()==null){
            ventanaController.mostrarAlerta("Debe inciar sesion para poder reservar", Alert.AlertType.ERROR);
        }else{
            try{
                ventanaController.getPlataforma().crearReserva(sesion.getPersona().getCedula(),alojamientoObservable.getId(),
                        datePickerLLegada.getValue(),datePickerSalida.getValue(),
                        Integer.parseInt(txtFieldCantidadHuespedes.getText()));
                ventanaController.mostrarAlerta("Exito, revisa tus reservas para completar el proceso",
                        Alert.AlertType.INFORMATION);
                limpiarCamposReserva();
            }catch (Exception e){
                ventanaController.mostrarAlerta(e.getMessage(), Alert.AlertType.ERROR);
            }
        }
    }


}

