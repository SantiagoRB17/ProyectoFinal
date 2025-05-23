package co.edu.uniquindio.poo.proyectofinal.Controllers;

import co.edu.uniquindio.poo.proyectofinal.Enums.TipoAlojamiento;
import co.edu.uniquindio.poo.proyectofinal.Model.entidades.Oferta;
import co.edu.uniquindio.poo.proyectofinal.Model.AlojamientosFactory.Alojamiento;
import co.edu.uniquindio.poo.proyectofinal.Model.entidades.ProductoHotel;
import co.edu.uniquindio.poo.proyectofinal.Model.entidades.Sesion;
import co.edu.uniquindio.poo.proyectofinal.Observers.AlojamientosObserver;
import co.edu.uniquindio.poo.proyectofinal.Repositorios.RepositorioImagenes;
import co.edu.uniquindio.poo.proyectofinal.Utils.RangoPrecio;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import jfxtras.scene.layout.HBox;
import jfxtras.scene.layout.VBox;

import java.net.URL;
import java.util.*;

public class InicioViewcontroller implements Initializable, AlojamientosObserver {

    @FXML
    private JFXButton btnIniciarSesion;

    @FXML
    private JFXButton btnRegistro;

    @FXML
    private JFXButton btnVerMas;

    @FXML
    private ComboBox<TipoAlojamiento> cmbTipoAlojamiento;

    @FXML
    private FlowPane flowPaneVistaTarjetasAlojamiento;

    @FXML
    private HBox hboxFiltroAlojamientosFields;

    @FXML
    private Label lblBienvenidoUsuario;


    @FXML
    private HBox hboxFiltroAlojamientosLabels;

    @FXML
    private HBox hboxIniSesionRegistro;

    @FXML
    private HBox hboxSaludoIniSesionRegistro;

    @FXML
    private HBox hboxUsuarioLogeado;

    @FXML
    private HBox hboxVistaTarjetaOfertas;

    @FXML
    private MenuItem menItemBilletera;

    @FXML
    private Label lblBienvenido;

    @FXML
    private Label lblEslogan;

    @FXML
    private Label lblEslogan2;

    @FXML
    private Label lblOfertas;

    @FXML
    private MenuButton menItemOpcionesUsuario;

    @FXML
    private ScrollPane scrollPaneAlojamientos;

    @FXML
    private ScrollPane scrollPaneOfertas;

    @FXML
    private ScrollPane scrollPanePrincipal;

    @FXML
    private Separator sepSeparadorAlojamientosOfertas;

    @FXML
    private Separator sepSeparadorSaludoYAlojamientos;

    @FXML
    private ComboBox<RangoPrecio> textFieldFiltroPrecio;

    @FXML
    private TextField txtFieldFiltroCiudad;

    @FXML
    private TextField txtFieldNombre;

    @FXML
    private VBox vboxAlojamientos;

    @FXML
    private VBox vboxContenedorAlojamientosOfertas;

    @FXML
    private VBox vboxEslogans;

    @FXML
    private MenuItem menItemCerrarSesion;

    @FXML
    private VBox vboxFiltroAlojamientos;

    @FXML
    private VBox vboxPrincipal;

    private final int CANTIDAD_RANDOM = 6;
    private List<Alojamiento> alojamientosTotales;
    private final VentanaController ventanasController = VentanaController.getInstancia();
    private final Sesion sesion = Sesion.getInstancia();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ventanasController.getPlataforma().registrarObservador(this);
        cmbTipoAlojamiento.getItems().addAll(TipoAlojamiento.values());
        textFieldFiltroPrecio.setItems(FXCollections.observableArrayList(
                new RangoPrecio(null, 50000.0, "Menos de 50.000"),
                new RangoPrecio(50000.0, 100000.0, "50.000 - 100.000"),
                new RangoPrecio(100000.0, 200000.0, "100.000 - 200.000"),
                new RangoPrecio(200000.0, null, "Más de 200.000")
        ));
        if(sesion.getPersona()!=null){
            conSesion();
        }else{
            sinSesion();
        }

