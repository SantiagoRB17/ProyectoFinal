package co.edu.uniquindio.poo.proyectofinal.Controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.controlsfx.control.SearchableComboBox;

import co.edu.uniquindio.poo.proyectofinal.Enums.TipoAlojamiento;
import co.edu.uniquindio.poo.proyectofinal.Model.AlojamientosFactory.Alojamiento;
import co.edu.uniquindio.poo.proyectofinal.Model.ProductoApartamento;
import co.edu.uniquindio.poo.proyectofinal.Model.ProductoCasa;
import co.edu.uniquindio.poo.proyectofinal.Observers.AlojamientosObserver;
import co.edu.uniquindio.poo.proyectofinal.Repositorios.RepositorioImagenes;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class CasaApartamentoViewController implements Initializable, AlojamientosObserver {

    @FXML
    private MenuButton menButtonOpciones;
    @FXML
    private MenuItem menItemCerrarSesion;
    @FXML
    private VBox vboxContenedorFormulario;

    @FXML
    private JFXButton btnAgregarServicio;


    @FXML
    private JFXButton btnCargarFoto;


    @FXML
    private JFXButton btnCrearAlojamiento;

    @FXML
    private JFXButton btnEditarAlojamiento;


    @FXML
    private JFXButton btnEliminarAlojamiento;


    @FXML
    private JFXButton btnEliminarServicio;

    @FXML
    private JFXButton btnRefrescar;



    @FXML
    private TableColumn<Alojamiento, Integer> clCantidadHuespedes;


    @FXML
    private TableColumn<Alojamiento, String> clCiudad;


    @FXML
    private TableColumn<Alojamiento, String> clTipoAlojamiento;

    @FXML
    private TableColumn<Alojamiento, String> clNombre;

    @FXML
    private TableColumn<Alojamiento, Integer> clNumeroServicios;

    @FXML
    private TableColumn<Alojamiento, Double> clPrecio;

    @FXML
    private ComboBox<?> cmbBoxFiltroOpciones;

    @FXML
    private SearchableComboBox<String> cmbBoxListaServicios;

    @FXML
    private ComboBox<TipoAlojamiento> cmbTipoAlojamiento;

    @FXML
    private GridPane gridPaneFormulario;

    @FXML
    private HBox hboxContenedorFiltros;

    @FXML
    private HBox hboxContenedorPrincipal;

    @FXML
    private ImageView imgViewFotoAlojamiento;

    @FXML
    private ScrollPane scrollPaneContenedorFormulario;

    @FXML
    private JFXButton btnLimpiarFormulario;

    @FXML
    private TableView<Alojamiento> tbAlojamientos;


    @FXML
    private TextArea txtAreaDescripcion;


    @FXML
    private TextField txtFieldCantidadHuespedes;

    @FXML
    private TextField txtFieldCiudad;

    @FXML
    private TextField txtFieldCostoExtra;


    @FXML
    private TextField txtFieldFiltro;


    @FXML
    private TextField txtFieldNombre;


    @FXML
    private TextField txtFieldPrecio;

    @FXML
    private TextField txtServicio;

    @FXML
    private Label lblTipo;

    @FXML
    private VBox vboxContenedorServicios;

    @FXML
    private VBox vboxContenedorTablaFiltro;

    private File fotoSeleccionada;


    @FXML
    void cerrarSesion(ActionEvent event) {

    }


    @FXML
    void refrescarTabla(ActionEvent event) {

    }

    private final Image imagenAlojamientoPorDefecto=new Image(Objects.requireNonNull(getClass()
            .getResourceAsStream("/imagenes/imagenAlojamientoPorDefecto.png")));
    private ObservableList<String> serviciosDisponibles;
    private final VentanaController ventanasController= VentanaController.getInstancia();
    private Alojamiento alojamientoSeleccionado;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ventanasController.getPlataforma().registrarObservador(this);
        // Configuración de columnas de la tabla de casas y apartamentos
        clTipoAlojamiento.setCellValueFactory(cellData -> {
            Alojamiento alojamiento = cellData.getValue();
            String tipo = "";

            if (alojamiento instanceof ProductoCasa) {
                tipo = "Casa";
            } else if (alojamiento instanceof ProductoApartamento) {
                tipo = "Apartamento";
            } else {
                tipo = "Desconocido";
            }

            return new SimpleStringProperty(tipo);
        });
        clNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        clCiudad.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCiudad()));
        clPrecio.setCellValueFactory(cellData-> {
            Alojamiento alojamiento=cellData.getValue();
            double precio=0;
            if(alojamiento instanceof ProductoCasa){
                ProductoCasa productoCasa=(ProductoCasa) alojamiento;
                precio=productoCasa.getPrecio();
            }else if(alojamiento instanceof ProductoApartamento) {
                ProductoApartamento productoApartamento = (ProductoApartamento) alojamiento;
                precio = productoApartamento.getPrecio();
            }
            return new SimpleObjectProperty<>(precio);
        });
        clCantidadHuespedes.setCellValueFactory(cellData -> {
            Alojamiento alojamiento = cellData.getValue();
            int cantidadHuespedes = 0;
            if(alojamiento instanceof ProductoCasa){
                ProductoCasa productoCasa = (ProductoCasa) alojamiento;
                cantidadHuespedes = productoCasa.getCapacidadMaxima();
            }else if(alojamiento instanceof ProductoApartamento){
                ProductoApartamento productoApartamento = (ProductoApartamento) alojamiento;
                cantidadHuespedes = productoApartamento.getCapacidadMaxima();
            }
            return new SimpleObjectProperty<>(cantidadHuespedes);
        });
        clNumeroServicios.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getServicios().size()));

        cmbTipoAlojamiento.setItems(FXCollections.observableList(ventanasController.getPlataforma().listarOpcionesAlojamiento()));
        imgViewFotoAlojamiento.setImage(imagenAlojamientoPorDefecto);
        serviciosDisponibles = FXCollections.observableArrayList();
        cmbBoxListaServicios.setItems(serviciosDisponibles);

        cargarDatosTabla();

        tbAlojamientos.setOnMouseClicked(mouseEvent -> {
            alojamientoSeleccionado=tbAlojamientos.getSelectionModel().getSelectedItem();
            if(alojamientoSeleccionado!=null) {
                txtFieldNombre.setText(alojamientoSeleccionado.getNombre());
                txtFieldCiudad.setText(alojamientoSeleccionado.getCiudad());
                txtAreaDescripcion.setText(alojamientoSeleccionado.getDescripcion());
                double precio = 0.0;
                if (alojamientoSeleccionado instanceof ProductoCasa casa) {
                    precio = casa.getPrecio();
                } else if (alojamientoSeleccionado instanceof ProductoApartamento apartamento) {
                    precio = apartamento.getPrecio();
                }
                txtFieldPrecio.setText(String.valueOf(precio));
                txtFieldCostoExtra.setText(String.valueOf(alojamientoSeleccionado.getCostoExtra()));
                int capacidadMaxima = 0;
                if(alojamientoSeleccionado instanceof ProductoCasa casa){
                    capacidadMaxima=casa.getCapacidadMaxima();
                }else if(alojamientoSeleccionado instanceof ProductoApartamento apartamento){
                    capacidadMaxima=apartamento.getCapacidadMaxima();
                }
                txtFieldCantidadHuespedes.setText(String.valueOf(capacidadMaxima));
                txtFieldCantidadHuespedes.setText(String.valueOf(capacidadMaxima));
                serviciosDisponibles.setAll(alojamientoSeleccionado.getServicios());
                try{
                    imgViewFotoAlojamiento.setImage(RepositorioImagenes.cargarImagen(alojamientoSeleccionado.getRutaFoto()));
                }catch(Exception e){
                    ventanasController.mostrarAlerta(e.getMessage(), Alert.AlertType.ERROR);
                }
                // Mostrar/ocultar elementos para modo edición
                lblTipo.setVisible(false);
                lblTipo.setManaged(false);
                cmbTipoAlojamiento.setVisible(false);
                cmbTipoAlojamiento.setManaged(false);
            }
        });

    }

    //Casa y Apartamentos
    /**
     * Metodo controlador para el boton de crear alojamiento
     * Crea un nuevo alojamiento con los datos ingresados en el formulario.
     * @param event
     * @throws Exception
     */
    public void crearAlojamiento(ActionEvent event) throws Exception {
        ArrayList<String> servicios = new ArrayList<>(serviciosDisponibles);
        if (cmbTipoAlojamiento.getValue() == null) {
            ventanasController.mostrarAlerta("Debes seleccionar un tipo de alojamiento", Alert.AlertType.ERROR);
        }else if (hayCamposVacios(txtFieldNombre, txtFieldCiudad, txtAreaDescripcion, txtFieldPrecio, txtFieldCantidadHuespedes, txtFieldCostoExtra)) {
            ventanasController.mostrarAlerta("Todos los campos son obligatorios", Alert.AlertType.ERROR);
        }else if (fotoSeleccionada == null) {
            ventanasController.mostrarAlerta("Debes seleccionar una imagen", Alert.AlertType.ERROR);
        } else {
            try {

                String rutaRelativa = RepositorioImagenes.guardarImagen(fotoSeleccionada);
                String rutaFotoGuardada = new File(rutaRelativa).getName();

                ventanasController.getPlataforma().agregarAlojamiento(cmbTipoAlojamiento.getValue()
                        , txtFieldNombre.getText()
                        , txtFieldCiudad.getText()
                        , txtAreaDescripcion.getText()
                        , rutaFotoGuardada
                        , Double.parseDouble(txtFieldPrecio.getText())
                        , servicios
                        , Integer.parseInt(txtFieldCantidadHuespedes.getText())
                        , Double.parseDouble(txtFieldCostoExtra.getText()));
                ventanasController.mostrarAlerta("Alojamiento creado con exito", Alert.AlertType.INFORMATION);
                limpiarCampos();
            } catch (Exception e) {
                ventanasController.mostrarAlerta(e.getMessage(), Alert.AlertType.ERROR);
            }
        }
    }

    /**
     * Metodo que carga una imagen desde el sistema de archivos y la asocia al alojamiento.
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
        Window ventana=btnCargarFoto.getScene().getWindow();

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
     * Metodo que agrega un servicio ingresado al listado de servicios del alojamiento.
     */
    public void agregarServicio() {
        String servicio = txtServicio.getText().trim();
        if (!servicio.isEmpty() && !serviciosDisponibles.contains(servicio)) {
            serviciosDisponibles.add(servicio);
            txtServicio.clear();
        }
    }

    /**
     * Metodo que elimina un servicio seleccionado del listado de servicios.
     */
    public void eliminarServicio() {
        String servicioSeleccionado = cmbBoxListaServicios.getValue();

        if (servicioSeleccionado != null) {
            serviciosDisponibles.remove(servicioSeleccionado);
        }
    }
    /**
     * Metodo que elimina el alojamiento seleccionado de la tabla y del repositorio.
     * @param event Evento del botón.
     */
    public void eliminarAlojamiento(ActionEvent event) {
        try {
            if (alojamientoSeleccionado == null) {
                ventanasController.mostrarAlerta("Debes seleccionar un alojamiento", Alert.AlertType.ERROR);
            }else{
                ventanasController.getPlataforma().eliminarAlojamiento(alojamientoSeleccionado.getId(),alojamientoSeleccionado.getRutaFoto());
                limpiarCampos();
                ventanasController.mostrarAlerta("Alojamiento eliminado con exito", Alert.AlertType.INFORMATION);
            }
        }catch (Exception e){
            ventanasController.mostrarAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    /**
     * M
     * @param event
     */
    public void editarAlojamiento(ActionEvent event) {
        try{
            ArrayList<String> serviciosActualizados = new ArrayList<>(serviciosDisponibles);

            String rutaRelativa = RepositorioImagenes.guardarImagen(fotoSeleccionada);
            String rutaFotoGuardada = new File(rutaRelativa).getName();

            String rutaFoto;
            if (fotoSeleccionada != null && !rutaFotoGuardada.equals(alojamientoSeleccionado.getRutaFoto())) {
                ventanasController.getPlataforma().eliminarImagen(alojamientoSeleccionado.getRutaFoto());
                rutaFoto = rutaFotoGuardada;
            } else {
                ventanasController.getPlataforma().eliminarImagen(alojamientoSeleccionado.getRutaFoto());
                rutaFoto = alojamientoSeleccionado.getRutaFoto();
            }
            if(alojamientoSeleccionado == null){
                ventanasController.mostrarAlerta("Seleccione un alojamiento",Alert.AlertType.ERROR);
            }else{
                ventanasController.getPlataforma().editarAlojamiento(alojamientoSeleccionado.getId()
                        , txtFieldNombre.getText()
                        , txtFieldCiudad.getText()
                        , txtAreaDescripcion.getText()
                        , rutaFoto
                        , Double.parseDouble(txtFieldPrecio.getText())
                        , serviciosActualizados
                        , Integer.parseInt(txtFieldCantidadHuespedes.getText())
                        , Double.parseDouble(txtFieldCostoExtra.getText()));
                limpiarCampos();
                ventanasController.mostrarAlerta("Alojamiento editado con exito", Alert.AlertType.INFORMATION);
            }

        }catch (Exception e){
            ventanasController.mostrarAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }
    /**
     * Limpia los campos de texto del formulario
     */
    public void limpiarCampos(){
        cmbTipoAlojamiento.setValue(null);
        txtFieldNombre.clear();
        txtFieldCiudad.clear();
        txtAreaDescripcion.clear();
        txtFieldPrecio.clear();
        txtFieldCantidadHuespedes.clear();
        txtFieldCostoExtra.clear();
        imgViewFotoAlojamiento.setImage(imagenAlojamientoPorDefecto);
        fotoSeleccionada = null;
        serviciosDisponibles.clear();
        cmbBoxListaServicios.setItems(serviciosDisponibles);
        lblTipo.setVisible(true);
        lblTipo.setManaged(true);
        cmbTipoAlojamiento.setVisible(true);
        cmbTipoAlojamiento.setManaged(true);
        alojamientoSeleccionado = null;
    }


    /**
     * Metodo que carga los datos de alojamiento en la tabla desde el repositorio.
     */
    public void cargarDatosTabla(){
        try {
            List<Alojamiento> lista = ventanasController.getPlataforma().listarAlojamientos();

            tbAlojamientos.setItems(FXCollections.observableArrayList(lista));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Método de que limpia el formulario.
     * @param event
     */
    public void limpiarFormulario(ActionEvent event){
        limpiarCampos();

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
        cargarDatosTabla();
    }
}