package co.edu.uniquindio.poo.proyectofinal.Controllers;

import co.edu.uniquindio.poo.proyectofinal.Model.AlojamientoDecorator.Oferta;
import co.edu.uniquindio.poo.proyectofinal.Model.AlojamientosFactory.Alojamiento;
import co.edu.uniquindio.poo.proyectofinal.Model.entidades.ProductoApartamento;
import co.edu.uniquindio.poo.proyectofinal.Model.entidades.ProductoCasa;
import co.edu.uniquindio.poo.proyectofinal.Model.entidades.ProductoHotel;
import co.edu.uniquindio.poo.proyectofinal.Model.entidades.Sesion;
import co.edu.uniquindio.poo.proyectofinal.Observers.AlojamientosObserver;
import com.jfoenix.controls.JFXButton;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class OfertaViewController implements Initializable, AlojamientosObserver {

    @FXML
    private JFXButton btnCrearOferta;

    @FXML
    private JFXButton btnEditarOferta;

    @FXML
    private TextArea txtAreaDescripcionOferta;

    @FXML
    private JFXButton btnEliminarOferta;

    @FXML
    private JFXButton btnLimpiarOferta;


    @FXML
    private TableColumn<Alojamiento, Integer> clCantidadHuespedesAlojamientoEnOfertas;

    @FXML
    private TableColumn<Alojamiento, String> clCiudadAlojamientoEnOfertas;

    @FXML
    private TableColumn<Oferta, Double> clDescuento;

    @FXML
    private TableColumn<Oferta, LocalDate> clInicioDescuento;

    @FXML
    private TableColumn<Alojamiento, String> clNombreAlojamientoEnOfertas;

    @FXML
    private TableColumn<Oferta, String> clNombreOferta;

    @FXML
    private TableColumn<Alojamiento, Integer> clNumeroServiciosAlojamientoEnOfertas;

    @FXML
    private TableColumn<Alojamiento, Double> clPrecioAlojamientoEnOfertas;

    @FXML
    private TableColumn<Oferta, LocalDate> clfinDescuento;


    @FXML
    private DatePicker datePickerFinOferta;

    @FXML
    private DatePicker datePickerInicioOferta;

    @FXML
    private GridPane gridPaneFormularioOfertas;


    @FXML
    private MenuButton menButtonOpcionesOferta;

    @FXML
    private MenuItem menItemCerrarSesionOferta;


    @FXML
    private TableView<Alojamiento> tbAlojamientosEnOfertas;

    @FXML
    private TableView<Oferta> tbOfertas;


    @FXML
    private TextField txtFieldDescuento;


    @FXML
    void editarOferta(ActionEvent event) {

    }


    private final VentanaController ventanasController= VentanaController.getInstancia();
    private Oferta ofertaSeleccionado;
    private Alojamiento alojamientoSeleccionado;
    private final Sesion sesion= Sesion.getInstancia();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ventanasController.getPlataforma().registrarObservador(this);
        //Configuracion de columnas de la tabla de ofertas
        clNombreOferta.setCellValueFactory(cellData ->
                {
                    try {
                        return new SimpleStringProperty(ventanasController.getPlataforma().buscarAlojamientoPorId(cellData.getValue().getIdAlojamiento()).getNombre());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                }
        );
        clDescuento.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getPorcentajeDescuento()));
        clInicioDescuento.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getFechaInicio()));
        clfinDescuento.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getFechaFin()));
        //Configuracion de columnas de la tabla de alojamientos en ofertas
        clNombreAlojamientoEnOfertas.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        clCiudadAlojamientoEnOfertas.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCiudad()));
        clPrecioAlojamientoEnOfertas.setCellValueFactory(cellData -> {
            Alojamiento alojamiento = cellData.getValue();
            double precio = 0;
            if (alojamiento instanceof ProductoCasa) {
                ProductoCasa productoCasa = (ProductoCasa) alojamiento;
                precio = productoCasa.getPrecio();
            } else if (alojamiento instanceof ProductoApartamento) {
                ProductoApartamento productoApartamento = (ProductoApartamento) alojamiento;
                precio = productoApartamento.getPrecio();
            }else if (alojamiento instanceof ProductoHotel){
                precio=0.0;
            }
            return new SimpleObjectProperty<>(precio);
        });
        clCantidadHuespedesAlojamientoEnOfertas.setCellValueFactory(cellData ->{
            Alojamiento alojamiento = cellData.getValue();
            int cantidadHuespedes = 0;
            if (alojamiento instanceof ProductoCasa) {
                ProductoCasa productoCasa = (ProductoCasa) alojamiento;
                cantidadHuespedes = productoCasa.getCapacidadMaxima();
            } else if (alojamiento instanceof ProductoApartamento) {
                ProductoApartamento productoApartamento = (ProductoApartamento) alojamiento;
                cantidadHuespedes = productoApartamento.getCapacidadMaxima();
            }else if (alojamiento instanceof ProductoHotel){
                cantidadHuespedes=0;
            }
            return  new SimpleObjectProperty<>(cantidadHuespedes);
        });
        clNumeroServiciosAlojamientoEnOfertas.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getServicios().size()));

        // Carga de tipos de alojamiento y configuración inicial de imagen
        cargarDatosAlojamientoEnOfertas();
        cargarDatosTablaOfertas();


        tbAlojamientosEnOfertas.setOnMouseClicked(mouseEvent -> {
            alojamientoSeleccionado= tbAlojamientosEnOfertas.getSelectionModel().getSelectedItem();
        });
        tbOfertas.setOnMouseClicked(mouseEvent -> {
            ofertaSeleccionado=tbOfertas.getSelectionModel().getSelectedItem();
            if(ofertaSeleccionado!=null){
                txtFieldDescuento.setText(String.valueOf(ofertaSeleccionado.getPorcentajeDescuento()));
                txtAreaDescripcionOferta.setText(ofertaSeleccionado.getDescripcionOferta());
                datePickerInicioOferta.setValue(ofertaSeleccionado.getFechaInicio());
                datePickerFinOferta.setValue(ofertaSeleccionado.getFechaFin());
            }
        });
    }
    //Ofertas

    /**
     * Metodo que permite crear una oferta
     * @param event
     */
    public void crearOferta(ActionEvent event) {
        if (alojamientoSeleccionado == null) {
            ventanasController.mostrarAlerta("Debe seleccionar un alojamiento", Alert.AlertType.ERROR);
        }
        if (hayCamposVacios(txtFieldDescuento, txtAreaDescripcionOferta)) {
            ventanasController.mostrarAlerta("Todos los campos son obligatorios", Alert.AlertType.ERROR);
        }
        try {
            ventanasController.getPlataforma().crearOferta(alojamientoSeleccionado.getId(), Double.parseDouble(txtFieldDescuento.getText()),
                    txtAreaDescripcionOferta.getText(),
                    datePickerInicioOferta.getValue(), datePickerFinOferta.getValue());
            ventanasController.mostrarAlerta("Oferta creada con exito",Alert.AlertType.INFORMATION);
            limpiarCampos();

        } catch (Exception e) {
            ventanasController.mostrarAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    /**
     * Metodo que carga los alojamientos en la tabla de alojamientos de la vista de ofertas
     */
    public void cargarDatosAlojamientoEnOfertas(){
        try{
            List<Alojamiento> alojamientos = ventanasController.getPlataforma().listarAlojamientos();
            tbAlojamientosEnOfertas.setItems(FXCollections.observableArrayList(alojamientos));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Metodo que permite eliminar una oferta
     * @param event
     */
    public void eliminarOferta(ActionEvent event){
        try {
            if (ofertaSeleccionado == null) {
                ventanasController.mostrarAlerta("Debes seleccionar una oferta", Alert.AlertType.ERROR);
            }else{
                ventanasController.getPlataforma().eliminarOferta(ofertaSeleccionado.getIdAlojamiento());
                limpiarCampos();
                ventanasController.mostrarAlerta("Oferta eliminada con exito", Alert.AlertType.INFORMATION);
            }
        }catch (Exception e){
            ventanasController.mostrarAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    /**
     * Metodo que carga las ofertas en la tabla de ofertas
     */
    public void cargarDatosTablaOfertas(){
        try{
            List<Oferta> ofertas = ventanasController.getPlataforma().listarOfertas();
            tbOfertas.setItems(FXCollections.observableArrayList(ofertas));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Limpia los campos del formulario de ofertas
     */
    public void limpiarCampos() {
        txtFieldDescuento.clear();
        txtAreaDescripcionOferta.clear();
        datePickerInicioOferta.setValue(null);
        datePickerFinOferta.setValue(null);
        alojamientoSeleccionado = null;
    }

    /**
     * Metodo que valida si los campos estan vacios en el formulario.
     * @param campos campos del formulario.
     * @return true si hay campos vacios, false en caso contrario.
     */
    private boolean hayCamposVacios(TextInputControl... campos) {
        for (TextInputControl campo : campos) {
            if (campo.getText() == null || campo.getText().trim().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Metodo que permite limpiar los campos del formulario de ofertas.
     * @param event
     */
    public void limpiarCamposOferta(ActionEvent event){
        limpiarCampos();
    }

    @Override
    public void actualizar() {
        cargarDatosAlojamientoEnOfertas();
        cargarDatosTablaOfertas();
    }

    /**
     * Metodo que permite cerrar la sesion del administrador actual.
     * @param event
     */
    public void cerrarSesion(ActionEvent event) {
        sesion.cerrarSesion();
        try{
            ventanasController.navegarVentanas("/InicioView.fxml","Inicio",true,true);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

