package co.edu.uniquindio.poo.proyectofinal.Controllers;

import co.edu.uniquindio.poo.proyectofinal.Model.enums.Rol;
import co.edu.uniquindio.poo.proyectofinal.PlataformaApp;
import co.edu.uniquindio.poo.proyectofinal.Model.entidades.Persona;
import co.edu.uniquindio.poo.proyectofinal.Model.entidades.Sesion;
import co.edu.uniquindio.poo.proyectofinal.Model.entidades.Persona;
import co.edu.uniquindio.poo.proyectofinal.Repositorios.RepositorioPersonas;
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
    private TextField txtNumeroIdentificacionInicioSesion;

    private final VentanasController ventanasController = VentanasController.getInstancia();
    RepositorioPersonas repositorioPersonas = RepositorioPersonas.getInstancia();

    @FXML
    void iniciarSesion(ActionEvent event) throws Exception {
        String cedula = txtNumeroIdentificacionInicioSesion.getText();
        String password = passFieldContrasenaInicioSesion.getText();

        if (cedula.isEmpty() || password.isEmpty()) {
            ventanasController.mostrarAlerta("Debe llenar todos los campos", Alert.AlertType.ERROR);
            return;
        }

        Persona personaEncontrada = repositorioPersonas.obtenerPorId(cedula);

        if (personaEncontrada == null || !personaEncontrada.getPassword().equals(password)) {
            ventanasController.mostrarAlerta("Usuario o contraseña incorrectos", Alert.AlertType.ERROR);
        } else {
            Sesion.getInstancia().setPersona(personaEncontrada);
            ventanasController.mostrarAlerta("Bienvenido " + personaEncontrada.getNombre(), Alert.AlertType.INFORMATION);

            // Redirección basada en el tipo de persona
            if (personaEncontrada.getRol()== Rol.ADMINISTRADOR) {
                ventanasController.navegarVentanas("/EditarPerfil.fxml", "panelEditar", false, false); // Debes implementar este método
            } else if (personaEncontrada.getRol()==Rol.USUARIO) {
                ventanasController.navegarVentanas("/", "", false, false); // Debes implementar este método
            }
        }
    }


    @FXML
    void volverAInicio(ActionEvent event) {

    }
}