        alojamientosTotales = null;
        cargarAlojamientos();
        cargarOfertas();

    }

    private void conSesion(){
        hboxUsuarioLogeado.setVisible(true);
        hboxUsuarioLogeado.setManaged(true);
        lblBienvenidoUsuario.setVisible(true);
        lblBienvenidoUsuario.setManaged(true);
        lblBienvenidoUsuario.setText("Bienvenido " + sesion.getPersona().getNombre());
        hboxIniSesionRegistro.setVisible(false);
        hboxIniSesionRegistro.setManaged(false);
        menItemOpcionesUsuario.setVisible(true);
    }

    private void sinSesion(){
        hboxIniSesionRegistro.setVisible(true);
        hboxIniSesionRegistro.setManaged(true);
        hboxUsuarioLogeado.setVisible(false);
        hboxUsuarioLogeado.setManaged(false);
        lblBienvenidoUsuario.setVisible(false);
        lblBienvenidoUsuario.setManaged(false);
        menItemOpcionesUsuario.setVisible(false);
        menItemOpcionesUsuario.setManaged(false);
    }

    public void abrirVistaIniciarSesion(ActionEvent actionEvent) throws Exception {
        ventanasController.navegarVentanas("/IniciarSesion.fxml","Inicio sesión",true,false);
    }

    public void abrirVistaRegistrarse(ActionEvent actionEvent) throws Exception {
        ventanasController.navegarVentanas("/Registrarse.fxml","Registrarse",true,false);

    }

    public void verMasAlojamientos(ActionEvent actionEvent) {
        if (alojamientosTotales == null) {
            alojamientosTotales = ventanasController.getPlataforma().listarAlojamientos();
        }
        cargarAlojamientos(alojamientosTotales);
    }

    private void cargarAlojamientos() {
        if (alojamientosTotales == null) {
            alojamientosTotales = ventanasController.getPlataforma().listarAlojamientos();
        }
        List<Alojamiento> copia = new ArrayList<>(alojamientosTotales);
        Collections.shuffle(copia);
        List<Alojamiento> seleccionados = copia.stream().limit(CANTIDAD_RANDOM).toList();
        cargarAlojamientos(seleccionados);
    }

    private void cargarOfertas() {
        List<Oferta> ofertas=ventanasController.getPlataforma().listarOfertas();
        for (Oferta oferta : ofertas) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/TarjetaAlojamientoOfertaView.fxml"));


                Node nodoTarjeta = loader.load();

                Label lblNombre = (Label) nodoTarjeta.lookup("#lblNombreAlojamientoOferta");
                Label lblUbicacion = (Label) nodoTarjeta.lookup("#lblUbicacionAlojamientoOferta");
                Label lblPrecio = (Label) nodoTarjeta.lookup("#lblPrecioAlojamientoOferta");
                ImageView img = (ImageView) nodoTarjeta.lookup("#imgViewFotoAlojamientoOferta");
                Label lblDescripcionOferta = (Label) nodoTarjeta.lookup("#lblDescripcionOferta");

                lblNombre.setText("Oferta " + ventanasController.getPlataforma().buscarAlojamientoPorId(oferta.getIdAlojamiento()).getNombre() );
                lblUbicacion.setText("Ubicacion "+ventanasController.getPlataforma().buscarAlojamientoPorId(oferta.getIdAlojamiento()).getCiudad());
                lblPrecio.setText("Descuento "+ oferta.getPorcentajeDescuento());
                lblDescripcionOferta.setText("Oferta "+ oferta.getDescripcionOferta());

                img.setImage(RepositorioImagenes.cargarImagen(ventanasController.getPlataforma().buscarAlojamientoPorId(oferta.getIdAlojamiento()).getRutaFoto()));


                hboxVistaTarjetaOfertas.getChildren().add(nodoTarjeta);

                TarjetaAlojamientoOfertaViewController controller = loader.getController();
                controller.setOfertaObervable(oferta);
                controller.setAlojamientoObservable(ventanasController.getPlataforma().buscarAlojamientoPorId(oferta.getIdAlojamiento()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @FXML
    void irAEditarPerfil(ActionEvent event) {
        try{
            ventanasController.navegarVentanas("/EditarPerfil.fxml","Editar Perfil", true, true);
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    public void irAReservas(ActionEvent event) {
        try{
            ventanasController.navegarVentanas("/reservasUsuarioView.fxml","Reservas",true, true);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void irconsultarSaldo(ActionEvent actionEvent) throws Exception {
        ventanasController.navegarVentanas("/ConsultarSaldo.fxml","Consultar Saldo",true, true);
    }
    @FXML
    void cerrarSesion(ActionEvent event) {
        sesion.cerrarSesion();
        sinSesion();
    }

    @Override
    public void actualizar() {
        cargarAlojamientos();
        cargarOfertas();
    }

    public void aplicarFiltro(ActionEvent event) {
        List<Alojamiento> alojamientosFiltrados=ventanasController.getPlataforma().filtrarAlojamientos(
                txtFieldFiltroCiudad.getText(),txtFieldNombre.getText(),cmbTipoAlojamiento.getValue(),textFieldFiltroPrecio.getValue()
        );
        cargarAlojamientos(alojamientosFiltrados);
    }

    private void cargarAlojamientos(List<Alojamiento> alojamientos) {
        flowPaneVistaTarjetasAlojamiento.getChildren().clear();
        for (Alojamiento alojamiento : alojamientos) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/TarjetaAlojamientoView.fxml"));
                Node nodoTarjeta = loader.load();
                TarjetaAlojamientoViewController controller = loader.getController();
                controller.setAlojamientoObservable(alojamiento);

                Label lblNombre = (Label) nodoTarjeta.lookup("#lblNombreAlojamiento");
                Label lblUbicacion = (Label) nodoTarjeta.lookup("#lblUbicacionAlojamiento");
                Label lblPrecio = (Label) nodoTarjeta.lookup("#lblPrecioAlojamiento");
                ImageView img = (ImageView) nodoTarjeta.lookup("#imgViewFotoAlojamiento");

                lblNombre.setText("Alojamiento " + (alojamiento.getNombre()));
                lblUbicacion.setText("Ubicacion " + (alojamiento.getCiudad()));
                if(alojamiento instanceof ProductoHotel){
                    lblPrecio.visibleProperty().setValue(false);
                    lblPrecio.managedProperty().setValue(false);
                }else{
                    lblPrecio.setText("Precio " + (alojamiento.calcularCosto()));
                }

                img.setImage(RepositorioImagenes.cargarImagen(alojamiento.getRutaFoto()));

                flowPaneVistaTarjetasAlojamiento.getChildren().add(nodoTarjeta);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void limpiarFiltro(ActionEvent event) {
        cargarAlojamientos();
        limpiarCamposFiltro();
    }
    private void limpiarCamposFiltro(){
        txtFieldFiltroCiudad.clear();
        txtFieldNombre.clear();
        cmbTipoAlojamiento.getSelectionModel().clearSelection();
        textFieldFiltroPrecio.getSelectionModel().clearSelection();
    }
}

