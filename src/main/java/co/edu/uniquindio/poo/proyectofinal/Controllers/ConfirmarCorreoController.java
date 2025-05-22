package co.edu.uniquindio.poo.proyectofinal.Controllers;

import co.edu.uniquindio.poo.proyectofinal.Repositorios.RepositorioPersonas;
import co.edu.uniquindio.poo.proyectofinal.Servicios.ServicioEnvioEmail;
import co.edu.uniquindio.poo.proyectofinal.Utils.CodigoTemporal;
import co.edu.uniquindio.poo.proyectofinal.Utils.CodigoVerificacion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class ConfirmarCorreoController {

    @FXML
    private Button btnIniciarSesion;

    @FXML
    private Text btnRegistrarse;

    @FXML
    private Button btnVolverAInicio;

    @FXML
    private GridPane gridPaneFormularioIniciarSesion;

    @FXML
    private TextField txtCorreo;
    private final VentanaController ventanasController = VentanaController.getInstancia();
    private ServicioEnvioEmail servicioEnvioEmail=new ServicioEnvioEmail();

    public ConfirmarCorreoController() {
    }

    public ConfirmarCorreoController(ServicioEnvioEmail servicioEnvioEmail) {
        this.servicioEnvioEmail = servicioEnvioEmail;
    }

    @FXML
    void EnviarMail(ActionEvent event) {
        String correo = txtCorreo.getText();

        if (correo.isBlank() || correo == null) {
            ventanasController.mostrarAlerta("Error,El correo no puede estar vacio", Alert.AlertType.ERROR);
            return;
        }
        if (ventanasController.getPlataforma().recuperarPersonaPorEmail(correo) == null) {
            ventanasController.mostrarAlerta("El correo no esta registrado", Alert.AlertType.ERROR);
            return;
        }


        String codigo = CodigoVerificacion.generarCodigo();
        CodigoTemporal.setCorreo(correo);
        CodigoTemporal.setCodigo(codigo);
        CodigoTemporal.setModo("recuperacion");

        servicioEnvioEmail.enviarNotificacion(correo, "Código de recuperación", "Tu código de verificación para recuperación de contraseña es: " + codigo);
        ventanasController.mostrarAlerta("Correo enviado", Alert.AlertType.INFORMATION);

        try{
            ventanasController.navegarVentanas("/CodigoConfirmacion.fxml","confirmarcodigo",true,true);
        } catch ( Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    void volverInicioSesion(ActionEvent event) throws Exception {
        ventanasController.navegarVentanas("/IniciarSesion.fxml","InicioSesion",true,true);

    }

}
