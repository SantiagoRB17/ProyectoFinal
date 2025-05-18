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

    private static VentanaController ventanaController = VentanaController.getInstancia();

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
            ventanaController.getPlataforma().crearUsuario(nombre,apellido,cedula,email,numero,password,rolSeleccionado);
            ventanaController.mostrarAlerta("¡Registro exitoso! Ahora puedes iniciar sesión.", Alert.AlertType.INFORMATION);
            ventanaController.navegarVentanas("/IniciarSesion.fxml","Iniciar Sesion",true,false);
        }
        catch (Exception e){
            ventanaController.mostrarAlerta(e.getMessage(), Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    void volverAInicio(ActionEvent event) throws Exception {
        ventanaController.navegarVentanas("/InicioView.fxml","Iniciar Sesión",true,true);

    }
    @FXML
    void  volverIniciarSesion(ActionEvent event) throws Exception {
        ventanaController.navegarVentanas("/IniciarSesion.fxml","Iniciar Sesion",true,false);
    }

}
