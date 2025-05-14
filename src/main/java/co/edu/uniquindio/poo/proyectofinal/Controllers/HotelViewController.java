package co.edu.uniquindio.poo.proyectofinal.Controllers;



import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.controlsfx.control.SearchableComboBox;

import co.edu.uniquindio.poo.proyectofinal.Model.AlojamientosFactory.Alojamiento;
import co.edu.uniquindio.poo.proyectofinal.Model.ProductoHabitacion;
import co.edu.uniquindio.poo.proyectofinal.Model.ProductoHotel;
import co.edu.uniquindio.poo.proyectofinal.Observers.AlojamientosObserver;
import co.edu.uniquindio.poo.proyectofinal.Observers.HotelDataOberserver;
import co.edu.uniquindio.poo.proyectofinal.Repositorios.RepositorioImagenes;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class HotelViewController implements AlojamientosObserver, Initializable, HotelDataOberserver {


    @FXML
    private JFXButton btnAgregarServicioHotel;

    @FXML
    private JFXButton btnCargarFotoHotel;


    @FXML
    private JFXButton btnCrearHotel;


    @FXML
    private JFXButton btnEditarHabitacion;

    @FXML
    private JFXButton btnEditarHotel;


    @FXML
    private JFXButton btnEliminarHotel;


    @FXML
    private JFXButton btnEliminarServicioHotel;


    @FXML
    private JFXButton btnLimpiarCamposHotel;


    @FXML
    private JFXButton btnRefrescartablaHabitacion;

    @FXML
    private JFXButton btnRefrescartablaHotel;


    @FXML
    private TableColumn<ProductoHabitacion, Integer> clCantidadHuespedesHabitacionHotel;



    @FXML
    private TableColumn<Alojamiento, String> clCiudadHotel;


    @FXML
    private TableColumn<Alojamiento, String> clNombreHotel;


    @FXML
    private TableColumn<ProductoHabitacion, Integer> clNumeroHabitacionHotel;

    @FXML
    private TableColumn<ProductoHotel, Integer> clNumeroHabitaciones;


    @FXML
    private TableColumn<Alojamiento, Integer> clNumeroServiciosHotel;


    @FXML
    private TableColumn<ProductoHabitacion, Double> clPrecioHabitacionHotel;


    @FXML
    private ComboBox<?> cmbBoxFiltroOpcionesHabitaciones;

    @FXML
    private ComboBox<?> cmbBoxFiltroOpcionesHotel;


    @FXML
    private SearchableComboBox<String> cmbBoxListaServiciosHotel;


    @FXML
    private GridPane gridPaneFormularioHoteles;


    @FXML
    private HBox hboxContenedorFiltrosHabitaciones;

    @FXML
    private HBox hboxContenedorFiltrosHotel;


    @FXML
    private ImageView imgViewFotoAlojamientoHotel;


    @FXML
    private MenuButton menButtonOpcionesHotel;


    @FXML
    private MenuItem menItemCerrarSesionHotel;


    @FXML
    private MenuItem menItemGuardarHotel;


    @FXML
    private TableView<ProductoHabitacion> tbHabitaciones;

    @FXML
    private TableView<Alojamiento> tbHoteles;


    @FXML
    private TextArea txtAreaDescripcionHotel;


    @FXML
    private TextField txtFieldCiudadHotel;


    @FXML
    private TextField txtFieldNumeroHabitaciones;


    @FXML
    private TextField txtFieldFiltroHabitaciones;

    @FXML
    private TextField txtFieldFiltroHotel;


    @FXML
    private TextField txtFieldNombreHotel;


    @FXML
    private TextField txtServicioHotel;


    @FXML
    private VBox vboxContenedorFormularioHoteles;


    private File fotoSeleccionada;


    @FXML
    void cerrarSesion(ActionEvent event) {

    }

    @FXML
    void editarHotel(ActionEvent event) {

    }


    @FXML
    void guardarCambios(ActionEvent event) {

    }



    @FXML
    void refrescarTablaHabitacion(ActionEvent event) {

    }

    @FXML
    void refrescarTablaHotel(ActionEvent event) {

    }


    private final Image imagenAlojamientoPorDefecto=new Image(Objects.requireNonNull(getClass()
            .getResourceAsStream("/imagenes/imagenAlojamientoPorDefecto.png")));
    private ObservableList<String> serviciosDisponiblesHotel;
    private final VentanaController ventanasController= VentanaController.getInstancia();
    private Alojamiento alojamientoSeleccionado;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ventanasController.getPlataforma().registrarObservador(this);
        //Configuracion de columnas de la tabla de hoteles
        clNombreHotel.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        clCiudadHotel.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCiudad()));
        clNumeroServiciosHotel.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getServicios().size()));
        clNumeroHabitaciones.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getNumeroDeHabitaciones()));
        //Configuracion de columnas de la tabla de habitaciones
        clNumeroHabitacionHotel.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getNumeroHabitacion()));
        clPrecioHabitacionHotel.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getPrecio()));
        clCantidadHuespedesHabitacionHotel.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCapacidad()));

        imgViewFotoAlojamientoHotel.setImage(imagenAlojamientoPorDefecto);
        serviciosDisponiblesHotel = FXCollections.observableArrayList();
        cmbBoxListaServiciosHotel.setItems(serviciosDisponiblesHotel);

        cargarDatosTablaHotel();

        //Evento de clic sobre la tabla de hoteles
        tbHoteles.setOnMouseClicked(mouseEvent -> {
            alojamientoSeleccionado=tbHoteles.getSelectionModel().getSelectedItem();
            if(alojamientoSeleccionado!=null){
                txtFieldNombreHotel.setText(alojamientoSeleccionado.getNombre());
                txtFieldCiudadHotel.setText(alojamientoSeleccionado.getCiudad());
                txtAreaDescripcionHotel.setText(alojamientoSeleccionado.getDescripcion());
                serviciosDisponiblesHotel.setAll(alojamientoSeleccionado.getServicios());
                try{
                    imgViewFotoAlojamientoHotel.setImage(RepositorioImagenes.cargarImagen(alojamientoSeleccionado.getRutaFoto()));
                }catch(Exception e){
                    ventanasController.mostrarAlerta(e.getMessage(), Alert.AlertType.ERROR);
                }
                cargarDatosTablaHabitaciones();
            }
        });



    }


    //Hoteles y Habitaciones
    
    /**
     * Metodo que permite crear un hotel
     * @param event
     * @throws Exception
     */
    public void crearHotel(ActionEvent event) throws Exception {
        ArrayList<String> servicios = new ArrayList<>(serviciosDisponiblesHotel);
        if(hayCamposVacios(txtFieldNombreHotel,txtFieldCiudadHotel,txtAreaDescripcionHotel,txtFieldNumeroHabitaciones)){
            ventanasController.mostrarAlerta("Todos los campos son obligatorios", Alert.AlertType.ERROR);
        }else if(fotoSeleccionada==null){
            ventanasController.mostrarAlerta("Debes seleccionar una imagen", Alert.AlertType.ERROR);

        }else {
            try {

                String rutaRelativa = RepositorioImagenes.guardarImagen(fotoSeleccionada);
                String rutaFotoGuardada = new File(rutaRelativa).getName();

                ProductoHotel nuevoHotel=(ProductoHotel) ventanasController.getPlataforma().agregarHotel(txtFieldNombreHotel.getText(), txtFieldCiudadHotel.getText(),
                        txtAreaDescripcionHotel.getText(), rutaFotoGuardada, servicios,
                        Integer.parseInt(txtFieldNumeroHabitaciones.getText()));

                actualizardatosHotel(nuevoHotel);
                abrirEditarHabitacion(event);

            } catch (Exception e) {
                e.printStackTrace();
                ventanasController.mostrarAlerta(e.getMessage(), Alert.AlertType.ERROR);
            }
        }
    }

    @Override
    public void actualizardatosHotel(ProductoHotel productoHotel) {
        System.out.println(alojamientoSeleccionado);
        if(alojamientoSeleccionado==null){
            this.alojamientoSeleccionado = productoHotel;
        }
    }

    /**
     * Metodo que agrega un servicio ingresado al listado de servicios del alojamiento.
     */
    public void agregarServicioHotel(ActionEvent event) {
        String servicio = txtServicioHotel.getText().trim();
        if (!servicio.isEmpty() && !serviciosDisponiblesHotel.contains(servicio)) {
            serviciosDisponiblesHotel.add(servicio);
            txtServicioHotel.clear();
        }
    }

    /**
     * Metodo que elimina un servicio seleccionado del listado de servicios.
     */
    public void eliminarServicioHotel(ActionEvent event) {
        String servicioSeleccionado = cmbBoxListaServiciosHotel.getValue();

        if (servicioSeleccionado != null) {
            serviciosDisponiblesHotel.remove(servicioSeleccionado);
        }
    }

    /**
     * Metodo que limpia los campos del formulario de hotel
     * @param event
     */
    public void limpiarCamposHotel(ActionEvent event){
        limpiarCamposHotel();
    }

    /**
     * Metodo que carga una imagen desde el sistema de archivos y la asocia al hotel.
     * @param e
     * @throws Exception
     */
    public void cargarFotoHotel(ActionEvent e) throws Exception{
        //Creacion de la instancia de la clase file chooser
        FileChooser fc = new FileChooser();
        fc.setTitle("Cargar Imagen");
        //Creacion del fitro para solo imagenes
        FileChooser.ExtensionFilter filtro = new FileChooser.ExtensionFilter("Archivos de Imagen", "*.jpg","*.png");
        fc.getExtensionFilters().add(filtro);

        //Obtener la ventana del boton para asociarla al file chooser
        Window ventana=btnCargarFotoHotel.getScene().getWindow();

        File file = fc.showOpenDialog(ventana);

        if (file != null) {
            try{
                this.fotoSeleccionada = file;
                imgViewFotoAlojamientoHotel.setImage(new Image(file.toURI().toString()));

            }catch (Exception ex){
                throw new Exception(ex.getMessage());
            }

        }
    }

    /**
     * Metodo que carga los datos de los hoteles en la tabla desde el repositorio.
     */
    public void cargarDatosTablaHotel(){
        try {
            List<Alojamiento> lista = ventanasController.getPlataforma().listarHoteles();

            List<Alojamiento> listaActivos = lista.stream()
                    .filter(Alojamiento::isActivo)
                    .collect(Collectors.toList());
            tbHoteles.setItems(FXCollections.observableArrayList(listaActivos));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Metodo que carga los datos de las habitaciones en la tabla desde el repositorio.
     */
    public void cargarDatosTablaHabitaciones(){
        try{
            ProductoHotel hotel = (ProductoHotel) ventanasController.getPlataforma()
                    .buscarAlojamientoPorId(alojamientoSeleccionado.getId());

            List<ProductoHabitacion> listaHabitacionesActivas = hotel.getHabitaciones().stream()
                    .filter(ProductoHabitacion::isActivo)
                    .collect(Collectors.toList());
            tbHabitaciones.setItems(FXCollections.observableArrayList(listaHabitacionesActivas));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Metodo que abre una nueva ventana para la edición de habitaciónes.
     */
    public void abrirEditarHabitacion(ActionEvent e){
        try {
            if(alojamientoSeleccionado==null || !(alojamientoSeleccionado instanceof ProductoHotel)){
                ventanasController.mostrarAlerta("Debes seleccionar un hotel", Alert.AlertType.ERROR);
            }else {
                ProductoHotel productoHotel = (ProductoHotel) alojamientoSeleccionado;

                // Cargar la vista
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/servicioEditarHabitacionView.fxml"));
                Parent root = loader.load();

                ServicioEditarHabitacionViewController controller = loader.getController();
                controller.actualizardatosHotel(productoHotel);
                controller.setObserver(this);

                // Crear la escena
                Scene scene = new Scene(root);

                // Crear un nuevo escenario (ventana)
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setResizable(true);
                stage.setTitle("Editar Habitación");

                stage.setOnCloseRequest(event -> {
                    // Validar si el número de habitaciones creadas es menor al número total requerido
                    if (productoHotel.getHabitaciones().size() < productoHotel.getNumeroDeHabitaciones()) {
                        ventanasController.mostrarAlerta(
                                "Debe completar la creación de todas las habitaciones antes de cerrar esta ventana."
                                , Alert.AlertType.WARNING);
                        event.consume();
                    }else{
                        ventanasController.getPlataforma().notificarObservadores();
                    }
                });
                // Mostrar la nueva ventana
                stage.showAndWait();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Controlador para evento de eliminar hotel
     * @param event
     */
    public void eliminarHotel(ActionEvent event){
        try {
            if (alojamientoSeleccionado == null) {
                ventanasController.mostrarAlerta("Debes seleccionar un Hotel", Alert.AlertType.ERROR);
            }else{
                ventanasController.getPlataforma().eliminarHotel(alojamientoSeleccionado.getId(),alojamientoSeleccionado.getRutaFoto());
                limpiarCamposHotel();
                ventanasController.mostrarAlerta("Hotel eliminado con exito", Alert.AlertType.INFORMATION);
            }
        }catch (Exception e){
            ventanasController.mostrarAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    /**
     * Método que limpia todos los campos del formulario para crear hoteles.
     */
    public void limpiarCamposHotel(){
        txtFieldNombreHotel.clear();
        txtFieldCiudadHotel.clear();
        txtAreaDescripcionHotel.clear();
        txtFieldNumeroHabitaciones.clear();
        cmbBoxListaServiciosHotel.getSelectionModel().clearSelection();
        cmbBoxListaServiciosHotel.getItems().clear();
        serviciosDisponiblesHotel.clear();
        cmbBoxListaServiciosHotel.setItems(serviciosDisponiblesHotel);
        imgViewFotoAlojamientoHotel.setImage(imagenAlojamientoPorDefecto);
        if (tbHabitaciones.getItems() != null && !tbHabitaciones.getItems().isEmpty()) {
            tbHabitaciones.getItems().clear();
        }
        fotoSeleccionada = null;
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


    @Override
    public void actualizar() {
        cargarDatosTablaHotel();

        if(alojamientoSeleccionado instanceof ProductoHotel){
            cargarDatosTablaHabitaciones();
        }
    }

}
