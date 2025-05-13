package co.edu.uniquindio.poo.proyectofinal.Controllers;

import co.edu.uniquindio.poo.proyectofinal.Enums.TipoAlojamiento;
import co.edu.uniquindio.poo.proyectofinal.Model.AlojamientoDecorator.Oferta;
import co.edu.uniquindio.poo.proyectofinal.Model.AlojamientosFactory.Alojamiento;
import co.edu.uniquindio.poo.proyectofinal.Model.ProductoApartamento;
import co.edu.uniquindio.poo.proyectofinal.Model.ProductoCasa;
import co.edu.uniquindio.poo.proyectofinal.Model.ProductoHabitacion;
import co.edu.uniquindio.poo.proyectofinal.Model.ProductoHotel;
import co.edu.uniquindio.poo.proyectofinal.Observers.AlojamientosObserver;
import co.edu.uniquindio.poo.proyectofinal.Observers.HotelDataOberserver;
import co.edu.uniquindio.poo.proyectofinal.Repositorios.RepositorioImagenes;
import com.jfoenix.controls.JFXButton;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import jfxtras.scene.layout.VBox;
import org.controlsfx.control.SearchableComboBox;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ServicioAlojamientosViewController implements AlojamientosObserver, Initializable, HotelDataOberserver {

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
    private TableColumn<Alojamiento, Integer> clCantidadHuespedes;

    @FXML
    private TableColumn<Alojamiento, Integer> clCantidadHuespedesAlojamientoEnOfertas;

    @FXML
    private TableColumn<ProductoHabitacion, Integer> clCantidadHuespedesHabitacionHotel;

    @FXML
    private TableColumn<Alojamiento, String> clCiudad;

    @FXML
    private TableColumn<Alojamiento, String> clCiudadAlojamientoEnOfertas;

    @FXML
    private TableColumn<Alojamiento, String> clCiudadHotel;

    @FXML
    private TableColumn<Oferta, Double> clDescuento;

    @FXML
    private TableColumn<Alojamiento, String> clTipoAlojamiento;


    @FXML
    private TableColumn<Oferta, LocalDate> clInicioDescuento;

    @FXML
    private TableColumn<Alojamiento, String> clNombre;

    @FXML
    private TableColumn<Alojamiento, String> clNombreAlojamientoEnOfertas;

    @FXML
    private TableColumn<Alojamiento, String> clNombreHotel;

    @FXML
    private TableColumn<Oferta, String> clNombreOferta;

    @FXML
    private TableColumn<ProductoHabitacion, Integer> clNumeroHabitacionHotel;

    @FXML
    private TableColumn<ProductoHotel, Integer> clNumeroHabitaciones;

    @FXML
    private TableColumn<Alojamiento, Integer> clNumeroServicios;

    @FXML
    private TableColumn<Alojamiento, Integer> clNumeroServiciosAlojamientoEnOfertas;

    @FXML
    private TableColumn<Alojamiento, Integer> clNumeroServiciosHotel;

    @FXML
    private TableColumn<Alojamiento, Double> clPrecio;

    @FXML
    private TableColumn<Alojamiento, Double> clPrecioAlojamientoEnOfertas;

    @FXML
    private TableColumn<ProductoHabitacion, Double> clPrecioHabitacionHotel;

    @FXML
    private TableColumn<Oferta, LocalDate> clfinDescuento;

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
    private SearchableComboBox<String> cmbBoxListaServicios;

    @FXML
    private SearchableComboBox<String> cmbBoxListaServiciosHotel;

    @FXML
    private ComboBox<TipoAlojamiento> cmbTipoAlojamiento;

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
    private ScrollPane scrollPaneContenedorFormulario;

    @FXML
    private JFXButton btnLimpiarFormulario;

    @FXML
    private TableView<Alojamiento> tbAlojamientos;

    @FXML
    private TableView<ProductoHabitacion> tbHabitaciones;

    @FXML
    private TableView<Alojamiento> tbHoteles;


    @FXML
    private TableView<Alojamiento> tbAlojamientosEnOfertas;

    @FXML
    private TableView<Oferta> tbOfertas;

    @FXML
    private TextArea txtAreaDescripcion;

    @FXML
    private TextArea txtAreaDescripcionHotel;

    @FXML
    private TextArea txtAreaDescripcionOferta;

    @FXML
    private TextField txtFieldCantidadHuespedes;

    @FXML
    private TextField txtFieldCiudad;

    @FXML
    private TextField txtFieldCostoExtra;

    @FXML
    private TextField txtFieldCiudadHotel;

    @FXML
    private TextField txtFieldDescuento;

    @FXML
    private TextField txtFieldFiltro;

    @FXML
    private TextField txtFieldNumeroHabitaciones;

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
    private TextField txtServicio;

    @FXML
    private Label lblDisponible;

    @FXML
    private Label lblTipo;

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

    private File fotoSeleccionada;


    @FXML
    void cerrarSesion(ActionEvent event) {

    }

    @FXML
    void editarHotel(ActionEvent event) {

    }

    @FXML
    void editarOferta(ActionEvent event) {

    }

    @FXML
    void eliminarOferta(ActionEvent event) {

    }

    @FXML
    void guardarCambios(ActionEvent event) {

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

    private final Image imagenAlojamientoPorDefecto=new Image(Objects.requireNonNull(getClass()
            .getResourceAsStream("/imagenes/imagenAlojamientoPorDefecto.png")));
    private ObservableList<String> serviciosDisponibles;
    private ObservableList<String> serviciosDisponiblesHotel;
    private final VentanaController ventanasController= VentanaController.getInstancia();
    private Alojamiento alojamientoSeleccionado;
    private Oferta ofertaSeleccionado;
    /**
     * Inicializa el controlador cargando datos iniciales, configurando columnas de la tabla
     * y eventos asociados a la selección de un alojamiento.
     * @param url
     * @param resourceBundle
     */
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
        //Configuracion de columnas de la tabla de hoteles
        clNombreHotel.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        clCiudadHotel.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCiudad()));
        clNumeroServiciosHotel.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getServicios().size()));
        clNumeroHabitaciones.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getNumeroDeHabitaciones()));
        //Configuracion de columnas de la tabla de habitaciones
        clNumeroHabitacionHotel.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getNumeroHabitacion()));
        clPrecioHabitacionHotel.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getPrecio()));
        clCantidadHuespedesHabitacionHotel.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCapacidad()));
        //Configuracion de columnas de la tabla de ofertas
        clNombreOferta.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
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
        cmbTipoAlojamiento.setItems(FXCollections.observableList(ventanasController.getPlataforma().listarOpcionesAlojamiento()));
        imgViewFotoAlojamiento.setImage(imagenAlojamientoPorDefecto);
        imgViewFotoAlojamientoHotel.setImage(imagenAlojamientoPorDefecto);
        serviciosDisponibles = FXCollections.observableArrayList();
        serviciosDisponiblesHotel = FXCollections.observableArrayList();
        cmbBoxListaServicios.setItems(serviciosDisponibles);
        cmbBoxListaServiciosHotel.setItems(serviciosDisponiblesHotel);

        cargarDatosTabla();
        cargarDatosTablaHotel();
        cargarDatosAlojamientoEnOfertas();
        cargarDatosTablaOfertas();


        // Evento de clic sobre la tabla de apartamentos y casas
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

        tbAlojamientosEnOfertas.setOnMouseClicked(mouseEvent -> {
           alojamientoSeleccionado= tbAlojamientosEnOfertas.getSelectionModel().getSelectedItem();
        });
        tbOfertas.setOnMouseClicked(mouseEvent -> {
            ofertaSeleccionado=tbOfertas.getSelectionModel().getSelectedItem();
            if(ofertaSeleccionado!=null){
                txtFieldDescuento.setText(String.valueOf(ofertaSeleccionado.getPorcentajeDescuento()));
                txtAreaDescripcion.setText(ofertaSeleccionado.getDescripcionOferta());
                datePickerInicioOferta.setValue(ofertaSeleccionado.getFechaInicio());
                datePickerFinOferta.setValue(ofertaSeleccionado.getFechaFin());
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
             * Método que actualiza la información del hotel en la interfaz
             */
            private void cargarDatosHotel() {
        if (alojamientoSeleccionado != null && alojamientoSeleccionado instanceof ProductoHotel) {
            ProductoHotel hotel = (ProductoHotel) alojamientoSeleccionado;
            
            txtFieldNombreHotel.setText(hotel.getNombre());
            txtFieldCiudadHotel.setText(hotel.getCiudad());
            txtFieldNumeroHabitaciones.setText(String.valueOf(hotel.getNumeroDeHabitaciones()));
            txtAreaDescripcionHotel.setText(hotel.getDescripcion());
            
            // Cargar los servicios del hotel
            serviciosDisponiblesHotel.clear();
            serviciosDisponiblesHotel.addAll(hotel.getServicios());
            
            try {
                if (hotel.getRutaFoto() != null && !hotel.getRutaFoto().isEmpty()) {
                    imgViewFotoAlojamientoHotel.setImage(RepositorioImagenes.cargarImagen(hotel.getRutaFoto()));
                } else {
                    imgViewFotoAlojamientoHotel.setImage(imagenAlojamientoPorDefecto);
                }
            } catch (Exception e) {
                ventanasController.mostrarAlerta("Error al cargar la imagen del hotel: " + e.getMessage(), Alert.AlertType.ERROR);
                imgViewFotoAlojamientoHotel.setImage(imagenAlojamientoPorDefecto);
            }
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

            List<Alojamiento> listaActivos = lista.stream()
                    .filter(Alojamiento::isActivo)
                    .filter(a -> a instanceof ProductoCasa || a instanceof ProductoApartamento)
                    .collect(Collectors.toList());

            tbAlojamientos.setItems(FXCollections.observableArrayList(listaActivos));
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
                ventanasController.mostrarAlerta(e.getMessage(), Alert.AlertType.ERROR);
            }
        }
    }
    @Override
    public void actualizardatosHotel(ProductoHotel productoHotel) {
        this.alojamientoSeleccionado = productoHotel;
        cargarDatosTablaHabitaciones();
        cargarDatosHotel();
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
                    }
                });

                // Mostrar la nueva ventana
                stage.showAndWait();

                cargarDatosTablaHotel();
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
                ventanasController.mostrarAlerta("Debes seleccionar un alojamiento", Alert.AlertType.ERROR);
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
        serviciosDisponiblesHotel.clear();
        imgViewFotoAlojamientoHotel.setImage(imagenAlojamientoPorDefecto);
        tbHabitaciones.getItems().clear();
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
            ventanasController.getPlataforma().crearOferta(alojamientoSeleccionado, Double.parseDouble(txtFieldDescuento.getText()),
                    txtAreaDescripcionOferta.getText(),
                    datePickerInicioOferta.getValue(), datePickerFinOferta.getValue());
            ventanasController.mostrarAlerta("Oferta creada con exito",Alert.AlertType.INFORMATION);
            limpiarCamposOferta();

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
            List<Alojamiento> alojamientosActivos= alojamientos.stream().filter(Alojamiento::isActivo).collect(Collectors.toList());
            tbAlojamientosEnOfertas.setItems(FXCollections.observableArrayList(alojamientosActivos));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Metodo que carga las ofertas en la tabla de ofertas
     */
    public void cargarDatosTablaOfertas(){
        try{
            List<Oferta> ofertas=ventanasController.getPlataforma().listarOfertas();
            tbOfertas.setItems(FXCollections.observableArrayList(ofertas));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Limpia los campos del formulario de ofertas
     */
    public void limpiarCamposOferta() {
        txtFieldDescuento.clear();
        txtAreaDescripcionOferta.clear();
        datePickerInicioOferta.setValue(null);
        datePickerFinOferta.setValue(null);
        alojamientoSeleccionado = null;
    }

    /**
     * Metodo que actualiza los datos de las tablas
     */
    @Override
    public void actualizar() {
        cargarDatosTabla();
        cargarDatosTablaHotel();
        cargarDatosTablaOfertas();
        cargarDatosAlojamientoEnOfertas();

        if(alojamientoSeleccionado instanceof ProductoHotel){
            cargarDatosTablaHabitaciones();
        }
    }
    
}

