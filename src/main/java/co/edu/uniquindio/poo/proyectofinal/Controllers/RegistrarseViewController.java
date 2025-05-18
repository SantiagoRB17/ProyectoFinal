package co.edu.uniquindio.poo.proyectofinal.Controllers;

import co.edu.uniquindio.poo.proyectofinal.Model.enums.Rol;
import co.edu.uniquindio.poo.proyectofinal.Repositorios.RepositorioPersonas;
import co.edu.uniquindio.poo.proyectofinal.Servicios.ServicioPersonas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class RegistrarseViewController {


    @FXML
    private Button btnRegistrarse;

    @FXML
    private Button btnVolverAInicio;

    @FXML
    private ComboBox<Rol> comboRol;

    @FXML
    private GridPane gridPaneFormularioRegistrase;

    @FXML
    private Hyperlink linkIniciarSesion;

    @FXML
    private PasswordField passFieldContrasenaRegistrarse;

    @FXML
    private TextField txtApellidosRegistrarse;

    @FXML
    private TextField txtEmailRegistrarse;

    @FXML
    private TextField txtNombreCompletoRegistrarse;

    @FXML
    private TextField txtNumeroIdentificacionRegistrarse;

    @FXML
    private TextField txtNumeroTelefonoRegistrarse;

    RepositorioPersonas repositorioPersonas= RepositorioPersonas.getInstancia();

    private static VentanasController ventanasController = VentanasController.getInstancia();

    private  final ServicioPersonas servicioPersonas=ServicioPersonas.getInstancia();

    public RegistrarseViewController() throws Exception {
    }

    @FXML
    public void initialize() {
        comboRol.getItems().addAll(Rol.values());
        comboRol.setValue(Rol.USUARIO);
    }
    @FXML
    void registrarse(ActionEvent event) {
        String nombre = txtNombreCompletoRegistrarse.getText();
        String apellido = txtApellidosRegistrarse.getText();
        String cedula = txtNumeroIdentificacionRegistrarse.getText();
        String email = txtEmailRegistrarse.getText();
        String numero = txtNumeroTelefonoRegistrarse.getText();
        String password = passFieldContrasenaRegistrarse.getText();
        Rol rolSeleccionado= (Rol) comboRol.getValue();
        try{
            servicioPersonas.agregarPersona(nombre,apellido,cedula,email,numero,password,rolSeleccionado);
            ventanasController.mostrarAlerta("¡Registro exitoso! Ahora puedes iniciar sesión.", Alert.AlertType.INFORMATION);
            ventanasController.navegarVentanas("/IniciarSesion.fxml","Iniciar Sesion",false,false);
        }
        catch (Exception e){
            ventanasController.mostrarAlerta(e.getMessage(), Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    void volverAInicio(ActionEvent event) throws Exception {
        ventanasController.navegarVentanas("/InicioView.fxml","Iniciar Sesión",false,false);

    }
    @FXML
    void  volverIniciarSesion(ActionEvent event) throws Exception {
        ventanasController.navegarVentanas("/IniciarSesion.fxml","Iniciar Sesion",false,false);
    }

}
