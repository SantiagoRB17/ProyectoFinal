package co.edu.uniquindio.poo.proyectofinal.Controllers;

import co.edu.uniquindio.poo.proyectofinal.Model.AlojamientosFactory.Alojamiento;
import co.edu.uniquindio.poo.proyectofinal.Model.entidades.*;
import co.edu.uniquindio.poo.proyectofinal.Observers.AlojamientosObserver;
import com.jfoenix.controls.JFXButton;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import static java.time.temporal.ChronoUnit.DAYS;

public class usuarioReservasController implements Initializable, AlojamientosObserver {

    @FXML
    private JFXButton btnCancelarReserva;

    @FXML
    private JFXButton btnPagar;

    @FXML
    private JFXButton btnResenarReserva;

    @FXML
    private JFXButton btnVolverAlInicio;

    @FXML
    private TableColumn<Reserva, String> clAlojamiento;

    @FXML
    private TableColumn<Reserva, String> clEstado;

    @FXML
    private TableColumn<Reserva, String> clFechaEntrada;

    @FXML
    private TableColumn<Reserva, String> clFechaSalida;

    @FXML
    private TableColumn<Reserva, Double> clPrecio;

    @FXML
    private Label lblEsloganReservas;

    @FXML
    private Label lblReservas;

    @FXML
    private TableView<Reserva> tbReservas;

    @FXML
    void resenarReserva(ActionEvent event) {

    }

    VentanaController ventanaController=VentanaController.getInstancia();
    Sesion sesion=Sesion.getInstancia();
    Reserva reservaSeleccinada;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ventanaController.getPlataforma().registrarObservador(this);
        clAlojamiento.setCellValueFactory(cellData-> {
            Reserva reserva = cellData.getValue();
            if(reserva.getIdHabitacion() != null) {
                ProductoHabitacion habitacion = ventanaController.getPlataforma().obtenerHabitacionPorId(reserva.getIdHabitacion());
                Alojamiento alojamiento=ventanaController.getPlataforma().buscarAlojamientoPorId(habitacion.getIdHotel());
                return new SimpleStringProperty("Hotel " +alojamiento.getNombre() + " Habitación " + habitacion.getNumeroHabitacion());
            } else {
                Alojamiento alojamiento = ventanaController.getPlataforma().buscarAlojamientoPorId(reserva.getIdAlojamiento());
                return new SimpleStringProperty(alojamiento.getNombre());
            }
        });

        clEstado.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getEstado().toString()));
        clFechaEntrada.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getFechaInicio().toString()));
        clFechaSalida.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFechaFin().toString()));
        clPrecio.setCellValueFactory(cellData-> {
            Reserva reserva = cellData.getValue();
            double precio;
            if(reserva.getIdHabitacion() != null) {
                ProductoHabitacion habitacion = ventanaController.getPlataforma().obtenerHabitacionPorId(reserva.getIdHabitacion());
                precio = habitacion.getPrecio() * (int) DAYS.between(reserva.getFechaInicio(), reserva.getFechaFin());
            }else{
                Alojamiento alojamiento= ventanaController.getPlataforma().buscarAlojamientoPorId(reserva.getIdAlojamiento());
                precio= alojamiento.calcularCosto() * (int) DAYS.between(reserva.getFechaInicio(), reserva.getFechaFin());
            }
            return new SimpleObjectProperty<>(precio);
        });

        cargarDatosTabla();

        tbReservas.setOnMouseClicked(mouseEvent -> reservaSeleccinada=tbReservas.getSelectionModel().getSelectedItem());

    }

    public void cargarDatosTabla(){
        try{
            List<Reserva> reservas= ventanaController.getPlataforma().recuperarReservasUsuario(sesion.getPersona().getCedula());
            tbReservas.setItems(FXCollections.observableArrayList(reservas));
            tbReservas.refresh();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void cancelarReserva(ActionEvent event) {
        if (reservaSeleccinada == null) {
            ventanaController.mostrarAlerta("Debe seleccionar una reserva", Alert.AlertType.ERROR);
        } else {
            try {
                ventanaController.getPlataforma().cancelarReserva(reservaSeleccinada.getIdReserva());
                ventanaController.mostrarAlerta("Reserva cancelada", Alert.AlertType.INFORMATION);
                reservaSeleccinada=null;
            } catch (Exception e) {
                ventanaController.mostrarAlerta(e.getMessage(), Alert.AlertType.ERROR);
            }
        }
    }

    public void volverAlInicio(ActionEvent event) {
        try{
            ventanaController.navegarVentanas("/InicioView.fxml","Inicio",true,true);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void pagarReserva(ActionEvent event) {
        try{
            if (reservaSeleccinada == null) {
                ventanaController.mostrarAlerta("Debe seleccionar una reserva", Alert.AlertType.ERROR);
            }
            ventanaController.getPlataforma().pagarReserva(reservaSeleccinada.getIdReserva());
            mostrarFacturaEmergente();
            reservaSeleccinada=null;
            cargarDatosTabla();
        }catch (Exception e){
            ventanaController.mostrarAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public void mostrarFacturaEmergente() {
        try {
            if (reservaSeleccinada == null) {
                throw new Exception("No hay reserva seleccionada");
            }

            Factura factura = ventanaController.getPlataforma().recuperarFactura(reservaSeleccinada);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Factura Generada");
            alert.setHeaderText("Detalle de la factura");

            String contenido = String.format(
                    "Reserva: %s\n" +
                            "Fecha de emisión: %s\n" +
                            "Precio Base: $%,.2f\n" +
                            "Total a pagar: $%,.2f",
                    reservaSeleccinada.getIdReserva(),
                    factura.getFechaEmision().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")),
                    factura.getSubtotal(),
                    factura.getTotal()
            );

            alert.setContentText(contenido);
            alert.showAndWait();

        } catch (Exception e) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setContentText("Error al mostrar factura: " + e.getMessage());
            error.showAndWait();
        }
    }


    @Override
    public void actualizar() {
        cargarDatosTabla();
    }
}
