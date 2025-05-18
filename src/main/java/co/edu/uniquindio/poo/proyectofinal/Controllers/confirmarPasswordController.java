package co.edu.uniquindio.poo.proyectofinal.Controllers;

import co.edu.uniquindio.poo.proyectofinal.Model.entidades.Persona;
import co.edu.uniquindio.poo.proyectofinal.Repositorios.RepositorioPersonas;
import co.edu.uniquindio.poo.proyectofinal.Servicios.ServicioPersonas;
import co.edu.uniquindio.poo.proyectofinal.Utils.CodigoTemporal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class confirmarPasswordController {

    @FXML
    private Button btnConfirmarPassword;

    @FXML
    private Text btnRegistrarse;

    @FXML
    private Button btnVolverAInicio;

    @FXML
    private GridPane gridPaneFormularioIniciarSesion;

    @FXML
    private TextField txtPassword;

    private final VentanaController ventanasController = VentanaController.getInstancia();

    @FXML
    void confirmarPassword(ActionEvent event) throws Exception {

        String newPassword = txtPassword.getText();
        String correo = CodigoTemporal.getCorreo();

        try{
            ventanasController.getPlataforma().cambiarContrasena(correo,newPassword);
            ventanasController.mostrarAlerta("Contraseña cambiada con exito", Alert.AlertType.INFORMATION);
            ventanasController.navegarVentanas("/IniciarSesion.fxml","IniciarSesion",false,false);
            CodigoTemporal.setCorreo(null);
            CodigoTemporal.setCodigo(null);
        }catch (Exception e){
            ventanasController.mostrarAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }

    }

    @FXML
    void volverAInicio(ActionEvent event) throws Exception {
        ventanasController.navegarVentanas("/IniciarSesion.fxml","inicioSesion",false,false);

    }

}
