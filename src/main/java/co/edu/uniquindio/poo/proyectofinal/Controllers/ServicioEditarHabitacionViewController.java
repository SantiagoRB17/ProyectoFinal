package co.edu.uniquindio.poo.proyectofinal.Controllers;

import co.edu.uniquindio.poo.proyectofinal.Model.entidades.ProductoHabitacion;
import co.edu.uniquindio.poo.proyectofinal.Model.entidades.ProductoHotel;
import co.edu.uniquindio.poo.proyectofinal.Observers.AlojamientosObserver;
import co.edu.uniquindio.poo.proyectofinal.Repositorios.RepositorioImagenes;
import co.edu.uniquindio.poo.proyectofinal.Observers.HotelDataOberserver;
import com.jfoenix.controls.JFXButton;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import jfxtras.scene.layout.VBox;
import lombok.Setter;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class ServicioEditarHabitacionViewController implements HotelDataOberserver, Initializable, AlojamientosObserver {

    @FXML
    private JFXButton btnCargarFotoHabitacion;

    @FXML
    private JFXButton btnCrearHabitacion;

    @FXML
    private JFXButton btnEditarHabitacion;

    @FXML
    private JFXButton btnEliminarHabitacion;

    @FXML
    private JFXButton btnLimpiarCamposHabitacion;


    @FXML
    private TableColumn<ProductoHabitacion, Integer> clCantidadHuespedesHabitacion;

    @FXML
    private TableColumn<ProductoHabitacion, Integer> clNumeroHabitacion;

    @FXML
    private TableColumn<ProductoHabitacion, Double> clPrecioHabitacion;

    @FXML
    private GridPane gridPaneFormulario;

    @FXML
    private ImageView imgViewFotoAlojamiento;

    @FXML
    private MenuButton menButtonOpcionesEditarHabitacion;

    @FXML
    private MenuItem menItemGuardar;

    @FXML
    private TableView<ProductoHabitacion> tbHabitaciones;

    @FXML
    private TextArea txtAreaDescripcionHabitacion;

    @FXML
    private TextField txtFieldCantidadHuespedesHabitacion;

    @FXML
    private TextField txtFieldNumeroHabitacion;

    @FXML
    private TextField txtFieldPrecioHabitacion;

    @FXML
    private VBox vboxContenedorFormulario;


    private ProductoHotel hotel;
    @Setter
    private HotelViewController observer;
    private final VentanaController ventanasController= VentanaController.getInstancia();
    private ProductoHabitacion habitacionSeleccionada;
    private File fotoSeleccionada;
    private final Image imagenPorDefecto=new Image(Objects.requireNonNull(getClass()
            .getResourceAsStream("/imagenes/habitacion.png")));
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.habitaciones = new ArrayList<>();
        clNumeroHabitacion.setCellValueFactory(cellData-> new SimpleObjectProperty<>(cellData.getValue().getNumeroHabitacion()));
        clPrecioHabitacion.setCellValueFactory(cellData-> new SimpleObjectProperty<>(cellData.getValue().getPrecio()));
        clCantidadHuespedesHabitacion.setCellValueFactory(cellData->new SimpleObjectProperty<>(cellData.getValue().getCapacidad()));

        imgViewFotoAlojamiento.setImage(imagenPorDefecto);


        tbHabitaciones.setOnMouseClicked(mouseEvent -> {
            habitacionSeleccionada=tbHabitaciones.getSelectionModel().getSelectedItem();
            if(habitacionSeleccionada!=null){
                txtFieldNumeroHabitacion.setText(String.valueOf(habitacionSeleccionada.getNumeroHabitacion()));
                txtFieldPrecioHabitacion.setText(String.valueOf(habitacionSeleccionada.getPrecio()));
                txtAreaDescripcionHabitacion.setText(habitacionSeleccionada.getDescripcion());
                txtFieldCantidadHuespedesHabitacion.setText(String.valueOf(habitacionSeleccionada.getCapacidad()));
                try{
                    imgViewFotoAlojamiento.setImage(RepositorioImagenes.cargarImagen(habitacionSeleccionada.getRutaImagenHabitacion()));
                }catch(Exception e){
                    ventanasController.mostrarAlerta(e.getMessage(), Alert.AlertType.ERROR);
                }
            }
        });

    }

    /**
     * Metodo que captura los datos proporcionados por el observer
     * @param hotel
     */
    @Override
    public void actualizardatosHotel(ProductoHotel hotel) {
        this.hotel = hotel;
        this.habitaciones = new ArrayList<>(hotel.getHabitaciones());
        cargarTablaHabitaciones();
    }

    private List<ProductoHabitacion> habitaciones;

    @FXML
    void crearHabitacion(ActionEvent event) {
        if(hayCamposVacios(txtFieldNumeroHabitacion,txtFieldPrecioHabitacion,txtFieldCantidadHuespedesHabitacion,txtAreaDescripcionHabitacion)){
            ventanasController.mostrarAlerta("Todos los campos son obligatorios.", Alert.AlertType.ERROR);
        }

        try {

            String rutaRelativa = RepositorioImagenes.guardarImagen(fotoSeleccionada);
            String rutaFotoGuardada = new File(rutaRelativa).getName();

           ProductoHabitacion habitacion= ventanasController.getPlataforma().crearHabitacion(hotel, Integer.parseInt(txtFieldNumeroHabitacion.getText()),
                   Double.parseDouble(txtFieldPrecioHabitacion.getText()), Integer.parseInt(txtFieldCantidadHuespedesHabitacion.getText()),
                    rutaFotoGuardada, txtAreaDescripcionHabitacion.getText());

            habitaciones.add(habitacion);

            limpiarCampos();
            cargarTablaHabitaciones();

            ventanasController.mostrarAlerta("Habitación creada con éxito.", Alert.AlertType.INFORMATION);

            if(habitaciones.size()==hotel.getNumeroDeHabitaciones()){
                ventanasController.getPlataforma().asignarHabitaciones(habitaciones,hotel.getId());
                cargarTablaHabitaciones();
                ventanasController.mostrarAlerta("Hotel creado con exito",Alert.AlertType.INFORMATION);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ventanasController.mostrarAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    /**
     * Metodo que carga una imagen desde el sistema de archivos y la asocia al hotel.
     * @param e
     * @throws Exception
     */
    public void cargarFoto(ActionEvent e) throws Exception{
        //Creacion de la instancia de la clase file chooser
        FileChooser fc = new FileChooser();
        fc.setTitle("Cargar Imagen");
        //Creacion del fitro para solo imagenes
        FileChooser.ExtensionFilter filtro = new FileChooser.ExtensionFilter("Archivos de Imagen", "*.jpg","*.png");
        fc.getExtensionFilters().add(filtro);

        //Obtener la ventana del boton para asociarla al file chooser
        Window ventana=btnCargarFotoHabitacion.getScene().getWindow();

        File file = fc.showOpenDialog(ventana);

        if (file != null) {
            try{
                this.fotoSeleccionada = file;
                imgViewFotoAlojamiento.setImage(new Image(file.toURI().toString()));

            }catch (Exception ex){
                throw new Exception(ex.getMessage());
            }

        }
    }

    /**
     * Metodo que carga las habitaciones en la tabla de habitaciones
     */
    public void cargarTablaHabitaciones(){
        tbHabitaciones.setItems(FXCollections.observableArrayList(hotel.getHabitaciones()));
    }

    public void limpiarCamposHabitacion(ActionEvent event){
        limpiarCampos();
    }

    /**
     * Metodo que limpia los campos del formulario de habitaciones
     */
    public void limpiarCampos(){
        txtFieldNumeroHabitacion.clear();
        txtFieldPrecioHabitacion.clear();
        txtAreaDescripcionHabitacion.clear();
        txtFieldCantidadHuespedesHabitacion.clear();
        imgViewFotoAlojamiento.setImage(imagenPorDefecto);
    }

    public void eliminarHabitacion(ActionEvent event) {
        if(habitacionSeleccionada==null){
            ventanasController.mostrarAlerta("Debe seleccionar una habitacion", Alert.AlertType.ERROR);
        }
        try{
            ventanasController.getPlataforma().eliminarHabitacion(habitacionSeleccionada.getId(),habitacionSeleccionada.getRutaImagenHabitacion());
            ventanasController.mostrarAlerta("Habitacion eliminada con exito",Alert.AlertType.INFORMATION);
        } catch (Exception e) {
            ventanasController.mostrarAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public void editarHabitacion(ActionEvent event) {
        try {
            if(habitacionSeleccionada ==null){
                ventanasController.mostrarAlerta("Debe seleccionar una habitacion", Alert.AlertType.ERROR);
                return;
            }
            String rutaFoto;
            if (fotoSeleccionada != null) {
                String rutaRelativa = RepositorioImagenes.guardarImagen(fotoSeleccionada);
                String rutaFotoGuardada = new File(rutaRelativa).getName();
                if (!rutaFotoGuardada.equals(habitacionSeleccionada.getRutaImagenHabitacion())) {
                    ventanasController.getPlataforma().eliminarImagen(habitacionSeleccionada.getRutaImagenHabitacion());
                }
                rutaFoto = rutaFotoGuardada;
            } else {
                rutaFoto = habitacionSeleccionada.getRutaImagenHabitacion();
            }

            ventanasController.getPlataforma().editarHabitacion(habitacionSeleccionada.getId()
                    , Integer.parseInt(txtFieldNumeroHabitacion.getText())
                    , Double.parseDouble(txtFieldPrecioHabitacion.getText())
                    , Integer.parseInt(txtFieldCantidadHuespedesHabitacion.getText())
                    ,txtAreaDescripcionHabitacion.getText()
                    , rutaFoto
                    , hotel.getId());

            limpiarCampos();
            cargarTablaHabitaciones();
            ventanasController.mostrarAlerta("Habitacion editada con exito", Alert.AlertType.INFORMATION);
        } catch (Exception e) {
            ventanasController.mostrarAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }


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
        cargarTablaHabitaciones();
    }
}

