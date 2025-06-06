package co.edu.uniquindio.poo.proyectofinal.Controllers;

import co.edu.uniquindio.poo.proyectofinal.Model.enums.Rol;
import co.edu.uniquindio.poo.proyectofinal.Model.entidades.Persona;
import co.edu.uniquindio.poo.proyectofinal.Model.entidades.Sesion;
import co.edu.uniquindio.poo.proyectofinal.Repositorios.RepositorioPersonas;
import co.edu.uniquindio.poo.proyectofinal.Servicios.ServicioPersonas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class IniciarSesionViewController {
    @FXML
    private Button btnIniciarSesion;
    @FXML
    private Button btnVolverAInicio;
    @FXML
    private GridPane gridPaneFormularioIniciarSesion;
    @FXML
    private PasswordField passFieldContrasenaInicioSesion;

    @FXML
    private TextField txtEmailInicioSesion;

    private final VentanaController ventanasController = VentanaController.getInstancia();

    @FXML
    void iniciarSesion(ActionEvent event) throws Exception {
        try {
        String email = txtEmailInicioSesion.getText();
        String password = passFieldContrasenaInicioSesion.getText();
        Persona persona = ventanasController.getPlataforma().iniciarSesion(email, password);

        if (email.isEmpty() || password.isEmpty()) {
            ventanasController.mostrarAlerta("Debe ingresar un correo valido", Alert.AlertType.ERROR);
            return;
        }
        if(persona.isCuentaActiva()==false){
            ventanasController.mostrarAlerta("Su cuenta se encuentra inactiva", Alert.AlertType.ERROR);
            return;
        }
        if (password == null || password.isEmpty()) {
            ventanasController.mostrarAlerta("Debe ingresar una contraseña válida", Alert.AlertType.ERROR);
            return;
        }

            Sesion.getInstancia().setPersona(persona);

            if (persona.getRol().equals(Rol.ADMINISTRADOR)) {
                ventanasController.navegarVentanas("/ServicioAlojamientosView.fxml", "Administrador", true, true);
            } else {
                ventanasController.navegarVentanas("/InicioView.fxml", "Cliente", true, true);
            }
        } catch (Exception e) {
            ventanasController.mostrarAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }


    @FXML
    void volverAInicio(ActionEvent event) throws Exception {
        ventanasController.navegarVentanas("/InicioView.fxml", "Inicio", true, true);
    }

    @FXML
    void irRegistrarse(ActionEvent event) throws Exception {
        ventanasController.navegarVentanas("/Registrarse.fxml", "Registrarse", true, true);
    }

    @FXML
    void irConfirmacionCorreo(ActionEvent event) throws Exception {
        ventanasController.navegarVentanas("/ConfirmarCorreo.fxml", "ConfirmacionCorreo", true, true);
    }
}

