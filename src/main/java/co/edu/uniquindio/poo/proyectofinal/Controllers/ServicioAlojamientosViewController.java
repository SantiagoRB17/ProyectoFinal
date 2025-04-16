package co.edu.uniquindio.poo.proyectofinal.Controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import jfxtras.scene.layout.VBox;
import org.controlsfx.control.SearchableComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ServicioAlojamientosViewController implements Initializable {

    @FXML
    private JFXButton btnAgregarServicio;

    @FXML
    private JFXButton btnAgregarServicioHotel;

    @FXML
    private JFXButton btnCargarFoto;

    @FXML
    private JFXButton btnCargarFotoHotel;

    @FXML
    private JFXButton btnCrearAlojamiento;

    @FXML
    private JFXButton btnCrearHotel;

    @FXML
    private JFXButton btnCrearOferta;

    @FXML
    private JFXButton btnEditarAlojamiento;

    @FXML
    private JFXButton btnEditarHabitacion;

    @FXML
    private JFXButton btnEditarHotel;

    @FXML
    private JFXButton btnEditarOferta;

    @FXML
    private JFXButton btnEliminarAlojamiento;

    @FXML
    private JFXButton btnEliminarHotel;

    @FXML
    private JFXButton btnEliminarOferta;

    @FXML
    private JFXButton btnEliminarServicio;

    @FXML
    private JFXButton btnEliminarServicioHotel;

    @FXML
    private JFXButton btnLimpiarCampos;

    @FXML
    private JFXButton btnLimpiarCamposHotel;

    @FXML
    private JFXButton btnLimpiarOferta;

    @FXML
    private JFXButton btnRefrescar;

    @FXML
    private JFXButton btnRefrescartablaAlojamientosEnOfertas;

    @FXML
    private JFXButton btnRefrescartablaHabitacion;

    @FXML
    private JFXButton btnRefrescartablaHotel;

    @FXML
    private JFXButton btnRefrescartablaOfertas;

    @FXML
    private CheckBox chkBoxDisponible;

    @FXML
    private CheckBox chkBoxDisponibleHotel;

    @FXML
    private TableColumn<?, ?> clCantidadHuespedes;

    @FXML
    private TableColumn<?, ?> clCantidadHuespedesAlojamientoEnOfertas;

    @FXML
    private TableColumn<?, ?> clCantidadHuespedesHabitacionHotel;

    @FXML
    private TableColumn<?, ?> clCiudad;

    @FXML
    private TableColumn<?, ?> clCiudadAlojamientoEnOfertas;

    @FXML
    private TableColumn<?, ?> clCiudadHotel;

    @FXML
    private TableColumn<?, ?> clDescuento;

    @FXML
    private TableColumn<?, ?> clDisponibleAlojamiento;

    @FXML
    private TableColumn<?, ?> clDisponibleAlojamientoAlojamientoEnOfertas;

    @FXML
    private TableColumn<?, ?> clDisponibleHabitacionHotel;

    @FXML
    private TableColumn<?, ?> clDisponibleHotel;

    @FXML
    private TableColumn<?, ?> clIDAlojameinto;

    @FXML
    private TableColumn<?, ?> clInicioDescuento;

    @FXML
    private TableColumn<?, ?> clNombre;

    @FXML
    private TableColumn<?, ?> clNombreAlojamientoEnOfertas;

    @FXML
    private TableColumn<?, ?> clNombreHotel;

    @FXML
    private TableColumn<?, ?> clNombreOferta;

    @FXML
    private TableColumn<?, ?> clNumeroHabitacionHotel;

    @FXML
    private TableColumn<?, ?> clNumeroHabitaciones;

    @FXML
    private TableColumn<?, ?> clNumeroServicios;

    @FXML
    private TableColumn<?, ?> clNumeroServiciosAlojamientoEnOfertas;

    @FXML
    private TableColumn<?, ?> clNumeroServiciosHotel;

    @FXML
    private TableColumn<?, ?> clPrecio;

    @FXML
    private TableColumn<?, ?> clPrecioAlojamientoEnOfertas;

    @FXML
    private TableColumn<?, ?> clPrecioHabitacionHotel;

    @FXML
    private TableColumn<?, ?> clPrecioHotel;

    @FXML
    private TableColumn<?, ?> clfinDescuento;

    @FXML
    private ComboBox<?> cmbBoxFiltroOpciones;

    @FXML
    private ComboBox<?> cmbBoxFiltroOpcionesAlojamientosEnOfertas;

    @FXML
    private ComboBox<?> cmbBoxFiltroOpcionesHabitaciones;

    @FXML
    private ComboBox<?> cmbBoxFiltroOpcionesHotel;

    @FXML
    private ComboBox<?> cmbBoxFiltroOpcionesOfertas;

    @FXML
    private SearchableComboBox<?> cmbBoxListaServicios;

    @FXML
    private SearchableComboBox<?> cmbBoxListaServiciosHotel;

    @FXML
    private DatePicker datePickerFinOferta;

    @FXML
    private DatePicker datePickerInicioOferta;

    @FXML
    private GridPane gridPaneFormulario;

    @FXML
    private GridPane gridPaneFormularioHoteles;

    @FXML
    private GridPane gridPaneFormularioOfertas;

    @FXML
    private HBox hboxContenedorFiltros;

    @FXML
    private HBox hboxContenedorFiltrosAlojamientos;

    @FXML
    private HBox hboxContenedorFiltrosAlojamientosEnOfertas;

    @FXML
    private HBox hboxContenedorFiltrosHabitaciones;

    @FXML
    private HBox hboxContenedorFiltrosHotel;

    @FXML
    private HBox hboxContenedorPrincipal;

    @FXML
    private ImageView imgViewFotoAlojamiento;

    @FXML
    private ImageView imgViewFotoAlojamientoHotel;

    @FXML
    private MenuButton menButtonOpciones;

    @FXML
    private MenuButton menButtonOpcionesHotel;

    @FXML
    private MenuButton menButtonOpcionesOferta;

    @FXML
    private MenuItem menItemCerrarSesion;

    @FXML
    private MenuItem menItemCerrarSesionHotel;

    @FXML
    private MenuItem menItemCerrarSesionOferta;

    @FXML
    private MenuItem menItemGuardar;

    @FXML
    private MenuItem menItemGuardarHotel;

    @FXML
    private MenuItem menItemGuardarOferta;

    @FXML
    private TableView<?> tbAlojamientos;

    @FXML
    private TableView<?> tbAlojamientosEnOfertas;

    @FXML
    private TableView<?> tbOfertas;

    @FXML
    private TextArea txtAreaDescripcion;

    @FXML
    private TextArea txtAreaDescripcionHotel;

    @FXML
    private TextArea txtAreaDescripcionOferta;

    @FXML
    private TextField txtFieldCantidadHuespedes;

    @FXML
    private TextField txtFieldCantidadHuespedesHotel;

    @FXML
    private TextField txtFieldCiudad;

    @FXML
    private TextField txtFieldCiudadHotel;

    @FXML
    private TextField txtFieldDescuento;

    @FXML
    private TextField txtFieldFiltro;

    @FXML
    private TextField txtFieldFiltroAlojamientosEnOfertas;

    @FXML
    private TextField txtFieldFiltroHabitaciones;

    @FXML
    private TextField txtFieldFiltroHotel;

    @FXML
    private TextField txtFieldFiltroOfertas;

    @FXML
    private TextField txtFieldNombre;

    @FXML
    private TextField txtFieldNombreHotel;

    @FXML
    private TextField txtFieldPrecio;

    @FXML
    private TextField txtFieldPrecioHotel;

    @FXML
    private TextField txtServicio;

    @FXML
    private TextField txtServicioHotel;

    @FXML
    private VBox vboxContenedorFormulario;

    @FXML
    private VBox vboxContenedorFormularioHoteles;

    @FXML
    private VBox vboxContenedorServicios;

    @FXML
    private VBox vboxContenedorTablaFiltro;

    @FXML
    void agregarServicio(ActionEvent event) {

    }

    @FXML
    void agregarServicioHotel(ActionEvent event) {

    }

    @FXML
    void cargarFoto(ActionEvent event) {

    }

    @FXML
    void cargarFotoHotel(ActionEvent event) {

    }

    @FXML
    void cerrarSesion(ActionEvent event) {

    }

    @FXML
    void crearAlojamiento(ActionEvent event) {

    }

    @FXML
    void crearHotel(ActionEvent event) {

    }

    @FXML
    void crearOferta(ActionEvent event) {

    }

    @FXML
    void editarAlojamiento(ActionEvent event) {

    }

    @FXML
    void editarHotel(ActionEvent event) {

    }

    @FXML
    void editarOferta(ActionEvent event) {

    }

    @FXML
    void eliminarAlojamiento(ActionEvent event) {

    }

    @FXML
    void eliminarHotel(ActionEvent event) {

    }

    @FXML
    void eliminarOferta(ActionEvent event) {

    }

    @FXML
    void eliminarServicio(ActionEvent event) {

    }

    @FXML
    void guardarCambios(ActionEvent event) {

    }

    @FXML
    void limpiarCampos(ActionEvent event) {

    }

    @FXML
    void limpiarCamposHotel(ActionEvent event) {

    }

    @FXML
    void limpiarCamposOferta(ActionEvent event) {

    }

    @FXML
    void refrescarTabla(ActionEvent event) {

    }

    @FXML
    void refrescarTablaAlojamientosEnOfertas(ActionEvent event) {

    }

    @FXML
    void refrescarTablaHabitacion(ActionEvent event) {

    }

    @FXML
    void refrescarTablaHotel(ActionEvent event) {

    }

    @FXML
    void refrescarTablaOfertas(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void abrirEditarHabitacion(ActionEvent event){
        try {

            // Cargar la vista
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/servicioEditarHabitacionView.fxml"));
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

