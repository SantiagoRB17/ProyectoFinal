package co.edu.uniquindio.poo.proyectofinal.Controllers;

import co.edu.uniquindio.poo.proyectofinal.Model.enums.Rol;
import co.edu.uniquindio.poo.proyectofinal.Repositorios.RepositorioPersonas;
import co.edu.uniquindio.poo.proyectofinal.Servicios.ServicioEnvioEmail;
import co.edu.uniquindio.poo.proyectofinal.Servicios.ServicioPersonas;
import co.edu.uniquindio.poo.proyectofinal.Utils.CodigoTemporal;
import co.edu.uniquindio.poo.proyectofinal.Utils.CodigoVerificacion;
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
    private ServicioEnvioEmail servicioEnvioEmail = new ServicioEnvioEmail();

    public RegistrarseViewController() throws Exception {
    }

    @FXML
    public void initialize() {
        comboRol.getItems().addAll(Rol.values());
        comboRol.setValue(Rol.USUARIO);
    }
    @FXML
    void registrarse(ActionEvent event) throws Exception {
        String nombre = txtNombreCompletoRegistrarse.getText();
        String apellido = txtApellidosRegistrarse.getText();
        String cedula = txtNumeroIdentificacionRegistrarse.getText();
        String email = txtEmailRegistrarse.getText();
        String numero = txtNumeroTelefonoRegistrarse.getText();
        String password = passFieldContrasenaRegistrarse.getText();
        Rol rolSeleccionado= (Rol) comboRol.getValue();

        // Generar código y guardarlo en CodigoTemporal
        String codigo = CodigoVerificacion.generarCodigo();
        CodigoTemporal.setCorreo(email);
        CodigoTemporal.setCodigo(codigo);
        CodigoTemporal.setNombre(nombre);
        CodigoTemporal.setApellido(apellido);
        CodigoTemporal.setCedula(cedula);
        CodigoTemporal.setTelefono(numero);
        CodigoTemporal.setRol(rolSeleccionado);
        CodigoTemporal.setPassword(password);
        CodigoTemporal.setModo("registro");




        // Enviar correo
        servicioEnvioEmail.enviarNotificacion(email, "Código de activación", "Tu código de activación es: " + codigo);

        ventanaController.mostrarAlerta("¡Código enviado! Revisa tu correo e ingrésalo para activar la cuenta.", Alert.AlertType.INFORMATION);

        // Ir a la vista de ingresar código
        ventanaController.navegarVentanas("/CodigoConfirmacion.fxml", "ActivarCuenta", true, true);

    }

    @FXML
    void volverAInicio(ActionEvent event) throws Exception {
        ventanaController.navegarVentanas("/InicioView.fxml","Iniciar Sesión",true,true);

    }
    @FXML
    void  volverIniciarSesion(ActionEvent event) throws Exception {
        ventanaController.navegarVentanas("/IniciarSesion.fxml","Iniciar Sesion",true, true);
    }

}
