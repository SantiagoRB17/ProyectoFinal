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

    private final VentanasController ventanasController = VentanasController.getInstancia();
    private final RepositorioPersonas repositorioPersonas = RepositorioPersonas.getInstancia();

    @FXML
    void iniciarSesion(ActionEvent event) throws Exception {
        String email = txtEmailInicioSesion.getText();
        String password = passFieldContrasenaInicioSesion.getText();

        if (email.isEmpty() || password.isEmpty()) {
            ventanasController.mostrarAlerta("Debe llenar todos los campos", Alert.AlertType.ERROR);
            return;
        }
        try {
            Persona persona = ServicioPersonas.getInstancia().inicarSesion(email, password);
            Sesion.getInstancia().setPersona(persona);
            if (persona.getRol().equals(Rol.ADMINISTRADOR)) {
                ventanasController.navegarVentanas("/ServicioAlojamientosView.fxml", "Administrador", false, false);
            } else {
                ventanasController.navegarVentanas("/InicioView.fxml", "Cliente", false, false);
            }
        } catch (Exception e) {
            ventanasController.mostrarAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }


    @FXML
    void volverAInicio(ActionEvent event) throws Exception {
        ventanasController.navegarVentanas("/InicioView.fxml", "Inicio", false, false);
    }

    @FXML
    void irRegistrarse(ActionEvent event) throws Exception {
        ventanasController.navegarVentanas("/Registrarse.fxml", "Registrarse", false, false);
    }

    @FXML
    void irConfirmacionCorreo(ActionEvent event) throws Exception {
        ventanasController.navegarVentanas("/ConfirmarCorreo.fxml", "ConfirmacionCorreo", false, false);
    }
}

